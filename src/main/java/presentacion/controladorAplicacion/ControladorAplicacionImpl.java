package presentacion.controladorAplicacion;

import presentacion.command.Command;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.factoria.GUI;
import presentacion.factoriaCommand.FactoriaCommand;

public class ControladorAplicacionImpl extends ControladorAplicacion{
	private GUI gui = FactoriaPresentacion.getInstance().generarVista(EventosMenu.MOSTRAR_HOME_GUI);
	
	@Override
	public void accion(Context contexto) {
		Command comando = FactoriaCommand.getInstance().getCommand(contexto.getEvento());
		contexto = comando.execute(contexto.getDatos());
		gui.actualizar(contexto.getEvento(), contexto.getDatos());
	}
}