package presentacion.command.turno;

import negocio.factoriaSA.FactoriaSA;
import negocio.turno.TurnoSA;
import negocio.turno.TTurno;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosTurno;

public class ModificarTurnoCommand implements Command{
	
	@Override
	public Context execute(Object datos) {
		String mensaje;
		TTurno turno = (TTurno) datos;
		TurnoSA turnoSA = FactoriaSA.getInstancia().generaTurnoSA();
		
		try{
			turnoSA.modificar(turno);
			mensaje = "El turno seleccionado ha sido editado correctamente.";
			return new Context(EventosTurno.MODIFICAR_TURNO_OK, mensaje);

		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosTurno.MODIFICAR_TURNO_KO, mensaje);
		}
	}
}