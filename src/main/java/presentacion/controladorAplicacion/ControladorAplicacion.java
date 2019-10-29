package presentacion.controladorAplicacion;

public abstract class ControladorAplicacion {
	private static ControladorAplicacion instance;

	public static ControladorAplicacion getInstance() {
		if (instance == null) {
			instance = new ControladorAplicacionImpl();
		}

		return instance;
	}

	public abstract void accion(Context contexto);
}