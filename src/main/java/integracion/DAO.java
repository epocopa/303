package integracion;

import java.util.List;

public interface DAO<T> {

	void insertar(T e);

	T mostrar(int id);

	List<T> mostrarTodos();

	void modificar(T e);

	void eliminar(int id);
}

