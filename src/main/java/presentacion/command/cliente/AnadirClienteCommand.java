package presentacion.command.cliente;

import negocio.cliente.TCliente;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;
import negocio.cliente.ClienteSA;
import negocio.factoriaSA.FactoriaSA;

public class AnadirClienteCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TCliente cliente = (TCliente) datos;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		try{
			clienteSA.altaCliente(cliente);
			mensaje = "El cliente ha sido anadido correctamente. Su id es: " + cliente.getId();
			return new Context(EventosCliente.ANADIR_CLIENTE_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosCliente.ANADIR_CLIENTE_KO, mensaje);
		}
	}
}