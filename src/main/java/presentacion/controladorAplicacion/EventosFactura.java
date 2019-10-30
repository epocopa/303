package presentacion.controladorAplicacion;

public class EventosFactura {
	public static final int ABRIR_FACTURA = 7;
	public static final int CERRAR_FACTURA = 8;
	public static final int MOSTRAR_FACTURA = 9;
	public static final int ANADIR_PRODUCTO_A_F = 10;
	public static final int BORRAR_PRODUCTO = 11;
	public static final int LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA = 12;
	
	// Eventos para actualizar la vista si la operacion se ha realizado correctamente (_OK) o incorrectamente (_KO).
	
	public static final int ABRIR_FACTURA_OK = 14;
	public static final int ABRIR_FACTURA_KO = 15;
	
	public static final int CERRAR_FACTURA_OK = 16;
	public static final int CERRAR_FACTURA_KO = 17;

	public static final int MOSTRAR_FACTURA_OK = 18;
	public static final int MOSTRAR_FACTURA_KO = 19;
	
	public static final int ANADIR_PRODUCTO_A_F_OK = 20;
	public static final int ANADIR_PRODUCTO_A_F_KO = 21;
	
	public static final int BORRAR_PRODUCTO_OK = 22;
	public static final int BORRAR_PRODUCTO_KO = 23;
	
	public static final int LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_OK = 24;
	public static final int LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_KO = 25;
}
