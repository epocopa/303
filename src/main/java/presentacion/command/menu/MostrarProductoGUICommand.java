package presentacion.command.menu;

import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosMenu;

public class MostrarProductoGUICommand implements Command{

	@Override
	public Context execute(Object datos) {
		return new Context(EventosMenu.MOSTRAR_PRODUCTO_GUI, null);
	}
}