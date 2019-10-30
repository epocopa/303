package main.java.presentacion.factura;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import negocio.factura.TFactura;
import negocio.producto.TProducto;
import presentacion.controladorAplicacion.EventosFactura;
import presentacion.factoria.GUI;
import presentacion.factoria.FactoriaPresentacion;

public class FacturaGUIImpl extends JPanel implements FacturaGUI, GUI{
	private static final long serialVersionUID = 1L;

	private String name = "FACTURAS";
	private Integer Id;
	
	private CardLayout _localCL;
	private JPanel _homePanel;
	private JPanel _currentPanel = _homePanel;
	
	private static FactoriaPresentacion presentacion = FactoriaPresentacion.getInstance();

	private JPanel pathPanel = presentacion.generarPath();
	List<Component> _lastPathComponents = new LinkedList<Component>();
	
	//--- COMPONENTES ---//

	private JPanel mostrarFacturaPanel;
	private CardLayout mostrarFacturaPanelCL;
	private JPanel mostrarErrorArea;
	private JLabel mostrarErrorLabel;
	private JLabel mostrarIDText;
	private JLabel mostrarDescuentoText;
	private JLabel mostrarPrecioText;
	private JLabel mostrarFechaText;
	private JLabel mostrarIDClienteText;
	private JLabel mostrarIDEmpleadoText;
	
	DefaultTableModel model;
	JTable dataTable;
	
	private JPanel facturasMainPanel;
	private CardLayout facturasMainPanelCL;
	private DefaultTableModel facturasModel;
	private JPanel facturaErrorArea;
	private JLabel facturaErrorLabel;
	
	private String descuentoAplicado;
	private JLabel descuentoLabel;
	private JLabel totalPrecio;
	
	private JTextField anadirIDProducto;
	private JTextField anadirProductoCantidad;
	
	private JTextField borrarIDProducto;
	private JTextField borrarProductoCantidad;
	
