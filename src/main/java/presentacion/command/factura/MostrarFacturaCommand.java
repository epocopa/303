package presentacion.command.factura;

import negocio.factoriaSA.FactoriaSA;
import negocio.factura.FacturaSA;
import negocio.factura.TFactura;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosFactura;

public class MostrarFacturaCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		FacturaSA facturaSA = FactoriaSA.getInstancia().generaFacturaSA();

		try {
			TFactura factura = facturaSA.mostrar(id);
			return new Context(EventosFactura.MOSTRAR_FACTURA_OK, factura);
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new Context(EventosFactura.MOSTRAR_FACTURA_KO, mensaje);
		}
	}
}