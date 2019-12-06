package negocio.empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmpleadoSAImp implements EmpleadoSA {
	
	@Override
	public void insertar(TEmpleado empleado) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		//check if DNI exists --> (comprobar consulta, lo estoy haciendo a pelo)
		String statement = "SELECT emp FROM Empleado emp WHERE emp.dni = :dni";
		Query query = em.createQuery(statement);
		query.setParameter("id", empleado.getId());
		Empleado empl = null;
		try{
			empl = (Empleado) query.getSingleResult();
		}
		catch(Exception e){}//creo que es NoResultException
		//TODO checks acording to SRS

		//empl does not exists in DB
		if(empl == null){
			/**
			 * DUDA: comprobar estado de los datos de turno/grupo
			 * si los hubiera al altaEmpleadoSA
			 */
			if (empleado.isEncargado()) {
				Encargado encargado = new Encargado((TEncargado) empleado);
				em.persist(encargado);
				empleado.setId(encargado.getId());
			} else {
				Dependiente dependiente = new Dependiente((TDependiente) empleado);
				em.persist(dependiente);
				empleado.setId(dependiente.getId());
			}
			try{
				em.getTransaction().commit();
			}
			catch(Exception e){
				em.getTransaction().rollback();
				//MENSAJE DE ERROR EN CONCURRENCIA
			}
		}
		//empl exists in DB
		else{
			//check state
			if(empl.isActivo()){
				//MENSAJE YA ESTA DADO DE ALTA
				em.getTransaction().rollback();
			}
			else{
				//Activamos el empleado
				empl.setActivo(true);
				try{
					em.getTransaction().commit();
				}
				catch(Exception e){
					em.getTransaction().rollback();
				}
				//MENSAJE DADO DE ALTA TRAS ESTAR INACTIVO
			}
		}
		
		
		emf.close();
		em.close();
	}

	@Override
	public TEmpleado mostrar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		

		















		emf.close();
		em.close();
	}

	@Override
	public List<TEmpleado> mostrarTodos() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();













		emf.close();
		em.close();
	}

	@Override
	public void modificar(TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Empleado empl = null;
		String statement = "SELECT emp FROM Empleado emp WHERE emp.id = :id";
		Query query = em.createQuery(statement);
		query.setParameter("id", empleado.getId());

		try{
			empl = (Empleado) query.getSingleResult();
		}
		catch(NoResultException){}

		//Empl exists in DB
		if(empl != null){


		}//Empl does not exists in DB
		else{
			
		}











		emf.close();
		em.close();
	}

	@Override
	public void eliminar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Empleado empl = em.find(Empleado.class,id);

		//empl does not exists in DB
		if(empl == null){
			//MENSAJE EMPLEADO CON ESE ID NO EXISTE
			em.getTransaction().rollback();
		}
		//empl exists in DB
		else{
			//empl activated

			/**
			 * NO SE SI SE PUEDE DAR DE BAJA SI EXISTE ALGUNA
			 * ASIGNACIÓN DE GRUPO
			 * if(empl.getAsiganciones().isEmpty()){
			 * 		tr.rollback();
			 * 		MENSAJE
			 * }
			 * else{ TODO LO DEMÁS}
			 */
			if(empl.isActivo()){
				empl.setActivo(false);
				try{
					em.getTransaction().commit();
				}
				catch(Exception e){
					em.getTransaction().rollback();
					//MENSAJE ERROR DE CONCURRENCIA
				}
			}
			//empl deactivaded
			else{
				//MENSAJE DE YA ESTA DADO DE BAJA
				em.getTransaction().rollback();
			}
		}
		
		emf.close();
		em.close();
	}
}
