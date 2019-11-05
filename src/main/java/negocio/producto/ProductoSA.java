package negocio.producto;

import java.util.List;

public interface ProductoSA {
	void insertar(TProducto producto) throws Exception;
	TProducto mostrar(int id) throws Exception;
	List<TProducto> mostrarTodos() throws Exception;
	void modificar(TProducto producto) throws Exception;
	void eliminar(int id) throws Exception;
}
