package main.java.integracion;

import java.util.List;

public interface DAO<T> {

	void insertar(T e) throws Exception;

	T mostrar(int id);

	List<T> mostrarTodos();

	void modificar(T e);

	void eliminar(int id);
}

