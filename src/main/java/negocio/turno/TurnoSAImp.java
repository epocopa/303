package negocio.turno;

import negocio.empleado.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoSAImp implements TurnoSA {
	@Override
	public void insertar(TTurno turno) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		
		Query query = em.createNamedQuery("Turno.READ", Turno.class);
		query.setParameter("nombre", turno.getNombre());
		Turno t = null;
		try{
			t = (Turno) query.getSingleResult();
		}
		catch(NoResultException ignored){}

		if (!turno.getHoraInicio().isBefore(turno.getHoraFin())) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("La hora de inicio no puede ser posterior o igual a la hora de fin");
		}

		//turno does not exists in DB
		if(t == null){
			Turno trn = new Turno(turno);
			em.persist(trn);
			em.getTransaction().commit();
			turno.setId(trn.getId());
		} else {
			if(t.isActivo()){
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("Ya existe un turno con nombre = " + t.getNombre());
			} else{
				t.setActivo(true);
				em.getTransaction().commit();
				em.close();
				emf.close();
				throw new Exception("Ya existe un turno con nombre  = " + t.getNombre() + ". Se ha reactivado");
			}
		}
		em.close();
		emf.close();
	}

	@Override
	public TTurno mostrar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Turno t = em.find(Turno.class,id, LockModeType.OPTIMISTIC);
		TTurno turno = null;
		if(t==null){
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el turno con id: "+id);
		}
		else{
			
			turno = new TTurno(t.getId(),t.getNombre(),t.getInicio(),t.getFin(),t.isActivo()); 
		}
		
		try{
			em.getTransaction().commit();
		}catch(RollbackException e){}
		
		
		
		em.close();
		emf.close();
		return turno;

	}

	@Override
	public List<TTurno> mostrarTodos() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Turno> listaTurno = null;
		List<TTurno> listaTransferTurno = new ArrayList<TTurno>();
		
		try{
			TypedQuery query = em.createQuery("SELECT t FROM Turno t", Turno.class);
			listaTurno = query.getResultList();
			for(int i = 0; i<listaTurno.size();i++){
				TTurno t = new TTurno(listaTurno.get(i).getId(), listaTurno.get(i).getNombre(),
								listaTurno.get(i).getInicio(),listaTurno.get(i).getFin(), listaTurno.get(i).isActivo());
				listaTransferTurno.add(t);
			}
			em.getTransaction().commit();
		}
		catch(Exception e){}

			
		
		em.close();
		emf.close();
		return listaTransferTurno;
	}

	@Override
	public void modificar(TTurno turno) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Turno t = em.find(Turno.class, turno.getId(), LockModeType.OPTIMISTIC);

		if (t == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe un turno con ID =" + turno.getId());
		}

		if (!turno.getHoraInicio().isBefore(turno.getHoraFin())) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("La hora de inicio no puede ser posterior o igual a la hora de fin");
		}
		
		Query query = em.createNamedQuery("Turno.READ", Turno.class);
		query.setParameter("nombre", turno.getNombre());
		Turno turn = null;
		try {
			turn = (Turno) query.getSingleResult();
		} catch (NoResultException ignored) {}

		if (turn != null && !t.getNombre().equals(turno.getNombre())) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("Ya existe un turno con nombre  =" + turno.getNombre());
		}


		t.setNombre(turno.getNombre());
		t.setInicio(turno.getHoraInicio());
		t.setFin(turno.getHoraFin());
		t.setActivo(turno.isActivo());

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void eliminar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Turno turno = em.find(Turno.class, id, LockModeType.OPTIMISTIC);

		//turno no existe
		if(turno == null){
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el turno con id: "+id);
		}
		//turno existe
		else{
			if(!turno.isActivo()){
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("El turno con id "+id+" ya está dado de baja");
			}
			else{
				if(turno.getEmpleados().size()>0){
					em.getTransaction().rollback();
					em.close();
					emf.close();
					throw new Exception("El turno con id "+id+" tiene empleados activos");
				}
				else{
					turno.setActivo(false);
				}

				try{
					em.getTransaction().commit();
				}
				catch(Exception e){
					throw new Exception("Error en concurrencia");
				}
				
			}
		}
		
		em.close();
		emf.close();
	}

	@Override
	public void insertarEmpleado(int idTurno, TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Turno turno = em.find(Turno.class, idTurno, LockModeType.OPTIMISTIC);
		Empleado e = em.find(Empleado.class, empleado.getId(), LockModeType.OPTIMISTIC);

		if (e == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe un empleado con ID = " + empleado.getId());
		}

		if(turno == null){
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el turno con id "+idTurno);
		}

		if(!turno.isActivo()){
			throw new Exception("El turno con id "+turno.getId() + " no esta activo");
		}
		if(!e.isActivo()){
			throw new Exception("El empleado con id "+ e.getId() + " no esta activo");
		}

		e.setTurno(turno);

		try {
			em.getTransaction().commit();
		} catch (Exception e1) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("Error en concurrencia");
		}


		em.close();
		emf.close();
	}

	@Override
	public void eliminarEmpleado(int idTurno, TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Turno turno = em.find(Turno.class, idTurno, LockModeType.OPTIMISTIC);
		Empleado e = em.find(Empleado.class, empleado.getId(), LockModeType.OPTIMISTIC);

		if (turno == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el turno con id " + idTurno);
		}
		if(e == null){
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el empleado con id " + empleado.getId());
		}
		if (e.getTurno() == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("El empleado " + empleado.getId() + " no esta en ningun turno ");
		}
		if (e.getTurno().getId() != idTurno) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("El empleado " + empleado.getId() + " no esta en el turno " + idTurno);
		}

		e.setTurno(null);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}
