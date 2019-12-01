package presentacion.controladorAplicacion;

public class EventosProducto {
	public static final int ANADIR_PRODUCTO = 20;
	public static final int MODIFICAR_PRODUCTO = 21;
	public static final int BAJA_PRODUCTO = 22;
	public static final int MOSTRAR_PRODUCTO = 23;
	public static final int LISTAR_PRODUCTOS = 24;
	public static final int MODIFICAR_BUSCAR_PRODUCTO = 25;	// Para modificar un PRODUCTO, primero tenemos que buscar si esta, o no en la BBDD.
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ANADIR_PRODUCTO_OK = 72;
	public static final int ANADIR_PRODUCTO_KO = 73;
	
	public static final int MODIFICAR_PRODUCTO_OK = 74;
	public static final int MODIFICAR_PRODUCTO_KO = 75;
	
	public static final int MODIFICAR_BUSCAR_PRODUCTO_OK = 76;
	public static final int MODIFICAR_BUSCAR_PRODUCTO_KO = 77;
	
	public static final int BAJA_PRODUCTO_OK = 78;
	public static final int BAJA_PRODUCTO_KO = 79;

	public static final int MOSTRAR_PRODUCTO_OK = 80;
	public static final int MOSTRAR_PRODUCTO_KO = 81;
	
	public static final int LISTAR_PRODUCTOS_OK = 82;
	public static final int LISTAR_PRODUCTOS_KO = 83;
}
