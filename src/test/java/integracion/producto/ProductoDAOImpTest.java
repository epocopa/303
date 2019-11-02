package integracion.producto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import negocio.producto.TProducto;

class ProductoDAOImpTest {
	private static Connection conn;
	private ProductoDAOImp productoDAOImp;
	private TProducto producto1;
	private TProducto producto2;
	
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
		
		productoDAOImp =new ProductoDAOImp();
		producto1 = new TProducto();
		producto2 = new TProducto();
		 
		 // Producto 1
		producto1.setCalzado(false);
		producto1.setCantidad(10);
		producto1.setPrecio(5f);
		producto1.setNombre("camiseta");
		
		// Producto 2
		producto2.setCalzado(false);
		producto2.setCantidad(20);
		producto2.setPrecio(12f);
		producto2.setNombre("polo");
		
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