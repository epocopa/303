package negocio.turno;

import negocio.empleado.Empleado;
import negocio.empleado.TEmpleado;

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
		//TODO checks acording to SRS

		//turno does not exists in DB
		if(t == null){
			Turno trn = new Turno(turno);
			em.persist(trn);
			//TURNO PERSISTIDO

			try{
				em.getTransaction().commit();
				//MENSAJE DADO DE ALTA CON Ã‰XITO
			}catch(Exception e){
				em.getTransaction().rollback();
				throw new Exception("Error en concurrencia");
			}
			
		}
		//turno exists in DB
		else{
			if(t.isActivo()){
				em.getTransaction().rollback();
				throw new Exception("Ya existe un turno con nombre =" + t.getNombre());
			}
			//reactivamos el turno
			else{
				t.setActivo(true);
				try{
					em.getTransaction().commit();
				}
				catch(Exception e){
					em.getTransaction().rollback();
					throw new Exception("Error en concurrencia");
				}
				//MENSAJE DADO DE ALTA TRAS INACTIVIDAD
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
			em.close();
			emf.close();
			throw new Exception("No existe el turno con id: "+id);
		}
		else{
			//MIRAR SI FALTA AÃ‘ADIR LISTA DE EMPLEADOS COMO CAMPO EN EL TRANSFER
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
		/*
		Turno t = null;
		
		t = em.find(Turno.class, turno.getId());
		t.setActivo(turno.isActivo());
	//	em.persist(t);
		try{
			em.getTransaction().commit();
		}
		catch (Exception e){
			
		}
		
 		*/

		
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
	public void anadirEmpleado(int idTurno, TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Turno turno = em.find(Turno.class, idTurno);

		if(turno == null){
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
				throw new Exception("Error en concurrencia");
			}
		}


		em.close();
		emf.close();
	}

	@Override
	public void borrarEmpleado(int idTurno, TEmpleado empleado) throws Exception {
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
