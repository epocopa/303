package presentacion.command.cliente;

import presentacion.command.Command;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.EventosCliente;
import negocio.cliente.ClienteSA;
import negocio.factoriaSA.FactoriaSA;
import negocio.cliente.TCliente;

public class ModificarClienteCommand implements Command{

	@Override
	public Context execute(Object datos) {
		TCliente cliente = (TCliente) datos;
		String mensaje;
		ClienteSA clienteSA = FactoriaSA.getInstancia().generaClienteSA();
		try{
			int resultado = clienteSA.modificarCliente(cliente);
			if(resultado == -1){
				mensaje = "No se ha encontrado ningún cliente con el ID introducido.";
				return new Context(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
			}
			else if(resultado == -2){
				mensaje = "No pueden existir dos clientes con el mismo Nombre.";
				return new Context(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
			}
			else{
				mensaje = "El cliente seleccionado ha sido modificado correctamente.";
				return new Context(EventosCliente.MODIFICAR_CLIENTE_OK, mensaje);
			}
		} catch(Exception e){
			mensaje = "No se ha podido conectar con la base de datos.";
			return new Context(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
		}
	}
}