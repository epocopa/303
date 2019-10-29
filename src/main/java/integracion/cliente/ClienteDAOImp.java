package integracion.cliente;

import integracion.ConnectionFactory;
import negocio.cliente.TCliente;

import java.sql.*;
import java.util.List;

public class ClienteDAOImp implements ClienteDAO{
	private Connection conn;
	//TODO QUERY

	private final String INSERT = "INSERT INTO cliente(nombre, fecha_registro, activo) VALUES(?, ?, ?)";
	private final String READALL = "SELECT * FROM cliente";
	private final String READ = READALL + " WHERE id_cliente = ?";
	private final String UPDATE = "UPDATE cliente SET nombre = ?, WHERE id_cliente = ?";
	private final String DELETE = "UPDATE cliente SET activo = 0 WHERE id_cliente = ?";


	public ClienteDAOImp() {
		this.conn = ConnectionFactory.getInstancia().getConnection();
	}

	public void insertar(TCliente e) throws Exception {
		try (PreparedStatement st = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			st.setString(1, e.getNombre());
			st.setDate(2, Date.valueOf(e.getFecha_registro()));
			st.setBoolean(3, e.isActivo());

			st.executeUpdate();
			try(ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					e.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new Exception("Insertar Cliente -> Error");
		}

	}

	public TCliente mostrar(int id) {
		return null;
	}

	public List<TCliente> mostrarTodos() {
		return null;
	}

	public void modificar(TCliente e) {

	}

	public void eliminar(int id) {

	}
}
