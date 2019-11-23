package presentacion.empleado;

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

import negocio.empleado.TDependiente;
import negocio.empleado.TEmpleado;
import negocio.empleado.TEncargado;
import presentacion.factoria.GUI;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.ControladorAplicacion;
import presentacion.controladorAplicacion.EventosEmpleado;
import presentacion.factoria.FactoriaPresentacion;

public class EmpleadoGUIImpl extends JPanel implements EmpleadoGUI, GUI{
	private static final long serialVersionUID = 1L;

	private String name = "EMPLEADOS";
	
	private CardLayout _localCL;
	private JPanel _homePanel;
	private JPanel _currentPanel = _homePanel;
	
	private static FactoriaPresentacion presentacion = FactoriaPresentacion.getInstance();

	private JPanel pathPanel = presentacion.generarPath();
	List<Component> _lastPathComponents = new LinkedList<Component>();

	//--- COMPONENTES ---//
	
	private JPanel anadirOutputArea;
	private JLabel anadirOutputLabel;
	
	private JPanel borrarOutputArea;
	private JLabel borrarOutputLabel;
	
	private JPanel mostrarEmpleadoPanel;
	private CardLayout mostrarEmpleadoPanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarNombreText;
	private JLabel mostrarDNIText;
	private JLabel mostrarSalarioBaseText;
	private JLabel mostrarActivoText;
	private JLabel mostrarEncargadoText;
	private JLabel mostrarIncentivoText;
	private JLabel mostrarPracticanteText;
	
	private DefaultTableModel mostrarModel;
	
	private JPanel editarEmpleadoPanel;
	private CardLayout editarEmpleadoPanelCL;
	private JPanel editarBuscarErrorArea;
	private JLabel editarBuscarErrorLabel;
	private JPanel editarOutputArea;
	private JLabel editarOutputLabel;
	private JTextField editarNombreField;
	private JTextField editarDNIField;
	private JTextField editarSalarioField;
	private JTextField editarActivoField;
	private JTextField editarEncargadoField;
	private JTextField editarIncentivoField;
	private JTextField editarPracticanteField;
	
