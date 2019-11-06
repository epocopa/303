package negocio.producto;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ProductoSAImpTest {
	private static Connection conn;
	private ProductoSAImp productoSAImp;
	private TProducto producto1;
	private TProducto producto2;

	private static Transaction t;

	@BeforeAll
	static void beforeAll() {
		t = TransactionManager.getInstancia().createTransaction();
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
		
		productoSAImp =new ProductoSAImp();
		producto1 = new TProductoCalzado(0, "Zapatillas", 10, 50, 40, true);
		producto2 = new TProductoTextil(0, "camiseta", 2, 10, "tela", true);
	}
	
	@Test
	void testInsertar() {
		try {
			productoSAImp.insertar(producto1);
			TProducto productoAux=productoSAImp.mostrar(producto1.getId());
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
				productoSAImp.insertar(tProducto);
			}
	
			List<TProducto> tp = productoSAImp.mostrarTodos();
	
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
			productoSAImp.insertar(producto1);
			
			producto1.setCantidad(70);
			producto1.setPrecio(12f);
			producto1.setNombre("producto modificado");
	
			productoSAImp.modificar(producto1);
			TProducto productoMod = productoSAImp.mostrar(producto1.getId());
	
			assertTrue(iguales(producto1, productoMod));
		} catch (Exception e) {
			fail("Excepcion al modificar");
		}
	}	

	@Test
	void testEliminar() {
		try {
			productoSAImp.insertar(producto1);
	
			productoSAImp.eliminar(producto1.getId());
			assertFalse(productoSAImp.mostrar(producto1.getId()).isActivo());	
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
				p1.getId()==p2.getId()&&
				p1.getNombre().equals(p2.getNombre()) &&
				p1.getPrecio()==p2.getPrecio();
	}
}
