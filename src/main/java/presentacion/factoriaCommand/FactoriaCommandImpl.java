package presentacion.factoriaCommand;

import presentacion.command.Command;
import presentacion.command.cliente.AnadirClienteCommand;
import presentacion.command.cliente.BajaClienteCommand;
import presentacion.command.cliente.ListarClientesCommand;
import presentacion.command.cliente.ListarClientesPorFechaAltaCommand;
import presentacion.command.cliente.ModificarBuscarClienteCommand;
import presentacion.command.cliente.ModificarClienteCommand;
import presentacion.command.cliente.MostrarClienteCommand;
import presentacion.command.factura.AbrirFacturaCommand;
import presentacion.command.factura.AnadirProductoAFCommand;
import presentacion.command.factura.BorrarProductoCommand;
import presentacion.command.factura.CerrarFacturaCommand;
import presentacion.command.factura.ListarProductosCompradosPorFechaCommand;
import presentacion.command.factura.MostrarFacturaCommand;
import presentacion.command.menu.MostrarClienteGUICommand;
import presentacion.command.menu.MostrarFacturaGUICommand;
import presentacion.command.menu.MostrarMenuGUICommand;
import presentacion.command.menu.MostrarProductoGUICommand;
import presentacion.command.producto.AnadirProductoCommand;
import presentacion.command.producto.BajaProductoCommand;
import presentacion.command.producto.ListarProductosCommand;
import presentacion.command.producto.ModificarBuscarProductoCommand;
import presentacion.command.producto.ModificarProductoCommand;
import presentacion.command.producto.MostrarProductoCommand;
import presentacion.controladorAplicacion.EventosCliente;
import presentacion.controladorAplicacion.EventosFactura;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.controladorAplicacion.EventosProducto;

public class FactoriaCommandImpl extends FactoriaCommand{

	@Override
	public Command getCommand(int evento) {
		Command comando = null;
		switch(evento){
			case EventosCliente.ANADIR_CLIENTE: comando = new AnadirClienteCommand(); break;
			case EventosCliente.MODIFICAR_CLIENTE: comando = new ModificarClienteCommand(); break;
			case EventosCliente.BAJA_CLIENTE: comando = new BajaClienteCommand(); break;
			case EventosCliente.MOSTRAR_CLIENTE: comando = new MostrarClienteCommand(); break;
			case EventosCliente.LISTAR_CLIENTES: comando = new ListarClientesCommand(); break;
			case EventosCliente.LISTAR_CLIENTES_POR_FECHA_ALTA: comando = new ListarClientesPorFechaAltaCommand(); break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE: comando = new ModificarBuscarClienteCommand(); break;
			
			case EventosFactura.ABRIR_FACTURA: comando = new AbrirFacturaCommand(); break;
			case EventosFactura.CERRAR_FACTURA: comando = new CerrarFacturaCommand(); break;
			case EventosFactura.MOSTRAR_FACTURA: comando = new MostrarFacturaCommand(); break;
			case EventosFactura.ANADIR_PRODUCTO_A_F: comando = new AnadirProductoAFCommand(); break;
			case EventosFactura.BORRAR_PRODUCTO: comando = new BorrarProductoCommand(); break;
			case EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA: comando = new ListarProductosCompradosPorFechaCommand(); break;
			
			case EventosProducto.ANADIR_PRODUCTO: comando = new AnadirProductoCommand(); break;
			case EventosProducto.MODIFICAR_PRODUCTO: comando = new ModificarProductoCommand(); break;
			case EventosProducto.BAJA_PRODUCTO: comando = new BajaProductoCommand(); break;
			case EventosProducto.MOSTRAR_PRODUCTO: comando = new MostrarProductoCommand(); break;
			case EventosProducto.LISTAR_PRODUCTOS: comando = new ListarProductosCommand(); break;
			case EventosProducto.MODIFICAR_BUSCAR_PRODUCTO: comando = new ModificarBuscarProductoCommand(); break;
			
			case EventosMenu.MOSTRAR_HOME_GUI: comando = new MostrarMenuGUICommand(); break;
			case EventosMenu.MOSTRAR_CLIENTE_GUI: comando = new MostrarClienteGUICommand(); break;
			case EventosMenu.MOSTRAR_FACTURA_GUI: comando = new MostrarFacturaGUICommand(); break;
			case EventosMenu.MOSTRAR_PRODUCTO_GUI: comando = new MostrarProductoGUICommand(); break;
		}

		return comando;	
	}
}