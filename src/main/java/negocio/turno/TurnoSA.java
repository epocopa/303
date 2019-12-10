package negocio.turno;

import negocio.empleado.TEmpleado;

import java.util.List;

public interface TurnoSA {
	void insertar(TTurno turno) throws Exception;
	TTurno mostrar(int id) throws Exception;
	List<TTurno> mostrarTodos() throws Exception;
	void modificar(TTurno turno) throws Exception;
	void eliminar(int id) throws Exception;
	void insertarEmpleado(int idTurno, TEmpleado empleado) throws Exception;
	void eliminarEmpleado(int idTurno, TEmpleado empleado) throws Exception;
}
