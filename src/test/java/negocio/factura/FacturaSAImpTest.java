package negocio.factura;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integracion.cliente.ClienteDAOImp;
import integracion.producto.ProductoDAOImp;
import negocio.cliente.ClienteSAImp;
import negocio.cliente.TCliente;
import negocio.producto.ProductoSAImp;
import negocio.producto.TProducto;


class FacturaSAImpTest {
	private static Connection conn;
	private FacturaSAImp facturaSAImp;
	private TFactura factura1;
	private TFactura factura2;
	private TCliente cliente1;
	private TCliente cliente2;
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
		 
		 		//Productos
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
		
		
		// Facturas
		facturaSAImp =new FacturaSAImp();
		factura1 = new TFactura();
		List<TLineaFactura> lineaFacturas =new ArrayList<TLineaFactura>();
		 
		// factura 1
		factura1.setActivo(true);
		factura1.setCliente(cliente1.getId());
		factura1.setFecha(LocalDate.now());
		lineaFacturas.add(new TLineaFactura(factura1.getId(),producto1.getId(),1));
		lineaFacturas.add(new TLineaFactura(factura1.getId(),producto2.getId(),1));
		factura1.setLineaFacturas(lineaFacturas);
		factura1.setPrecio(producto1.getPrecio()+producto2.getPrecio());
		 
		// factura 2
		factura2.setActivo(true);
		factura2.setCliente(cliente1.getId());
		factura2.setFecha(LocalDate.now());
		factura2.setLineaFacturas( new ArrayList<TLineaFactura>());
		factura2.setPrecio(0);
		 
		
	}
	
	@Test
	void testInsertar() {
		try {
			facturaSAImp.insertar(factura1);
			TFactura facturaAux=facturaSAImp.mostrar(factura1.getId());
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
				facturaSAImp.insertar(tFactura);
			}
	
			List<TFactura> tf = facturaSAImp.mostrarTodos();
	
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
	void testModificar() {
		try {
			facturaSAImp.insertar(factura1);
	
			factura1.setActivo(true);
			factura1.setCliente(factura1.getId());
	
			facturaSAImp.modificar(factura1);
			TFactura facturaMod = facturaSAImp.mostrar(factura1.getId());
	
			assertTrue(iguales(factura1, facturaMod));
		} catch (Exception e) {
			fail("Excepcion al modificar");
		}	}

	@Test
	void testEliminar() {
		try {
			facturaSAImp.insertar(factura1);
			facturaSAImp.eliminar(factura1.getId());
			assertFalse(facturaSAImp.mostrar(factura1.getId()).isActivo());	
		} catch (Exception e) {
			fail("Excepcion al eliminar");
		}	
	}

	@Test
	void testAnadirProducto() {
		try {
			facturaSAImp.insertar(factura2);
			TLineaFactura linea1 = new TLineaFactura(factura2.getId(),producto1.getId(),1);
			facturaSAImp.anadirProducto(linea1,producto1);	
			assertNotNull(factura2.getLineaFacturas());
		} catch (Exception e) {
			fail("Excepcion al anadir producto");
		}	
	}
	
	private boolean iguales(TFactura f1, TFactura f2) {
		return f1.getCliente()==f2.getCliente()&&
				f1.getFecha()==f2.getFecha()&&
				f1.getId()==f2.getId()&&
				f1.getPrecio()==f2.getPrecio()&&
				f1.getLineaFacturas()==f2.getLineaFacturas();
	}
}
