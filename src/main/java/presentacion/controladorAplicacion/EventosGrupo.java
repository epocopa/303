package presentacion.controladorAplicacion;

public class EventosGrupo {
	public static final int ANADIR_GRUPO = 25;
	public static final int MODIFICAR_GRUPO = 26;
	public static final int BAJA_GRUPO = 27;
	public static final int MOSTRAR_GRUPO = 28;
	public static final int LISTAR_GRUPOS = 29;
	public static final int ANADIR_EMPLEADO_A_GRUPO = 30;
	public static final int BAJA_EMPLEADO_DE_GRUPO = 31;
	public static final int MODIFICAR_BUSCAR_GRUPO = 32;	// Para modificar un GRUPO DE TRABAJO, primero tenemos que buscar si esta, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_GRUPO_OK = 50;
	public static final int ANADIR_GRUPO_KO = 51;
	
	public static final int MODIFICAR_GRUPO_OK = 52;
	public static final int MODIFICAR_GRUPO_KO = 53;
	
	public static final int MODIFICAR_BUSCAR_GRUPO_OK = 54;
	public static final int MODIFICAR_BUSCAR_GRUPO_KO = 55;
	
	public static final int BAJA_GRUPO_OK = 56;
	public static final int BAJA_GRUPO_KO = 57;

	public static final int MOSTRAR_GRUPO_OK = 58;
	public static final int MOSTRAR_GRUPO_KO = 59;
	
	public static final int LISTAR_GRUPOS_OK = 60;
	public static final int LISTAR_GRUPOS_KO = 61;
	
	public static final int MODIFICAR_BUSCAR_TURNO_OK = 62;
	public static final int MODIFICAR_BUSCAR_TURNO_KO = 63;
	
	public static final int BAJA_EMPLEADO_DE_GRUPO_OK = 64;
	public static final int BAJA_EMPLEADO_DE_GRUPO_KO = 65;
	
	public static final int ANADIR_EMPLEADO_A_GRUPO_OK = 66;
	public static final int ANADIR_EMPLEADO_A_GRUPO_KO = 67;

}
