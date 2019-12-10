package presentacion.command.turno;

import negocio.factoriaSA.FactoriaSA;
import negocio.turno.TurnoSA;
import negocio.turno.TTurno;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosTurno;

public class AnadirTurnoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TTurno turno = (TTurno) datos;
		TurnoSA turnoSA = FactoriaSA.getInstancia().generaTurnoSA();
		
		try{
			int id = turnoSA.insertar(turno);
			mensaje = "El turno se ha registrado con exito. Su id es:" + id;
			return new Context(EventosTurno.ANADIR_TURNO_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosTurno.ANADIR_TURNO_KO, mensaje);
		}
	}
}