	public FacturaGUIImpl() {
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
		
		JButton abrirBtn = createMenuButton("resources/icons/facturas/abrir-factura.png", new Color(77, 198, 51));
		abrirBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				JTextField IDCliente = new JTextField();
				JTextField IDEmpleado = new JTextField();
				final JComponent[] input = new JComponent[] {
				        new JLabel("ID Cliente"),
				        IDCliente,
				        new JLabel("ID Empleado"),
				        IDEmpleado,
				};
				Object[] options = {"Confirmar", "Cancelar"};
				int result = JOptionPane.showOptionDialog(null, input, " Abrir Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (result == JOptionPane.OK_OPTION) {
					String IDClienteText = IDCliente.getText(); 
					String IDEmpleadoText = IDEmpleado.getText(); 
					
					if ((IDClienteText.length() > 0 && !IDClienteText.equals(" ")) && (IDEmpleadoText.length() > 0 && !IDEmpleadoText.equals(" "))) {
						TFactura factura = new TFactura(0, 0.0, 0.0,  null, Integer.valueOf(IDEmpleadoText), Integer.valueOf(IDClienteText));
						SingletonControlador.getInstancia().accion(EventosFactura.ABRIR_FACTURA, factura);
					} else {
						JOptionPane.showMessageDialog(null, "ERROR: Los datos introducidos no son validos.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		_homePanel.add(abrirBtn, c);
		
		c.gridx++;
		JButton facturasBtn = createMenuButton("resources/icons/clientes/facturas-cliente.png", new Color(47, 133, 28));
		facturasBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("FACTURAS CLIENTE");
				facturasPanel();
			}
		});
		_homePanel.add(facturasBtn, c);
		
		c.gridx++;
		JButton buscarBtn = createMenuButton("resources/icons/facturas/buscar-factura.png", new Color(47, 101, 175));
		buscarBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addPathSeparator();
				createPathButton("BUSCAR FACTURA");
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
		button.setPreferredSize(new Dimension(200,150));
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
		for (int row = 0; row < table.getRowCount(); row++){
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
		
		String[] columns = {"ID Producto", "Nombre", "Cantidad", "Calorias", "Precio por Unidad"};

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
		
		//--
		
		JLabel totalTitle = new JLabel("TOTAL: ");
		totalTitle.setForeground(new Color(230,230,230));
		totalTitle.setFont(new Font("Arial", Font.BOLD, 12));
		totalTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		totalPrecio = new JLabel("0.0");
		totalPrecio.setForeground(new Color(230,230,230));
		totalPrecio.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel descuentoTitle = new JLabel("DESCUENTO: ");
		descuentoTitle.setForeground(new Color(230,230,230));
		descuentoTitle.setFont(new Font("Arial", Font.BOLD, 10));
		descuentoTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		descuentoLabel = new JLabel("0%");
		descuentoLabel.setForeground(new Color(230,230,230));
		descuentoLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		toolPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JButton anadirProducto = createToolButton("resources/icons/tools/add.png", new Color(63, 164, 31), "Anade un producto a la factura");
		anadirProducto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				anadirIDProducto = new JTextField();
				anadirProductoCantidad = new JTextField();
				final JComponent[] input = new JComponent[] {
				        new JLabel("ID Producto"),
				        anadirIDProducto,
				        new JLabel("Cantidad"),
				        anadirProductoCantidad,
				};
				Object[] options = {"Anadir", "Cancelar"};
				int result = JOptionPane.showOptionDialog(null, input, " Anadir Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (result == JOptionPane.OK_OPTION) {
					String IDProductoText = anadirIDProducto.getText();
					String productoCantidadText = anadirProductoCantidad.getText();
					if ((IDProductoText.length() > 0 && !IDProductoText.equals(" ")) && (productoCantidadText.length() > 0 && !productoCantidadText.equals(" "))) {
						TProducto producto = new TProducto(Integer.valueOf(IDProductoText), null, 0.0,  0, Integer.valueOf(productoCantidadText), true);
						SingletonControlador.getInstancia().accion(EventosFactura.ANADIR_PRODUCTO_A_F, producto);
					} else {
						JOptionPane.showMessageDialog(null, "ERROR: Los datos introducidos no son validos.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		toolPanel.add(anadirProducto);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton borrarProducto = createToolButton("resources/icons/tools/delete.png", new Color(220, 34, 34), "Borra un producto de la factura");
		borrarProducto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				borrarIDProducto = new JTextField();
				borrarProductoCantidad = new JTextField();
				final JComponent[] input = new JComponent[] {
				        new JLabel("ID Producto"),
				        borrarIDProducto,
				        new JLabel("Cantidad"),
				        borrarProductoCantidad,
				};
				Object[] options = {"Eliminar", "Cancelar"};
				int result = JOptionPane.showOptionDialog(null, input, " Eliminar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (result == JOptionPane.OK_OPTION) {
					String IDProductoText = borrarIDProducto.getText();
					String productoCantidadText = borrarProductoCantidad.getText();
					if ((IDProductoText.length() > 0 && !IDProductoText.equals(" ")) && (productoCantidadText.length() > 0 && !productoCantidadText.equals(" "))) {
						TProducto producto = new TProducto(Integer.valueOf(IDProductoText), null, 0.0,  0, Integer.valueOf(productoCantidadText), true);
						SingletonControlador.getInstancia().accion(EventosFactura.BORRAR_PRODUCTO, producto);
					} else {
						JOptionPane.showMessageDialog(null, "ERROR: Los datos introducidos no son validos.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		toolPanel.add(borrarProducto);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton aplicarDescuento = createToolButton("resources/icons/tools/discount.png", new Color(237, 162, 38), "Aplica un descuento a la factura");
		aplicarDescuento.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField nuevoDescuento = new JTextField();
				final JComponent[] input = new JComponent[] {
				        new JLabel("Descuento"),
				        nuevoDescuento,
				};
				Object[] options = {"Aplicar", "Cancelar"};
				int result = JOptionPane.showOptionDialog(null, input, " Aplicar Descuento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (result == JOptionPane.OK_OPTION) {
					descuentoAplicado = nuevoDescuento.getText();
					
					if ((descuentoAplicado.length() > 0 && !descuentoAplicado.equals(" "))) {
						TFactura factura = new TFactura(Id, Double.valueOf(descuentoAplicado), 0.0,  null, 0, 0);
						SingletonControlador.getInstancia().accion(EventosFactura.APLICAR_DESCUENTO, factura);
					} else {
						JOptionPane.showMessageDialog(null, "ERROR: El valor introducido no es valido.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		toolPanel.add(aplicarDescuento);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton confirmar = createToolButton("resources/icons/tools/complete.png", new Color(38, 180, 237), "Confirma y cierra la factura");
		confirmar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final JComponent[] input = new JComponent[] {
				        new JLabel("¿Desea enviar y cerrar su factura?"),
				};
				Object[] options = {"Si", "No"};
				int result = JOptionPane.showOptionDialog(null, input, " Confirmar Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (result == JOptionPane.OK_OPTION) {
					TFactura factura = new TFactura(Id, 0.0, Double.valueOf(totalPrecio.getText()), null, 0, 0);
					SingletonControlador.getInstancia().accion(EventosFactura.CERRAR_FACTURA, factura);
				}
			}
		});
		toolPanel.add(confirmar);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		toolPanel.add(descuentoTitle);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		toolPanel.add(descuentoLabel);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		toolPanel.add(totalTitle);
		toolPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		toolPanel.add(totalPrecio);
		
		//--
		
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
	
	public void facturasPanel() {
		facturasMainPanel = new JPanel();
		facturasMainPanelCL = new CardLayout();
		facturasMainPanel.setLayout(facturasMainPanelCL);
		facturasMainPanel.setBackground(new Color(235, 237, 241));
		facturasMainPanel.setMaximumSize(new Dimension(1024, 460));
		
		JPanel buscarPanel = new JPanel();
		buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
		buscarPanel.setBackground(new Color(235, 237, 241));
		buscarPanel.setMaximumSize(new Dimension(1024, 460));
	
		//--
		
		JPanel errorPanel = new JPanel();
		errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
		errorPanel.setBackground(new Color(235, 237, 241));
		errorPanel.setMaximumSize(new Dimension(1024, 50));
		
		facturaErrorArea = new JPanel();
		facturaErrorArea.setLayout(new BoxLayout(facturaErrorArea, BoxLayout.X_AXIS));
		facturaErrorArea.setBackground(new Color(172, 40, 40));
		facturaErrorArea.setMaximumSize(new Dimension(800, 50));
		
		facturaErrorLabel = new JLabel("ERROR: El ID introducido no es valido.");
		facturaErrorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		facturaErrorLabel.setForeground(new Color(230,230,230));
		
		facturaErrorArea.add(Box.createRigidArea(new Dimension(40, 0)));
		facturaErrorArea.add(facturaErrorLabel);
		facturaErrorArea.setVisible(false);
		errorPanel.add(facturaErrorArea);
		
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
		
		JPanel facturasPanel = new JPanel();
		facturasPanel.setLayout(new BoxLayout(facturasPanel, BoxLayout.Y_AXIS));
		facturasPanel.setBackground(new Color(235, 237, 241));
		facturasPanel.setMaximumSize(new Dimension(1024, 460));
		
		JLabel tableTitle = new JLabel("FACTURAS");
		tableTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tablePanel.setBackground(new Color(235, 237, 241));
		tablePanel.setMaximumSize(new Dimension(800, 320));
		
		String[] columns = {"ID Factura", "ID Cliente", "ID Empleado", "Descuento", "Precio", "Fecha"};

		facturasModel = new DefaultTableModel(); 
        for (String column : columns) {
        	facturasModel.addColumn(column);
        }
		JTable dataTable = new JTable(facturasModel);
		dataTable.setEnabled(false);
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.setPreferredScrollableViewportSize(new Dimension(450, 63));
		dataTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(dataTable);
		tablePanel.add(scrollPane);
		
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
					SingletonControlador.getInstancia().accion(EventosFactura.FACTURAS_PAGADAS_POR_CLIENTE, Integer.valueOf(ID));
				} else {
					showOutputMsg(facturaErrorArea, facturaErrorLabel, "ERROR: El ID introducido no es valido.", false);
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
		
		facturasPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		facturasPanel.add(tableTitle);
		facturasPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		facturasPanel.add(tablePanel);
		
		//--
		
		facturasMainPanel.add(buscarPanel, "BUSCAR");
		facturasMainPanel.add(facturasPanel, "FACTURAS PAGADAS");
		add(facturasMainPanel, "FACTURAS PAGADAS");
		
		facturasMainPanelCL.show(facturasMainPanel, "BUSCAR");
		_localCL.show(this, "FACTURAS PAGADAS");
		_currentPanel = facturasMainPanel;
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
		JLabel IDLabel = new JLabel("ID Factura: ");
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		formPanel.add(IDLabel, c);
		
		c.gridx++;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		JTextField IDField = new JTextField(15);
		formPanel.add(IDField, c);
		
		//--
		
		JPanel facturaPanel = new JPanel();
		facturaPanel.setLayout(new BoxLayout(facturaPanel, BoxLayout.Y_AXIS));
		facturaPanel.setBackground(new Color(235, 237, 241));
		facturaPanel.setMaximumSize(new Dimension(1024, 460));
			
		//--
					
		JPanel dataPanel = new JPanel(new GridBagLayout());
		dataPanel.setBackground(new Color(235, 237, 241));
		dataPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		dataPanel.setMaximumSize(new Dimension(1024, 190));
						
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;	
		c2.anchor = GridBagConstraints.LINE_END;
		c2.insets = new Insets(5,5,0,0);
		JLabel IDLabel2 = new JLabel("ID Factura: ");
		IDLabel2.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDLabel2, c2);
					
		c2.gridy++;
		JLabel descuentoLabel = new JLabel("Descuento: ");	
		descuentoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(descuentoLabel, c2);
				
		c2.gridy++;
		JLabel PrecioLabel = new JLabel("Precio: ");
		PrecioLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(PrecioLabel, c2);
					
		c2.gridy++;
		JLabel FechaLabel = new JLabel("Fecha: ");
		FechaLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(FechaLabel, c2);
		
		c2.gridy++;
		JLabel IDClienteLabel = new JLabel("ID Cliente: ");
		IDClienteLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDClienteLabel, c2);
		
		c2.gridy++;
		JLabel IDEmpleadoLabel = new JLabel("ID Empleado: ");
		IDEmpleadoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
		dataPanel.add(IDEmpleadoLabel, c2);
						
		c2.gridx++;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.LINE_START;
		mostrarIDText = new JLabel("0");
		mostrarIDText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDText, c2);
						
		c2.gridy++;
		mostrarDescuentoText = new JLabel("0.0");
		mostrarDescuentoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarDescuentoText, c2);
				
		c2.gridy++;
		mostrarPrecioText = new JLabel("0");
		mostrarPrecioText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarPrecioText, c2);
				
		c2.gridy++;
		mostrarFechaText = new JLabel("0");
		mostrarFechaText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarFechaText, c2);
		
		c2.gridy++;
		mostrarIDClienteText = new JLabel("0");
		mostrarIDClienteText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDClienteText, c2);
		
		c2.gridy++;
		mostrarIDEmpleadoText = new JLabel("0");
		mostrarIDEmpleadoText.setFont(new Font("Arial", Font.PLAIN, 18));
		dataPanel.add(mostrarIDEmpleadoText, c2);
						
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
					SingletonControlador.getInstancia().accion(EventosFactura.MOSTRAR_FACTURA, Integer.valueOf(ID));
				} else {
					showOutputMsg(mostrarErrorArea, mostrarErrorLabel, "ERROR: El nombre introducido no es valido.", false);
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
					
		facturaPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		facturaPanel.add(dataPanel);
					
		//--
						
		mostrarFacturaPanel.add(buscarPanel, "BUSCAR");
		mostrarFacturaPanel.add(facturaPanel, "FACTURA");
		add(mostrarFacturaPanel, "BUSCAR");
						
		mostrarFacturaPanelCL.show(mostrarFacturaPanel, "BUSCAR");
		_localCL.show(this, "BUSCAR");
		_currentPanel = mostrarFacturaPanel;
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
		TProducto producto;
		switch (evento){
			case EventosFactura.MOSTRAR_FACTURA_OK:
				factura = (TFactura) datos;
				
				mostrarIDText.setText(factura.getId().toString());
				mostrarDescuentoText.setText(Double.valueOf(factura.getDescuento()).toString());
				mostrarPrecioText.setText(Double.valueOf(factura.getPrecio()).toString());
				mostrarFechaText.setText(factura.getFecha().toString());
				mostrarIDClienteText.setText(factura.getIdCliente().toString());
				mostrarIDEmpleadoText.setText(factura.getIdEmpleado().toString());
				
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
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				break;
			case EventosFactura.CERRAR_FACTURA_OK:
				mostrar();
				break;
			case EventosFactura.CERRAR_FACTURA_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				break;
			case EventosFactura.ANADIR_PRODUCTO_A_F_OK:
				producto = (TProducto) datos;
				
				Integer cantidad = producto.getCantidad();
				Double descuento = Double.valueOf(descuentoLabel.getText().replaceAll("\\D+",""));
				Double precioUnidad = producto.getPrecio();
				Double precioTeorico = (Double.valueOf(cantidad)*precioUnidad);
				Double precioReal = precioTeorico*(1-descuento/100);
				
				int row = findRow(dataTable, anadirIDProducto.getText());
				if (row >= 0) {
					Integer cantidadPresente = Integer.valueOf(model.getValueAt(row, 2).toString().replaceAll("\\D+",""));
					String cantidadTotal = Integer.valueOf(cantidadPresente + Integer.valueOf(cantidad)).toString(); 
					model.setValueAt("x"+cantidadTotal, row, 2);
				} else {
					model.addRow(new Object[]{producto.getId().toString(), producto.getNombre(), "x"+cantidad, producto.getCalorias().toString(), Double.valueOf(precioUnidad).toString()});
				}
				totalPrecio.setText(Double.valueOf(Double.valueOf(totalPrecio.getText()) + precioReal).toString());
				break;
			case EventosFactura.ANADIR_PRODUCTO_A_F_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				break;
			case EventosFactura.BORRAR_PRODUCTO_OK:
				producto = (TProducto) datos;
				
				int row2 = findRow(dataTable, borrarIDProducto.getText());
				if (row2 >= 0) {
					Integer cantidad2 = producto.getCantidad();
					Double descuento2 = Double.valueOf(descuentoLabel.getText().replaceAll("\\D+",""));
					Integer cantidadPresente = Integer.valueOf(model.getValueAt(row2, 2).toString().replaceAll("\\D+",""));
					Double precioUnidad2 = producto.getPrecio();
					Double precioTeorico2;
					
					if (cantidad2 >= cantidadPresente) {
						precioTeorico2 = (cantidadPresente*precioUnidad2);
						
						model.removeRow(row2);
					} else {
						String cantidadRestante = Integer.valueOf(cantidadPresente - cantidad2).toString();
						
						precioTeorico2 = (cantidad2*precioUnidad2);
						model.setValueAt("x"+cantidadRestante, row2, 2);
					}
					Double precioReal2 = precioTeorico2*(1-descuento2/100);
					totalPrecio.setText(Double.valueOf(Double.valueOf(totalPrecio.getText())-precioReal2).toString());
				}
				break;
			case EventosFactura.BORRAR_PRODUCTO_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				break;
			case EventosFactura.APLICAR_DESCUENTO_OK:
				double descuento3 = Double.parseDouble(descuentoLabel.getText().replaceAll("\\D+",""));
				descuentoLabel.setText(descuentoAplicado+"%");
				
				Double precioOriginal = Double.valueOf(Double.valueOf(totalPrecio.getText()) / (1 - descuento3 / 100));
				totalPrecio.setText(Double.valueOf(precioOriginal * (1 - Double.parseDouble(descuentoAplicado) / 100)).toString());
				break;
			case EventosFactura.APLICAR_DESCUENTO_KO:
				mensaje = (String) datos;
				
				JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
				break;
			case EventosFactura.FACTURAS_CLIENTE_OK:
				@SuppressWarnings("unchecked") List<TFactura> listaFacturas = (List<TFactura>) datos;

				for (TFactura f : listaFacturas) {
					facturasModel.addRow(new Object[]{f.getId().toString(), f.getIdCliente().toString(), f.getIdEmpleado().toString(), Double.valueOf(f.getDescuento()).toString(), Double.valueOf(f.getPrecio()).toString(), f.getFecha().toString()});
				}
				
				facturasMainPanelCL.show(facturasMainPanel, "FACTURAS PAGADAS");
				System.out.println("Listar Clientes OK");
				break;
			case EventosFactura.FACTURAS_CLIENTE_KO:
				mensaje = (String) datos;
				
				showOutputMsg(facturaErrorArea, facturaErrorLabel, mensaje, false);
				System.out.println("Listar Clientes KO");
				break;
			
		}
	}
}
