package presentacion.factoria;

import presentacion.cliente.ClienteGUIImpl;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.factura.FacturaGUIImp;
import presentacion.grupo.GrupoGUIImpl;
import presentacion.main.MainGUIImpl;
import presentacion.producto.ProductoGUIImpl;
import presentacion.turno.TurnoGUIImpl;

import javax.swing.*;
import java.awt.*;

public class FactoriaPresentacionImpl extends FactoriaPresentacion {
	JPanel pathPanel;
	
	public GUI generarVista(int evento){
		switch(evento){
		case EventosMenu.MOSTRAR_HOME_GUI: return new MainGUIImpl();
		case EventosMenu.MOSTRAR_CLIENTE_GUI: return new ClienteGUIImpl(); 
		case EventosMenu.MOSTRAR_FACTURA_GUI: return new FacturaGUIImp(); 
		case EventosMenu.MOSTRAR_PRODUCTO_GUI: return new ProductoGUIImpl();
		case EventosMenu.MOSTRAR_TURNO_GUI: return new TurnoGUIImpl();
		case EventosMenu.MOSTRAR_GRUPO_GUI: return new GrupoGUIImpl();
		default: return null;
		}
	}
	
	public JPanel generarPath() {
		if (pathPanel == null) {
			pathPanel = new JPanel();
			pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.X_AXIS));
			pathPanel.setBackground(new Color(255, 255, 255));
			pathPanel.setMaximumSize(new Dimension(1024, 110));
		}
		return pathPanel;
	}
}