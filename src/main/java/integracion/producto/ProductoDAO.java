package integracion.producto;

import integracion.DAO;
import negocio.producto.TProducto;

public interface ProductoDAO extends DAO<TProducto> {
	TProducto mostrarPorNombre(String nombre) throws Exception;
}
