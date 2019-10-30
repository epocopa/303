package presentacion.factoria;

import presentacion.cliente.ClienteGUIImpl;
import presentacion.factura.FacturaGUIImp;
import presentacion.main.MainGUIImpl;
import presentacion.producto.ProductoGUIImpl;

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
	public abstract FacturaGUIImp generarFacturaGUI();
	public abstract ProductoGUIImpl generarProductoGUI();
	public abstract JPanel generarPath();
}