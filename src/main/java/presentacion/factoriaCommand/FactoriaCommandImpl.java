package presentacion.factoriaCommand;

import presentacion.command.Command;
import presentacion.command.cliente.AnadirClienteCommand;
import presentacion.command.cliente.BajaClienteCommand;
import presentacion.command.cliente.ListarClientesCommand;
import presentacion.command.cliente.ListarClientesPorFechaAltaCommand;
import presentacion.command.cliente.ModificarBuscarClienteCommand;
import presentacion.command.cliente.ModificarClienteCommand;
import presentacion.command.cliente.MostrarClienteCommand;
import presentacion.command.empleado.AnadirEmpleadoCommand;
import presentacion.command.empleado.BajaEmpleadoCommand;
import presentacion.command.empleado.ListarEmpleadosCommand;
import presentacion.command.empleado.ModificarBuscarEmpleadoCommand;
import presentacion.command.empleado.ModificarEmpleadoCommand;
import presentacion.command.empleado.MostrarEmpleadoCommand;
import presentacion.command.factura.AbrirFacturaCommand;
import presentacion.command.factura.AnadirProductoAFCommand;
import presentacion.command.factura.BorrarProductoCommand;
import presentacion.command.factura.CerrarFacturaCommand;
import presentacion.command.factura.ListarProductosCompradosPorFechaCommand;
import presentacion.command.factura.MostrarFacturaCommand;
import presentacion.command.grupo.AnadirEmpleadoAGrupoCommand;
import presentacion.command.grupo.AnadirGrupoCommand;
import presentacion.command.grupo.BajaEmpleadoDeGrupoCommand;
import presentacion.command.grupo.BajaGrupoCommand;
import presentacion.command.grupo.ListarGrupoCommand;
import presentacion.command.grupo.ModificarBuscarGrupoCommand;
import presentacion.command.grupo.ModificarGrupoCommand;
import presentacion.command.grupo.MostrarGrupoCommand;
import presentacion.command.menu.MostrarClienteGUICommand;
import presentacion.command.menu.MostrarEmpleadoGUICommand;
import presentacion.command.menu.MostrarFacturaGUICommand;
import presentacion.command.menu.MostrarGrupoGUICommand;
import presentacion.command.menu.MostrarMenuGUICommand;
import presentacion.command.menu.MostrarProductoGUICommand;
import presentacion.command.menu.MostrarTurnoGUICommand;
import presentacion.command.producto.AnadirProductoCommand;
import presentacion.command.producto.BajaProductoCommand;
import presentacion.command.producto.ListarProductosCommand;
import presentacion.command.producto.ModificarBuscarProductoCommand;
import presentacion.command.producto.ModificarProductoCommand;
import presentacion.command.producto.MostrarProductoCommand;
import presentacion.command.turno.AnadirTurnoCommand;
import presentacion.command.turno.BajaTurnoCommand;
import presentacion.command.turno.ListarTurnosCommand;
import presentacion.command.turno.ModificarBuscarTurnoCommand;
import presentacion.command.turno.ModificarTurnoCommand;
import presentacion.command.turno.MostrarTurnoCommand;
import presentacion.controladorAplicacion.EventosCliente;
import presentacion.controladorAplicacion.EventosEmpleado;
import presentacion.controladorAplicacion.EventosFactura;
import presentacion.controladorAplicacion.EventosGrupo;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.controladorAplicacion.EventosProducto;
import presentacion.controladorAplicacion.EventosTurno;

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
			
			case EventosTurno.ANADIR_TURNO: comando = new AnadirTurnoCommand(); break;
			case EventosTurno.MODIFICAR_TURNO: comando = new ModificarTurnoCommand(); break;
			case EventosTurno.BAJA_TURNO: comando = new BajaTurnoCommand(); break;
			case EventosTurno.MOSTRAR_TURNO: comando = new MostrarTurnoCommand(); break;
			case EventosTurno.LISTAR_TURNO: comando = new ListarTurnosCommand(); break;
			case EventosTurno.MODIFICAR_BUSCAR_TURNO: comando = new ModificarBuscarTurnoCommand(); break;
			
			case EventosGrupo.ANADIR_GRUPO: comando = new AnadirGrupoCommand(); break;
			case EventosGrupo.MODIFICAR_GRUPO: comando = new ModificarGrupoCommand(); break;
			case EventosGrupo.BAJA_GRUPO: comando = new BajaGrupoCommand(); break;
			case EventosGrupo.MOSTRAR_GRUPO: comando = new MostrarGrupoCommand(); break;
			case EventosGrupo.LISTAR_GRUPOS: comando = new ListarGrupoCommand(); break;
			case EventosGrupo.MODIFICAR_BUSCAR_GRUPO: comando = new ModificarBuscarGrupoCommand(); break;
			case EventosGrupo.ANADIR_EMPLEADO_A_GRUPO: comando = new AnadirEmpleadoAGrupoCommand(); break;
			case EventosGrupo.BAJA_EMPLEADO_DE_GRUPO: comando = new BajaEmpleadoDeGrupoCommand(); break;
			
			case EventosEmpleado.ANADIR_EMPLEADO: comando = new AnadirEmpleadoCommand(); break;
			case EventosEmpleado.MODIFICAR_EMPLEADO: comando = new ModificarEmpleadoCommand(); break;
			case EventosEmpleado.BAJA_EMPLEADO: comando = new BajaEmpleadoCommand(); break;
			case EventosEmpleado.MOSTRAR_EMPLEADO: comando = new MostrarEmpleadoCommand(); break;
			case EventosEmpleado.LISTAR_EMPLEADOS: comando = new ListarEmpleadosCommand(); break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO: comando = new ModificarBuscarEmpleadoCommand(); break;
			
			case EventosMenu.MOSTRAR_HOME_GUI: comando = new MostrarMenuGUICommand(); break;
			case EventosMenu.MOSTRAR_CLIENTE_GUI: comando = new MostrarClienteGUICommand(); break;
			case EventosMenu.MOSTRAR_FACTURA_GUI: comando = new MostrarFacturaGUICommand(); break;
			case EventosMenu.MOSTRAR_PRODUCTO_GUI: comando = new MostrarProductoGUICommand(); break;
			case EventosMenu.MOSTRAR_TURNO_GUI: comando = new MostrarTurnoGUICommand(); break;			
			case EventosMenu.MOSTRAR_GRUPO_GUI: comando = new MostrarGrupoGUICommand(); break;
			case EventosMenu.MOSTRAR_EMPLEADO_GUI: comando = new MostrarEmpleadoGUICommand(); break;
		}

		return comando;	
	}
}