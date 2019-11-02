package negocio.factura;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FacturaSAImpTest {
	private static Connection conn;
	private TFactura factura1;
	private FacturaSAImp facturaSAImp;
	
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
		
		facturaSAImp =new FacturaSAImp();
		 factura1 = new TFactura();
		 
		 // factura 1
		 
		 
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
