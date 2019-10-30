package presentacion.factoria;

import presentacion.cliente.ClienteGUIImpl;
import presentacion.factura.FacturaGUIImp;
import presentacion.main.MainGUIImpl;
import presentacion.producto.ProductoGUIImpl;

import javax.swing.*;
import java.awt.*;

public class FactoriaPresentacionImpl extends FactoriaPresentacion {
	
	JPanel pathPanel;

	public MainGUIImpl generarMainGUI() {
		return new MainGUIImpl();
	}

	public ClienteGUIImpl generarClienteGUI() {
		return new ClienteGUIImpl();
	}

	public FacturaGUIImp generarFacturaGUI() {
		return new FacturaGUIImp();
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