package negocio.empleado;

import java.util.List;

public interface EmpleadoSA {
	void insertar(TEmpleado empleado) throws Exception;

	TEmpleado mostrar(int id) throws Exception;

	List<TEmpleado> mostrarTodos() throws Exception;

	void modificar(TEmpleado empleado) throws Exception;

	void eliminar(int id) throws Exception;
}
