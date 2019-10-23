package main.java.presentacion.factoriaPresentacion;

import javax.swing.JPanel;

public abstract class FactoriaPresentacion {
	private static FactoriaPresentacion presentacion;
	
	public static synchronized  FactoriaPresentacion getInstancia() {
		if(presentacion == null) {
			presentacion = new FactoriaPresentacionImpl();
		}
		return presentacion;
	}
	
	public abstract MainGUIImpl generarMainGUI();
	//public abstract ClienteGUIImpl generarClienteGUI();
	//public abstract FacturaGUIImpl generarFacturaGUI();
	//public abstract ProductoGUIImpl generarProductoGUI();
	public abstract JPanel generarPath();
}