package negocio.empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmpleadoSAImp implements EmpleadoSA {
	@Override
	public void insertar(TEmpleado empleado) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		//TODO checks acording to SRS

		if (empleado.isEncargado()) {
			Encargado encargado = new Encargado((TEncargado) empleado);
			em.persist(encargado);
			empleado.setId(encargado.getId());
		} else {
			Dependiente dependiente = new Dependiente((TDependiente) empleado);
			em.persist(dependiente);
			empleado.setId(dependiente.getId());
		}

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public TEmpleado mostrar(int id) throws Exception {
		return null;
	}

	@Override
	public List<TEmpleado> mostrarTodos() throws Exception {
		return null;
	}

	@Override
	public void modificar(TEmpleado empleado) throws Exception {

	}

	@Override
	public void eliminar(int id) throws Exception {

	}
}
