package presentacion.controladorAplicacion;

public class EventosFactura {
	public static final int ABRIR_FACTURA = 14;
	public static final int CERRAR_FACTURA = 15;
	public static final int MOSTRAR_FACTURA = 16;
	public static final int ANADIR_PRODUCTO_A_F = 17;
	public static final int BORRAR_PRODUCTO = 18;
	public static final int LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA = 19;
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ABRIR_FACTURA_OK = 60;
	public static final int ABRIR_FACTURA_KO = 61;
	
	public static final int CERRAR_FACTURA_OK = 62;
	public static final int CERRAR_FACTURA_KO = 63;

	public static final int MOSTRAR_FACTURA_OK = 64;
	public static final int MOSTRAR_FACTURA_KO = 65;
	
	public static final int ANADIR_PRODUCTO_A_F_OK = 66;
	public static final int ANADIR_PRODUCTO_A_F_KO = 67;
	
	public static final int BORRAR_PRODUCTO_OK = 68;
	public static final int BORRAR_PRODUCTO_KO = 69;
	
	public static final int LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_OK = 70;
	public static final int LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_KO = 71;
}
