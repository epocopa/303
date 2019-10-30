package presentacion.factoria;

import javax.swing.*;

public abstract class FactoriaPresentacion {
	
	private static FactoriaPresentacion presentacion;
	
	public static FactoriaPresentacion getInstance() {
		if(presentacion == null) {
			presentacion = new FactoriaPresentacionImpl();
		}
		return presentacion;
	}
	
	public abstract GUI generarVista(int evento);
	public abstract JPanel generarPath();
}