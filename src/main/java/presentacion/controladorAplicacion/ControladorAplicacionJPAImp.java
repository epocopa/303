package presentacion.controlador;

import presentacion.comando.Comando;
import presentacion.despachadorVista.DespachadorVistaJPA;
import presentacion.factoriaComandos.FactoriaComandos;


public class ControladorAplicacionJPAImp  extends ControladorAplicacionJPA {
	
	@Override
	public void accion(Contexto contexto) {
		Comando comando = FactoriaComandos.obtenerInstancia().obtenerComando(contexto.getEvento());
		contexto = comando.execute(contexto.getDatos());
		DespachadorVistaJPA.obtenerInstancia().crearVista(contexto);	
	}
}
