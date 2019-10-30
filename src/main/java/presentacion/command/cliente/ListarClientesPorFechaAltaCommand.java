package presentacion.command.cliente;

import java.time.LocalDate;
import java.util.List;

import negocio.cliente.ClienteSA;
import negocio.cliente.TCliente;
import negocio.factoriaSA.FactoriaSA;
import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;

public class ListarClientesPorFechaAltaCommand implements Command{

	@Override
	public Context execute(Object datos) {
		String mensaje;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		List<LocalDate> clientesFecha = (List<LocalDate>) datos;
		try{
			List<TCliente> listaClientes = clienteSA.listarClientesPorFecha(clientesFecha);
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