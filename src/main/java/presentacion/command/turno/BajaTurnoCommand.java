package presentacion.command.turno;

import negocio.factoriaSA.FactoriaSA;
import negocio.turno.TurnoSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosTurno;

public class BajaTurnoCommand implements Command{
	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		TurnoSA turnoSA = FactoriaSA.getInstancia().generaTurnoSA();
		
		try{
			turnoSA.eliminar(id);
			mensaje = "El turno se ha eliminado con exito.";
			return new Context(EventosTurno.BAJA_TURNO_OK, mensaje);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosTurno.BAJA_TURNO_KO, mensaje);
		}
	}
}
