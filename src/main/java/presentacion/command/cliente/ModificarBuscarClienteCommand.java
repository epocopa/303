package presentacion.command.cliente;

import negocio.cliente.TCliente;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;
import negocio.cliente.ClienteSA;
import negocio.factoriaSA.FactoriaSA;

public class ModificarBuscarClienteCommand implements Command {

	@Override
	public Context execute(Object datos) {
		int id = (int) datos;
		String mensaje;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		try {
			TCliente cliente = clienteSA.mostrar(id);
			return new Context(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_OK,
					cliente);
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new Context(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO,
					mensaje);
		}
	}
}