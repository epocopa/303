package integracion;

import java.util.List;

public interface DAO<T> {

	void insertar(T e) throws Exception;

	T mostrar(int id) throws Exception;

	List<T> mostrarTodos() throws Exception;

	void modificar(T e) throws Exception;

	void eliminar(int id) throws Exception;
}
