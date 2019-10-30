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
			int resultado = clienteSA.altaCliente(cliente);
			if(resultado != -1){
				mensaje = "El cliente ha sido anadido correctamente. Su id es: " + resultado;
				return new Context(EventosCliente.ANADIR_CLIENTE_OK, mensaje);
			}
			else{
				mensaje = "No pueden existir dos clientes con el mismo Nombre.";
				return new Context(EventosCliente.ANADIR_CLIENTE_KO, mensaje);
			}
		} catch(Exception e){
			mensaje = "No se ha podido conectar con la base de datos.";
			return new Context(EventosCliente.ANADIR_CLIENTE_KO, mensaje);
		}
	}
}