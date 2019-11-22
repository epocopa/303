package presentacion.command.grupo;

import negocio.factoriaSA.FactoriaSA;
import negocio.grupo.GrupoSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosGrupo;

public class BajaGrupoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		GrupoSA grupoSA = FactoriaSA.getInstancia().generaGrupoSA();
		
		try{
			grupoSA.eliminar(id);
			mensaje = "El grupo de trabajo se ha eliminado con exito.";
			return new Context(EventosGrupo.BAJA_GRUPO_OK, mensaje);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosGrupo.BAJA_GRUPO_KO, mensaje);
		}
	}
}