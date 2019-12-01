package presentacion.factoria;

import presentacion.controladorAplicacion.EventosMenu;
import presentacion.empleado.EmpleadoGUIImpl;
import presentacion.grupo.GrupoGUIImpl;
import presentacion.turno.TurnoGUIImpl;

public class FactoriaPresentacionJPAImpl extends FactoriaPresentacionImpl{
	public GUI generarVista(int evento) {
		GUI gui = null;
		if (evento < 4) {
			gui = super.generarVista(evento);
		}
		else {
			switch(evento){
			case EventosMenu.MOSTRAR_TURNO_GUI: gui = new TurnoGUIImpl(); break;
			case EventosMenu.MOSTRAR_GRUPO_GUI: gui = new GrupoGUIImpl(); break;
			case EventosMenu.MOSTRAR_EMPLEADO_GUI: gui = new EmpleadoGUIImpl(); break;
			}
		}
		return gui;
	}
	
}
