package presentacion.command.grupo;

import java.util.List;

import negocio.factoriaSA.FactoriaSA;
import negocio.grupo.GrupoSA;
import negocio.grupo.TGrupo;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosGrupo;

public class ListarGrupoCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		GrupoSA grupoSA = FactoriaSA.getInstancia().generaGrupoSA();
		
		try{
			List<TGrupo> listaGrupos = grupoSA.mostrarTodos();
			return new Context(EventosGrupo.LISTAR_GRUPOS_OK, listaGrupos);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosGrupo.LISTAR_GRUPOS_KO, mensaje);
		}
	}
}