package main.java.presentacion.controladorAplicacion;

import main.java.presentacion.command.Command;
import main.java.presentacion.dispatcherView.DispatcherView;
import main.java.presentacion.factoriaCommand.FactoriaCommand;

public class ControladorAplicacionImpl extends ControladorAplicacion{

	@Override
	public void accion(Context contexto) {
		Command comando = FactoriaCommand.getInstance().getCommand(contexto.getEvento());
		contexto = comando.execute(contexto.getDatos());
		DispatcherView.getInstance().changeView(contexto);
	}
}