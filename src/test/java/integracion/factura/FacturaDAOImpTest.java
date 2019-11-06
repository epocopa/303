
package integracion.factura;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import integracion.cliente.ClienteDAOImp;
import integracion.producto.ProductoDAO;
import integracion.producto.ProductoDAOImp;
import integracion.transaction.Transaction;
import integracion.transactionManager.TransactionManager;
import negocio.factura.TOAProductoFactura;
import negocio.producto.TProductoCalzado;
import negocio.producto.TProductoTextil;
import org.junit.jupiter.api.*;
import negocio.cliente.TCliente;
import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import negocio.producto.TProducto;

class FacturaDAOImpTest {
	private static Connection conn;
	private TFactura factura1;
	private TFactura factura2;
	private FacturaDAOImp facturaDAOImp;
	private TCliente cliente1;
	private TCliente cliente2;
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
			st.execute("DELETE FROM cliente");
			st.execute("DELETE FROM producto");
			st.execute("DELETE FROM factura");
		}catch(SQLException e) {
			e.printStackTrace();
		}

		// Clientes
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

		try {
			new ClienteDAOImp().insertar(cliente1);
			new ClienteDAOImp().insertar(cliente2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Productos
		producto1 = new TProductoCalzado(0, "Zapatillas", 10, 50, 40, true);
		producto2 = new TProductoTextil(0, "camiseta", 2, 10, "tela", true);

		try {
			new ProductoDAOImp().insertar(producto1);
			new ProductoDAOImp().insertar(producto2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Facturas
		facturaDAOImp = new FacturaDAOImp();
		factura1 = new TFactura();
		factura2 = new TFactura();
		List<TLineaFactura> lineaFacturas = new ArrayList<TLineaFactura>();

		// factura 1
		factura1.setAbierta(true);
		factura1.setCliente(cliente1.getId());
		factura1.setFecha(LocalDate.now());
		lineaFacturas.add(new TLineaFactura(factura1.getId(), producto1.getId(), 1));
		lineaFacturas.add(new TLineaFactura(factura1.getId(), producto2.getId(), 1));
		factura1.setLineaFacturas(lineaFacturas);
		factura1.setPrecio(producto1.getPrecio() + producto2.getPrecio());

		// factura 2
		factura2.setAbierta(true);
		factura2.setCliente(cliente1.getId());
		factura2.setFecha(LocalDate.now());
		factura2.setLineaFacturas(new ArrayList<TLineaFactura>());
		factura2.setPrecio(0);
		 
		
	}
	
	@Test
	void testInsertar() {
		try {
			facturaDAOImp.insertar(factura1);
			TFactura facturaAux=facturaDAOImp.mostrar(factura1.getId());
			assertTrue(iguales(factura1,facturaAux));
	
		} catch (Exception e) {
			fail("Excepcion al insertar");
		}
	}

	@Test
	void testMostrarTodos() {
		List<TFactura> lista= new ArrayList<TFactura>();
		lista.add(factura1);
		lista.add(factura2);
		try {
			for (TFactura tFactura : lista) {
				facturaDAOImp.insertar(tFactura);
			}
	
			List<TFactura> tf = facturaDAOImp.mostrarTodos();
	
			for (int i = 0; i < lista.size(); i++) {
				if (!iguales(lista.get(i), tf.get(i))) {
					fail("La factura leida no se corresponde con la insertada");
				}
			}
		} catch (Exception e) {
			fail("Excepcion al mostrar todos");
		}
	}

	@Test
	void testEliminar() {
		try {
			facturaDAOImp.insertar(factura1);
			facturaDAOImp.eliminar(factura1.getId());
			assertFalse(facturaDAOImp.mostrar(factura1.getId()).isAbierta());	
		} catch (Exception e) {
			fail("Excepcion al eliminar");
		}	
	}

	@Test
	void testAnadirProducto() {
		try {
			facturaDAOImp.insertar(factura2);
			TLineaFactura linea1 = new TLineaFactura(factura2.getId(),producto1.getId(),1);
			facturaDAOImp.anadirProducto(new TOAProductoFactura(linea1, producto1));
			assertNotNull(factura2.getLineaFacturas());
		} catch (Exception e) {
			fail("Excepcion al anadir producto");
		}	
	}

	@AfterEach
	 void afterEach(){
		t.commit();
	}

	@AfterAll
	static void afterAll() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean iguales(TFactura f1, TFactura f2) {
		return f1.getCliente() == f2.getCliente()&&
				f1.getFecha().equals(f2.getFecha())&&
				f1.getId()==f2.getId()&&
				f1.getPrecio()==f2.getPrecio();
	}
}