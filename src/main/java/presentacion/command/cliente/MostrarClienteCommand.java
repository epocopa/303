package presentacion.command.cliente;

import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import negocio.cliente.TCliente;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;
import negocio.cliente.ClienteSA;

public class MostrarClienteCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		try{
			TCliente cliente = clienteSA.mostrarCliente(id);
			return new Context(EventosCliente.MOSTRAR_CLIENTE_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosCliente.MOSTRAR_CLIENTE_KO, mensaje);
		}
	}
}