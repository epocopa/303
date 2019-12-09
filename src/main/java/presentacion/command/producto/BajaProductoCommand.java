package presentacion.command.producto;

import negocio.factoriaSA.FactoriaSA;
import negocio.producto.ProductoSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosProducto;

public class BajaProductoCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		ProductoSA productoSA = FactoriaSA.getInstancia().generaProductoSA();

		try {
			productoSA.eliminar(id);
			mensaje = "El producto se ha eliminado con exito.";
			return new Context(EventosProducto.BAJA_PRODUCTO_OK, mensaje);
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new Context(EventosProducto.BAJA_PRODUCTO_KO, mensaje);
		}
	}
}