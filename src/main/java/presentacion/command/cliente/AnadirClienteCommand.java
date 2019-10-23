package main.java.presentacion.command.cliente;

import main.java.presentacion.command.Command;
import main.java.presentacion.controladorAplicacion.Context;
import main.java.presentacion.controladorAplicacion.EventosCliente;
import main.java.negocio.cliente.TCliente;

public class AnadirClienteCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TCliente cliente = (TCliente) datos;
		//SACliente saCliente = sa.generaSACliente();
		int resultado = 1; // borrar luego.
		try{
			//Integer resultado = saCliente.altaCliente(cliente);
			if(resultado != -1){
				mensaje = "El cliente ha sido añadido correctamente. Su id es: " + resultado;
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