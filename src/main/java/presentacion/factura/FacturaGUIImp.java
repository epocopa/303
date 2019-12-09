package presentacion.factura;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import negocio.TFecha;
import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.ControladorAplicacion;
import presentacion.controladorAplicacion.EventosFactura;
import presentacion.factoria.GUI;
import presentacion.factoria.FactoriaPresentacion;

public class FacturaGUIImp extends JPanel implements FacturaGUI, GUI {
	private static final long serialVersionUID = 1L;

	private String name = "FACTURAS";
	private Integer Id;

	private CardLayout _localCL;
	private JPanel _homePanel;
	private JPanel _currentPanel = _homePanel;

	private static FactoriaPresentacion presentacion = FactoriaPresentacion
			.getInstance();

	private JPanel pathPanel = presentacion.generarPath();
	List<Component> _lastPathComponents = new LinkedList<Component>();

	// --- COMPONENTES ---//

	private JPanel mostrarFacturaPanel;
	private CardLayout mostrarFacturaPanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarActivoText;
	private JLabel mostrarPrecioText;
	private JLabel mostrarFechaText;
	private JLabel mostrarIDClienteText;

	private DefaultTableModel mostrarFacturaModel;
	private DefaultTableModel mostrarModel;
	DefaultTableModel model;
	JTable dataTable;

	private JPanel pedirFechasOutputArea;
	private JLabel pedirFechasOutputLabel;
	private JLabel totalPrecio;

	private JTextField anadirIDProducto;
	private JTextField anadirProductoCantidad;

	private JTextField borrarIDProducto;
	private JTextField borrarProductoCantidad;

