package presentacion.grupo;

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

import negocio.empleado.TTrabaja;
import negocio.grupo.TGrupo;
import presentacion.factoria.GUI;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.ControladorAplicacion;
import presentacion.controladorAplicacion.EventosGrupo;
import presentacion.factoria.FactoriaPresentacion;

public class GrupoGUIImpl extends JPanel implements GrupoGUI, GUI {
	private static final long serialVersionUID = 1L;

	private String name = "GRUPOS";

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

	private JPanel anadirEmpleadoOutputArea;
	private JLabel anadirEmpleadoOutputLabel;

	private JPanel borrarOutputArea;
	private JLabel borrarOutputLabel;

	private JPanel borrarEmpleadoOutputArea;
	private JLabel borrarEmpleadoOutputLabel;

	private JPanel mostrarGrupoPanel;
	private CardLayout mostrarGrupoPanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarSeccionText;
	private JLabel mostrarActivoText;

	private DefaultTableModel mostrarModel;

	private JPanel editarGrupoPanel;
	private CardLayout editarGrupoPanelCL;
	private JPanel editarBuscarErrorArea;
	private JLabel editarBuscarErrorLabel;
	private JPanel editarOutputArea;
	private JLabel editarOutputLabel;
	private JTextField editarSeccionField;
	private JTextField editarActivoField;

