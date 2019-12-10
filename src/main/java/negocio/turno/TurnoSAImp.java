package negocio.turno;

import negocio.empleado.Dependiente;
import negocio.empleado.Empleado;
import negocio.empleado.Encargado;
import negocio.empleado.TDependiente;
import negocio.empleado.TEmpleado;
import negocio.empleado.TEncargado;

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
		catch(NoResultException e){}
		

		//turno does not exists in DB
		if(t == null){
			Turno trn = new Turno(turno);
			em.persist(trn);
			turno.setId(trn.getId());

			try{
				em.getTransaction().commit();
			}
			catch(Exception e){
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("Error en concurrencia");
			}
			
		}
		//turno exists in DB
		else{
			if(t.isActivo()){
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("Ya existe un turno con nombre = " + t.getNombre());
			}
			//reactivamos el turno
			else{
				t.setActivo(true);
				try{
					em.getTransaction().commit();
				}
				catch(Exception e){
					em.getTransaction().rollback();
					em.close();
					emf.close();
					throw new Exception("Error en concurrencia");
				}
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
		
		Turno t = em.find(Turno.class,id);
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
		
		Turno t = em.find(Turno.class, turno.getId());

		if (t == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe un turno con ID =" + t.getId());
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

		Turno turno = em.find(Turno.class, id);

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
					//MENSAJE: ESE TURNO TIENE EMPLEADOS
					em.getTransaction().rollback();
				}
				else{
					turno.setActivo(false);
					//MENSAJE BAJA CORRECTA
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

		Turno turno = em.find(Turno.class, idTurno);

		if(turno == null){
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el turno con id"+idTurno);
		}
		else{
	/*		Empleado emp = new Empleado(empleado.getDNI(),empleado.getNombre(),
							empleado.getSalarioBase(),empleado.isActivo(),empleado.getId());
			turno.getEmpleados().add(emp);*/

			try{
				em.getTransaction().commit();
			}
			catch(Exception e){
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("Error en concurrencia");
			}
		}


		em.close();
		emf.close();
	}

	@Override
	public void eliminarEmpleado(int idTurno, TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Turno turno = em.find(Turno.class, idTurno);

		if(turno == null){
			//MENSAJE TURNO CON ESE ID NO EXISTE
		}
		else{
/*			Empleado emp = new Empleado(empleado.getDNI(),empleado.getNombre(),
							empleado.getSalarioBase(),empleado.isActivo(),empleado.getId());
			
			turno.getEmpleados().remove(emp);*/
			
			try{
				em.getTransaction().commit();
			}
			catch(Exception e){
				//MENSAJE ERROR CONCURRENCIA
				em.getTransaction().rollback();
			}
		}

		
		em.close();
		emf.close();
	}
}
