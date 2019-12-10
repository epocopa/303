package presentacion.command.turno;

import negocio.empleado.TEmpleado;
import negocio.factoriaSA.FactoriaSA;
import negocio.turno.TurnoSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosTurno;

public class AnadirEmpleadoATurnoCommand implements Command{
	@Override
	public Context execute(Object datos) {
		String mensaje;
		TEmpleado empleado = (TEmpleado) datos;
		TurnoSA turnoSA = FactoriaSA.getInstancia().generaTurnoSA();
		
		try{
			turnoSA.insertarEmpleado(empleado.getIdTurno() ,empleado);
			mensaje = "El empleado se ha anadido con exito.";
			return new Context(EventosTurno.ANADIR_EMPLEADO_A_TURNO_OK, mensaje);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosTurno.ANADIR_EMPLEADO_A_TURNO_KO, mensaje);
		}
	}
}