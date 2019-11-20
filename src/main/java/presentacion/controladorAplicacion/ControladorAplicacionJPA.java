package presentacion.controlador;

public abstract class ControladorAplicacionJPA {
	private static ControladorAplicacionJPA instancia;
	
	public static ControladorAplicacionJPA obtenerInstancia() {
		if (instancia == null) {
			instancia = new ControladorAplicacionJPAImp();
		}
		
		return instancia;
	}
	
	public abstract void accion(Contexto contexto);
}
