package integracion.cliente;

import integracion.transactionManager.TransactionManager;
import negocio.TFecha;
import negocio.cliente.TCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImp implements ClienteDAO {
	private Connection conn;

	private final String INSERT = "INSERT INTO cliente(nombre, fecha_registro, activo) VALUES(?, ?, ?)";
	private final String READALL = "SELECT * FROM cliente";
	private final String READ = READALL + " WHERE id_cliente = ?";
	private final String UPDATE = "UPDATE cliente SET nombre = ?, activo = ? WHERE id_cliente = ?";
	private final String DELETE = "UPDATE cliente SET activo = 0 WHERE id_cliente = ?";
	private final String READFECHA = "SELECT * FROM cliente WHERE fecha_registro BETWEEN ? AND ?";

	public ClienteDAOImp() {
		this.conn = TransactionManager.getInstancia().getTransaction()
				.getConnection();
	}

	public void insertar(TCliente e) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			st.setString(1, e.getNombre());
			st.setDate(2, Date.valueOf(e.getFecha_registro()));
			st.setBoolean(3, e.isActivo());

			st.executeUpdate();
			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					e.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new Exception("Insertar Cliente -> Error");
		}
	}

	public TCliente mostrar(int id) throws Exception {
		TCliente c = null;
		try (PreparedStatement st = conn.prepareStatement(READ)) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					c = new TCliente(id, rs.getBoolean("activo"), rs.getDate(
							"fecha_registro").toLocalDate(),
							rs.getString("nombre"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mostrar Cliente -> Error");
		}
		return c;
	}

	public List<TCliente> mostrarTodos() throws Exception {
		ArrayList<TCliente> lista = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(READALL);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				lista.add(new TCliente(rs.getInt("id_cliente"), rs
						.getBoolean("activo"), rs.getDate("fecha_registro")
						.toLocalDate(), rs.getString("nombre")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Mostrar Clientes -> Error");
		}
		return lista;
	}

	public void modificar(TCliente e) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(UPDATE)) {
			st.setString(1, e.getNombre());
			st.setBoolean(2, e.isActivo());
			st.setInt(3, e.getId());
			st.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new Exception("Modificar Cliente -> Error");
		}
	}

	public void eliminar(int id) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(DELETE)) {
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Eliminar Cliente -> Error");
		}
	}

	public List<TCliente> listarClientesPorFecha(TFecha fecha) throws Exception {
		ArrayList<TCliente> lista = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(READFECHA)) {
			st.setDate(1, Date.valueOf(fecha.getFechaIni()));
			st.setDate(2, Date.valueOf(fecha.getFechaFin()));
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					lista.add(new TCliente(rs.getInt("id_cliente"), rs
							.getBoolean("activo"), rs.getDate("fecha_registro")
							.toLocalDate(), rs.getString("nombre")));
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Mostrar Clientes por fecha -> Error");
			}
			return lista;
		}
	}
}
