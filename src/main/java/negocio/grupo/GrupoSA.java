package negocio.grupo;

import negocio.empleado.TEmpleado;
import negocio.empleado.TTrabaja;

import java.util.List;

public interface GrupoSA {
	void insertar(TGrupo grupo) throws Exception;
	TGrupo mostrar(int id) throws Exception;
	List<TGrupo> mostrarTodos() throws Exception;
	void modificar(TGrupo grupo) throws Exception;
	void eliminar(int id) throws Exception;
	void insertarEmpleado(TTrabaja empleado) throws Exception;
	void eliminarEmpleado(TTrabaja empleado) throws Exception;
}
