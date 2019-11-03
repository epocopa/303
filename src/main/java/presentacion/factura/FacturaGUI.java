package presentacion.factura;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface FacturaGUI {
	public abstract void initialize();
	
	public abstract String getName();
	public abstract void createPathButton(String name);
	public abstract void addPathSeparator();
	public abstract JButton createMenuButton(String iconPath, Color color);
	public abstract JButton createToolButton(String iconPath, Color color, String tooltip);
	public abstract void showOutputMsg(JPanel area, JLabel text, String msg, Boolean ok);
	
	public abstract void abrirPanel();
	public abstract void productosCompradosPanel();
	public abstract void buscarPanel();
	
	public abstract void clear();
	public abstract void mostrar();
	
	public abstract void actualizar(int evento, Object datos);
}