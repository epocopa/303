package presentacion.factoria;

import presentacion.cliente.ClienteGUIImpl;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.factura.FacturaGUIImp;
import presentacion.main.MainGUIImpl;
import presentacion.producto.ProductoGUIImpl;
import javax.swing.*;
import java.awt.*;

public class FactoriaPresentacionImpl extends FactoriaPresentacion {
	JPanel pathPanel;
	
	public GUI generarVista(int evento){
		GUI gui = null;
		switch(evento){
		case EventosMenu.MOSTRAR_HOME_GUI: gui = new MainGUIImpl(); break;
		case EventosMenu.MOSTRAR_CLIENTE_GUI: gui = new ClienteGUIImpl(); break;
		case EventosMenu.MOSTRAR_FACTURA_GUI: gui = new FacturaGUIImp(); break;
		case EventosMenu.MOSTRAR_PRODUCTO_GUI: gui = new ProductoGUIImpl(); break;
		}
		return gui;
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