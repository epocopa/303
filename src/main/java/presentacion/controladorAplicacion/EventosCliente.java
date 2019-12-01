package presentacion.controladorAplicacion;

public class EventosCliente {
	public static final int ANADIR_CLIENTE = 7;
	public static final int MODIFICAR_CLIENTE = 8;
	public static final int BAJA_CLIENTE = 9;
	public static final int MOSTRAR_CLIENTE = 10;
	public static final int LISTAR_CLIENTES = 11;
	public static final int LISTAR_CLIENTES_POR_FECHA_ALTA = 12;
	public static final int MODIFICAR_BUSCAR_CLIENTE = 13;	// Para modificar un CLIENTE, primero tenemos que buscar si esta, o no en la BBDD.

	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_CLIENTE_OK = 46;
	public static final int ANADIR_CLIENTE_KO = 47;
	
	public static final int MODIFICAR_CLIENTE_OK = 48;
	public static final int MODIFICAR_CLIENTE_KO = 49;

	public static final int MODIFICAR_BUSCAR_CLIENTE_OK = 50;
	public static final int MODIFICAR_BUSCAR_CLIENTE_KO = 51;

	public static final int BAJA_CLIENTE_OK = 52;
	public static final int BAJA_CLIENTE_KO = 53;

	public static final int MOSTRAR_CLIENTE_OK = 54;
	public static final int MOSTRAR_CLIENTE_KO = 55;
	
	public static final int LISTAR_CLIENTES_OK = 56;
	public static final int LISTAR_CLIENTES_KO = 57;

	public static final int LISTAR_CLIENTES_POR_FECHA_ALTA_OK = 58;
	public static final int LISTAR_CLIENTES_POR_FECHA_ALTA_KO = 59;
}