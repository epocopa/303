package presentacion.controladorAplicacion;

public class EventosGrupo {
	public static final int ANADIR_GRUPO = 32;
	public static final int MODIFICAR_GRUPO = 33;
	public static final int BAJA_GRUPO = 34;
	public static final int MOSTRAR_GRUPO = 35;
	public static final int LISTAR_GRUPOS = 36;
	public static final int ANADIR_EMPLEADO_A_GRUPO = 37;
	public static final int BAJA_EMPLEADO_DE_GRUPO = 38;
	public static final int MODIFICAR_BUSCAR_GRUPO = 39;	// Para modificar un GRUPO DE TRABAJO, primero tenemos que buscar si esta, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_GRUPO_OK = 96;
	public static final int ANADIR_GRUPO_KO = 97;
	
	public static final int MODIFICAR_GRUPO_OK = 98;
	public static final int MODIFICAR_GRUPO_KO = 99;
	
	public static final int MODIFICAR_BUSCAR_GRUPO_OK = 100;
	public static final int MODIFICAR_BUSCAR_GRUPO_KO = 101;
	
	public static final int BAJA_GRUPO_OK = 102;
	public static final int BAJA_GRUPO_KO = 103;

	public static final int MOSTRAR_GRUPO_OK = 104;
	public static final int MOSTRAR_GRUPO_KO = 105;
	
	public static final int LISTAR_GRUPOS_OK = 106;
	public static final int LISTAR_GRUPOS_KO = 107;
	
	public static final int MODIFICAR_BUSCAR_TURNO_OK = 108;
	public static final int MODIFICAR_BUSCAR_TURNO_KO = 109;
	
	public static final int BAJA_EMPLEADO_DE_GRUPO_OK = 110;
	public static final int BAJA_EMPLEADO_DE_GRUPO_KO = 111;
	
	public static final int ANADIR_EMPLEADO_A_GRUPO_OK = 112;
	public static final int ANADIR_EMPLEADO_A_GRUPO_KO = 113;

}