	public EmpleadoGUIImpl() {
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
		
		JButton anadirBtn = createMenuButton("resources/icons/empleado/anadir-empleado.png", new Color(91, 155, 213));
		anadirBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ANADIR EMPLEADO");
				anadirPanel();
			}
		});
		_homePanel.add(anadirBtn, c);
		
		c.gridx++;
		JButton editarBtn = createMenuButton("resources/icons/empleado/editar-empleado.png", new Color(255, 192, 0));
		editarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("EDITAR EMPLEADO");
				editarPanel();;
			}
		});
		_homePanel.add(editarBtn, c);
		
		c.gridx++;
		JButton borrarBtn = createMenuButton("resources/icons/empleado/eliminar-empleado.png", new Color(112, 173, 71));
		borrarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ELIMINAR EMPLEADO");
				borrarPanel();;
			}
		});
		_homePanel.add(borrarBtn, c);
		
		c.gridx = 0;
		c.gridy++;
		JButton listarBtn = createMenuButton("resources/icons/empleado/mostrar-empleados.png", new Color(234, 80, 54));
		listarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosEmpleado.LISTAR_EMPLEADOS, null);
				ControladorAplicacion.getInstance().accion(contexto);
			}
		});
		_homePanel.add(listarBtn, c);
		
		c.gridx++;
		JButton buscarBtn = createMenuButton("resources/icons/empleado/buscar-empleado.png", new Color(47, 85, 151));
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("MOSTRAR EMPLEADOS");
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
		button.setPreferredSize(new Dimension(200,125));
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
		
		anadirOutputLabel = new JLabel("ERROR: El nombre introducido no es valido.");
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
		formPanel.setMaximumSize(new Dimension(1024, 140));
				
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;	
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5,5,0,0);
		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(nombreLabel, c);
				
		c.gridy++;
		JLabel dniLabel = new JLabel("DNI: ");
		dniLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(dniLabel, c);
				
		c.gridy++;
		JLabel salarioBaseLabel = new JLabel("Salario base: ");
		salarioBaseLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(salarioBaseLabel, c);

		c.gridy++;
		JLabel encargadoLabel = new JLabel("Encargado(?): ");
		encargadoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(encargadoLabel, c);
				
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField nombreField = new JTextField(15);
		formPanel.add(nombreField, c);
				
		c.gridy++;
		JTextField dniField = new JTextField(15);
		formPanel.add(dniField, c);
			
		c.gridy++;
		JTextField salarioBaseField = new JTextField(15);
		formPanel.add(salarioBaseField, c);
		
		c.gridy++;
		JTextField encargadoField = new JTextField(15);
		formPanel.add(encargadoField, c);
				
		//--
				
		JButton enviarBtn = new JButton("ENVIAR");
		enviarBtn.setFocusPainted(false);
		enviarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		enviarBtn.setBackground(new Color(230,230,230));
		enviarBtn.setMaximumSize(new Dimension(125, 30));
		enviarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		enviarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				try {
					String nombre = nombreField.getText();
					String dni = dniField.getText();
					double salarioBase = Double.parseDouble(salarioBaseField.getText());
					boolean encargado = Boolean.valueOf(encargadoField.getText());

					if (nombre.length() > 0 && !nombre.equals("") && dni.length() > 0 && !dni.equals("") && salarioBaseField.getText().length() > 0 && !salarioBaseField.getText().equals("") && encargadoField.getText().length() > 0 && !encargadoField.getText().equals("")) {
						if(encargado == true) {
							String valor_incentivo = JOptionPane.showInputDialog(null, "Introduzca el incentivo", "Finalizar operacion", JOptionPane.INFORMATION_MESSAGE);
							if(valor_incentivo != null) {
								TEncargado e1 = new TEncargado(0, nombre, dni, salarioBase, true, Double.parseDouble(valor_incentivo));
								Context contexto = new Context(EventosEmpleado.ANADIR_EMPLEADO, e1);
								ControladorAplicacion.getInstance().accion(contexto);
							}
						}
						else {
							String practicante = JOptionPane.showInputDialog(null, "¿Es practicante?", "Finalizar operacion", JOptionPane.INFORMATION_MESSAGE);
							if(practicante != null) {
								TDependiente dependiente = new TDependiente(0, nombre, dni, salarioBase, true, Boolean.valueOf(practicante));
								Context contexto = new Context(EventosEmpleado.ANADIR_EMPLEADO, dependiente);
								ControladorAplicacion.getInstance().accion(contexto);
							}
						}
					} else {
						showOutputMsg(anadirOutputArea, anadirOutputLabel, "ERROR: El nombre introducido no es valido.", false);
					}
				} catch(NumberFormatException ex) {
					showOutputMsg(anadirOutputArea, anadirOutputLabel, "ERROR: Los valores introducidos no son validos.", false);
				}
			}
		});
				
		//--
				
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(formPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		panel.add(enviarBtn);
		
		//--
		
		add(panel, "ANADIR");
		_localCL.show(this, "ANADIR");
		_currentPanel = panel;
	}
	
	public void editarPanel() {
		editarEmpleadoPanel = new JPanel();
		editarEmpleadoPanelCL = new CardLayout();
		editarEmpleadoPanel.setLayout(editarEmpleadoPanelCL);
		editarEmpleadoPanel.setBackground(new Color(235, 237, 241));
		editarEmpleadoPanel.setMaximumSize(new Dimension(1024, 460));
		
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
		JLabel IDLabel = new JLabel("ID Empleado: ");
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
		formPanel2.setMaximumSize(new Dimension(1024, 190));
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel nombreLabel2 = new JLabel("Nombre: ");
		nombreLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(nombreLabel2, c2);
		
		c2.gridy++;
		JLabel dniLabel = new JLabel("DNI: ");
		dniLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(dniLabel, c2);
		
		c2.gridy++;
		JLabel salarioBaseLabel = new JLabel("Salario base: ");
		salarioBaseLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(salarioBaseLabel, c2);
		
		c2.gridy++;
		JLabel activoLabel = new JLabel("Activo: ");
		activoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(activoLabel, c2);
		
		c2.gridy++;
		JLabel encargadoLabel = new JLabel("Encargado: ");
		encargadoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(encargadoLabel, c2);
		
		c2.gridy++;
		JLabel incentivoLabel = new JLabel("Incentivo: ");
		incentivoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(incentivoLabel, c2);
		
		c2.gridy++;
		JLabel practicanteLabel = new JLabel("Practicante: ");
		practicanteLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(practicanteLabel, c2);
		
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		editarNombreField = new JTextField(15);
		formPanel2.add(editarNombreField, c2);
		
		c2.gridy++;
		editarDNIField = new JTextField(15);
		formPanel2.add(editarDNIField, c2);
		
		c2.gridy++;
		editarSalarioField = new JTextField(15);
		formPanel2.add(editarSalarioField, c2);
		
		c2.gridy++;
		editarActivoField = new JTextField(15);
		formPanel2.add(editarActivoField, c2);
		
		c2.gridy++;
		editarEncargadoField = new JTextField(15);
		formPanel2.add(editarEncargadoField, c2);
		
		c2.gridy++;
		editarIncentivoField = new JTextField(15);
		formPanel2.add(editarIncentivoField, c2);
		
		c2.gridy++;
		editarPracticanteField = new JTextField(15);
		formPanel2.add(editarPracticanteField, c2);
		
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
					Context contexto = new Context(EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO, Integer.valueOf(ID));
					ControladorAplicacion.getInstance().accion(contexto);
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
				String dni = editarDNIField.getText();
				double salario = Double.parseDouble(editarSalarioField.getText());
				Boolean activo = Boolean.valueOf(editarActivoField.getText());
				Boolean encargado = Boolean.valueOf(editarEncargadoField.getText());
				
				if (nombre.length() > 0 && !nombre.equals("") && dni.length() > 0 && !dni.equals("") && editarSalarioField.getText().length() > 0 && !editarSalarioField.getText().equals("") && editarActivoField.getText().length() > 0 && !editarActivoField.getText().equals("") && editarEncargadoField.getText().length() > 0 && !editarEncargadoField.getText().equals("")) {
					if(encargado == false) {
						TDependiente eDependiente = new TDependiente(ID, nombre, dni, salario, activo, Boolean.valueOf(editarPracticanteField.getText()));
						Context contexto = new Context(EventosEmpleado.MODIFICAR_EMPLEADO, eDependiente);
						ControladorAplicacion.getInstance().accion(contexto);
					}
					else {
						TEncargado eEncargado = new TEncargado(ID, nombre, dni, salario, activo, Double.parseDouble(editarIncentivoField.getText()));
						Context contexto = new Context(EventosEmpleado.MODIFICAR_EMPLEADO, eEncargado);
						ControladorAplicacion.getInstance().accion(contexto);
					}
				} else {
					showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, "ERROR: Los datos introducidos no son validos.", false);
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
		
		editarPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		editarPanel.add(outputPanel);
		editarPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		editarPanel.add(formPanel2);
		editarPanel.add(confirmBtn);
		
		//-- 
		
		editarEmpleadoPanel.add(buscarPanel, "BUSCAR");
		editarEmpleadoPanel.add(editarPanel, "SECOND");
		add(editarEmpleadoPanel, "EDITAR");
		
		editarEmpleadoPanelCL.show(editarEmpleadoPanel, "BUSCAR");
		_localCL.show(this, "EDITAR");
		_currentPanel = editarEmpleadoPanel;
	}
	
	public void borrarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		outputPanel.setBackground(new Color(235, 237, 241));
		outputPanel.setMaximumSize(new Dimension(1024, 50));
		
		borrarOutputArea = new JPanel();
		borrarOutputArea.setLayout(new BoxLayout(borrarOutputArea, BoxLayout.X_AXIS));
		borrarOutputArea.setBackground(new Color(172, 40, 40));
		borrarOutputArea.setMaximumSize(new Dimension(800, 50));
		
		borrarOutputLabel = new JLabel("ERROR: El ID introducido no es valido.");
		borrarOutputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		borrarOutputLabel.setForeground(new Color(230,230,230));
		
		borrarOutputArea.add(Box.createRigidArea(new Dimension(40, 0)));
		borrarOutputArea.add(borrarOutputLabel);
		borrarOutputArea.setVisible(false);
		outputPanel.add(borrarOutputArea);
		
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
		JLabel IDLabel = new JLabel("ID Empleado: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JButton borrarBtn = new JButton("ELIMINAR");
		borrarBtn.setFocusPainted(false);
		borrarBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		borrarBtn.setBackground(new Color(230,230,230));
		borrarBtn.setMaximumSize(new Dimension(125, 30));
		borrarBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		borrarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String ID = IDField.getText();	
				if (ID.length() > 0 && !ID.equals(" ")) {
					Context contexto = new Context(EventosEmpleado.BAJA_EMPLEADO, Integer.valueOf(ID));
					ControladorAplicacion.getInstance().accion(contexto);
				} else {
					showOutputMsg(borrarOutputArea, borrarOutputLabel, "ERROR: El ID introducido no es valido.", false);
				}
			}
		});
		
		//--
		
		panel.add(Box.createRigidArea(new Dimension(0, 40)));
		panel.add(outputPanel);
		panel.add(Box.createRigidArea(new Dimension(0,60)));
		panel.add(formPanel);
		panel.add(borrarBtn);
		
		//--
		
		add(panel, "ELIMINAR");
		
		_localCL.show(this, "ELIMINAR");
		_currentPanel = panel;
	}
	
	public void mostrarPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(235, 237, 241));
		panel.setMaximumSize(new Dimension(1024, 460));
		
		JLabel tableTitle = new JLabel("EMPLEADOS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));
		
		String[] columns = {"ID Empleado", "Nombre", "DNI", "Salario base", "Activo", "Encargado", "Incentivo", "Practicante"};
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
		mostrarEmpleadoPanel = new JPanel();
		mostrarEmpleadoPanelCL = new CardLayout();
		mostrarEmpleadoPanel.setLayout(mostrarEmpleadoPanelCL);
		mostrarEmpleadoPanel.setBackground(new Color(235, 237, 241));
		mostrarEmpleadoPanel.setMaximumSize(new Dimension(1024, 460));
		
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
		JLabel IDLabel = new JLabel("ID Empleado: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel turnoPanel = new JPanel();
		turnoPanel.setLayout(new BoxLayout(turnoPanel, BoxLayout.Y_AXIS));
		turnoPanel.setBackground(new Color(235, 237, 241));
		turnoPanel.setMaximumSize(new Dimension(1024, 460));
			
		//--
					
		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 250));
						
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;	
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel IDLabel2 = new JLabel("ID Empleado: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDLabel2, c2);
					
		c2.gridy++;
		JLabel nombreLabel = new JLabel("Nombre: ");	
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(nombreLabel, c2);
				
		c2.gridy++;
		JLabel dniLabel = new JLabel("DNI: ");
		dniLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(dniLabel, c2);
					
		c2.gridy++;
		JLabel salarioBaseLabel = new JLabel("Salario base: ");
		salarioBaseLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(salarioBaseLabel, c2);
		
		c2.gridy++;
		JLabel ActivoLabel = new JLabel("Activo: ");
		ActivoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(ActivoLabel, c2);
		
		c2.gridy++;
		JLabel encargadoLabel = new JLabel("Encargado: ");
		encargadoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(encargadoLabel, c2);
		
		c2.gridy++;
		JLabel incentivoLabel = new JLabel("Incentivo: ");
		incentivoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(incentivoLabel, c2);
		
		c2.gridy++;
		JLabel practicanteLabel = new JLabel("Practicante: ");
		practicanteLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(practicanteLabel, c2);
						
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		mostrarIDText = new JLabel("0");
		mostrarIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDText, c2);
						
		c2.gridy++;
		mostrarNombreText = new JLabel("Nombre");
		mostrarNombreText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarNombreText, c2);
				
		c2.gridy++;
		mostrarDNIText = new JLabel("0");
		mostrarDNIText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarDNIText, c2);
				
		c2.gridy++;
		mostrarSalarioBaseText = new JLabel("0");
		mostrarSalarioBaseText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarSalarioBaseText, c2);
		
		c2.gridy++;
		mostrarActivoText = new JLabel("true");
		mostrarActivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarActivoText, c2);
		
		c2.gridy++;
		mostrarEncargadoText = new JLabel("true");
		mostrarEncargadoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarEncargadoText, c2);
		
		c2.gridy++;
		mostrarIncentivoText = new JLabel("true");
		mostrarIncentivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIncentivoText, c2);
		
		c2.gridy++;
		mostrarPracticanteText = new JLabel("true");
		mostrarPracticanteText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarPracticanteText, c2);
						
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
					Context contexto = new Context(EventosEmpleado.MOSTRAR_EMPLEADO, Integer.valueOf(ID));
					ControladorAplicacion.getInstance().accion(contexto);
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
					
		turnoPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		turnoPanel.add(dataPanel);
					
		//--
						
		mostrarEmpleadoPanel.add(buscarPanel, "BUSCAR");
		mostrarEmpleadoPanel.add(turnoPanel, "EMPLEADO");
		add(mostrarEmpleadoPanel, "BUSCAR");
						
		mostrarEmpleadoPanelCL.show(mostrarEmpleadoPanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarEmpleadoPanel;
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
		TEmpleado empleado;
		switch (evento){
			case EventosEmpleado.ANADIR_EMPLEADO_OK:
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, true);
				System.out.println("Anadir Empleado OK");
				break;
			case EventosEmpleado.ANADIR_EMPLEADO_KO: {
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, false);
				System.out.println("Anadir Empleado KO");
				}
				break;
			case EventosEmpleado.BAJA_EMPLEADO_OK:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, true);
				System.out.println("Eliminar Empleado OK");
				break;
			case EventosEmpleado.BAJA_EMPLEADO_KO:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, false);
				System.out.println("Eliminar Empleado KO");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_OK:
				empleado = (TEmpleado) datos;
				Integer id = empleado.getId();
				String salario = String.valueOf(empleado.getSalarioBase());

				mostrarIDText.setText(id.toString());
				mostrarNombreText.setText(empleado.getNombre());
				mostrarDNIText.setText(empleado.getDNI());
				mostrarSalarioBaseText.setText(salario);
				mostrarActivoText.setText(Boolean.toString(empleado.isActivo()));
				mostrarEncargadoText.setText(Boolean.toString(empleado.isEncargado()));
				
				if(empleado.isEncargado() == true) {
					TEncargado eEncargado = (TEncargado)datos;
					String incentivo = String.valueOf(eEncargado.getIncentivo());
					mostrarIncentivoText.setText(incentivo);
					mostrarPracticanteText.setText("N/A");
				}
				else {
					TDependiente eDependiente = (TDependiente)datos;
					String practicante = String.valueOf(eDependiente.getPracticante());
					mostrarPracticanteText.setText(practicante);
					mostrarIncentivoText.setText("N/A");
				}
				
				mostrarEmpleadoPanelCL.show(mostrarEmpleadoPanel, "EMPLEADO");
				System.out.println("Mostrar Empleado OK");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(mostrarErrorArea, mostrarErrorLabel, mensaje, false);
				System.out.println("Mostrar Empleado KO");
				break;
			case EventosEmpleado.LISTAR_EMPLEADO_OK:
				@SuppressWarnings("unchecked") List<TEmpleado> listaEmpleados = (List<TEmpleado>) datos;
				addPathSeparator();
				createPathButton("LISTAR EMPLEADOS");
				mostrarPanel();
				
				for(TEmpleado e : listaEmpleados) {
					if(e.isEncargado() == true) {
						TEncargado eEncargado = (TEncargado)e;
						mostrarModel.addRow(new Object[]{e.getId(), e.getNombre(), e.getDNI(), e.getSalarioBase(), e.isActivo(), e.isEncargado(), eEncargado.getIncentivo(), "N/A"});
					}
					else {
						TDependiente eDependiente = (TDependiente)e;
						mostrarModel.addRow(new Object[]{e.getId(), e.getNombre(), e.getDNI(), e.getSalarioBase(), e.isActivo(), e.isEncargado(), "N/A", eDependiente.getPracticante()});
					}
				}
				
				System.out.println("Listar Empleados OK");
				break;
			case EventosEmpleado.LISTAR_EMPLEADO_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Listar Empleados KO");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_OK:
				empleado = (TEmpleado) datos;
				
				editarNombreField.setText(empleado.getNombre());
				editarDNIField.setText(empleado.getDNI());
				editarSalarioField.setText(String.valueOf(empleado.getSalarioBase()));
				editarActivoField.setText(Boolean.toString(empleado.isActivo()));
				editarEncargadoField.setText(Boolean.toString(empleado.isEncargado()));
				editarEncargadoField.setEnabled(false);
				
				if(empleado.isEncargado() == true) {
					TEncargado eEncargado = (TEncargado)datos;
					editarIncentivoField.setText(String.valueOf(eEncargado.getIncentivo()));
					editarPracticanteField.setText("N/A");
					editarPracticanteField.setEnabled(false);
				}
				else {
					TDependiente eDependiente = (TDependiente)datos;
					editarPracticanteField.setText(String.valueOf(eDependiente.getPracticante()));
					editarIncentivoField.setText("N/A");
					editarIncentivoField.setEnabled(false);
				}
				
				editarEmpleadoPanelCL.show(editarEmpleadoPanel, "SECOND");
				System.out.println("Editar Buscar Empleado OK");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_KO:
				mensaje = (String) datos;

				showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, mensaje, false);
				System.out.println("Editar Buscar Empleado KO");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_OK:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, true);
				System.out.println("Editar Empleado OK");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, false);
				System.out.println("Editar Empleado KO");
				break;
		}
	}
}
