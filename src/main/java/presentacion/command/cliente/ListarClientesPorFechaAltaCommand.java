package presentacion.command.cliente;

import java.util.List;

import negocio.cliente.ClienteSA;
import negocio.cliente.TCliente;
import negocio.TFecha;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;

public class ListarClientesPorFechaAltaCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		TFecha clientesFecha = (TFecha) datos;
		try{
			List<TCliente> listaClientes = clienteSA.listarClientesPorFecha(clientesFecha);
			return new Context(EventosCliente.LISTAR_CLIENTES_OK, listaClientes);
		}catch(Exception e){
			mensaje = e.getMessage();
			return new Context(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
		}
	}
}