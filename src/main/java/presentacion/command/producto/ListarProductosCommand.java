package presentacion.command.producto;

import java.util.List;

import negocio.factoriaSA.FactoriaSA;
import negocio.producto.ProductoSA;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosProducto;

public class ListarProductosCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		ProductoSA productoSA = FactoriaSA.getInstancia().generaProductoSA();
		
		try{
			List<TProducto> listaProductos = productoSA.mostrarTodos();
			return new Context(EventosProducto.LISTAR_PRODUCTOS_OK, listaProductos);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosProducto.LISTAR_PRODUCTOS_KO, mensaje);
		}
	}
}