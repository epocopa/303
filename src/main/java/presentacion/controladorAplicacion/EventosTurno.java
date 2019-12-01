package presentacion.controladorAplicacion;

public class EventosTurno {
	public static final int ANADIR_TURNO = 26;
	public static final int MODIFICAR_TURNO = 27;
	public static final int BAJA_TURNO = 28;
	public static final int MOSTRAR_TURNO = 29;
	public static final int LISTAR_TURNO = 30;
	public static final int MODIFICAR_BUSCAR_TURNO = 31;	// Para modificar un TURNO, primero tenemos que buscar si esta, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_TURNO_OK = 84;
	public static final int ANADIR_TURNO_KO = 85;
	
	public static final int MODIFICAR_TURNO_OK = 86;
	public static final int MODIFICAR_TURNO_KO = 87;
	
	public static final int MODIFICAR_BUSCAR_TURNO_OK = 88;
	public static final int MODIFICAR_BUSCAR_TURNO_KO = 89;
	
	public static final int BAJA_TURNO_OK = 90;
	public static final int BAJA_TURNO_KO = 91;

	public static final int MOSTRAR_TURNO_OK = 92;
	public static final int MOSTRAR_TURNO_KO = 93;
	
	public static final int LISTAR_TURNO_OK = 94;
	public static final int LISTAR_TURNO_KO = 95;
}
