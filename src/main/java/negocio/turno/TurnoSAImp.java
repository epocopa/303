package negocio.turno;

import negocio.empleado.Empleado;
import negocio.empleado.TEmpleado;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoSAImp implements TurnoSA {
	@Override
	public void insertar(TTurno turno) throws Exception {
/*		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		
		String statement = "SELECT t FROM Turno emp WHERE t.id = :id";
		Query query = em.createQuery(statement);
		query.setParameter("id", empleado.getId());
		Turno turno = null;
		try{
			turno = (Turno) query.getSingleResult();
		}
		catch(NoResultException e){}
		//TODO checks acording to SRS

		//turno does not exists in DB
		if(turno == null){
			Turno trn = new Turno((TTurno))turno);
			em.persist(trn);
			//TURNO PERSISTIDO

			try{
				em.getTransaction().commit();
				//MENSAJE DADO DE ALTA CON ÉXITO
			}catch(Exception e){
				em.getTransaction().rollback();
				//MENSAJE DE ERROR EN CONCURRENCIA
			}
			
		}
		//turno exists in DB
		else{
			if(turno.isActivo()){
				//MENSAJE YA ESTÁ DADO DE ALTA
				em.getTransaction().rollback();
			}
			//reactivamos el turno
			else{
				turno.setActivo(true);
				try{
					em.getTransaction().commit();
				}
				catch(Exception e){
					em.getTransaction().rollback();
					//ERROR EN CONCURRENCIA
				}
				//MENSAJE DADO DE ALTA TRAS INACTIVIDAD
			}
		}


			
		emf.close();
		em.close();*/
	}

	@Override
	public TTurno mostrar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Turno t = em.find(Turno.class,id);

		if(t==null){
			//MENSAJE NO EXISTE TURNO CON ESE ID
		}
		else{
			//MIRAR SI FALTA AÑADIR LISTA DE EMPLEADOS COMO CAMPO EN EL TRANSFER
			TTurno turno = new TTurno(t.getId(),t.getNombre(),t.getInicio(),t.getFin(),t.isActivo()); 
		}
		
		try{
			em.getTransaction().commit();
		}catch(RollbackException e){}
		
		
		emf.close();
		em.close();
		return null;

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

			
		emf.close();
		em.close();
		return listaTransferTurno;
	}

	@Override
	public void modificar(TTurno turno) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();




		emf.close();
		em.close();
	}

	@Override
	public void eliminar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Turno turno = em.find(Turno.class, id);

		//turno no existe
		if(turno == null){
			//MENSAJE TURNO CON ESE ID NO EXISTE
			em.getTransaction().rollback();
		}
		//turno existe
		else{
			if(!turno.isActivo()){
				//MENSAJE YA ESTA DADO DE BAJA
				em.getTransaction().rollback();
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
					//ERROR EN CONCURRENCIA
				}
				
			}
		}


		emf.close();
		em.close();
	}

	@Override
	public void anadirEmpleado(int idTurno, TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Turno turno = em.find(Turno.class, idTurno);

		if(turno == null){
			//MENSAJE TURNO CON ESE ID NO EXISTE
		}
		else{
	/*		Empleado emp = new Empleado(empleado.getDNI(),empleado.getNombre(),
							empleado.getSalarioBase(),empleado.isActivo(),empleado.getId());
			turno.getEmpleados().add(emp);*/

			try{
				em.getTransaction().commit();
			}
			catch(Exception e){
				//MENSAJE ERROR CONCURRENCIA
				em.getTransaction().rollback();
			}
		}


		emf.close();
		em.close();
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

		emf.close();
		em.close();
	}
}
