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
			if(resultado != -1) {
				mensaje = "El turno se ha eliminado con exito.";
				return new Context(EventosCliente.BAJA_CLIENTE_OK, mensaje);
			}
			else{
				mensaje = "No se ha encontrado ningún turno con el ID proporcionado.";
				return new Context(EventosCliente.BAJA_CLIENTE_KO, mensaje);
			}
		}catch(Exception e){
			mensaje = "No se ha podido conectar con la base de datos.";
			return new Context(EventosCliente.BAJA_CLIENTE_KO, mensaje);
		}
	}
}