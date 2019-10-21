package presentacion.controlador;

import java.util.LinkedList;
import negocio.factoriaSA.FactoriaSA;
import negocio.negocioCliente.SACliente;
import negocio.negocioCliente.TCliente;
import negocio.negocioFactura.SAFactura;
import negocio.negocioFactura.TFactura;
import negocio.negocioProducto.SAProducto;
import negocio.negocioProducto.TProducto;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.mainGUI.MainGUIImpl;

public class Controlador extends SingletonControlador{
	
	private FactoriaSA sa = FactoriaSA.getInstancia();
	private FactoriaPresentacion presentacion = FactoriaPresentacion.getInstancia();
	private MainGUIImpl gui = presentacion.generarMainGUI();
	
	public void accion(int evento, Object objeto) {
		String mensaje = new String();
		switch(evento)
		{
			//--- OPERACIONES MÓDULO CLIENTE ---//
			case EventosCliente.ANADIR_CLIENTE: {	
				TCliente cliente = (TCliente) objeto;
				SACliente saCliente = sa.generaSACliente();
				try{
					Integer resultado = saCliente.altaCliente(cliente);
					if(resultado != -1){
						mensaje = "El cliente ha sido añadido correctamente. Su id es: " + resultado.toString();
						gui.actualizar(EventosCliente.ANADIR_CLIENTE_OK, mensaje);
					}
					else{
						mensaje = "No pueden existir dos clientes con el mismo Nombre.";
						gui.actualizar(EventosCliente.ANADIR_CLIENTE_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.ANADIR_CLIENTE_KO, mensaje);
				
				}
			}; break;
			case EventosCliente.MODIFICAR_CLIENTE: {
				TCliente cliente = (TCliente) objeto;
				SACliente saCliente = sa.generaSACliente();
				try{
					Integer resultado = saCliente.editarCliente(cliente);
					if(resultado == -1){
						mensaje = "No se ha encontrado ningún cliente con el ID introducido.";
						gui.actualizar(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
					
					}
					else if(resultado == -2){
						mensaje = "No pueden existir dos clientes con el mismo Nombre.";
						gui.actualizar(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
					}
					else{
						mensaje = "El cliente seleccionado ha sido modificado correctamente.";
						gui.actualizar(EventosCliente.MODIFICAR_CLIENTE_OK, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosFactura.BAJA_CLIENTE: {
				// NUEVO BOTÓN
			}; break;
			case EventosCliente.MOSTRAR_CLIENTE: {
				Integer id = (Integer) objeto;
				SACliente saCliente = sa.generaSACliente();
				try{
					TCliente cliente = saCliente.devolverCliente(id);
					if(cliente != null) {
						gui.actualizar(EventosCliente.MOSTRAR_CLIENTE_OK, cliente);
					}
					else{
						mensaje = "No se ha encontrado ningún cliente con el ID introducido.";
						gui.actualizar(EventosCliente.MOSTRAR_CLIENTE_KO, mensaje);;
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.MOSTRAR_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosCliente.LISTAR_CLIENTES: {
				SACliente saCliente = sa.generaSACliente();
				try{
					LinkedList<TCliente> listaClientes = saCliente.listarClientes();
					if(listaClientes != null){
						gui.actualizar(EventosCliente.LISTAR_CLIENTES_OK, listaClientes);
					}
					else{
						mensaje = "No hay clientes disponibles.";
						gui.actualizar(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
					}
				}catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
				}
			}; break;
			//--- OPERACIONES MÓDULO PRODUCTO ---//
			case EventosProducto.ANADIR_PRODUCTO:{
				TProducto producto = (TProducto) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					Integer resultado = saProducto.altaProducto(producto);
					if(resultado != -1 && resultado != -2){
						mensaje = "El producto se ha registrado con exito. Su id es:" + resultado.toString();
						gui.actualizar(EventosProducto.ANADIR_PRODUCTO_OK, mensaje);
					}
					else if(resultado == -1){
						mensaje = "No pueden existir dos productos con el mismo Nombre.";
						gui.actualizar(EventosProducto.ANADIR_PRODUCTO_KO, mensaje);
					}
					else if(resultado == -2){///
						mensaje = "Los valores introducidos no son válidos.";
						gui.actualizar(EventosProducto.ANADIR_PRODUCTO_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.ANADIR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosProducto.MODIFICAR_PRODUCTO:{
				TProducto producto = (TProducto) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					Integer resultado = saProducto.editarProducto(producto);
					if(resultado == -1){
						mensaje = "No se ha encontrado ningún producto con el ID proporcionado.";
						gui.actualizar(EventosProducto.MODIFICAR_PRODUCTO_KO, mensaje);
					}
					else if(resultado == -2){
						mensaje = "No pueden existir dos productos con el mismo Nombre.";
						gui.actualizar(EventosProducto.MODIFICAR_PRODUCTO_KO, mensaje);
					}
					else if(resultado == -3){
						mensaje = "Los valores asignados a los campos no son validos.";
						gui.actualizar(EventosProducto.MODIFICAR_PRODUCTO_KO, mensaje);
					}
					else{
						mensaje = "El producto seleccionado ha sido editado correctamente.";
						gui.actualizar(EventosProducto.MODIFICAR_PRODUCTO_OK, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.MODIFICAR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosProducto.BAJA_PRODUCTO:{
				Integer id = (Integer) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					Integer resultado = saProducto.bajaProducto(id);
					if(resultado == -1) {
						mensaje = "No se ha encontrado ningún producto con el ID proporcionado.";
						gui.actualizar(EventosProducto.BAJA_PRODUCTO_KO, mensaje);
					}
					else{
						mensaje = "El producto se ha eliminado con exito.";
						gui.actualizar(EventosProducto.BAJA_PRODUCTO_OK, mensaje);
					}
				}catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.BAJA_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosProducto.MOSTRAR_PRODUCTO:{
				Integer id = (Integer) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					TProducto producto = saProducto.devolverProducto(id);
					if(producto != null) {
						gui.actualizar(EventosProducto.MOSTRAR_PRODUCTO_OK, producto);
					}
					else{
						mensaje = "No se ha encontrado ningún producto con el ID introducido.";
						gui.actualizar(EventosProducto.MOSTRAR_PRODUCTO_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.MOSTRAR_PRODUCTO_KO, mensaje);
				}
			}; break;
			case EventosProducto.LISTAR_PRODUCTOS:{
				SAProducto saProducto = sa.generaSAProducto();
				try{
					LinkedList<TProducto> listaProductos = saProducto.listarProductos();
					if(listaProductos != null){
						gui.actualizar(EventosProducto.LISTAR_PRODUCTOS_OK, listaProductos);
					}
					else{
						mensaje = "No hay productos disponibles.";
						gui.actualizar(EventosProducto.LISTAR_PRODUCTOS_KO, mensaje);
					}
				}catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.LISTAR_PRODUCTOS_KO, mensaje);
				}
			}; break;
			//--- OPERACIONES MÓDULO FACTURA ---//
			case EventosFactura.ABRIR_FACTURA:{
				TFactura factuta = (TFactura) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					Integer resultado = saFactura.abrirFactura(factuta);
					if(resultado != -1){
						mensaje = "La factura se ha abierto correctamente. Su id es: " + resultado.toString();
						gui.actualizar(EventosFactura.ABRIR_FACTURA_OK, resultado);
					}
					else{
						mensaje = "Los valores introducidos no son válidos.";
						gui.actualizar(EventosFactura.ABRIR_FACTURA_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.ABRIR_FACTURA_KO, mensaje);
				}
			}; break;
			case EventosFactura.CERRAR_FACTURA:{
				TFactura factuta = (TFactura) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					Integer resultado = saFactura.cerrarFactura(factuta);
					if(resultado != -1){
						mensaje = "La factura se ha cerrado correctamente.";
						gui.actualizar(EventosFactura.CERRAR_FACTURA_OK, mensaje);
					}
					else{
						mensaje = "Se ha producido un error.";
						gui.actualizar(EventosFactura.CERRAR_FACTURA_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.CERRAR_FACTURA_KO, mensaje);
				}
			}; break;
			case EventosFactura.MOSTRAR_FACTURA:{
				Integer id = (Integer) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					TFactura factura = saFactura.mostrarFactura(id);
					if(factura != null) {
						gui.actualizar(EventosFactura.MOSTRAR_FACTURA_OK, factura);
					}
					else{
						mensaje = "No se ha encontrado ninguna factura con el ID introducido.";
						gui.actualizar(EventosFactura.MOSTRAR_FACTURA_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.MOSTRAR_FACTURA_KO, mensaje);
				}
			}; break;
			case EventosFactura.ANADIR_PRODUCTO_A_F:{
				TProducto producto = (TProducto) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					TProducto p = saFactura.anadirProducto(producto);
					if(p != null){
						gui.actualizar(EventosFactura.ANADIR_PRODUCTO_A_F_OK, p);
					}
					else {
						mensaje = "No se ha podido añadir producto a la factura.";
						gui.actualizar(EventosFactura.ANADIR_PRODUCTO_A_F_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.ANADIR_PRODUCTO_A_F_KO, mensaje);
				}
			}; break;
			case EventosFactura.BORRAR_PRODUCTO:{
				TProducto p = (TProducto) objeto;
				SAFactura saFactura = sa.generaSAFactura();
				try{
					TProducto producto = saFactura.borrarProducto(p);
					if(producto != null) {
						mensaje = "La factura ha sido actualizada correctamente.";
						gui.actualizar(EventosFactura.BORRAR_PRODUCTO_OK, p);
					}
					else{
						mensaje = "Los valores introducidos no son válidos.";
						gui.actualizar(EventosFactura.BORRAR_PRODUCTO_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosFactura.BORRAR_PRODUCTO_KO, mensaje);
				}				
			}; break;
			case EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA:{
				// NUEVO BOTÓN.
			}; break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE:{
				Integer id = (Integer) objeto;
				SACliente saCliente = sa.generaSACliente();
				try{
					TCliente cliente = saCliente.devolverCliente(id);
					if(cliente != null) {
						gui.actualizar(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_OK, cliente);
					}
					else{
						mensaje = "No se ha encontrado ningún empleado con el ID introducido.";
						gui.actualizar(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO, mensaje);;
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO, mensaje);
				}
			}; break;
			case EventosProducto.EDITAR_BUSCAR_PRODUCTO:{
				Integer id = (Integer) objeto;
				SAProducto saProducto = sa.generaSAProducto();
				try{
					TProducto producto = saProducto.devolverProducto(id);
					if(producto != null) {
						gui.actualizar(EventosProducto.EDITAR_BUSCAR_PRODUCTO_OK, producto);
					}
					else{
						mensaje = "No se ha encontrado ningún producto con el ID introducido.";
						gui.actualizar(EventosProducto.EDITAR_BUSCAR_PRODUCTO_KO, mensaje);
					}
				} catch(Exception e){
					mensaje = "No se ha podido conectar con la base de datos.";
					gui.actualizar(EventosProducto.EDITAR_BUSCAR_PRODUCTO_KO, mensaje);
				}
			}; break;
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
