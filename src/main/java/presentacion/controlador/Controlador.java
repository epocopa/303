package presentacion.controlador;

import negocio.factoriaSA.FactoriaSA;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.main.MainGUIImpl;

public class Controlador extends SingletonControlador{
	
	private FactoriaSA sa = FactoriaSA.getInstancia();
	private FactoriaPresentacion presentacion = FactoriaPresentacion.getInstancia();
	private MainGUIImpl gui = presentacion.generarMainGUI();
	
	public void accion(int evento, Object objeto) {
		String mensaje = new String();
		switch(evento){
		
			//--- OPERACIONES M�DULO CLIENTE ---//
			case EventosCliente.ANADIR_CLIENTE: {	
				
			}; break;
			case EventosCliente.MODIFICAR_CLIENTE: {
				
			}; break;
			case EventosFactura.BAJA_CLIENTE: {
				// NUEVO BOT�N
			}; break;
			case EventosCliente.MOSTRAR_CLIENTE: {
				
			}; break;
			case EventosCliente.LISTAR_CLIENTES: {
				
			}; break;
			case EventosCliente.LISTAR_CLIENTES_POR_FECHA_ALTA: {
				// NUEVO BOT�N
			}; break;
			
			//--- OPERACIONES M�DULO PRODUCTO ---//
			case EventosProducto.ANADIR_PRODUCTO:{
				
			}; break;
			case EventosProducto.MODIFICAR_PRODUCTO:{
				
			}; break;
			case EventosProducto.BAJA_PRODUCTO:{
				
			}; break;
			case EventosProducto.MOSTRAR_PRODUCTO:{
				
			}; break;
			case EventosProducto.LISTAR_PRODUCTOS:{
				
			}; break;
			
			//--- OPERACIONES M�DULO FACTURA ---//
			case EventosFactura.ABRIR_FACTURA:{
				
			}; break;
			case EventosFactura.CERRAR_FACTURA:{
				
			}; break;
			case EventosFactura.MOSTRAR_FACTURA:{
				
			}; break;
			case EventosFactura.ANADIR_PRODUCTO_A_F:{
				
			}; break;
			case EventosFactura.BORRAR_PRODUCTO:{
				
			}; break;
			case EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA:{
				// NUEVO BOT�N.
			}; break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE:{
				
			}; break;
			case EventosProducto.MODIFICAR_BUSCAR_PRODUCTO:{

			}; break;
			
			//--- ACTUALIZACI�N DE LA VISTA ---//
			case EventosMenu.MOSTRAR_HOME_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_HOME_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_CLIENTE_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_CLIENTE_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_FACTURA_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_FACTURA_GUI, null);
			}; break;
			case EventosMenu.MOSTRAR_PRODUCTO_GUI:{
				gui.actualizar(EventosMenu.MOSTRAR_PRODUCTO_GUI, null);
			}; break;
		}
	}
}
