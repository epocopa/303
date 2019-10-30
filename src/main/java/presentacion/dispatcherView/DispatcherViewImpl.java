package presentacion.dispatcherView;

import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.main.MainGUIImpl;

public class DispatcherViewImpl extends DispatcherView{
	private FactoriaPresentacion presentacion = FactoriaPresentacion.getInstancia();
	private MainGUIImpl gui = presentacion.generarMainGUI();

	@Override
	public void changeView(Context contexto) {
		switch(contexto.getEvento()) {
		case EventosMenu.MOSTRAR_HOME_GUI: gui.actualizar(EventosMenu.MOSTRAR_HOME_GUI, null); break;
		case EventosMenu.MOSTRAR_CLIENTE_GUI: gui.actualizar(EventosMenu.MOSTRAR_CLIENTE_GUI, null); break;
		case EventosMenu.MOSTRAR_FACTURA_GUI: gui.actualizar(EventosMenu.MOSTRAR_FACTURA_GUI, null); break;
		case EventosMenu.MOSTRAR_PRODUCTO_GUI: gui.actualizar(EventosMenu.MOSTRAR_PRODUCTO_GUI, null); break;
		default: gui.actualizar(contexto.getEvento(), contexto.getDatos()); break;
		}
	}
}