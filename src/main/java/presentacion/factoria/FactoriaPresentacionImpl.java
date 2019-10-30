package presentacion.factoria;

import presentacion.cliente.ClienteGUIImpl;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.factura.FacturaGUIImpl;
import presentacion.main.MainGUIImpl;
import presentacion.producto.ProductoGUIImpl;

import javax.swing.*;
import java.awt.*;

public class FactoriaPresentacionImpl extends FactoriaPresentacion {
	JPanel pathPanel;
	
	public GUI generarVista(int evento){
		switch(evento){
		case EventosMenu.MOSTRAR_HOME_GUI: return new MainGUIImpl();
		case EventosMenu.MOSTRAR_CLIENTE_GUI: return new ClienteGUIImpl(); 
		case EventosMenu.MOSTRAR_FACTURA_GUI: return new FacturaGUIImpl(); 
		case EventosMenu.MOSTRAR_PRODUCTO_GUI: return new ProductoGUIImpl(); 
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