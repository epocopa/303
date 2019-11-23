package presentacion.controladorAplicacion;

public class EventosEmpleado {
	public static final int ANADIR_EMPLEADO = 33;
	public static final int MODIFICAR_EMPLEADO = 34;
	public static final int BAJA_EMPLEADO = 35;
	public static final int MOSTRAR_EMPLEADO = 36;
	public static final int LISTAR_EMPLEADOS = 37;
	public static final int MODIFICAR_BUSCAR_EMPLEADO = 38;	// Para modificar un EMPLEADO, primero tenemos que buscar si esta, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_EMPLEADO_OK = 68;
	public static final int ANADIR_EMPLEADO_KO = 69;
	
	public static final int MODIFICAR_EMPLEADO_OK = 70;
	public static final int MODIFICAR_EMPLEADO_KO = 71;
	
	public static final int MODIFICAR_BUSCAR_EMPLEADO_OK = 72;
	public static final int MODIFICAR_BUSCAR_EMPLEADO_KO = 73;
	
	public static final int BAJA_EMPLEADO_OK = 74;
	public static final int BAJA_EMPLEADO_KO = 75;

	public static final int MOSTRAR_EMPLEADO_OK = 76;
	public static final int MOSTRAR_EMPLEADO_KO = 77;
	
	public static final int LISTAR_EMPLEADO_OK = 78;
	public static final int LISTAR_EMPLEADO_KO = 79;

}
