package presentacion.factoriaCommand;

import presentacion.command.Command;
import presentacion.command.cliente.AnadirClienteCommand;
import presentacion.controladorAplicacion.EventosCliente;

public class FactoriaCommandImpl extends FactoriaCommand{

	@Override
	public Command getCommand(int evento) {
		Command comando = null;
		switch(evento){
			case EventosCliente.ANADIR_CLIENTE: comando = new AnadirClienteCommand(); break;
			//--- RESTO DE COMANDOS --- �Por cada caso de uso hay que hacer un command (BajaClienteCommand, etc.)?//
		}

		return comando;	
	}
}