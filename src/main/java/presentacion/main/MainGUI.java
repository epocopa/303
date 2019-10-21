package presentacion.mainGUI;

import java.awt.Color;
import javax.swing.JButton;

public interface MainGUI {
	public abstract void initialize();
	public abstract String getName();
	public abstract JButton createPathButton(String name);
	public abstract void addPathSeparator();
	public abstract void actualizarPath();
	public abstract JButton createMenuButton(String iconPath, Color color);
	public abstract void mostrar();
	public abstract void actualizar(int evento, Object datos);
}