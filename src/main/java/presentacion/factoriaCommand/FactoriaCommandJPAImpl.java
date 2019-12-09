package presentacion.factoriaCommand;

import presentacion.command.Command;
import presentacion.command.empleado.AnadirEmpleadoCommand;
import presentacion.command.empleado.BajaEmpleadoCommand;
import presentacion.command.empleado.ListarEmpleadosCommand;
import presentacion.command.empleado.ModificarBuscarEmpleadoCommand;
import presentacion.command.empleado.ModificarEmpleadoCommand;
import presentacion.command.empleado.MostrarEmpleadoCommand;
import presentacion.command.grupo.AnadirEmpleadoAGrupoCommand;
import presentacion.command.grupo.AnadirGrupoCommand;
import presentacion.command.grupo.BajaEmpleadoDeGrupoCommand;
import presentacion.command.grupo.BajaGrupoCommand;
import presentacion.command.grupo.ListarGrupoCommand;
import presentacion.command.grupo.ModificarBuscarGrupoCommand;
import presentacion.command.grupo.ModificarGrupoCommand;
import presentacion.command.grupo.MostrarGrupoCommand;
import presentacion.command.menu.MostrarEmpleadoGUICommand;
import presentacion.command.menu.MostrarGrupoGUICommand;
import presentacion.command.menu.MostrarTurnoGUICommand;
import presentacion.command.turno.AnadirEmpleadoATurnoCommand;
import presentacion.command.turno.AnadirTurnoCommand;
import presentacion.command.turno.BajaEmpleadoATurnoCommand;
import presentacion.command.turno.BajaTurnoCommand;
import presentacion.command.turno.ListarTurnosCommand;
import presentacion.command.turno.ModificarBuscarTurnoCommand;
import presentacion.command.turno.ModificarTurnoCommand;
import presentacion.command.turno.MostrarTurnoCommand;
import presentacion.controladorAplicacion.EventosEmpleado;
import presentacion.controladorAplicacion.EventosGrupo;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.controladorAplicacion.EventosTurno;

public class FactoriaCommandJPAImpl extends FactoriaCommandImpl{
	
	public Command getCommand(int evento) {
		Command comando = null;
		if (evento < 4 || ((evento > 6 && evento < 26))) { 
			comando = super.getCommand(evento);
		}
		else {
			switch (evento) {
			case EventosTurno.ANADIR_TURNO: comando = new AnadirTurnoCommand(); break;
			case EventosTurno.MODIFICAR_TURNO: comando = new ModificarTurnoCommand(); break;
			case EventosTurno.BAJA_TURNO: comando = new BajaTurnoCommand(); break;
			case EventosTurno.MOSTRAR_TURNO: comando = new MostrarTurnoCommand(); break;
			case EventosTurno.LISTAR_TURNO: comando = new ListarTurnosCommand(); break;
			case EventosTurno.MODIFICAR_BUSCAR_TURNO: comando = new ModificarBuscarTurnoCommand(); break;
			case EventosTurno.ANADIR_EMPLEADO_A_TURNO: comando = new AnadirEmpleadoATurnoCommand(); break;
			case EventosTurno.BAJA_EMPLEADO_A_TURNO: comando = new BajaEmpleadoATurnoCommand(); break;
			
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
			
			case EventosMenu.MOSTRAR_TURNO_GUI: comando = new MostrarTurnoGUICommand(); break;			
			case EventosMenu.MOSTRAR_GRUPO_GUI: comando = new MostrarGrupoGUICommand(); break;
			case EventosMenu.MOSTRAR_EMPLEADO_GUI: comando = new MostrarEmpleadoGUICommand(); break;
			}
		}
		return comando;
	}
}
