package negocio.empleado;

import javax.persistence.*;
import java.util.List;
//TODO

public class EmpleadoSAImp implements EmpleadoSA {

	@Override
	public void insertar(TEmpleado empleado) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("Empleado.READ", Empleado.class);
		query.setParameter("dni", empleado.getDNI());
		Empleado empl = null;
		try {
			empl = (Empleado) query.getSingleResult();
		} catch (NoResultException ignored) {}

		//empl does not exists in DB
		if (empl == null) {
			if (empleado.isEncargado()) {
				Encargado encargado = new Encargado((TEncargado) empleado);
				em.persist(encargado);
				empleado.setId(encargado.getId());
			} else {
				Dependiente dependiente = new Dependiente((TDependiente) empleado);
				em.persist(dependiente);
				empleado.setId(dependiente.getId());
			}
		} else {
			if (empl.isActivo()) {
				em.getTransaction().rollback();
				return;
			} else {
				empl.setActivo(true);
			}
		}

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public TEmpleado mostrar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();


		emf.close();
		em.close();
		return null;
	}

	@Override
	public List<TEmpleado> mostrarTodos() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();


		emf.close();
		em.close();
		return null;
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

		try {
			empl = (Empleado) query.getSingleResult();
		} catch (NoResultException e) {
		}

		//Empl exists in DB
		if (empl != null) {


		}//Empl does not exists in DB
		else {

		}


		emf.close();
		em.close();
	}

	@Override
	public void eliminar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Empleado empl = em.find(Empleado.class, id);

		//empl does not exists in DB
		if (empl == null) {
			//MENSAJE EMPLEADO CON ESE ID NO EXISTE
			em.getTransaction().rollback();
		}
		//empl exists in DB
		else {
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
			if (empl.isActivo()) {
				empl.setActivo(false);
				try {
					em.getTransaction().commit();
				} catch (Exception e) {
					em.getTransaction().rollback();
					//MENSAJE ERROR DE CONCURRENCIA
				}
			}
			//empl deactivaded
			else {
				//MENSAJE DE YA ESTA DADO DE BAJA
				em.getTransaction().rollback();
			}
		}

		emf.close();
		em.close();
	}
}
