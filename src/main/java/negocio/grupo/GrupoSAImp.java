package negocio.grupo;

import negocio.empleado.*;
import negocio.turno.Turno;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GrupoSAImp implements GrupoSA {
	@Override
	public void insertar(TGrupo grupo) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		
		Query query = em.createNamedQuery("Grupo.READ", Grupo.class);
		query.setParameter("seccion", grupo.getSeccion());
		Grupo g = null;
		try{
			g = (Grupo) query.getSingleResult();
		}
		catch(NoResultException ignored){}

		//grupo does not exists in DB
		if(g == null){
			Grupo gr = new Grupo(grupo);
			em.persist(gr);
			em.getTransaction().commit();
			grupo.setId(gr.getId());
		} else {
			if(g.isActivo()){
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("Ya existe un grupo con seccion = " + g.getSeccion());
			} else{
				g.setActivo(true);
				em.getTransaction().commit();
				em.close();
				emf.close();
				throw new Exception("Ya existe un grupo con seccion = " + g.getSeccion() + ". Se ha reactivado");
			}
		}
		em.close();
		emf.close();
	}

	@Override
	public TGrupo mostrar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Grupo g = em.find(Grupo.class, id, LockModeType.OPTIMISTIC);
		TGrupo grupo = null;
		if(g==null){
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el grupo con id: "+id);
		}
		else{
			grupo = new TGrupo(g.getId(),g.getSeccion(),g.isActivo());
			int total = 0;

			for (AsignacionGrupo a : g.getGrupos()) {
				em.lock(a.getEmpleado(), LockModeType.OPTIMISTIC);
				grupo.getListaEmpleados().add(new TTrabaja(a.getGrupo().getId(), a.getEmpleado().getId(), a.getEmpleado().calcularSalario()));
				if (a.getEmpleado().isActivo()) {
					total += a.getEmpleado().calcularSalario();
				}
			}
			grupo.setSalario(total);
		}
		
		try{
			em.getTransaction().commit();
		}catch(RollbackException e){}
		
		
		
		em.close();
		emf.close();
		return grupo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TGrupo> mostrarTodos() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Grupo> listaGrupo = null;
		List<TGrupo> listaTransferGrupo = new ArrayList<TGrupo>();
		
		try{
			TypedQuery query = em.createQuery("SELECT g FROM Grupo g", Grupo.class);
			listaGrupo = query.getResultList();
			
			for(int i = 0; i<listaGrupo.size();i++){
				TGrupo t = new TGrupo(listaGrupo.get(i).getId(), listaGrupo.get(i).getSeccion(),
						listaGrupo.get(i).isActivo());
				int total = 0;

				for (AsignacionGrupo a : listaGrupo.get(i).getGrupos()) {
					em.lock(a.getEmpleado(), LockModeType.OPTIMISTIC);
					t.getListaEmpleados().add(new TTrabaja(a.getGrupo().getId(), a.getEmpleado().getId(), a.getEmpleado().calcularSalario()));
					if (a.getEmpleado().isActivo()) {
						total += a.getEmpleado().calcularSalario();
					}
				}
				t.setSalario(total);
				listaTransferGrupo.add(t);
			}
			em.getTransaction().commit();
		}
		catch(Exception e){}

		em.close();
		emf.close();
		return listaTransferGrupo;
	}

	@Override
	public void modificar(TGrupo grupo) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Grupo g = em.find(Grupo.class, grupo.getId(), LockModeType.OPTIMISTIC);

		if (g == null) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe un grupo con ID =" + grupo.getId());
		}
		
		Query query = em.createNamedQuery("Grupo.READ", Turno.class);
		query.setParameter("seccion", grupo.getSeccion());
		Grupo grp = null;
		try {
			grp = (Grupo) query.getSingleResult();
		} catch (NoResultException ignored) {}

		if (grp != null && !g.getSeccion().equals(grupo.getSeccion())) {
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("Ya existe un grupo con seccion  =" + grupo.getSeccion());
		}


		g.setSeccion(grupo.getSeccion());
		g.setActivo(grupo.isActivo());
		

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void eliminar(int id) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Grupo grupo = em.find(Grupo.class, id, LockModeType.OPTIMISTIC);

		//turno no existe
		if(grupo == null){
			em.getTransaction().rollback();
			em.close();
			emf.close();
			throw new Exception("No existe el grupo con id: "+id);
		}
		//turno existe
		else{
			if(!grupo.isActivo()){
				em.getTransaction().rollback();
				em.close();
				emf.close();
				throw new Exception("El grupo con id "+id+" ya esta dado de baja");
			}
			else{
				if(grupo.getGrupos().size()>0){
					//MENSAJE: ESE TURNO TIENE EMPLEADOS
					em.getTransaction().rollback();
					em.close();
					emf.close();
					throw new Exception("El grupo no se puede borrar ya que contiene empleados");
				}
				else{
					grupo.setActivo(false);
					//MENSAJE BAJA CORRECTA
				}

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
	public void insertarEmpleado(TTrabaja trabaja) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Grupo grupo = em.find(Grupo.class, trabaja.getIdGrupo(), LockModeType.OPTIMISTIC);
		Empleado emp = em.find(Empleado.class, trabaja.getIdEmpleado(), LockModeType.OPTIMISTIC);
		AsignacionGrupoId agId = new AsignacionGrupoId();
		agId.setEmpleado(trabaja.getIdEmpleado());
		agId.setGrupo( trabaja.getIdGrupo());
		AsignacionGrupo ag = em.find(AsignacionGrupo.class, agId);

		if (grupo == null && emp == null) {
			throw new Exception("No existe el grupo con id " + trabaja.getIdGrupo() + " ni el empleado con id " + trabaja.getIdEmpleado());
		} else if (grupo == null) {
			throw new Exception("No existe el grupo con id " + trabaja.getIdGrupo());
		} else if (emp == null) {
			throw new Exception("No existe el empleado con id " + trabaja.getIdEmpleado());
		} else if (!emp.isActivo()) {
			throw new Exception("El empleado con id " + trabaja.getIdEmpleado() + " no esta activo");
		} else if (!grupo.isActivo()) {
			throw new Exception("El grupo con id " + grupo.getId() + " no esta activo");
		} else if (ag != null) {
			throw new Exception("El empleado " + trabaja.getIdEmpleado() + " ya esta en el grupo " + trabaja.getIdGrupo());
		} else {
			AsignacionGrupo asign = new AsignacionGrupo();
			asign.setEmpleado(emp);
			asign.setGrupo(grupo);

			asign.setFecha(LocalDate.now());
			em.persist(asign);
			try {
				em.getTransaction().commit();
			} catch (RollbackException e) {
				em.close();
				emf.close();
				throw new Exception("Ya existe el grupo con id " + trabaja.getIdGrupo() + " asignado al empleado con id " + trabaja.getIdEmpleado());
			} catch (Exception e) {
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
	public void eliminarEmpleado(TTrabaja trabaja) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Grupo grupo = em.find(Grupo.class, trabaja.getIdGrupo(), LockModeType.OPTIMISTIC);
		Empleado emp = em.find(Empleado.class, trabaja.getIdEmpleado(), LockModeType.OPTIMISTIC);
		AsignacionGrupoId agId = new AsignacionGrupoId();
		agId.setEmpleado(trabaja.getIdEmpleado());
		agId.setGrupo( trabaja.getIdGrupo());
		AsignacionGrupo ag = em.find(AsignacionGrupo.class, agId);

		if (grupo == null && emp == null) {
			throw new Exception("No existe el grupo con id " + trabaja.getIdGrupo() + " ni el empleado con id " + trabaja.getIdEmpleado());
		}
		if (grupo == null) {
			throw new Exception("No existe el grupo con id " + trabaja.getIdGrupo());
		}
		if (emp == null) {
			throw new Exception("No existe el empleado con id " + trabaja.getIdEmpleado());
		}
		if (ag == null) {
			throw new Exception("El empleado " + trabaja.getIdEmpleado() + " no esta en el grupo " + trabaja.getIdGrupo());
		}

		em.remove(ag);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
