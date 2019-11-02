package presentacion.command.cliente;

import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;
import negocio.cliente.ClienteSA;
import negocio.factoriaSA.FactoriaSA;
import negocio.cliente.TCliente;

public class ModificarClienteCommand implements Command{

	@Override
	public Context execute(Object datos) {
		TCliente cliente = (TCliente) datos;
		String mensaje;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		try{
			int resultado = clienteSA.modificar(cliente);
			mensaje = "El cliente seleccionado ha sido modificado correctamente.";
			return new Context(EventosCliente.MODIFICAR_CLIENTE_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
		}
	}
}