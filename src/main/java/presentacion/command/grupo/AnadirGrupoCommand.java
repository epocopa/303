package presentacion.command.grupo;

import negocio.factoriaSA.FactoriaSA;
import negocio.grupo.GrupoSA;
import negocio.grupo.TGrupo;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosGrupo;

public class AnadirGrupoCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TGrupo grupo = (TGrupo) datos;
		GrupoSA grupooSA = FactoriaSA.getInstancia().generaGrupoSA();
		
		try{
			grupooSA.insertar(grupo);
			mensaje = "El grupo de trabajo se ha registrado con exito. Su id es:" + grupo.getId();
			return new Context(EventosGrupo.ANADIR_GRUPO_OK, mensaje);
		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosGrupo.ANADIR_GRUPO_KO, mensaje);

		}
	}
}