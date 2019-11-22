package negocio.turno;

import negocio.empleado.TEmpleado;

import java.util.List;

public interface TurnoSA {
	void insertar(TTurno turno) throws Exception;
	TTurno mostrar(int id) throws Exception;
	List<TTurno> mostrarTodos() throws Exception;
	void modificar(TTurno turno) throws Exception;
	void eliminar(int id) throws Exception;
	void anadirEmpleado(TEmpleado empleado) throws Exception;
	void borrarEmpleado(TEmpleado empleado) throws Exception;
}