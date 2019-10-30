package presentacion.command.cliente;

import java.util.List;

import negocio.cliente.ClienteSA;
import negocio.cliente.TCliente;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;

public class ListarClientesCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		try{
			List<TCliente> listaClientes = clienteSA.listarClientes();
			if(listaClientes != null){
				return new Context(EventosCliente.LISTAR_CLIENTES_OK, listaClientes);
			}
			else{
				mensaje = "No hay clientes disponibles.";
				return new Context(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
			}
		}catch(Exception e){
			mensaje = "No se ha podido conectar con la base de datos.";
			return new Context(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
		}
	}
}