package presentacion.controladorAplicacion;

public class Context {
	private Object datos;
	private int evento;
	
	public Context(int evento, Object datos) {
		this.datos = datos;
		this.evento = evento;
	}
	
	public Object getDatos() {
		return datos;
	}
	
	public int getEvento() {
		return evento;	
	}
}