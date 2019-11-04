package presentacion.command.factura;

import negocio.factoriaSA.FactoriaSA;
import negocio.factura.FacturaSA;
import negocio.factura.TLineaFactura;
import negocio.producto.TProducto;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosFactura;

public class BorrarProductoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TLineaFactura factura = (TLineaFactura) datos;
		FacturaSA facturaSA = FactoriaSA.getInstancia().generaFacturaSA();
		
		try{
			TProducto producto = facturaSA.borrarProducto(factura);
			return new Context(EventosFactura.BORRAR_PRODUCTO_OK, producto);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosFactura.BORRAR_PRODUCTO_KO, mensaje);
		}
	}
}