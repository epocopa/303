package negocio.factura;

import java.util.List;

public class FacturaSAImp implements FacturaSA {
	
	/* 
	   abrirFactura -> te paso un TFactura con (id, fecha, activo) y no me devuelves nada (void).
	 
	   añadirProducto -> te paso un TLineaFactura con (idFactura, idProducto, cantidad que se quiere comprar).
	  					 me tiene que devolver un TProducto para mostrar en pantalla (id, nombre, precio, cantidad).
	  					 
	   borrarProducto -> te paso un TLineaFactura con (idFactura, idProducto, cantidad que se quiere quitar).
	   					 me tiene que devolver un TProducto con el (id, precio, cantidad a restar).
	   
	   cerrarFactura -> te paso un TFactura con (id, precio_total) y no me devuelves nada (void).
	  
	 */
	
	@Override
	public void insertar(TFactura factura) throws Exception {
	}

	@Override
	public TFactura mostrar(int id) throws Exception {
		return null;
	}

	@Override
	public List<TFactura> mostrarTodos() throws Exception {
		return null;
	}

	@Override
	public void modificar(TFactura factura) {
	}

	@Override
	public void eliminar(int id) {
	}
}
