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
			if(cliente != null) {
				return new Context(EventosCliente.MOSTRAR_CLIENTE_OK, mensaje);
			}
			else{
				mensaje = "No se ha encontrado ningún cliente con el ID introducido.";
				return new Context(EventosCliente.MOSTRAR_CLIENTE_KO, mensaje);
			}
		} catch(Exception e){
			mensaje = "No se ha podido conectar con la base de datos.";
			return new Context(EventosCliente.MOSTRAR_CLIENTE_KO, mensaje);
		}
	}
}