	public FacturaGUIImp() {
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

		JButton abrirBtn = createMenuButton(
				"resources/icons/facturas/abrir-factura.png", new Color(91,
						155, 213));
		abrirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField IDCliente = new JTextField();
				final JComponent[] input = new JComponent[] {
						new JLabel("ID Cliente"), IDCliente, };
				Object[] options = { "Confirmar", "Cancelar" };
				int result = JOptionPane
						.showOptionDialog(null, input, " Abrir Factura",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

				if (result == JOptionPane.OK_OPTION) {
					String IDClienteText = IDCliente.getText();

					if (IDClienteText.length() > 0
							&& !IDClienteText.equals(" ")) {
						TFactura factura = new TFactura();
						factura.setCliente(Integer.parseInt(IDCliente.getText()));
						factura.setAbierta(true);
						factura.setFecha(LocalDate.now());
						Context contexto = new Context(
								EventosFactura.ABRIR_FACTURA, factura);
						ControladorAplicacion.getInstance().accion(contexto);
					} else {
						JOptionPane.showMessageDialog(null,
								"ERROR: El dato introducido no es valido.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		_homePanel.add(abrirBtn, c);

		c.gridx++;
		JButton buscarBtn = createMenuButton(
				"resources/icons/facturas/buscar-factura.png", new Color(0,
						112, 192));
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("MOSTRAR FACTURA");
				buscarPanel();
			}
		});
		_homePanel.add(buscarBtn, c);

		c.gridx++;
		JButton facturasFechasBtn = createMenuButton(
				"resources/icons/facturas/productos_comprados_entre_fechas.png",
				new Color(234, 80, 54));
		facturasFechasBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("PRODUCTOS COMPRADOS");
				productosCompradosPanel();
			}
		});
		_homePanel.add(facturasFechasBtn, c);

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
		pathBtn.setMaximumSize(new Dimension(250, 50));
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
		button.setPreferredSize(new Dimension(200, 150));
		button.setBackground(color);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setIcon(new ImageIcon(iconPath));

		return button;
	}

	public JButton createToolButton(String iconPath, Color color, String tooltip) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(35, 35));
		button.setBackground(color);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		button.setToolTipText(tooltip);
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

	private int findRow(JTable table, String ID) {
		int foundRow = -1;
		for (int row = 0; row < table.getRowCount(); row++) {
			String rowID = table.getModel().getValueAt(row, 0).toString();
			if (rowID.equals(ID)) {
				foundRow = row;
				break;
			}
		}
		return foundRow;
	}

	public void abrirPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		JLabel tableTitle = new JLabel("FACTURA");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel facturaPanel = new JPanel();
		facturaPanel.setLayout(new BoxLayout(facturaPanel, BoxLayout.X_AXIS));
		facturaPanel.setBackground(new Color(235, 237, 241));
		facturaPanel.setMaximumSize(new Dimension(1024, 320));

		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
		toolPanel.setBackground(new Color(70, 70, 70));
		toolPanel.setMaximumSize(new Dimension(80, 320));

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(600, 320));

		String[] columns = { "ID Producto", "Cantidad" };

		model = new DefaultTableModel();
		for (String column : columns) {
			model.addColumn(column);
		}
		dataTable = new JTable(model);
		dataTable.setEnabled(false);
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.setPreferredScrollableViewportSize(new Dimension(450, 63));
		dataTable.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(dataTable);
		tablePanel.add(scrollPane);

		// --

		JLabel totalTitle = new JLabel("TOTAL: ");
		totalTitle.setForeground(new Color(230, 230, 230));
		totalTitle.setFont(new Font("Arial", Font.BOLD, 12));
		totalTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		totalPrecio = new JLabel("0.0");
		totalPrecio.setForeground(new Color(230, 230, 230));
		totalPrecio.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		toolPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JButton anadirProducto = createToolButton(
				"resources/icons/tools/add.png", new Color(63, 164, 31),
				"Anade un producto a la factura");
		anadirProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirIDProducto = new JTextField();
				anadirProductoCantidad = new JTextField();
				final JComponent[] input = new JComponent[] {
						new JLabel("ID Producto"), anadirIDProducto,
						new JLabel("Cantidad"), anadirProductoCantidad, };
				Object[] options = { "Anadir", "Cancelar" };
				int result = JOptionPane
						.showOptionDialog(null, input, " Anadir Producto",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

				if (result == JOptionPane.OK_OPTION) {
					String IDProductoText = anadirIDProducto.getText();
					String productoCantidadText = anadirProductoCantidad
							.getText();
					if ((IDProductoText.length() > 0 && !IDProductoText
							.equals(" "))
							&& (productoCantidadText.length() > 0 && !productoCantidadText
									.equals(" "))) {
						TLineaFactura cesta = new TLineaFactura(Id, Integer
								.parseInt(IDProductoText), Integer
								.parseInt(productoCantidadText));
						Context contexto = new Context(
								EventosFactura.ANADIR_PRODUCTO_A_F, cesta);
						ControladorAplicacion.getInstance().accion(contexto);
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"ERROR: Los datos introducidos no son validos.",
										"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		toolPanel.add(anadirProducto);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		JButton borrarProducto = createToolButton(
				"resources/icons/tools/delete.png", new Color(220, 34, 34),
				"Borra un producto de la factura");
		borrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarIDProducto = new JTextField();
				borrarProductoCantidad = new JTextField();
				final JComponent[] input = new JComponent[] {
						new JLabel("ID Producto"), borrarIDProducto,
						new JLabel("Cantidad"), borrarProductoCantidad, };
				Object[] options = { "Eliminar", "Cancelar" };
				int result = JOptionPane
						.showOptionDialog(null, input, " Eliminar Producto",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

				if (result == JOptionPane.OK_OPTION) {
					String IDProductoText = borrarIDProducto.getText();
					String productoCantidadText = borrarProductoCantidad
							.getText();
					if ((IDProductoText.length() > 0 && !IDProductoText
							.equals(" "))
							&& (productoCantidadText.length() > 0 && !productoCantidadText
									.equals(" "))) {
						TLineaFactura eliminar = new TLineaFactura(Id, Integer
								.parseInt(IDProductoText), Integer
								.parseInt(productoCantidadText));
						Context contexto = new Context(
								EventosFactura.BORRAR_PRODUCTO, eliminar);
						ControladorAplicacion.getInstance().accion(contexto);
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"ERROR: Los datos introducidos no son validos.",
										"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		toolPanel.add(borrarProducto);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		JButton confirmar = createToolButton(
				"resources/icons/tools/complete.png", new Color(38, 180, 237),
				"Confirma y cierra la factura");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JComponent[] input = new JComponent[] { new JLabel(
						"¿Desea enviar y cerrar su factura?"), };
				Object[] options = { "Si", "No" };
				int result = JOptionPane
						.showOptionDialog(null, input, " Confirmar Factura",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

				if (result == JOptionPane.OK_OPTION) {
					Context contexto = new Context(
							EventosFactura.CERRAR_FACTURA, Id);
					ControladorAplicacion.getInstance().accion(contexto);
				}
			}
		});
		toolPanel.add(confirmar);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 25)));

		// --

		facturaPanel.add(Box.createRigidArea(new Dimension(170, 0)));
		facturaPanel.add(tablePanel);
		facturaPanel.add(toolPanel);

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(tableTitle);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(facturaPanel);

		add(panel, "MOSTRAR");
		_localCL.show(this, "MOSTRAR");
		_currentPanel = panel;
	}

	public void productosCompradosPanel() {
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
							EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA,
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

		add(panel, "PRODUCTOS COMPRADOS");

		_localCL.show(this, "PRODUCTOS COMPRADOS");
		_currentPanel = panel;
	}

	public void buscarPanel() {
		mostrarFacturaPanel = new JPanel();
		mostrarFacturaPanelCL = new CardLayout();
		mostrarFacturaPanel.setLayout(mostrarFacturaPanelCL);
		mostrarFacturaPanel.setBackground(new Color(235, 237, 241));
		mostrarFacturaPanel.setMaximumSize(new Dimension(1024, 460));

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
		JLabel IDLabel = new JLabel("ID Factura: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);

		// --

		JPanel facturaPanel = new JPanel();
		facturaPanel.setLayout(new BoxLayout(facturaPanel, BoxLayout.Y_AXIS));
		facturaPanel.setBackground(new Color(235, 237, 241));
		facturaPanel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setMaximumSize(new Dimension(600, 320));

		String[] columns = { "ID Producto", "Cantidad" };

		mostrarFacturaModel = new DefaultTableModel();
		for (String column : columns) {
			mostrarFacturaModel.addColumn(column);
		}
		JTable table = new JTable(mostrarFacturaModel);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		dataPanel.add(scrollPane);

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5, 5, 0, 0);
		JLabel separacion = new JLabel(" ");
		dataPanel.add(separacion, c2);

		mostrarIDText = new JLabel(" ");
		mostrarIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDText, c2);

		c2.gridy++;
		mostrarFechaText = new JLabel(" ");
		mostrarFechaText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarFechaText, c2);

		c2.gridy++;
		mostrarPrecioText = new JLabel(" ");
		mostrarPrecioText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarPrecioText, c2);

		c2.gridy++;
		mostrarActivoText = new JLabel(" ");
		mostrarActivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarActivoText, c2);

		c2.gridy++;
		mostrarIDClienteText = new JLabel(" ");
		mostrarIDClienteText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDClienteText, c2);

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
					Context contexto = new Context(
							EventosFactura.MOSTRAR_FACTURA, Integer.valueOf(ID));
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(mostrarErrorArea, mostrarErrorLabel,
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

		facturaPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		facturaPanel.add(dataPanel);

		// --

		mostrarFacturaPanel.add(buscarPanel, "BUSCAR");
		mostrarFacturaPanel.add(facturaPanel, "FACTURA");
		add(mostrarFacturaPanel, "BUSCAR");

		mostrarFacturaPanelCL.show(mostrarFacturaPanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarFacturaPanel;
	}

	public void mostrarProductosPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		JLabel tableTitle = new JLabel("PRODUCTOS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));

		String[] columns = { "ID Factura", "ID Producto", "Cantidad vendida" };
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
		TFactura factura;
		switch (evento) {
		case EventosFactura.MOSTRAR_FACTURA_OK:
			factura = (TFactura) datos;
			mostrarIDText.setText("ID Factura: "
					+ String.valueOf(factura.getId()));
			mostrarPrecioText.setText("Precio: "
					+ Double.valueOf(factura.getPrecio()).toString());
			mostrarFechaText.setText("Fecha: " + factura.getFecha().toString());
			mostrarIDClienteText.setText("ID Cliente: "
					+ String.valueOf(factura.getCliente()));
			mostrarActivoText.setText("Abierta: "
					+ String.valueOf(factura.isAbierta()));

			for (TLineaFactura l : factura.getLineaFacturas()) {
				mostrarFacturaModel.addRow(new Object[] { l.getProducto(),
						l.getCantidad() });
			}

			mostrarFacturaPanelCL.show(mostrarFacturaPanel, "FACTURA");
			System.out.println("Mostrar Factura OK");
			break;
		case EventosFactura.MOSTRAR_FACTURA_KO:
			mensaje = (String) datos;

			showOutputMsg(mostrarErrorArea, mostrarErrorLabel, mensaje, false);
			System.out.println("Mostrar Factura KO");
			break;
		case EventosFactura.ABRIR_FACTURA_OK:
			Id = (Integer) datos;
			addPathSeparator();
			createPathButton("ABRIR FACTURA");
			abrirPanel();
			break;
		case EventosFactura.ABRIR_FACTURA_KO:
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case EventosFactura.CERRAR_FACTURA_OK:
			mostrar();
			break;
		case EventosFactura.CERRAR_FACTURA_KO:
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case EventosFactura.ANADIR_PRODUCTO_A_F_OK:
			TLineaFactura linea = (TLineaFactura) datos;

			Integer id = linea.getProducto();
			Integer cantidad = linea.getCantidad();

			int row = findRow(dataTable, anadirIDProducto.getText());
			if (row >= 0) {
				Integer cantidadPresente = Integer.valueOf(model
						.getValueAt(row, 1).toString().replaceAll("\\D+", ""));
				String cantidadTotal = Integer.valueOf(
						cantidadPresente + Integer.valueOf(cantidad))
						.toString();
				model.setValueAt("x" + cantidadTotal, row, 1);
			} else {
				model.addRow(new Object[] { id, "x" + cantidad });
			}
			break;
		case EventosFactura.ANADIR_PRODUCTO_A_F_KO:
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case EventosFactura.BORRAR_PRODUCTO_OK:
			TLineaFactura lineaBorrar = (TLineaFactura) datos;

			int row2 = findRow(dataTable, borrarIDProducto.getText());
			if (row2 >= 0) {
				Integer cantidad2 = lineaBorrar.getCantidad();
				Integer cantidadPresente = Integer.valueOf(model
						.getValueAt(row2, 1).toString().replaceAll("\\D+", ""));

				if (cantidad2 >= cantidadPresente) {
					model.removeRow(row2);
				} else {
					String cantidadRestante = Integer.valueOf(
							cantidadPresente - cantidad2).toString();
					model.setValueAt("x" + cantidadRestante, row2, 1);
				}
			}
			break;
		case EventosFactura.BORRAR_PRODUCTO_KO:
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_OK: {
			@SuppressWarnings("unchecked")
			List<TFactura> listaFacturas = (List<TFactura>) datos;

			addPathSeparator();
			createPathButton("LISTAR PRODUCTOS");
			mostrarProductosPanel();

			for (TFactura p : listaFacturas) {
				for (TLineaFactura l : p.getLineaFacturas()) {
					mostrarModel.addRow(new Object[] { l.getFactura(),
							l.getProducto(), l.getCantidad() });
				}
			}
		}
			;
			break;
		case EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_KO: {
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
			;
			break;
		}
	}
}
