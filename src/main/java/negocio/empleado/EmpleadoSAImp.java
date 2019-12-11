package negocio.empleado;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoSAImp implements EmpleadoSA {

	@Override
	public void insertar(TEmpleado empleado) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("Empleado.READBYDNI", Empleado.class);
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
				em.getTransaction().commit();
				empleado.setId(encargado.getId());
			} else {
				Dependiente dependiente = new Dependiente((TDependiente) empleado);
				em.persist(dependiente);
				em.getTransaction().commit();
				empleado.setId(dependiente.getId());
			}
		} else {
			if (empl.isActivo()) {
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("Ya existe un empleado con DNI = " + empleado.getDNI());
			} else {
				empl.setActivo(true);
				em.getTransaction().commit();
				em.close();
				emf.close();
				throw new Exception("Ya existe un empleado con DNI = " + empleado.getDNI() + ". Se ha reactivado");
			}
		}
		em.close();
		emf.close();
	}

	@Override
	public TEmpleado mostrar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Empleado e = em.find(Empleado.class, id, LockModeType.OPTIMISTIC);

		if (e == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe un empleado con ID =" + id);
		}

		TEmpleado empleado = null;
		int t = -1;
		if (e.getTurno() != null) {
			t = e.getTurno().getId();
		}

		if (e.getType().equals("Encargado")) {
			Encargado enc = (Encargado) e;
			empleado = new TEncargado(enc.getId(), enc.getNombre(), enc.getDNI(), enc.getSalarioBase(), enc.isActivo(), enc.getMultiplicador(), t);
		} else {
			Dependiente dep = (Dependiente) e;
			empleado = new TDependiente(dep.getId(), dep.getNombre(), dep.getDNI(), dep.getSalarioBase(), dep.isActivo(), dep.getSumador(), t);
		}


		em.getTransaction().commit();
		em.close();
		emf.close();
		return empleado;
	}

	@Override
	public List<TEmpleado> mostrarTodos() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("Empleado.READALL", Empleado.class);
		List<Empleado> result = query.getResultList();
		List<TEmpleado> list = new ArrayList<>();

		for (Empleado e : result) {
			int t = -1;
			if (e.getTurno() != null) {
				t = e.getTurno().getId();
			}
			if (e.getType().equals("Encargado")) {
				Encargado enc = (Encargado) e;
				list.add(new TEncargado(enc.getId(), enc.getNombre(), enc.getDNI(), enc.getSalarioBase(), enc.isActivo(), enc.getMultiplicador(), t));
			} else {
				Dependiente dep = (Dependiente) e;
				list.add(new TDependiente(dep.getId(), dep.getNombre(), dep.getDNI(), dep.getSalarioBase(), dep.isActivo(), dep.getSumador(), t));
			}
		}

		em.close();
		emf.close();
		return list;
	}

	@Override
	public void modificar(TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Empleado e = em.find(Empleado.class, empleado.getId(), LockModeType.OPTIMISTIC);

		if (e == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe un empleado con ID =" + empleado.getId());
		}

		Query query = em.createNamedQuery("Empleado.READBYDNI", Empleado.class);
		query.setParameter("dni", empleado.getDNI());
		Empleado empl = null;
		try {
			empl = (Empleado) query.getSingleResult();
		} catch (NoResultException ignored) {}

		if (empl != null && !e.getDNI().equals(empleado.getDNI())) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("Ya existe un empleado con DNI = " + empleado.getDNI());
		}

		if (e.getType().equals("Encargado")) {
			((Encargado) e).setMultiplicador(((TEncargado) empleado).getMultiplicador());
		} else {
			((Dependiente) e).setSumador(((TDependiente) empleado).getSumador());
		}

		e.setDNI(empleado.getDNI());
		e.setNombre(empleado.getNombre());
		e.setSalarioBase(empleado.getSalarioBase());
		e.setActivo(empleado.isActivo());

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void eliminar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Empleado e = em.find(Empleado.class, id, LockModeType.OPTIMISTIC);

		if (e == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe un empleado con ID =" + id);
		}

		if (e.isActivo()) {
			e.setActivo(false);
		} else {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("El empleado ya estaba dado de baja");
		}

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
