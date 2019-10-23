package main.java.presentacion.factoriaPresentacion;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.java.presentacion.cliente.ClienteGUIImpl;
import main.java.presentacion.main.MainGUIImpl;

public class FactoriaPresentacionImpl extends FactoriaPresentacion{
	JPanel pathPanel;

	public MainGUIImpl generarMainGUI() {
		return new MainGUIImpl();
	}

	public ClienteGUIImpl generarClienteGUI() {
		return new ClienteGUIImpl();
	}

	public FacturaGUIImpl generarFacturaGUI() {
		return new FacturaGUIImpl();
	}

	public ProductoGUIImpl generarProductoGUI() {
		return new ProductoGUIImpl();
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