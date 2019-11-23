package presentacion.command.menu;

import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosMenu;

public class MostrarEmpleadoGUICommand implements Command{
	
	@Override
	public Context execute(Object datos) {
		return new Context(EventosMenu.MOSTRAR_EMPLEADO_GUI, null);
	}
}