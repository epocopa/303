package negocio.producto;

import java.util.List;

public interface ProductoSA {
	boolean insertar(TProducto producto) throws Exception;
	TProducto mostrar(int id) throws Exception;
	List<TProducto> mostrarTodos() throws Exception;
	boolean modificar(TProducto producto);
	boolean eliminar(int id);
}
