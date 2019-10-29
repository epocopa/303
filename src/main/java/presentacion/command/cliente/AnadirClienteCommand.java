package presentacion.command.cliente;


import negocio.cliente.TCliente;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;

public class AnadirClienteCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TCliente cliente = (TCliente) datos;
		//SACliente saCliente = sa.generaSACliente();
		int resultado = 1; // borrar luego.
		try{
			//Integer resultado = saCliente.altaCliente(cliente);
			if(resultado != -1){
				mensaje = "El cliente ha sido a�adido correctamente. Su id es: " + resultado;
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