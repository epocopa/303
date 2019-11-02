package negocio.cliente;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integracion.cliente.ClienteDAOImp;

class ClienteSAImpTest {

	private static Connection conn;
	private TCliente cliente1;
	private TCliente cliente2;
	private ClienteSAImp clienteSAImp;
	
	@BeforeAll
	static void beforeAll() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trescerotres", "empleado", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeEach
	void BeforeEach() {
		try(Statement st=conn.createStatement()){
			st.execute("DELETE FROM cliente");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		 clienteSAImp =new ClienteSAImp();
		 cliente1 = new TCliente();
		 cliente2 = new TCliente();
		 
		 // Cliente 1
		 cliente1.setActivo(true);
		 cliente1.setFecha_registro(LocalDate.now());
		 cliente1.setNombre("Jose");
		 //Cliente 2
		 cliente2.setActivo(true);
		 cliente2.setFecha_registro(LocalDate.now());
		 cliente2.setNombre("Dani");
	}
	
	@Test
	void testInsertar() {
		fail("Not yet implemented");
	}

	@Test
	void testMostrar() {
		fail("Not yet implemented");
	}

	@Test
	void testMostrarTodos() {
		fail("Not yet implemented");
	}

	@Test
	void testModificar() {
		fail("Not yet implemented");
	}

	@Test
	void testEliminar() {
		fail("Not yet implemented");
	}
}
