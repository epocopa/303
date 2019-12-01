package presentacion.controladorAplicacion;

public class EventosEmpleado {
	public static final int ANADIR_EMPLEADO = 40;
	public static final int MODIFICAR_EMPLEADO = 41;
	public static final int BAJA_EMPLEADO = 42;
	public static final int MOSTRAR_EMPLEADO = 43;
	public static final int LISTAR_EMPLEADOS = 44;
	public static final int MODIFICAR_BUSCAR_EMPLEADO = 45;	// Para modificar un EMPLEADO, primero tenemos que buscar si esta, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_EMPLEADO_OK = 114;
	public static final int ANADIR_EMPLEADO_KO = 115;
	
	public static final int MODIFICAR_EMPLEADO_OK = 116;
	public static final int MODIFICAR_EMPLEADO_KO = 117;
	
	public static final int MODIFICAR_BUSCAR_EMPLEADO_OK = 118;
	public static final int MODIFICAR_BUSCAR_EMPLEADO_KO = 119;
	
	public static final int BAJA_EMPLEADO_OK = 120;
	public static final int BAJA_EMPLEADO_KO = 121;

	public static final int MOSTRAR_EMPLEADO_OK = 122;
	public static final int MOSTRAR_EMPLEADO_KO = 123;
	
	public static final int LISTAR_EMPLEADO_OK = 124;
	public static final int LISTAR_EMPLEADO_KO = 125;

}
