package test.java.integracion.producto;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class ClienteDAOImpTest {
	private static Connection conn;

	
	@BeforeAll
	static void beforeAll() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trescerotres", "empleado", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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