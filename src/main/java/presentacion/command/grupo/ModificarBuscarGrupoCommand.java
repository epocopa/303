package presentacion.command.grupo;

import negocio.factoriaSA.FactoriaSA;
import negocio.grupo.GrupoSA;
import negocio.grupo.TGrupo;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosGrupo;

public class ModificarBuscarGrupoCommand implements Command {

	@Override
	public Context execute(Object datos) {
		String mensaje;
		int id = (int) datos;
		GrupoSA grupoSA = FactoriaSA.getInstancia().generaGrupoSA();

		try {
			TGrupo grupo = grupoSA.mostrar(id);
			return new Context(EventosGrupo.MODIFICAR_BUSCAR_GRUPO_OK, grupo);
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new Context(EventosGrupo.MODIFICAR_BUSCAR_GRUPO_KO, mensaje);
		}
	}
}