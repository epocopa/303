package integracion.producto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
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
			st.execute("DELETE FROM producto");
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
		try {
			productoDAOImp.insertar(producto1);
			TProducto productoAux=productoDAOImp.mostrar(producto1.getId());
			assertTrue(iguales(producto1,productoAux));
	
		} catch (Exception e) {
			fail("Excepcion al insertar");
		}	}

	@Test
	void testMostrarTodos() {
		List<TProducto> lista= new ArrayList<TProducto>();
		lista.add(producto1);
		lista.add(producto2);
		try {
			for (TProducto tProducto : lista) {
				productoDAOImp.insertar(tProducto);
			}
	
			List<TProducto> tp = productoDAOImp.mostrarTodos();
	
			for (int i = 0; i < lista.size(); i++) {
				if (!iguales(lista.get(i), tp.get(i))) {
					fail("El producto leido no se corresponde con el insertado");
				}
			}
		} catch (Exception e) {
			fail("Excepcion al mostrar todos");
		}	}

	@Test
	void testModificar() {
		try {
			productoDAOImp.insertar(producto1);
			
			producto1.setCalzado(false);
			producto1.setCantidad(70);
			producto1.setPrecio(12f);
			producto1.setNombre("producto modificado");
	
			productoDAOImp.modificar(producto1);
			TProducto productoMod = productoDAOImp.mostrar(producto1.getId());
	
			assertTrue(iguales(producto1, productoMod));
		} catch (Exception e) {
			fail("Excepcion al modificar");
		}
	}	

	@Test
	void testEliminar() {
		try {
			productoDAOImp.insertar(producto1);
	
			productoDAOImp.eliminar(producto1.getId());
			assertFalse(productoDAOImp.mostrar(producto1.getId()).isActivo());	
		} catch (Exception e) {
			fail("Excepcion al eliminar");
		}
		}
	
	@AfterAll
	static void afterAll() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean iguales(TProducto p1, TProducto p2) {
		return p1.getCantidad()==p2.getCantidad()&&
				p1.getId()==p2.getCantidad()&&
				p1.getNombre()==p2.getNombre()&&
				p1.getPrecio()==p2.getPrecio();
	}

}