package presentacion.command.cliente;

import negocio.cliente.ClienteSA;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;

public class BajaClienteCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		try{
			int resultado = clienteSA.bajaCliente(id);
			mensaje = "El turno se ha eliminado con exito.";
			return new Context(EventosCliente.BAJA_CLIENTE_OK, mensaje);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosCliente.BAJA_CLIENTE_KO, mensaje);
		}
	}
}