package presentacion.controlador;

public abstract class SingletonControlador {
	
	private static SingletonControlador controlador;
	
	public static SingletonControlador getInstancia() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}
	
	public abstract void accion(int evento, Object objeto);
}