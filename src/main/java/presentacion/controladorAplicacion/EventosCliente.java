package main.java.presentacion.controladorAplicacion;

public class EventosCliente {
	public static final int ANADIR_CLIENTE = 0;
	public static final int MODIFICAR_CLIENTE = 1;
	public static final int BAJA_CLIENTE = 2;
	public static final int MOSTRAR_CLIENTE = 3;
	public static final int LISTAR_CLIENTES = 4;
	public static final int LISTAR_CLIENTES_POR_FECHA_ALTA = 5;
	public static final int MODIFICAR_BUSCAR_CLIENTE = 6;	// Para modificar un CLIENTE, primero tenemos que buscar si est�, o no en la BBDD.

	// Eventos para actualizar la vista si la operaci�n se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_CLIENTE_OK = 0;
	public static final int ANADIR_CLIENTE_KO = 1;
	
	public static final int MODIFICAR_CLIENTE_OK = 2;
	public static final int MODIFICAR_CLIENTE_KO = 3;

	public static final int MODIFICAR_BUSCAR_CLIENTE_OK = 4;
	public static final int MODIFICAR_BUSCAR_CLIENTE_KO = 5;

	public static final int BAJA_CLIENTE_OK = 6;
	public static final int BAJA_CLIENTE_KO = 7;

	public static final int MOSTRAR_CLIENTE_OK = 8;
	public static final int MOSTRAR_CLIENTE_KO = 9;
	
	public static final int LISTAR_CLIENTES_OK = 10;
	public static final int LISTAR_CLIENTES_KO = 11;

	public static final int LISTAR_CLIENTES_POR_FECHA_ALTA_OK = 12;
	public static final int LISTAR_CLIENTES_POR_FECHA_ALTA_KO = 13;
}