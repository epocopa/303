package presentacion.turno;

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
import java.time.LocalTime;
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

import negocio.producto.TProducto;
import negocio.producto.TProductoCalzado;
import negocio.producto.TProductoTextil;
import presentacion.factoria.GUI;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.ControladorAplicacion;
import presentacion.controladorAplicacion.ControladorAplicacionJPA;
import presentacion.controladorAplicacion.EventosProducto;
import presentacion.controladorAplicacion.EventosTurno;
import presentacion.factoria.FactoriaPresentacion;

public class TurnoGUIImpl extends JPanel implements TurnoGUI, GUI{
	private static final long serialVersionUID = 1L;

	private String name = "TURNOS";
	
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
	
	private JPanel mostrarTurnoPanel;
	private CardLayout mostrarTurnoPanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarNombreText;
	private JLabel mostrarHoraInicioText;
	private JLabel mostrarHoraFinText;
	private JLabel mostrarActivoText;
	
	private DefaultTableModel mostrarModel;
	
	private JPanel editarTurnoPanel;
	private CardLayout editarTurnoPanelCL;
	private JPanel editarBuscarErrorArea;
	private JLabel editarBuscarErrorLabel;
	private JPanel editarOutputArea;
	private JLabel editarOutputLabel;
	private JTextField editarNombreField;
	private JTextField editarHoraInicioField;
	private JTextField editarHoraFinField;
	private JTextField editarActivoField;
	
