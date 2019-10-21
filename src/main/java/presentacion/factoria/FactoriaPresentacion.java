package presentacion.factoria;

import javax.swing.JPanel;

import presentacion.clienteGUI.ClienteGUIImpl;
import presentacion.facturaGUI.FacturaGUIImpl;
import presentacion.mainGUI.*;
import presentacion.productoGUI.ProductoGUIImpl;

public abstract class FactoriaPresentacion {
	
	private static FactoriaPresentacion presentacion;
	
	public static synchronized  FactoriaPresentacion getInstancia() {
		if(presentacion == null) {
			presentacion = new FactoriaPresentacionImpl();
		}
		return presentacion;
	}
	
	public abstract MainGUIImpl generarMainGUI();
	public abstract ClienteGUIImpl generarClienteGUI();
	public abstract FacturaGUIImpl generarFacturaGUI();
	public abstract ProductoGUIImpl generarProductoGUI();
	public abstract JPanel generarPath();
}