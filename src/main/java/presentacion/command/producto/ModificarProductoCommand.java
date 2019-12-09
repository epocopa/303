package presentacion.command.producto;

import negocio.factoriaSA.FactoriaSA;
import negocio.producto.ProductoSA;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosProducto;

public class ModificarProductoCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TProducto producto = (TProducto) datos;
		ProductoSA productoSA = FactoriaSA.getInstancia().generaProductoSA();

		try {
			productoSA.modificar(producto);
			mensaje = "El producto seleccionado ha sido editado correctamente.";
			return new Context(EventosProducto.MODIFICAR_PRODUCTO_OK, mensaje);

		} catch (Exception e) {
			mensaje = e.getMessage();
			return new Context(EventosProducto.MODIFICAR_PRODUCTO_KO, mensaje);
		}
	}
}