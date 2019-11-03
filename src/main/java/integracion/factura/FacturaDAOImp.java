package integracion.factura;

import integracion.transactionManager.TransactionManager;
import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import negocio.producto.TProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAOImp implements FacturaDAO {

	private Connection conn;

	private final String INSERT = "INSERT INTO factura(cliente, fecha, activo) VALUES(?, ?, ?)";
	private final String READALL = "SELECT * FROM factura";
	private final String READ = READALL + " WHERE id_factura = ?";
	private final String UPDATE = "UPDATE factura SET precio = ? WHERE id_factura = ?";
	private final String DELETE = "UPDATE factura SET activo = 0 WHERE id_cliente = ?";
	private final String READPRODCUTOSDEFACTURA = "SELECT * FROM lista WHERE factura = ?";
	private final String READLISTA = "SELECT * FROM lista WHERE factura = ? AND producto = ?";
	private final String INSERTLISTA = "INSERT INTO lista VALUES (?,?,?,?)";
	private final String UPDATECANTIDADLISTA = "UPDATE lista SET cantidad = ? WHERE factura = ? AND producto = ?";
	private final String UPDATECANTIDADPRODUCTO = "UPDATE producto SET cantidad = ? WHERE id_producto = ?";

	public FacturaDAOImp() {
		this.conn =  TransactionManager.getInstancia().getTransaction().getConnection();
	}

	@Override
	public void insertar(TFactura e) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			st.setInt(1, e.getCliente());
			st.setDate(2, Date.valueOf(e.getFecha()));
			st.setBoolean(3, e.isActivo());

			st.executeUpdate();
			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					e.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new Exception("Insertar Factura -> Error");
		}
	}

	@Override
	public TFactura mostrar(int id) throws Exception {
		TFactura t = null;
		List<TLineaFactura> lista = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(READ)) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					t = new TFactura(id, rs.getDouble("precio"), rs.getBoolean("activo"),
							rs.getDate("fecha_").toLocalDate(), lista, rs.getInt("cliente"));
				}
				try (PreparedStatement ste = conn.prepareStatement(READPRODCUTOSDEFACTURA)) {
					ste.setInt(1, id);
					try (ResultSet rse = ste.executeQuery()) {
						while (rse.next()) {
							lista.add(new TLineaFactura(rse.getInt("factura"), rse.getInt("producto"), rse.getInt("cantidad")));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mostrar Factura -> Error");
		}
		return t;
	}

	@Override
	public List<TFactura> mostrarTodos() {
		ArrayList<TFactura> lista = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(READALL); ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				lista.add(new TFactura(rs.getInt("id_factura"), rs.getDouble("precio"), rs.getBoolean("activo"),
						rs.getDate("fecha_").toLocalDate(), new ArrayList<>(), rs.getInt("cliente")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void modificar(TFactura e) throws Exception {
		throw new Exception("Operation Not Available");
	}

	@Override
	public void eliminar(int id) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(DELETE)) {
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Eliminar Factura -> Error");
		}
	}

	@Override
	public void anadirProducto(TLineaFactura linea, TProducto p) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(READLISTA)) {
			st.setInt(1, linea.getFactura());
			st.setInt(2, linea.getProducto());
			try (ResultSet rs = st.executeQuery()) {
				if (!rs.next()) {//Insertamos linea o actualizamos cantidad
					try (PreparedStatement ste = conn.prepareStatement(INSERTLISTA)) {
						ste.setInt(1, linea.getFactura());
						ste.setInt(2, linea.getProducto());
						ste.setInt(3, linea.getCantidad());
						ste.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new Exception("Anadir Producto Factura -> Error");
					}
				} else {
					try (PreparedStatement ste = conn.prepareStatement(UPDATECANTIDADLISTA)) {
						ste.setInt(1, linea.getCantidad() + rs.getInt("cantidad"));
						ste.setInt(2, linea.getFactura());
						ste.setInt(3, linea.getProducto());
						ste.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new Exception("Anadir Producto Factura -> Error");
					}
				}
				//Actualizamos la cantidad en la tabla de productos
				try (PreparedStatement ste = conn.prepareStatement(UPDATECANTIDADPRODUCTO)) {
					ste.setInt(1, p.getCantidad() - linea.getCantidad());
					ste.setInt(2, linea.getProducto());
					ste.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//Actualizamos total factura
				try (PreparedStatement ste = conn.prepareStatement(READ)) {
					ste.setInt(1, linea.getFactura());
					try (ResultSet rse = ste.executeQuery()) {
						if (rse.next()) {
							double precio = rse.getDouble("precio");
							try (PreparedStatement ste2 = conn.prepareStatement(UPDATE)) {
								ste2.setDouble(1, precio + (linea.getCantidad() * p.getPrecio()));
								ste2.setInt(2, linea.getFactura());
								ste2.executeUpdate();
							} catch (SQLException e) {
								e.printStackTrace();
								throw new Exception("Anadir Producto Factura -> Error");
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Anadir Producto Factura -> Error");
		}
	}
}
