package presentacion.command.turno;

import negocio.factoriaSA.FactoriaSA;
import negocio.turno.TurnoSA;
import negocio.turno.TTurno;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosTurno;

public class MostrarTurnoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		TurnoSA turnoSA = FactoriaSA.getInstancia().generaTurnoSA();	
		
		try{
			TTurno turno = turnoSA.mostrar(id);
			return new Context(EventosTurno.MOSTRAR_TURNO_OK, turno);

		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosTurno.MOSTRAR_TURNO_KO, mensaje);
		}
	}
}