	public GrupoGUIImpl() {
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
				"resources/icons/grupo/anadir-grupo.png", new Color(91, 155,
						213));
		anadirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("ANADIR GRUPO");
				anadirPanel();
			}
		});
		_homePanel.add(anadirBtn, c);

		c.gridx++;
		JButton editarBtn = createMenuButton(
				"resources/icons/grupo/editar-grupo.png",
				new Color(255, 192, 0));
		editarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("EDITAR GRUPO");
				editarPanel();
				;
			}
		});
		_homePanel.add(editarBtn, c);

		c.gridx++;
		JButton borrarBtn = createMenuButton(
				"resources/icons/grupo/eliminar-grupo.png", new Color(112, 173,
						71));
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("ELIMINAR GRUPO");
				borrarPanel();
				;
			}
		});
		_homePanel.add(borrarBtn, c);

		c.gridx++;
		JButton listarBtn = createMenuButton(
				"resources/icons/grupo/mostrar-grupos.png", new Color(234, 80,
						54));
		listarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Context contexto = new Context(EventosGrupo.LISTAR_GRUPOS, null);
				ControladorAplicacion.getInstance().accion(contexto);
			}
		});
		_homePanel.add(listarBtn, c);

		c.gridx = 0;
		c.gridy++;
		JButton buscarBtn = createMenuButton(
				"resources/icons/grupo/buscar-grupo.png",
				new Color(47, 85, 151));
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("MOSTRAR GRUPO");
				buscarPanel();
			}
		});
		_homePanel.add(buscarBtn, c);

		c.gridx++;
		JButton anadirEmpleadoBtn = createMenuButton(
				"resources/icons/grupo/anadir-empleado-grupo.png", new Color(
						112, 48, 160));
		anadirEmpleadoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("ANADIR EMPLEADO");
				anadirEmpleadoPanel();
			}
		});
		_homePanel.add(anadirEmpleadoBtn, c);

		c.gridx++;
		JButton borrarEmpleadoBtn = createMenuButton(
				"resources/icons/grupo/baja-empleado-grupo.png", new Color(237,
						125, 49));
		borrarEmpleadoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPathSeparator();
				createPathButton("ELIMINAR EMPLEADO");
				borrarEmpleadoPanel();
			}
		});
		_homePanel.add(borrarEmpleadoBtn, c);

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
		button.setPreferredSize(new Dimension(200, 125));
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
		formPanel.setMaximumSize(new Dimension(1024, 140));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel seccionLabel = new JLabel("Seccion: ");
		seccionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(seccionLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField seccionField = new JTextField(15);
		formPanel.add(seccionField, c);

		// --

		JButton enviarBtn = new JButton("ENVIAR");
		enviarBtn.setFocusPainted(false);
		enviarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		enviarBtn.setBackground(new Color(230, 230, 230));
		enviarBtn.setMaximumSize(new Dimension(125, 30));
		enviarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		enviarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TGrupo grupo = new TGrupo();
					String seccion = seccionField.getText();
					if (seccion.length() > 0 && !seccion.equals("")) {
						grupo.setSeccion(seccion);
						grupo.setActivo(true);
						Context contexto = new Context(
								EventosGrupo.ANADIR_GRUPO, grupo);
						ControladorAplicacion.getInstance().accion(contexto);
					} else {
						showOutputMsg(anadirOutputArea, anadirOutputLabel,
								"ERROR: El ID introducido no es valido.", false);
					}
				} catch (NumberFormatException ex) {
					showOutputMsg(anadirOutputArea, anadirOutputLabel,
							"ERROR: Los valores introducidos no son validos.",
							false);
				}
			}
		});

		// --

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(formPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(enviarBtn);

		// --

		add(panel, "ANADIR");
		_localCL.show(this, "ANADIR");
		_currentPanel = panel;
	}

	public void editarPanel() {
		editarGrupoPanel = new JPanel();
		editarGrupoPanelCL = new CardLayout();
		editarGrupoPanel.setLayout(editarGrupoPanelCL);
		editarGrupoPanel.setBackground(new Color(235, 237, 241));
		editarGrupoPanel.setMaximumSize(new Dimension(1024, 460));

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
		JLabel IDLabel = new JLabel("ID Grupo de trabajo: ");
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

		editarOutputLabel = new JLabel("ERROR: El ID introducido no es valido.");
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
		formPanel2.setMaximumSize(new Dimension(1024, 190));

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5, 5, 0, 0);
		JLabel seccionLabel2 = new JLabel("Seccion: ");
		seccionLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(seccionLabel2, c2);

		c2.gridy++;
		JLabel activoLabel = new JLabel("Activo: ");
		activoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(activoLabel, c2);

		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		editarSeccionField = new JTextField(15);
		formPanel2.add(editarSeccionField, c2);

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
					Context contexto = new Context(
							EventosGrupo.MODIFICAR_BUSCAR_GRUPO, Integer
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
				String seccion = editarSeccionField.getText();
				Boolean activo = Boolean.valueOf(editarActivoField.getText());

				if (seccion.length() > 0 && !seccion.equals("")
						&& activo.toString().length() > 0
						&& !activo.toString().equals("")) {
					TGrupo grupo = new TGrupo(ID, seccion, activo);
					Context contexto = new Context(
							EventosGrupo.MODIFICAR_GRUPO, grupo);
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(editarBuscarErrorArea,
							editarBuscarErrorLabel,
							"ERROR: Los datos introducidos no son validos.",
							false);
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
		editarPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		editarPanel.add(formPanel2);
		editarPanel.add(confirmBtn);

		// --

		editarGrupoPanel.add(buscarPanel, "BUSCAR");
		editarGrupoPanel.add(editarPanel, "SECOND");
		add(editarGrupoPanel, "EDITAR");

		editarGrupoPanelCL.show(editarGrupoPanel, "BUSCAR");
		_localCL.show(this, "EDITAR");
		_currentPanel = editarGrupoPanel;
	}

	public void borrarPanel() {
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

		borrarOutputLabel = new JLabel("ERROR: El ID introducido no es valido.");
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
		JLabel IDLabel = new JLabel("ID Grupo de trabajo: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);

		// --

		JButton borrarBtn = new JButton("ELIMINAR");
		borrarBtn.setFocusPainted(false);
		borrarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		borrarBtn.setBackground(new Color(230, 230, 230));
		borrarBtn.setMaximumSize(new Dimension(125, 30));
		borrarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDField.getText();
				if (ID.length() > 0 && !ID.equals(" ")) {
					Context contexto = new Context(EventosGrupo.BAJA_GRUPO,
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

		JLabel tableTitle = new JLabel("GRUPOS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));

		String[] columns = { "ID Grupo", "Seccion", "Activo" };
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
		mostrarGrupoPanel = new JPanel();
		mostrarGrupoPanelCL = new CardLayout();
		mostrarGrupoPanel.setLayout(mostrarGrupoPanelCL);
		mostrarGrupoPanel.setBackground(new Color(235, 237, 241));
		mostrarGrupoPanel.setMaximumSize(new Dimension(1024, 460));

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
		JLabel IDLabel = new JLabel("ID Grupo de trabajo: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);

		// --

		JPanel grupoPanel = new JPanel();
		grupoPanel.setLayout(new BoxLayout(grupoPanel, BoxLayout.Y_AXIS));
		grupoPanel.setBackground(new Color(235, 237, 241));
		grupoPanel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 210));

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5, 5, 0, 0);
		JLabel IDLabel2 = new JLabel("ID Grupo de trabajo: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDLabel2, c2);

		c2.gridy++;
		JLabel seccionLabel = new JLabel("Seccion: ");
		seccionLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(seccionLabel, c2);

		c2.gridy++;
		JLabel ActivoLabel = new JLabel("Activo: ");
		ActivoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(ActivoLabel, c2);

		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		mostrarIDText = new JLabel("0");
		mostrarIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDText, c2);

		c2.gridy++;
		mostrarSeccionText = new JLabel("Nombre");
		mostrarSeccionText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarSeccionText, c2);

		c2.gridy++;
		mostrarActivoText = new JLabel("true");
		mostrarActivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarActivoText, c2);

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
					Context contexto = new Context(EventosGrupo.MOSTRAR_GRUPO,
							Integer.valueOf(ID));
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

		grupoPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		grupoPanel.add(dataPanel);

		// --

		mostrarGrupoPanel.add(buscarPanel, "BUSCAR");
		mostrarGrupoPanel.add(grupoPanel, "GRUPO");
		add(mostrarGrupoPanel, "BUSCAR");

		mostrarGrupoPanelCL.show(mostrarGrupoPanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarGrupoPanel;
	}

	public void anadirEmpleadoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));

		anadirEmpleadoOutputArea = new JPanel();
		anadirEmpleadoOutputArea.setLayout(new BoxLayout(
				anadirEmpleadoOutputArea, BoxLayout.X_AXIS));
		anadirEmpleadoOutputArea.setBackground(new Color(172, 40, 40));
		anadirEmpleadoOutputArea.setMaximumSize(new Dimension(800, 50));

		anadirEmpleadoOutputLabel = new JLabel(
				"ERROR: El ID introducido no es valido.");
		anadirEmpleadoOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		anadirEmpleadoOutputLabel.setForeground(new Color(230, 230, 230));

		anadirEmpleadoOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		anadirEmpleadoOutputArea.add(anadirEmpleadoOutputLabel);
		anadirEmpleadoOutputArea.setVisible(false);
		outputPanel.add(anadirEmpleadoOutputArea);

		// --

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 140));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel idGrupoLabel = new JLabel("ID Grupo de trabajo: ");
		idGrupoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(idGrupoLabel, c);

		c.gridy++;
		JLabel idEmpleadoLabel = new JLabel("ID Empleado: ");
		idEmpleadoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(idEmpleadoLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField idGrupoField = new JTextField(15);
		formPanel.add(idGrupoField, c);

		c.gridy++;
		JTextField idEmpleadoField = new JTextField(15);
		formPanel.add(idEmpleadoField, c);

		// --

		JButton enviarBtn = new JButton("ENVIAR");
		enviarBtn.setFocusPainted(false);
		enviarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		enviarBtn.setBackground(new Color(230, 230, 230));
		enviarBtn.setMaximumSize(new Dimension(125, 30));
		enviarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		enviarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (idGrupoField.getText().length() > 0
							&& !idGrupoField.getText().equals("")
							&& idEmpleadoField.getText().length() > 0
							&& !idEmpleadoField.getText().equals("")) {
						TTrabaja trabaja = new TTrabaja();
						int idGrupo = Integer.parseInt(idGrupoField.getText());
						int idEmpleado = Integer.parseInt(idEmpleadoField
								.getText());
						trabaja.setIdEmpleado(idEmpleado);
						trabaja.setIdGrupo(idGrupo);
						Context contexto = new Context(
								EventosGrupo.ANADIR_EMPLEADO_A_GRUPO, trabaja);
						ControladorAplicacion.getInstance().accion(contexto);
					} else {
						showOutputMsg(anadirEmpleadoOutputArea,
								anadirEmpleadoOutputLabel,
								"ERROR: El ID introducido no es valido.", false);
					}
				} catch (NumberFormatException ex) {
					showOutputMsg(anadirEmpleadoOutputArea,
							anadirEmpleadoOutputLabel,
							"ERROR: Los valores introducidos no son validos.",
							false);
				}
			}
		});

		// --

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(formPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(enviarBtn);

		// --

		add(panel, "ANADIR");
		_localCL.show(this, "ANADIR");
		_currentPanel = panel;
	}

	public void borrarEmpleadoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));

		// --

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));

		borrarEmpleadoOutputArea = new JPanel();
		borrarEmpleadoOutputArea.setLayout(new BoxLayout(
				borrarEmpleadoOutputArea, BoxLayout.X_AXIS));
		borrarEmpleadoOutputArea.setBackground(new Color(172, 40, 40));
		borrarEmpleadoOutputArea.setMaximumSize(new Dimension(800, 50));

		borrarEmpleadoOutputLabel = new JLabel(
				"ERROR: El ID introducido no es valido.");
		borrarEmpleadoOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		borrarEmpleadoOutputLabel.setForeground(new Color(230, 230, 230));

		borrarEmpleadoOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		borrarEmpleadoOutputArea.add(borrarEmpleadoOutputLabel);
		borrarEmpleadoOutputArea.setVisible(false);
		outputPanel.add(borrarEmpleadoOutputArea);

		// --

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 140));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 0, 0);
		JLabel idGrupoLabel = new JLabel("ID Grupo de trabajo: ");
		idGrupoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(idGrupoLabel, c);

		c.gridy++;
		JLabel idEmpleadoLabel = new JLabel("ID Empleado: ");
		idEmpleadoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(idEmpleadoLabel, c);

		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField idGrupoField = new JTextField(15);
		formPanel.add(idGrupoField, c);

		c.gridy++;
		JTextField idEmpleadoField = new JTextField(15);
		formPanel.add(idEmpleadoField, c);

		// --

		JButton enviarBtn = new JButton("ENVIAR");
		enviarBtn.setFocusPainted(false);
		enviarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		enviarBtn.setBackground(new Color(230, 230, 230));
		enviarBtn.setMaximumSize(new Dimension(125, 30));
		enviarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		enviarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (idGrupoField.getText().length() > 0
							&& !idGrupoField.getText().equals("")
							&& idEmpleadoField.getText().length() > 0
							&& !idEmpleadoField.getText().equals("")) {
						TTrabaja trabaja = new TTrabaja();
						int idGrupo = Integer.parseInt(idGrupoField.getText());
						int idEmpleado = Integer.parseInt(idEmpleadoField
								.getText());
						trabaja.setIdEmpleado(idEmpleado);
						trabaja.setIdGrupo(idGrupo);
						Context contexto = new Context(
								EventosGrupo.BAJA_EMPLEADO_DE_GRUPO, trabaja);
						ControladorAplicacion.getInstance().accion(contexto);
					} else {
						showOutputMsg(borrarEmpleadoOutputArea,
								borrarEmpleadoOutputLabel,
								"ERROR: El ID introducido no es valido.", false);
					}
				} catch (NumberFormatException ex) {
					showOutputMsg(borrarEmpleadoOutputArea,
							borrarEmpleadoOutputLabel,
							"ERROR: Los valores introducidos no son validos.",
							false);
				}
			}
		});

		// --

		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(formPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(enviarBtn);

		// --

		add(panel, "ANADIR");
		_localCL.show(this, "ANADIR");
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
		TGrupo grupo;
		switch (evento) {
		case EventosGrupo.ANADIR_GRUPO_OK:
			mensaje = (String) datos;
			showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, true);
			System.out.println("Anadir Grupo OK");
			break;
		case EventosGrupo.ANADIR_GRUPO_KO: {
			mensaje = (String) datos;
			showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, false);
			System.out.println("Anadir Grupo KO");
		}
			break;
		case EventosGrupo.BAJA_GRUPO_OK:
			mensaje = (String) datos;
			showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, true);
			System.out.println("Eliminar Grupo OK");
			break;
		case EventosGrupo.BAJA_GRUPO_KO:
			mensaje = (String) datos;
			showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, false);
			System.out.println("Eliminar Grupo KO");
			break;
		case EventosGrupo.MOSTRAR_GRUPO_OK:
			grupo = (TGrupo) datos;
			Integer id = grupo.getId();

			mostrarIDText.setText(id.toString());
			mostrarSeccionText.setText(grupo.getSeccion());
			mostrarActivoText.setText(Boolean.toString(grupo.isActivo()));

			mostrarGrupoPanelCL.show(mostrarGrupoPanel, "GRUPO");
			System.out.println("Mostrar Grupo OK");
			break;
		case EventosGrupo.MOSTRAR_GRUPO_KO:
			mensaje = (String) datos;

			showOutputMsg(mostrarErrorArea, mostrarErrorLabel, mensaje, false);
			System.out.println("Mostrar Grupo KO");
			break;
		case EventosGrupo.LISTAR_GRUPOS_OK:
			@SuppressWarnings("unchecked")
			List<TGrupo> listaGrupos = (List<TGrupo>) datos;
			addPathSeparator();
			createPathButton("LISTAR GRUPOS");
			mostrarPanel();

			for (TGrupo g : listaGrupos) {
				mostrarModel.addRow(new Object[] { g.getId(), g.getSeccion(),
						g.isActivo() });
			}

			System.out.println("Listar Grupos OK");
			break;
		case EventosGrupo.LISTAR_GRUPOS_KO:
			mensaje = (String) datos;

			JOptionPane.showMessageDialog(null, mensaje, "Error",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Listar Grupos KO");
			break;
		case EventosGrupo.MODIFICAR_BUSCAR_GRUPO_OK:
			grupo = (TGrupo) datos;

			editarSeccionField.setText(grupo.getSeccion());
			editarActivoField.setText(Boolean.toString(grupo.isActivo()));

			editarGrupoPanelCL.show(editarGrupoPanel, "SECOND");
			System.out.println("Editar Buscar Grupo OK");
			break;
		case EventosGrupo.MODIFICAR_BUSCAR_GRUPO_KO:
			mensaje = (String) datos;

			showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel,
					mensaje, false);
			System.out.println("Editar Buscar Grupo KO");
			break;
		case EventosGrupo.MODIFICAR_GRUPO_OK:
			mensaje = (String) datos;

			showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, true);
			System.out.println("Editar Grupo OK");
			break;
		case EventosGrupo.MODIFICAR_GRUPO_KO:
			mensaje = (String) datos;

			showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, false);
			System.out.println("Editar Grupo KO");
			break;
		case EventosGrupo.ANADIR_EMPLEADO_A_GRUPO_OK:
			mensaje = (String) datos;
			showOutputMsg(anadirEmpleadoOutputArea, anadirEmpleadoOutputLabel,
					mensaje, true);
			System.out.println("Anadir Empleado A Grupo OK");
			break;
		case EventosGrupo.ANADIR_EMPLEADO_A_GRUPO_KO:
			mensaje = (String) datos;
			showOutputMsg(anadirEmpleadoOutputArea, anadirEmpleadoOutputLabel,
					mensaje, false);
			System.out.println("Anadir Empleado A Grupo KO");
			break;
		case EventosGrupo.BAJA_EMPLEADO_DE_GRUPO_OK:
			mensaje = (String) datos;
			showOutputMsg(borrarEmpleadoOutputArea, borrarEmpleadoOutputLabel,
					mensaje, true);
			System.out.println("Baja Empleado De Grupo OK");
			break;
		case EventosGrupo.BAJA_EMPLEADO_DE_GRUPO_KO:
			mensaje = (String) datos;
			showOutputMsg(borrarEmpleadoOutputArea, borrarEmpleadoOutputLabel,
					mensaje, false);
			System.out.println("Baja Empleado De Grupo KO");
			break;
		}
	}
}
