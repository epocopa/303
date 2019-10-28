package main.java;


import main.java.integracion.cliente.ClienteDAO;
import main.java.integracion.factoriaDAO.FactoriaDAO;
//import main.java.integracion.factoriaDAO.FactoriaDAOImp;
import main.java.negocio.cliente.TCliente;

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
