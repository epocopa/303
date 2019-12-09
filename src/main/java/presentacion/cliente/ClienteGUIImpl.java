package presentacion.cliente;

import negocio.cliente.TCliente;
import negocio.TFecha;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.ControladorAplicacion;
import presentacion.controladorAplicacion.EventosCliente;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.factoria.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ClienteGUIImpl extends JPanel implements ClienteGUI, GUI {
	private static final long serialVersionUID = 1L;

	private String name = "CLIENTES";

	private CardLayout _localCL;
	private JPanel _homePanel;
	private JPanel _currentPanel = _homePanel;

	private static FactoriaPresentacion presentacion = FactoriaPresentacion
			.getInstance();

	private JPanel pathPanel = presentacion.generarPath();
	List<Component> _lastPathComponents = new LinkedList<Component>();

	// --- COMPONENTES ---//

	private JPanel anadirOutputArea;
	private JLabel anadirOutputLabel;

	private JPanel borrarOutputArea;
	private JLabel borrarOutputLabel;

	private JPanel pedirFechasOutputArea;
	private JLabel pedirFechasOutputLabel;

	private JPanel mostrarClientePanel;
	private CardLayout mostrarClientePanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarNombreText;
	private JLabel mostrarActivoText;
	private JLabel mostrarFechaText;

	private DefaultTableModel mostrarModel;

	private JPanel editarClientePanel;
	private CardLayout editarClientePanelCL;
	private JPanel editarBuscarErrorArea;
	private JLabel editarBuscarErrorLabel;
	private JPanel editarOutputArea;
	private JLabel editarOutputLabel;
	private JTextField editarNombreField;
	private JTextField editarActivoField;
	private JTextField editarFechaField;

	public ClienteGUIImpl() {
		initialize();
	}

	public void initialize() {
		_localCL = new CardLayout();
		setLayout(_localCL);
		setBackground(new Color(235, 237, 241));
		setMaximumSize(new Dimension(1024, 460));

		_homePanel = new JPanel(new GridBagLayout());
		_homePanel.setBackground(new Color(235, 237, 241));
		_homePanel.setMaximumSize(new Dimension(1024, 460));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);

		JButton anadirBtn = createMenuButton(
				"resources/icons/clientes/anadir-cliente.png", new Color(91,
						155, 213));
		anadirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("ANADIR CLIENTE");
				anadirPanel();
			}
		});
		_homePanel.add(anadirBtn, c);

		c.gridx++;
		JButton editarBtn = createMenuButton(
				"resources/icons/clientes/editar-cliente.png", new Color(255,
						192, 0));
		editarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("EDITAR CLIENTE");
				editarPanel();
				;
			}
		});
		_homePanel.add(editarBtn, c);

		c.gridx++;
		JButton bajaBtn = createMenuButton(
				"resources/icons/clientes/baja-cliente.png", new Color(112,
						173, 71));
		bajaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("ELIMINAR CLIENTE");
				bajaPanel();
			}
		});
		_homePanel.add(bajaBtn, c);

		c.gridx = 0;
		c.gridy++;
		JButton listarBtn = createMenuButton(
				"resources/icons/clientes/mostrar-clientes.png", new Color(234,
						80, 54));
		listarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Context contexto = new Context(EventosCliente.LISTAR_CLIENTES,
						null);
				ControladorAplicacion.getInstance().accion(contexto);
			}
		});
		_homePanel.add(listarBtn, c);

		c.gridx++;
		JButton buscarBtn = createMenuButton(
				"resources/icons/clientes/buscar-cliente.png", new Color(0,
						112, 192));
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("BUSCAR CLIENTE");
				buscarPanel();
			}
		});
		_homePanel.add(buscarBtn, c);

		c.gridx++;
		JButton clientesPorFechaBtn = createMenuButton(
				"resources/icons/clientes/clientes-por-fechas.png", new Color(
						0, 176, 80));
		clientesPorFechaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("LISTAR POR FECHA");
				clientesPorFechaPanel();
			}
		});
		_homePanel.add(clientesPorFechaBtn, c);

		add(_homePanel, name);
	}

	public String getName() {
		return name;
	}

	public void createPathButton(String name) {
		JButton pathBtn = new JButton(name);
		pathBtn.setFocusPainted(false);
		pathBtn.setBorderPainted(false);
		pathBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		pathBtn.setBackground(new Color(230, 230, 230));
		pathBtn.setMaximumSize(new Dimension(220, 50));
		_lastPathComponents.add(pathBtn);
		pathPanel.add(pathBtn);
	}

	public void addPathSeparator() {
		JLabel pathSeparator = new JLabel(">", SwingConstants.CENTER);
		pathSeparator.setOpaque(false);
		pathSeparator.setFont(new Font("Arial", Font.BOLD, 22));
		pathSeparator.setMaximumSize(new Dimension(50, 50));
		_lastPathComponents.add(pathSeparator);
		pathPanel.add(pathSeparator);
	}

	public JButton createMenuButton(String iconPath, Color color) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(220, 140));
		button.setBackground(color);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setIcon(new ImageIcon(iconPath));

		return button;
	}

	public void showOutputMsg(JPanel area, JLabel text, String msg, Boolean ok) {
		text.setText(msg);
		if (ok == true) {
			area.setBackground(new Color(37, 183, 50));
		} else {
			area.setBackground(new Color(172, 40, 40));
		}
		area.setVisible(true);

	}

	public void anadirPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));

		anadirOutputArea = new JPanel();
		anadirOutputArea.setLayout(new BoxLayout(anadirOutputArea,
				BoxLayout.X_AXIS));
		anadirOutputArea.setBackground(new Color(172, 40, 40));
		anadirOutputArea.setMaximumSize(new Dimension(800, 50));

		anadirOutputLabel = new JLabel("ERROR: El ID introducido no es valido.");
		anadirOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		anadirOutputLabel.setForeground(new Color(230, 230, 230));

		anadirOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		anadirOutputArea.add(anadirOutputLabel);
		anadirOutputArea.setVisible(false);
		outputPanel.add(anadirOutputArea);

		// --

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(nombreLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField nombreField = new JTextField(15);
		formPanel.add(nombreField, c);

		// --

		JButton enviarBtn = new JButton("ENVIAR");
		enviarBtn.setFocusPainted(false);
		enviarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		enviarBtn.setBackground(new Color(230, 230, 230));
		enviarBtn.setMaximumSize(new Dimension(125, 30));
		enviarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		enviarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCliente cliente = new TCliente();
				cliente.setNombre(nombreField.getText());
				cliente.setFecha_registro(LocalDate.now());
				cliente.setActivo(true);
				if (cliente.getNombre().length() > 0
						&& !cliente.getNombre().equals(" ")) {
					Context contexto = new Context(
							EventosCliente.ANADIR_CLIENTE, cliente);
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(anadirOutputArea, anadirOutputLabel,
							"ERROR: El nombre introducido no es valido.", false);
				}
			}
		});

		// --

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 60)));
		panel.add(formPanel);
		panel.add(enviarBtn);

		// --

		add(panel, "ANADIR");
		_localCL.show(this, "ANADIR");
		_currentPanel = panel;
	}

	public void editarPanel() {
		editarClientePanel = new JPanel();
		editarClientePanelCL = new CardLayout();
		editarClientePanel.setLayout(editarClientePanelCL);
		editarClientePanel.setBackground(new Color(235, 237, 241));
		editarClientePanel.setMaximumSize(new Dimension(1024, 460));

		JPanel buscarPanel = new JPanel();
		buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
		buscarPanel.setBackground(new Color(235, 237, 241));
		buscarPanel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));

		editarBuscarErrorArea = new JPanel();
		editarBuscarErrorArea.setLayout(new BoxLayout(editarBuscarErrorArea,
				BoxLayout.X_AXIS));
		editarBuscarErrorArea.setBackground(new Color(172, 40, 40));
		editarBuscarErrorArea.setMaximumSize(new Dimension(800, 50));

		editarBuscarErrorLabel = new JLabel(
				"ERROR: El ID introducido no es valido.");
		editarBuscarErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		editarBuscarErrorLabel.setForeground(new Color(230, 230, 230));

		editarBuscarErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		editarBuscarErrorArea.add(editarBuscarErrorLabel);
		editarBuscarErrorArea.setVisible(false);
		errorPanel.add(editarBuscarErrorArea);

		// --

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel IDLabel = new JLabel("ID Cliente: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);

		// --

		JPanel editarPanel = new JPanel();
		editarPanel.setLayout(new BoxLayout(editarPanel, BoxLayout.Y_AXIS));
		editarPanel.setBackground(new Color(235, 237, 241));
		editarPanel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));

		editarOutputArea = new JPanel();
		editarOutputArea.setLayout(new BoxLayout(editarOutputArea,
				BoxLayout.X_AXIS));
		editarOutputArea.setBackground(new Color(172, 40, 40));
		editarOutputArea.setMaximumSize(new Dimension(800, 50));

		editarOutputLabel = new JLabel(
				"ERROR: El nombre introducido no es valido.");
		editarOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		editarOutputLabel.setForeground(new Color(230, 230, 230));

		editarOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		editarOutputArea.add(editarOutputLabel);
		editarOutputArea.setVisible(false);
		outputPanel.add(editarOutputArea);

		// --

		JPanel formPanel2 = new JPanel(new GridBagLayout());
		formPanel2.setBackground(new Color(235, 237, 241));
		formPanel2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel2.setMaximumSize(new Dimension(1024, 120));

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5, 5, 0, 0);
		JLabel editarNombreLabel = new JLabel("Nombre: ");
		editarNombreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(editarNombreLabel, c2);

		c2.gridy++;
		JLabel editarActivoLabel = new JLabel("Activo: ");
		editarActivoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(editarActivoLabel, c2);

		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		editarNombreField = new JTextField(15);
		formPanel2.add(editarNombreField, c2);

		c2.gridy++;
		editarActivoField = new JTextField(15);
		formPanel2.add(editarActivoField, c2);

		// --

		JButton buscarBtn = new JButton("BUSCAR");
		buscarBtn.setFocusPainted(false);
		buscarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		buscarBtn.setBackground(new Color(230, 230, 230));
		buscarBtn.setMaximumSize(new Dimension(125, 30));
		buscarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDField.getText();
				if (ID.length() > 0 && !ID.equals(" ")) {
					;
					Context contexto = new Context(
							EventosCliente.MODIFICAR_BUSCAR_CLIENTE, Integer
									.valueOf(ID));
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(editarBuscarErrorArea,
							editarBuscarErrorLabel,
							"ERROR: El ID introducido no es valido.", false);
				}
			}
		});

		// --

		JButton confirmBtn = new JButton("CONFIRMAR");
		confirmBtn.setFocusPainted(false);
		confirmBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		confirmBtn.setBackground(new Color(230, 230, 230));
		confirmBtn.setMaximumSize(new Dimension(150, 30));
		confirmBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer ID = Integer.valueOf(IDField.getText());
				String nombre = editarNombreField.getText();
				Boolean activo = Boolean.valueOf(editarActivoField.getText());
				LocalDate fecha = LocalDate.parse(editarFechaField.getText());
				if (nombre.length() > 0 && !nombre.equals(" ")) {
					;
					TCliente cliente = new TCliente(ID, activo, fecha, nombre);
					Context contexto = new Context(
							EventosCliente.MODIFICAR_CLIENTE, cliente);
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(editarOutputArea, editarOutputLabel,
							"ERROR: El nombre introducido no es valido.", false);
				}
			}
		});

		// --

		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);

		// --

		editarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		editarPanel.add(outputPanel);
		editarPanel.add(Box.createRigidArea(new Dimension(0, 60)));
		editarPanel.add(formPanel2);
		editarPanel.add(confirmBtn);

		// --

		editarClientePanel.add(buscarPanel, "BUSCAR");
		editarClientePanel.add(editarPanel, "SECOND");
		add(editarClientePanel, "EDITAR");

		editarClientePanelCL.show(editarClientePanel, "BUSCAR");
		_localCL.show(this, "EDITAR");
		_currentPanel = editarClientePanel;
	}

	public void bajaPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));

		borrarOutputArea = new JPanel();
		borrarOutputArea.setLayout(new BoxLayout(borrarOutputArea,
				BoxLayout.X_AXIS));
		borrarOutputArea.setBackground(new Color(172, 40, 40));
		borrarOutputArea.setMaximumSize(new Dimension(800, 50));

		borrarOutputLabel = new JLabel(
				"ERROR: El ID Cliente introducido no es valido.");
		borrarOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		borrarOutputLabel.setForeground(new Color(230, 230, 230));

		borrarOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		borrarOutputArea.add(borrarOutputLabel);
		borrarOutputArea.setVisible(false);
		outputPanel.add(borrarOutputArea);

		// --

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel IDLabel = new JLabel("ID Cliente: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);

		// --

		JButton borrarBtn = new JButton("ENVIAR");
		borrarBtn.setFocusPainted(false);
		borrarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		borrarBtn.setBackground(new Color(230, 230, 230));
		borrarBtn.setMaximumSize(new Dimension(125, 30));
		borrarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDField.getText();
				if (ID.length() > 0 && !ID.equals(" ")) {
					Context contexto = new Context(EventosCliente.BAJA_CLIENTE,
							Integer.valueOf(ID));
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(borrarOutputArea, borrarOutputLabel,
							"ERROR: El ID introducido no es valido.", false);
				}
			}
		});

		// --

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 60)));
		panel.add(formPanel);
		panel.add(borrarBtn);

		// --

		add(panel, "ELIMINAR");

		_localCL.show(this, "ELIMINAR");
		_currentPanel = panel;
	}

	public void mostrarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		JLabel tableTitle = new JLabel("CLIENTES");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));

		String[] columns = { "ID Cliente", "Nombre", "Fecha de registro",
				"Activo" };

		mostrarModel = new DefaultTableModel();
		for (String column : columns) {
			mostrarModel.addColumn(column);
		}
		JTable dataTable = new JTable(mostrarModel);
		dataTable.setEnabled(false);
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.setPreferredScrollableViewportSize(new Dimension(450, 63));
		dataTable.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(dataTable);
		tablePanel.add(scrollPane);

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(tableTitle);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(tablePanel);

		add(panel, "MOSTRAR");
		_localCL.show(this, "MOSTRAR");
		_currentPanel = panel;
	}

	public void buscarPanel() {
		mostrarClientePanel = new JPanel();
		mostrarClientePanelCL = new CardLayout();
		mostrarClientePanel.setLayout(mostrarClientePanelCL);
		mostrarClientePanel.setBackground(new Color(235, 237, 241));
		mostrarClientePanel.setMaximumSize(new Dimension(1024, 460));

		JPanel buscarPanel = new JPanel();
		buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
		buscarPanel.setBackground(new Color(235, 237, 241));
		buscarPanel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));

		mostrarErrorArea = new JPanel();
		mostrarErrorArea.setLayout(new BoxLayout(mostrarErrorArea,
				BoxLayout.X_AXIS));
		mostrarErrorArea.setBackground(new Color(172, 40, 40));
		mostrarErrorArea.setMaximumSize(new Dimension(800, 50));

		mostrarErrorLabel = new JLabel("ERROR: El ID introducido no es valido.");
		mostrarErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		mostrarErrorLabel.setForeground(new Color(230, 230, 230));

		mostrarErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		mostrarErrorArea.add(mostrarErrorLabel);
		mostrarErrorArea.setVisible(false);
		errorPanel.add(mostrarErrorArea);

		// --

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel IDLabel = new JLabel("ID Cliente: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);

		// --

		JPanel clientePanel = new JPanel();
		clientePanel.setLayout(new BoxLayout(clientePanel, BoxLayout.Y_AXIS));
		clientePanel.setBackground(new Color(235, 237, 241));
		clientePanel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 150));

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5, 5, 0, 0);
		JLabel IDLabel2 = new JLabel("ID Cliente: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDLabel2, c2);

		c2.gridy++;
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(nombreLabel, c2);

		c2.gridy++;
		JLabel activoLabel = new JLabel("Activo: ");
		activoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(activoLabel, c2);

		c2.gridy++;
		JLabel fechaLabel = new JLabel("Fecha de registro: ");
		fechaLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(fechaLabel, c2);

		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		mostrarIDText = new JLabel("2");
		mostrarIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDText, c2);

		c2.gridy++;
		mostrarNombreText = new JLabel("Benzema");
		mostrarNombreText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarNombreText, c2);

		c2.gridy++;
		mostrarActivoText = new JLabel("true");
		mostrarActivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarActivoText, c2);

		c2.gridy++;
		mostrarFechaText = new JLabel("1990-11-18");
		mostrarFechaText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarFechaText, c2);

		// --

		JButton buscarBtn = new JButton("ENVIAR");
		buscarBtn.setFocusPainted(false);
		buscarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		buscarBtn.setBackground(new Color(230, 230, 230));
		buscarBtn.setMaximumSize(new Dimension(125, 30));
		buscarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDField.getText();
				if (ID.length() > 0 && !ID.equals(" ")) {
					Context contexto = new Context(
							EventosCliente.MOSTRAR_CLIENTE, Integer.valueOf(ID));
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(mostrarErrorArea, mostrarErrorLabel,
							"ERROR: El ID introducido no es valido.", false);
				}
			}
		});

		// --

		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);

		// --

		clientePanel.add(Box.createRigidArea(new Dimension(0, 160)));
		clientePanel.add(dataPanel);

		// --

		mostrarClientePanel.add(buscarPanel, "BUSCAR");
		mostrarClientePanel.add(clientePanel, "CLIENTE");
		add(mostrarClientePanel, "BUSCAR");

		mostrarClientePanelCL.show(mostrarClientePanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarClientePanel;

		// --
	}

	public void clientesPorFechaPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));

		pedirFechasOutputArea = new JPanel();
		pedirFechasOutputArea.setLayout(new BoxLayout(pedirFechasOutputArea,
				BoxLayout.X_AXIS));
		pedirFechasOutputArea.setBackground(new Color(172, 40, 40));
		pedirFechasOutputArea.setMaximumSize(new Dimension(800, 50));

		pedirFechasOutputLabel = new JLabel(
				"ERROR: Las fechas introducidas no son validas.");
		pedirFechasOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		pedirFechasOutputLabel.setForeground(new Color(230, 230, 230));

		pedirFechasOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		pedirFechasOutputArea.add(pedirFechasOutputLabel);
		pedirFechasOutputArea.setVisible(false);
		outputPanel.add(pedirFechasOutputArea);

		// --

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel Fecha1Label = new JLabel("FECHA INICIO: ");
		Fecha1Label.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(Fecha1Label, c);

		c.gridy++;
		JLabel Fecha2Label = new JLabel("FECHA FIN: ");
		Fecha2Label.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(Fecha2Label, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField Fecha1Field = new JTextField(15);
		formPanel.add(Fecha1Field, c);

		c.gridy++;
		JTextField Fecha2Field = new JTextField(15);
		formPanel.add(Fecha2Field, c);

		// --

		JButton listarBtn = new JButton("ENVIAR");
		listarBtn.setFocusPainted(false);
		listarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		listarBtn.setBackground(new Color(230, 230, 230));
		listarBtn.setMaximumSize(new Dimension(125, 30));
		listarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		listarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Fecha1 = Fecha1Field.getText();
				String Fecha2 = Fecha2Field.getText();
				if (Fecha1.length() > 0 && !Fecha1.equals(" ")
						&& Fecha2.length() > 0 && !Fecha2.equals(" ")) {
					LocalDate date1 = LocalDate.parse(Fecha1);
					LocalDate date2 = LocalDate.parse(Fecha2);
					TFecha listaFechas = new TFecha(date1, date2);
					Context contexto = new Context(
							EventosCliente.LISTAR_CLIENTES_POR_FECHA_ALTA,
							listaFechas);
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(pedirFechasOutputArea,
							pedirFechasOutputLabel,
							"ERROR: Las fechas introducidas no son validas.",
							false);
				}
			}
		});

		// --

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 60)));
		panel.add(formPanel);
		panel.add(listarBtn);

		// --

		add(panel, "MOSTRAR POR FECHAS");

		_localCL.show(this, "MOSTRAR POR FECHAS");
		_currentPanel = panel;
	}

	public void clear() {
		for (Component c : _lastPathComponents) {
			pathPanel.remove(c);
			pathPanel.revalidate();
			pathPanel.repaint();
		}

		this.remove(_currentPanel);
		this.revalidate();
		this.repaint();
	}

	public void mostrar() {
		_localCL.show(this, name);

		if (_currentPanel != null) {
			if (!_currentPanel.equals(_homePanel))
				clear();
		}
	}

	public void actualizar(int evento, Object datos) {
		String mensaje;
		TCliente cliente;
		switch (evento) {
		case EventosCliente.ANADIR_CLIENTE_OK:
			mensaje = (String) datos;
			showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, true);
			System.out.println("Anadir Cliente OK");
			break;
		case EventosCliente.ANADIR_CLIENTE_KO: {
			mensaje = (String) datos;
			showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, false);
			System.out.println("Anadir Cliente KO");
		}
			break;
		case EventosCliente.BAJA_CLIENTE_OK: {
			mensaje = (String) datos;
			showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, true);
			System.out.println("Eliminar Cliente OK");
		}
			;
			break;
		case EventosCliente.BAJA_CLIENTE_KO: {
			mensaje = (String) datos;
			showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, false);
			System.out.println("Eliminar Cliente KO");
		}
			;
			break;
		case EventosCliente.LISTAR_CLIENTES_OK:
			@SuppressWarnings("unchecked")
			List<TCliente> listaClientes = (List<TCliente>) datos;

			addPathSeparator();
			createPathButton("LISTAR CLIENTES");
			mostrarPanel();

			for (TCliente c : listaClientes) {
				mostrarModel.addRow(new Object[] { c.getId(), c.getNombre(),
						c.getFecha_registro(), c.isActivo() });
			}
			System.out.println("Listar Clientes OK");
			break;
		case EventosCliente.LISTAR_CLIENTES_KO:
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Listar Clientes KO");
			break;
		case EventosCliente.MOSTRAR_CLIENTE_OK:
			cliente = (TCliente) datos;
			Integer id = cliente.getId();
			mostrarIDText.setText(id.toString());
			mostrarNombreText.setText(cliente.getNombre());
			mostrarActivoText.setText(Boolean.toString(cliente.isActivo()));
			mostrarFechaText.setText(cliente.getFecha_registro().toString());

			mostrarClientePanelCL.show(mostrarClientePanel, "CLIENTE");
			System.out.println("Mostrar Cliente OK");
			break;
		case EventosCliente.MOSTRAR_CLIENTE_KO:
			mensaje = (String) datos;

			showOutputMsg(mostrarErrorArea, mostrarErrorLabel, mensaje, false);
			System.out.println("Mostrar Cliente KO");
			break;
		case EventosCliente.MODIFICAR_BUSCAR_CLIENTE_OK:
			cliente = (TCliente) datos;

			editarNombreField.setText(cliente.getNombre());
			editarActivoField.setText(Boolean.toString(cliente.isActivo()));
			editarClientePanelCL.show(editarClientePanel, "SECOND");
			System.out.println("Editar Buscar Cliente OK");
			break;
		case EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO:
			mensaje = (String) datos;

			showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel,
					mensaje, false);
			System.out.println("Editar Buscar Clientes KO");
			break;
		case EventosCliente.MODIFICAR_CLIENTE_OK:
			mensaje = (String) datos;

			showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, true);
			System.out.println("Editar Cliente OK");
			break;
		case EventosCliente.MODIFICAR_CLIENTE_KO:
			mensaje = (String) datos;

			showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, false);
			System.out.println("Editar Cliente KO");
			break;
		case EventosCliente.LISTAR_CLIENTES_POR_FECHA_ALTA_OK: {
			@SuppressWarnings("unchecked")
			List<TCliente> listaClientesFecha = (List<TCliente>) datos;

			addPathSeparator();
			createPathButton("MOSTRAR CLIENTES");
			mostrarPanel();

			for (TCliente c : listaClientesFecha) {
				mostrarModel.addRow(new Object[] { c.getId(), c.getNombre(),
						c.getFecha_registro(), c.isActivo() });
			}
			System.out.println("Listar Clientes OK");
		}
			;
			break;
		case EventosCliente.LISTAR_CLIENTES_POR_FECHA_ALTA_KO: {
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Listar Clientes KO");
		}
			;
			break;
		}
	}
}