	public TurnoGUIImpl() {
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
		
		JButton anadirBtn = createMenuButton("resources/icons/turno/anadir-turno.png", new Color(91, 155, 213));
		anadirBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ANADIR TURNO");
				anadirPanel();
			}
		});
		_homePanel.add(anadirBtn, c);
		
		c.gridx++;
		JButton editarBtn = createMenuButton("resources/icons/turno/editar-turno.png", new Color(255, 192, 0));
		editarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("EDITAR TURNO");
				editarPanel();;
			}
		});
		_homePanel.add(editarBtn, c);
		
		c.gridx++;
		JButton borrarBtn = createMenuButton("resources/icons/turno/eliminar-turno.png", new Color(112, 173, 71));
		borrarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("ELIMINAR TURNO");
				borrarPanel();;
			}
		});
		_homePanel.add(borrarBtn, c);
		
		c.gridx = 0;
		c.gridy++;
		JButton listarBtn = createMenuButton("resources/icons/turno/mostrar-turnos.png", new Color(234, 80, 54));
		listarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosTurno.LISTAR_TURNO, null);
				ControladorAplicacionJPA.getInstance().accion(contexto);
			}
		});
		_homePanel.add(listarBtn, c);
		
		c.gridx++;
		JButton buscarBtn = createMenuButton("resources/icons/turno/buscar-turno.png", new Color(47, 85, 151));
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("MOSTRAR TURNO");
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
		JLabel horaInicioLabel = new JLabel("Hora inicio: ");
		horaInicioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(horaInicioLabel, c);
				
		c.gridy++;
		JLabel horaFinLabel = new JLabel("Hora fin: ");
		horaFinLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(horaFinLabel, c);
				
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField nombreField = new JTextField(15);
		formPanel.add(nombreField, c);
				
		c.gridy++;
		JTextField horaInicioField = new JTextField(15);
		formPanel.add(horaInicioField, c);
			
		c.gridy++;
		JTextField horaFinField = new JTextField(15);
		formPanel.add(horaFinField, c);
				
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
					TTurno turno = new TTurno();
					String nombre = nombreField.getText();
					LocalTime horaInicio = LocalTime.parse(horaInicioField.getText());
					LocalTime horaFin = LocalTime.parse(horaFinField.getText());
					if (nombre.length() > 0 && !nombre.equals("") && horaInicioField.getText().length() > 0 && !horaInicioField.getText().equals("") && horaFinField.getText().length() > 0 && !horaFinField.getText().equals("")) {
						turno.setNombre(nombre);
						turno.setHoraInicio(horaInicio);
						turno.setHoraFin(horaFin);
						turno.setActivo(true);
						Context contexto = new Context(EventosTurno.ANADIR_TURNO, turno);
						ControladorAplicacionJPA.getInstance().accion(contexto);
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
		editarTurnoPanel = new JPanel();
		editarTurnoPanelCL = new CardLayout();
		editarTurnoPanel.setLayout(editarTurnoPanelCL);
		editarTurnoPanel.setBackground(new Color(235, 237, 241));
		editarTurnoPanel.setMaximumSize(new Dimension(1024, 460));
		
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
		JLabel IDLabel = new JLabel("ID Turno: ");
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
		JLabel horaInicioLabel = new JLabel("Hora inicio: ");
		horaInicioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(horaInicioLabel, c2);
		
		c2.gridy++;
		JLabel horaFinLabel = new JLabel("Hora fin: ");
		horaFinLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(horaFinLabel, c2);
		
		c2.gridy++;
		JLabel activoLabel = new JLabel("Activo: ");
		activoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel2.add(activoLabel, c2);
		
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		editarNombreField = new JTextField(15);
		formPanel2.add(editarNombreField, c2);
		
		c2.gridy++;
		editarHoraInicioField = new JTextField(15);
		formPanel2.add(editarHoraInicioField, c2);
		
		c2.gridy++;
		editarHoraFinField = new JTextField(15);
		formPanel2.add(editarHoraFinField, c2);
		
		c2.gridy++;
		editarActivoField = new JTextField(15);
		formPanel2.add(editarActivoField, c2);
		
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
					Context contexto = new Context(EventosTurno.MODIFICAR_BUSCAR_TURNO, Integer.valueOf(ID));
					ControladorAplicacionJPA.getInstance().accion(contexto);
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
				LocalTime horaInicio = LocalTime.parse(editarHoraInicioField.getText());
				LocalTime horaFin = LocalTime.parse(editarHoraFinField.getText());
				Boolean activo = Boolean.valueOf(editarActivoField.getText());

				if (nombre.length() > 0 && !nombre.equals("") && horaInicio.toString().length() > 0 && !horaInicio.toString().equals("") && horaFin.toString().length() > 0 && !horaFin.toString().equals("") && activo.toString().length() > 0 && !activo.toString().equals("")) {
					TTurno turno = new TTurno(ID, nombre, horaInicio, horaFin, activo);
					Context contexto = new Context(EventosTurno.MODIFICAR_TURNO, turno);
					ControladorAplicacionJPA.getInstance().accion(contexto);
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
		
		editarTurnoPanel.add(buscarPanel, "BUSCAR");
		editarTurnoPanel.add(editarPanel, "SECOND");
		add(editarTurnoPanel, "EDITAR");
		
		editarTurnoPanelCL.show(editarTurnoPanel, "BUSCAR");
		_localCL.show(this, "EDITAR");
		_currentPanel = editarTurnoPanel;
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
		JLabel IDLabel = new JLabel("ID Turno: ");
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
					Context contexto = new Context(EventosTurno.BAJA_TURNO, Integer.valueOf(ID));
					ControladorAplicacionJPA.getInstance().accion(contexto);
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
		
		JLabel tableTitle = new JLabel("TURNOS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));
		
		String[] columns = {"ID Turno", "Nombre", "Hora Inicio", "Hora Fin", "Activo"};
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
		mostrarTurnoPanel = new JPanel();
		mostrarTurnoPanelCL = new CardLayout();
		mostrarTurnoPanel.setLayout(mostrarTurnoPanelCL);
		mostrarTurnoPanel.setBackground(new Color(235, 237, 241));
		mostrarTurnoPanel.setMaximumSize(new Dimension(1024, 460));
		
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
		
		mostrarErrorLabel = new JLabel("ERROR: El nombre introducido no es valido.");
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
		JLabel IDLabel = new JLabel("ID Turno: ");
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
		dataPanel.setMaximumSize(new Dimension(1024, 210));
						
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;	
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel IDLabel2 = new JLabel("ID Turno: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDLabel2, c2);
					
		c2.gridy++;
		JLabel nombreLabel = new JLabel("Nombre: ");	
		nombreLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(nombreLabel, c2);
				
		c2.gridy++;
		JLabel horaInicioLabel = new JLabel("Hora inicio: ");
		horaInicioLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(horaInicioLabel, c2);
					
		c2.gridy++;
		JLabel horaFinLabel = new JLabel("Hora fin: ");
		horaFinLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(horaFinLabel, c2);
		
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
		mostrarNombreText = new JLabel("Nombre");
		mostrarNombreText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarNombreText, c2);
				
		c2.gridy++;
		mostrarHoraInicioText = new JLabel("0");
		mostrarHoraInicioText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarHoraInicioText, c2);
				
		c2.gridy++;
		mostrarHoraFinText = new JLabel("0");
		mostrarHoraFinText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarHoraFinText, c2);
		
		c2.gridy++;
		mostrarActivoText = new JLabel("true");
		mostrarActivoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarActivoText, c2);
						
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
					Context contexto = new Context(EventosTurno.MOSTRAR_TURNO, Integer.valueOf(ID));
					ControladorAplicacionJPA.getInstance().accion(contexto);
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
						
		mostrarTurnoPanel.add(buscarPanel, "BUSCAR");
		mostrarTurnoPanel.add(turnoPanel, "TURNO");
		add(mostrarTurnoPanel, "BUSCAR");
						
		mostrarTurnoPanelCL.show(mostrarTurnoPanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarTurnoPanel;
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
		TTurno turno;
		switch (evento){
			case EventosTurno.ANADIR_TURNO_OK:
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, true);
				System.out.println("Anadir Turno OK");
				break;
			case EventosTurno.ANADIR_TURNO_KO: {
				mensaje = (String) datos;
				showOutputMsg(anadirOutputArea, anadirOutputLabel, mensaje, false);
				System.out.println("Anadir Turno KO");
				}
				break;
			case EventosTurno.BAJA_TURNO_OK:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, true);
				System.out.println("Eliminar Turno OK");
				break;
			case EventosTurno.BAJA_TURNO_KO:
				mensaje = (String) datos;
				showOutputMsg(borrarOutputArea, borrarOutputLabel, mensaje, false);
				System.out.println("Eliminar Turno KO");
				break;
			case EventosTurno.MOSTRAR_TURNO_OK:
				turno = (TTurno) datos;
				Integer id = turno.getId();

				mostrarIDText.setText(id.toString());
				mostrarNombreText.setText(turno.getNombre());
				mostrarHoraInicioText.setText(turno.getHoraInicio().toString());
				mostrarHoraFinText.setText(turno.getHoraFin().toString());
				mostrarActivoText.setText(Boolean.toString(turno.isActivo()));
				
				mostrarTurnoPanelCL.show(mostrarTurnoPanel, "TURNO");
				System.out.println("Mostrar Turno OK");
				break;
			case EventosTurno.MOSTRAR_TURNO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(mostrarErrorArea, mostrarErrorLabel, mensaje, false);
				System.out.println("Mostrar Turno KO");
				break;
			case EventosProducto.LISTAR_PRODUCTOS_OK:
				@SuppressWarnings("unchecked") List<TTurno> listaTurnos = (List<TTurno>) datos;
				addPathSeparator();
				createPathButton("LISTAR TURNOS");
				mostrarPanel();
				
				for(TTurno t : listaTurnos) {
					mostrarModel.addRow(new Object[]{t.getId(), t.getNombre(), t.getHoraInicio().toString(), t.getHoraFin().toString(), t.isActivo()});
				}
				
				System.out.println("Listar Turnos OK");
				break;
			case EventosTurno.LISTAR_TURNO_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Listar Turnos KO");
				break;
			case EventosTurno.MODIFICAR_BUSCAR_TURNO_OK:
				turno = (TTurno) datos;
				
				editarNombreField.setText(turno.getNombre());
				editarHoraInicioField.setText(turno.getHoraInicio().toString());
				editarHoraFinField.setText(turno.getHoraFin().toString());
				editarActivoField.setText(Boolean.toString(turno.isActivo()));
				
				editarTurnoPanelCL.show(editarTurnoPanel, "SECOND");
				System.out.println("Editar Buscar Turno OK");
				break;
			case EventosTurno.MODIFICAR_BUSCAR_TURNO_KO:
				mensaje = (String) datos;

				showOutputMsg(editarBuscarErrorArea, editarBuscarErrorLabel, mensaje, false);
				System.out.println("Editar Buscar Turno KO");
				break;
			case EventosTurno.MODIFICAR_TURNO_OK:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, true);
				System.out.println("Editar Turno OK");
				break;
			case EventosTurno.MODIFICAR_TURNO_KO:
				mensaje = (String) datos;
				
				showOutputMsg(editarOutputArea, editarOutputLabel, mensaje, false);
				System.out.println("Editar Turno KO");
				break;
		}
	}
}
