package main.java.presentacion.controladorAplicacion;

public class EventosProducto {
	public static final int ANADIR_PRODUCTO = 13;
	public static final int MODIFICAR_PRODUCTO = 14;
	public static final int BAJA_PRODUCTO = 15;
	public static final int MOSTRAR_PRODUCTO = 16;
	public static final int LISTAR_PRODUCTOS = 17;
	public static final int MODIFICAR_BUSCAR_PRODUCTO = 18;	// Para modificar un PRODUCTO, primero tenemos que buscar si est�, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operaci�n se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_PRODUCTO_OK = 26;
	public static final int ANADIR_PRODUCTO_KO = 27;
	
	public static final int MODIFICAR_PRODUCTO_OK = 28;
	public static final int MODIFICAR_PRODUCTO_KO = 29;
	
	public static final int MODIFICAR_BUSCAR_PRODUCTO_OK = 30;
	public static final int MODIFICAR_BUSCAR_PRODUCTO_KO = 31;
	
	public static final int BAJA_PRODUCTO_OK = 32;
	public static final int BAJA_PRODUCTO_KO = 33;

	public static final int MOSTRAR_PRODUCTO_OK = 34;
	public static final int MOSTRAR_PRODUCTO_KO = 35;
	
	public static final int LISTAR_PRODUCTOS_OK = 36;
	public static final int LISTAR_PRODUCTOS_KO = 37;
}
