package negocio.empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmpleadoSAImp implements EmpleadoSA {
	
	@Override
	public void insertar(TEmpleado empleado) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		//em.getTransaction().begin();

		//check if DNI exists --> (comprobar consulta, lo estoy haciendo a pelo)
		String statement = "SELECT emp FROM Empleado emp WHERE emp.dni = :dni";
		Query query = em.createQuery(statement);
		query.setParameter("dni", empleado.getDNI());
		Empleado empl = null;
		try{
			empl = (Empleado) query.getSingleResult();
		}
		catch(Exception e){}//creo que es NoResultException
		//TODO checks acording to SRS

		//empl does not exists in BD
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
				tr.commit();
			}
			catch(Exception e){
				tr.rollback();
				//emf.close();
				//em.close();
				//MENSAJE DE ERROR EN CONCURRENCIA
			}
		}
		//empl exists in BD
		else{
			//check state
			if(empl.isActivo()){
				//MENSAJE YA ESTA DADO DE ALTA
				tr.rollback();
			}
			else{
				//Activamos el empleado
				empl.setActivo(true);
				try{
					tr.commit();
				}
				catch(Exception e){
					tr.rollback();
					//emf.close();
					//em.close();
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
		EntityTransaction tr = em.getTransaction();
		tr.begin();

		















		emf.close();
		em.close();
	}

	@Override
	public List<TEmpleado> mostrarTodos() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();












		emf.close();
		em.close();
	}

	@Override
	public void modificar(TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();












		emf.close();
		em.close();
	}

	@Override
	public void eliminar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();









		emf.close();
		em.close();
	}
}
