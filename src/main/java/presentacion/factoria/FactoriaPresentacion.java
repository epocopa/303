package main.java.presentacion.factoria;

import main.java.presentacion.cliente.ClienteGUIImpl;
//import main.java.presentacion.factura.FacturaGUIImpl;
import main.java.presentacion.main.MainGUIImpl;
import main.java.presentacion.producto.ProductoGUIImpl;

import javax.swing.*;

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