package integracion.producto;

import integracion.transactionManager.TransactionManager;
import negocio.producto.TProducto;
import negocio.producto.TProductoCalzado;
import negocio.producto.TProductoTextil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImp implements ProductoDAO {
	private Connection conn;

	private final String INSERT = "INSERT INTO producto(nombre, cantidad, precio, activo) VALUES(?, ?, ?, ?)";
	private final String INSERTCALZADO = "INSERT INTO producto_calzado(id_producto, numero) VALUES(?, ?)";
	private final String INSERTTEXTIL = "INSERT INTO producto_textil(id_producto, tejido) VALUES(?, ?)";

	private final String READALL = "SELECT * FROM producto LEFT JOIN producto_calzado USING(id_producto) " +
			"LEFT JOIN producto_textil USING(id_producto)";
	private final String READ = READALL + " WHERE id_producto = ?";
	private final String READBYNAME = READALL + " WHERE nombre = ?";

	private final String UPDATE = "UPDATE producto SET nombre = ?, cantidad = ?, precio = ? WHERE id_producto = ?";
	private final String UPDATECALZADO = "UPDATE producto_calzado SET numero = ? WHERE id_producto = ?";
	private final String UPDATETEXTIL = "UPDATE producto_textil SET tejido = ? WHERE id_producto = ?";

	private final String DELETE = "UPDATE producto SET activo = 0 WHERE id_producto = ?";

	public ProductoDAOImp() {
		this.conn =  TransactionManager.getInstancia().getTransaction().getConnection();
	}

	@Override
	public void insertar(TProducto e) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			st.setString(1, e.getNombre());
			st.setInt(2, e.getCantidad());
			st.setDouble(3, e.getPrecio());
			st.setBoolean(4, e.isActivo());

			st.executeUpdate();
			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					e.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new Exception("Insertar Producto -> Error");
		}

		if (e.isCalzado()) {
			TProductoCalzado productoCalzado = (TProductoCalzado) e;
			try (PreparedStatement ste = conn.prepareStatement(INSERTCALZADO, PreparedStatement.RETURN_GENERATED_KEYS)) {
				ste.setInt(1, productoCalzado.getId());
				ste.setInt(2, productoCalzado.getNumero());
				ste.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new Exception("Insertar ProductInsertaro Calzado -> Error");
			}
		} else {
			TProductoTextil productoTextil = (TProductoTextil) e;
			try (PreparedStatement ste = conn.prepareStatement(INSERTTEXTIL, PreparedStatement.RETURN_GENERATED_KEYS)) {
				ste.setInt(1, productoTextil.getId());
				ste.setString(2, productoTextil.getTejido());
				ste.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new Exception("Insertar Producto Textil -> Error");
			}
		}
	}

	@Override
	public TProducto mostrar(int id) throws Exception {
		TProducto p = null;
		try (PreparedStatement st = conn.prepareStatement(READ)) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					int numero = rs.getInt("numero");
					if (rs.wasNull()) {
						p = new TProductoTextil(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getInt("cantidad"), rs.getDouble("precio"), rs.getString("tejido"),
								rs.getBoolean("activo"));
					} else {
						p = new TProductoCalzado(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getInt("cantidad"), rs.getDouble("precio"), rs.getInt("numero"),
								rs.getBoolean("activo"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mostrar Producto -> Error");
		}
		return p;
	}

	@Override
	public List<TProducto> mostrarTodos() throws Exception {
		ArrayList<TProducto> lista = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(READALL); ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				int numero = rs.getInt("numero");
				if (rs.wasNull()) {
					lista.add(new TProductoTextil(rs.getInt("id_producto"), rs.getString("nombre"),
							rs.getInt("cantidad"), rs.getDouble("precio"), rs.getString("tejido"),
							rs.getBoolean("activo")));
				} else {
					lista.add(new TProductoCalzado(rs.getInt("id_producto"), rs.getString("nombre"),
							rs.getInt("cantidad"), rs.getDouble("precio"), rs.getInt("numero"),
							rs.getBoolean("activo")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mostrar Productos -> Error");
		}
		return lista;
	}

	@Override
	public void modificar(TProducto e) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(UPDATE)) {
			st.setString(1, e.getNombre());
			st.setInt(2, e.getCantidad());
			st.setDouble(3, e.getPrecio());
			st.setInt(4, e.getId());
			st.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new Exception("Modificar Producto -> Error");
		}

		if (e.isCalzado()) {
			TProductoCalzado productoCalzado = (TProductoCalzado) e;
			try (PreparedStatement ste = conn.prepareStatement(UPDATECALZADO, PreparedStatement.RETURN_GENERATED_KEYS)) {
				ste.setInt(1, productoCalzado.getNumero());
				ste.setInt(2, productoCalzado.getId());
				ste.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new Exception("Modificar Producto Calzado -> Error");
			}
		} else {
			TProductoTextil productoTextil = (TProductoTextil) e;
			try (PreparedStatement ste = conn.prepareStatement(UPDATETEXTIL, PreparedStatement.RETURN_GENERATED_KEYS)) {
				ste.setString(1, productoTextil.getTejido());
				ste.setInt(2, productoTextil.getId());
				ste.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new Exception("Modificar Producto Textil -> Error");
			}
		}

	}

	@Override
	public void eliminar(int id) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(DELETE)) {
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Eliminar Producto -> Error");
		}
	}

	@Override
	public TProducto mostrarPorNombre(String nombre) throws Exception {
		TProducto p = null;
		try (PreparedStatement st = conn.prepareStatement(READBYNAME)) {
			st.setString(1, nombre);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					int numero = rs.getInt("numero");
					if (rs.wasNull()) {
						p = new TProductoTextil(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getInt("cantidad"), rs.getDouble("precio"), rs.getString("tejido"),
								rs.getBoolean("activo"));
					} else {
						p = new TProductoCalzado(rs.getInt("id_producto"), rs.getString("nombre"),
								rs.getInt("cantidad"), rs.getDouble("precio"), rs.getInt("numero"),
								rs.getBoolean("activo"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mostrar Producto -> Error");
		}
		return p;
	}
}
