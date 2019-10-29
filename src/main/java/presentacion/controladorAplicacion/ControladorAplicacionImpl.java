package presentacion.controladorAplicacion;

import presentacion.command.Command;
import presentacion.dispatcherView.DispatcherView;
import presentacion.factoriaCommand.FactoriaCommand;

public class ControladorAplicacionImpl extends ControladorAplicacion{

	@Override
	public void accion(Context contexto) {
		Command comando = FactoriaCommand.getInstance().getCommand(contexto.getEvento());
		contexto = comando.execute(contexto.getDatos());
		DispatcherView.getInstance().changeView(contexto);
	}
}