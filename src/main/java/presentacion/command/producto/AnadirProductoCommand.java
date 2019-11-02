package presentacion.command.producto;

import negocio.factoriaSA.FactoriaSA;
import negocio.producto.ProductoSA;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;
import presentacion.controladorAplicacion.EventosProducto;

public class AnadirProductoCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TProducto producto = (TProducto) datos;
		ProductoSA productoSA = FactoriaSA.getInstancia().generaProductoSA();
		
		try{
			productoSA.insertar(producto);
			mensaje = "El producto se ha registrado con exito. Su id es:" + producto.getId();
			return new Context(EventosProducto.ANADIR_PRODUCTO_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosProducto.ANADIR_PRODUCTO_KO, mensaje);

		}
	}
}