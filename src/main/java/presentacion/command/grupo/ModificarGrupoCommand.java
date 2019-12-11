package presentacion.command.grupo;

import negocio.factoriaSA.FactoriaSA;
import negocio.grupo.GrupoSA;
import negocio.grupo.TGrupo;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosGrupo;

public class ModificarGrupoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		TGrupo grupo = (TGrupo) datos;
		GrupoSA grupoSA = FactoriaSA.getInstancia().generaGrupoSA();
		try{
			grupoSA.modificar(grupo);
			mensaje = "El grupo de trabajo seleccionado ha sido editado correctamente.";
			return new Context(EventosGrupo.MODIFICAR_GRUPO_OK, mensaje);

		} catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosGrupo.MODIFICAR_GRUPO_KO, mensaje);
		}
	}
}