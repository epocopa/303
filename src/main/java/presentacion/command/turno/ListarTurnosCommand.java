package presentacion.command.turno;

import java.util.List;

import negocio.factoriaSA.FactoriaSA;
import negocio.turno.TurnoSA;
import negocio.turno.TTurno;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosTurno;

public class ListarTurnosCommand implements Command{
	
	@Override
	public Context execute(Object datos) {
		String mensaje;
		TurnoSA turnoSA = FactoriaSA.getInstancia().generaTurnoSA();
		
		try{
			List<TTurno> listaTurnos = turnoSA.mostrarTodos();
			return new Context(EventosTurno.LISTAR_TURNO_OK, listaTurnos);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosTurno.LISTAR_TURNO_KO, mensaje);
		}
	}
}