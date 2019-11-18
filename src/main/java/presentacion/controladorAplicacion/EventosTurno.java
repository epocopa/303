package presentacion.controladorAplicacion;

public class EventosTurno {
	public static final int ANADIR_TURNO = 19;
	public static final int MODIFICAR_TURNO = 20;
	public static final int BAJA_TURNO = 21;
	public static final int MOSTRAR_TURNO = 22;
	public static final int LISTAR_TURNO = 23;
	public static final int MODIFICAR_BUSCAR_TURNO = 24;	// Para modificar un TURNO, primero tenemos que buscar si esta, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_TURNO_OK = 38;
	public static final int ANADIR_TURNO_KO = 39;
	
	public static final int MODIFICAR_TURNO_OK = 40;
	public static final int MODIFICAR_TURNO_KO = 41;
	
	public static final int MODIFICAR_BUSCAR_TURNO_OK = 42;
	public static final int MODIFICAR_BUSCAR_TURNO_KO = 43;
	
	public static final int BAJA_TURNO_OK = 44;
	public static final int BAJA_TURNO_KO = 45;

	public static final int MOSTRAR_TURNO_OK = 46;
	public static final int MOSTRAR_TURNO_KO = 47;
	
	public static final int LISTAR_TURNO_OK = 48;
	public static final int LISTAR_TURNO_KO = 49;
}
