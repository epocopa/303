package presentacion.command.producto;

import negocio.factoriaSA.FactoriaSA;
import negocio.producto.ProductoSA;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosProducto;

public class ModificarBuscarProductoCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		ProductoSA productoSA = FactoriaSA.getInstancia().generaProductoSA();
		
		try{
			TProducto producto = productoSA.mostrar(id);
			return new Context(EventosProducto.MODIFICAR_BUSCAR_PRODUCTO_OK, producto);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosProducto.MODIFICAR_BUSCAR_PRODUCTO_KO, mensaje);
		}
	}
}