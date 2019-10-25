import integracion.cliente.ClienteDAO;
import integracion.factoriaDAO.FactoriaDAO;
import integracion.factoriaDAO.FactoriaDAOImp;
import negocio.cliente.TCliente;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		//TODO DELETE
		ClienteDAO clienteDAO = FactoriaDAO.getInstancia().getClienteDAO();
		TCliente cliente = new TCliente();
		cliente.setActivo(true);
		cliente.setFecha_registro(LocalDate.now());
		cliente.setNombre("Uno");
		try {
			clienteDAO.insertar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
