package presentacion.cliente;

import negocio.cliente.TCliente;
import presentacion.controlador.SingletonControlador;
import presentacion.factoria.FactoriaPresentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class ClienteGUIImpl extends JFrame implements ClienteGUI {
	private static final long serialVersionUID = 1L;

	private String name = "CLIENTES";
	
	private CardLayout _localCL;
	private JPanel _homePanel;
	private JPanel _currentPanel = _homePanel;
	
	private static FactoriaPresentacion presentacion = FactoriaPresentacion.getInstancia();
	//private static SingletonControlador controlador = SingletonControlador.getInstancia();

	private JPanel pathPanel = presentacion.generarPath();
	List<Component> _lastPathComponents = new LinkedList<Component>();
	
	//--- COMPONENTES ---//
	
	private JPanel anadirOutputArea;
	private JLabel anadirOutputLabel;	
	
	private JPanel mostrarClientePanel;
	private CardLayout mostrarClientePanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarNombreText;
	
	private DefaultTableModel mostrarModel;
	
	private JPanel editarClientePanel;
	private CardLayout editarClientePanelCL;
	private JPanel editarBuscarErrorArea;
	private JLabel editarBuscarErrorLabel;
	private JPanel editarOutputArea;
	private JLabel editarOutputLabel;
	private JTextField editarNombreField;
	
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
		c.insets = new Insets(10,10,10,10);
		
		JButton anadirBtn = createMenuButton("resources/icons/clientes/anadir-cliente.png", new Color(77, 198, 51));
		anadirBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ANADIR CLIENTE");
				anadirPanel();
			}
		});
		_homePanel.add(anadirBtn, c);
		
		c.gridx++;
		JButton editarBtn = createMenuButton("resources/icons/clientes/editar-cliente.png", new Color(240, 178, 44));
		editarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("EDITAR CLIENTE");
				editarPanel();;
			}
		});
		_homePanel.add(editarBtn, c);
		
		c.gridx = 0;
		c.gridy++;
		JButton listarBtn = createMenuButton("resources/icons/clientes/mostrar-clientes.png", new Color(56, 176, 225));
		listarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SingletonControlador.getInstancia().accion(EventosCliente.LISTAR_CLIENTES, null);
			}
		});
		_homePanel.add(listarBtn, c);
		
		c.gridx++;
		JButton buscarBtn = createMenuButton("resources/icons/clientes/buscar-cliente.png", new Color(47, 101, 175));
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("BUSCAR CLIENTE");
				buscarPanel();
			}
		});
		_homePanel.add(buscarBtn, c);

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
		pathBtn.setBackground(new Color(230,230,230));
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
		button.setPreferredSize(new Dimension(220,140));
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
		
		//--
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));
		
		anadirOutputArea = new JPanel();
		anadirOutputArea.setLayout(new BoxLayout(anadirOutputArea, BoxLayout.X_AXIS));
		anadirOutputArea.setBackground(new Color(172, 40, 40));
		anadirOutputArea.setMaximumSize(new Dimension(800, 50));
		
		anadirOutputLabel = new JLabel("ERROR: El ID introducido no es valido.");
		anadirOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		anadirOutputLabel.setForeground(new Color(230,230,230));
		
		anadirOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		anadirOutputArea.add(anadirOutputLabel);
		anadirOutputArea.setVisible(false);
		outputPanel.add(anadirOutputArea);
		
		//--
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(nombreLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField nombreField = new JTextField(15);
		formPanel.add(nombreField, c);
		
		//--
		
		JButton enviarBtn = new JButton("ENVIAR");
		enviarBtn.setFocusPainted(false);
		enviarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		enviarBtn.setBackground(new Color(230,230,230));
		enviarBtn.setMaximumSize(new Dimension(125, 30));
		enviarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		enviarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nombre = nombreField.getText();	
				TCliente cliente = new TCliente(nombre);
				if (nombre.length() > 0 && !nombre.equals(" ")) {
					SingletonControlador.getInstancia().accion(EventosCliente.ANADIR_CLIENTE, cliente);
				} else {
					showOutputMsg(anadirOutputArea, anadirOutputLabel, "ERROR: El nombre introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 60)));
		panel.add(formPanel);
		panel.add(enviarBtn);
		
		//--
		
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
	
		//--
		
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));
		
		editarBuscarErrorArea = new JPanel();
		editarBuscarErrorArea.setLayout(new BoxLayout(editarBuscarErrorArea, BoxLayout.X_AXIS));
		editarBuscarErrorArea.setBackground(new Color(172, 40, 40));
		editarBuscarErrorArea.setMaximumSize(new Dimension(800, 50));
		
		editarBuscarErrorLabel = new JLabel("ERROR: El ID introducido no es valido.");
		editarBuscarErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		editarBuscarErrorLabel.setForeground(new Color(230,230,230));
		
		editarBuscarErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		editarBuscarErrorArea.add(editarBuscarErrorLabel);
		editarBuscarErrorArea.setVisible(false);
		errorPanel.add(editarBuscarErrorArea);
		
		//--
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel IDLabel = new JLabel("ID Cliente: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel editarPanel = new JPanel();
		editarPanel.setLayout(new BoxLayout(editarPanel, BoxLayout.Y_AXIS));
		editarPanel.setBackground(new Color(235, 237, 241));
		editarPanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));
		
		editarOutputArea = new JPanel();
		editarOutputArea.setLayout(new BoxLayout(editarOutputArea, BoxLayout.X_AXIS));
		editarOutputArea.setBackground(new Color(172, 40, 40));
		editarOutputArea.setMaximumSize(new Dimension(800, 50));
		
		editarOutputLabel = new JLabel("ERROR: El nombre introducido no es valido.");
		editarOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		editarOutputLabel.setForeground(new Color(230,230,230));
		
		editarOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		editarOutputArea.add(editarOutputLabel);
		editarOutputArea.setVisible(false);
		outputPanel.add(editarOutputArea);
		
		//--
		
		JPanel formPanel2 = new JPanel(new GridBagLayout());
		formPanel2.setBackground(new Color(235, 237, 241));
		formPanel2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel2.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel editarNombreLabel = new JLabel("Nombre: ");
		editarNombreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(editarNombreLabel, c2);
		
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		editarNombreField = new JTextField(15);
		formPanel2.add(editarNombreField, c2);
		
		//--
		
		JButton buscarBtn = new JButton("BUSCAR");
		buscarBtn.setFocusPainted(false);
		buscarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		buscarBtn.setBackground(new Color(230,230,230));
		buscarBtn.setMaximumSize(new Dimension(125, 30));
		buscarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String ID = IDField.getText();	
				if (ID.length() > 0 && !ID.equals(" ")) {;
					SingletonControlador.getInstancia().accion(EventosCliente.MODIFICAR_BUSCAR_CLIENTE, Integer.valueOf(ID));
				} else {
					showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, "ERROR: El ID introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		JButton confirmBtn = new JButton("CONFIRMAR");
		confirmBtn.setFocusPainted(false);
		confirmBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		confirmBtn.setBackground(new Color(230,230,230));
		confirmBtn.setMaximumSize(new Dimension(150, 30));
		confirmBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		confirmBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Integer ID = Integer.valueOf(IDField.getText());
				String nombre = editarNombreField.getText();	
				if (nombre.length() > 0 && !nombre.equals(" ")) {;
					TCliente cliente = new TCliente(ID, nombre);
					SingletonControlador.getInstancia().accion(EventosCliente.MODIFICAR_CLIENTE, cliente);
				} else {
					showOutputMsg(editarOutputArea, editarOutputLabel, "ERROR: El nombre introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0,60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);
		
		//--
		
		editarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		editarPanel.add(outputPanel);
		editarPanel.add(Box.createRigidArea(new Dimension(0,60)));
		editarPanel.add(formPanel2);
		editarPanel.add(confirmBtn);
		
		//-- 
		
		editarClientePanel.add(buscarPanel, "BUSCAR");
		editarClientePanel.add(editarPanel, "SECOND");
		add(editarClientePanel, "EDITAR");
		
		editarClientePanelCL.show(editarClientePanel, "BUSCAR");
		_localCL.show(this, "EDITAR");
		_currentPanel = editarClientePanel;
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
		
		String[] columns = {"ID Cliente", "Nombre"};

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
	
		//--
		
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));
		
		mostrarErrorArea = new JPanel();
		mostrarErrorArea.setLayout(new BoxLayout(mostrarErrorArea, BoxLayout.X_AXIS));
		mostrarErrorArea.setBackground(new Color(172, 40, 40));
		mostrarErrorArea.setMaximumSize(new Dimension(800, 50));
		
		mostrarErrorLabel = new JLabel("ERROR: El ID introducido no es valido.");
		mostrarErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		mostrarErrorLabel.setForeground(new Color(230,230,230));
		
		mostrarErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		mostrarErrorArea.add(mostrarErrorLabel);
		mostrarErrorArea.setVisible(false);
		errorPanel.add(mostrarErrorArea);
		
		//--
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBackground(new Color(235, 237, 241));
		formPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		formPanel.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel IDLabel = new JLabel("ID Cliente: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel clientePanel = new JPanel();
		clientePanel.setLayout(new BoxLayout(clientePanel, BoxLayout.Y_AXIS));
		clientePanel.setBackground(new Color(235, 237, 241));
		clientePanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 70));
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel IDLabel2 = new JLabel("ID Cliente: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDLabel2, c2);
		
		c2.gridy++;
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(nombreLabel, c2);
		
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
		
		//--
		
		JButton buscarBtn = new JButton("BUSCAR");
		buscarBtn.setFocusPainted(false);
		buscarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		buscarBtn.setBackground(new Color(230,230,230));
		buscarBtn.setMaximumSize(new Dimension(125, 30));
		buscarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String ID = IDField.getText();	
				if (ID.length() > 0 && !ID.equals(" ")) {
					SingletonControlador.getInstancia().accion(EventosCliente.MOSTRAR_CLIENTE, Integer.valueOf(ID));
				} else {
					showOutputMsg(mostrarErrorArea, mostrarErrorLabel, "ERROR: El ID introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		buscarPanel.add(errorPanel);
		buscarPanel.add(Box.createRigidArea(new Dimension(0, 60)));
		buscarPanel.add(formPanel);
		buscarPanel.add(buscarBtn);
		
		//--
		
		clientePanel.add(Box.createRigidArea(new Dimension(0, 160)));
		clientePanel.add(dataPanel);
		
		//--
		
		mostrarClientePanel.add(buscarPanel, "BUSCAR");
		mostrarClientePanel.add(clientePanel, "CLIENTE");
		add(mostrarClientePanel, "BUSCAR");
		
		mostrarClientePanelCL.show(mostrarClientePanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarClientePanel;
		
		//--
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
		switch (evento){
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
			case EventosCliente.LISTAR_CLIENTES_OK:
				@SuppressWarnings("unchecked") List<TCliente> listaClientes = (List<TCliente>) datos;
				
				addPathSeparator();
				createPathButton("MOSTRAR CLIENTES");
				mostrarPanel();
				
				for (TCliente c : listaClientes) {
					mostrarModel.addRow(new Object[]{c.getId().toString(), c.getNombre()});
				}
				System.out.println("Listar Clientes OK");
				break;
			case EventosCliente.LISTAR_CLIENTES_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Listar Clientes KO");
				break;
			case EventosCliente.MOSTRAR_CLIENTE_OK:
				cliente = (TCliente) datos;
				
				mostrarIDText.setText(cliente.getId().toString());
				mostrarNombreText.setText(cliente.getNombre());
				
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
				editarClientePanelCL.show(editarClientePanel, "SECOND");
				System.out.println("Editar Buscar Cliente OK");
				break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO:
				mensaje = (String) datos;
				
				showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, mensaje, false);
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
		}
	}
}
