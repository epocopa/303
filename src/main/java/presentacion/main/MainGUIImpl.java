package presentacion.main;

import presentacion.cliente.ClienteGUIImpl;
import presentacion.controladorAplicacion.Context;
import presentacion.controladorAplicacion.ControladorAplicacion;
import presentacion.controladorAplicacion.EventosCliente;
import presentacion.controladorAplicacion.EventosEmpleado;
import presentacion.controladorAplicacion.EventosFactura;
import presentacion.controladorAplicacion.EventosGrupo;
import presentacion.controladorAplicacion.EventosMenu;
import presentacion.controladorAplicacion.EventosProducto;
import presentacion.controladorAplicacion.EventosTurno;
import presentacion.empleado.EmpleadoGUIImpl;
import presentacion.factoria.FactoriaPresentacion;
import presentacion.factoria.GUI;
import presentacion.factura.FacturaGUIImp;
import presentacion.grupo.GrupoGUIImpl;
import presentacion.producto.ProductoGUIImpl;
import presentacion.turno.TurnoGUIImpl;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUIImpl extends JFrame implements MainGUI, GUI{

	private static final long serialVersionUID = 1L;

	private String name = "HOME";

	private CardLayout cl;
	private JPanel mainPanel, homePanel;

	private ClienteGUIImpl clientesPanel;
	private ProductoGUIImpl productosPanel;
	private FacturaGUIImp facturasPanel;
	private TurnoGUIImpl turnosPanel;
	private GrupoGUIImpl gruposPanel;
	private EmpleadoGUIImpl empleadosPanel;

	private static FactoriaPresentacion presentacion;
	private static ControladorAplicacion controlador;

	private JPanel pathPanel = presentacion.generarPath();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					presentacion = FactoriaPresentacion.getInstance();
					controlador =  ControladorAplicacion.getInstance();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGUIImpl() {
		initialize();
	}

	public void initialize() {
		setBackground(new Color(255, 255, 255));
		setUndecorated(true);
		setBounds(100, 100, 1024, 620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel topBar = new JPanel();
		topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
		topBar.setBackground(new Color(41, 48, 64));
		topBar.setMaximumSize(new Dimension(1024, 50));

		topBar.add(Box.createRigidArea(new Dimension(20,0)));

		topBar.add(Box.createRigidArea(new Dimension(8,0)));

		JLabel appName = new JLabel("303 APPLICATION");
		appName.setFont(new Font("Arial", Font.BOLD, 14));
		appName.setForeground(new Color(215,215,215));
		topBar.add(appName);

		topBar.add(Box.createRigidArea(new Dimension(820,0)));

		JButton exitBtn = new JButton("X");
		exitBtn.setFocusPainted(false);
		exitBtn.setBorder(null);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setBorderPainted(false);
		exitBtn.setPreferredSize(new Dimension(30, 30));
		exitBtn.setFont(new Font("Corbel", Font.BOLD, 20));
		exitBtn.setForeground(new Color(110,120,140));
		exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el programa?", "Salir", JOptionPane.YES_NO_OPTION);

				if(confirmacion == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		topBar.add(exitBtn);


		pathPanel.add(Box.createRigidArea(new Dimension(35,0)));
		JButton homePathBtn = createPathButton(name);
		homePathBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosMenu.MOSTRAR_HOME_GUI, null);
				controlador.accion(contexto);
			}
		});
		pathPanel.add(homePathBtn);

		cl = new CardLayout();
		mainPanel = new JPanel(cl);
		mainPanel.setBackground(new Color(235, 237, 241));
		mainPanel.setMaximumSize(new Dimension(1024, 460));
		
		clientesPanel = (ClienteGUIImpl)presentacion.generarVista(EventosMenu.MOSTRAR_CLIENTE_GUI);
		productosPanel = (ProductoGUIImpl)presentacion.generarVista(EventosMenu.MOSTRAR_PRODUCTO_GUI);
		facturasPanel = (FacturaGUIImp)presentacion.generarVista(EventosMenu.MOSTRAR_FACTURA_GUI);
		turnosPanel = (TurnoGUIImpl)presentacion.generarVista(EventosMenu.MOSTRAR_TURNO_GUI);
		gruposPanel = (GrupoGUIImpl)presentacion.generarVista(EventosMenu.MOSTRAR_GRUPO_GUI);
		empleadosPanel = (EmpleadoGUIImpl)presentacion.generarVista(EventosMenu.MOSTRAR_EMPLEADO_GUI);

		homePanel = new JPanel(new GridBagLayout());
		homePanel.setBackground(new Color(235, 237, 241));
		homePanel.setMaximumSize(new Dimension(1024, 460));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10,10,10,10);
		JButton clientesBtn = createMenuButton("resources/icons/home/clientes.png", new Color(91,155,213));
		clientesBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosMenu.MOSTRAR_CLIENTE_GUI, null);
				controlador.accion(contexto);
			}
		});
		homePanel.add(clientesBtn, c);

		c.gridx++;
		JButton productosBtn = createMenuButton("resources/icons/home/productos.png", new Color(112, 173, 71));
		productosBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosMenu.MOSTRAR_PRODUCTO_GUI, null);
				controlador.accion(contexto);
			}
		});
		homePanel.add(productosBtn, c);

		c.gridx++;
		JButton facturasBtn = createMenuButton("resources/icons/home/facturas.png", new Color(255, 192, 0));
		facturasBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosMenu.MOSTRAR_FACTURA_GUI, null);
				controlador.accion(contexto);
			}
		});
		homePanel.add(facturasBtn, c);
		
		c.gridx = 0;
		c.gridy++;
		JButton turnosBtn = createMenuButton("resources/icons/home/turnos.png", new Color(243, 89, 63));
		turnosBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosMenu.MOSTRAR_TURNO_GUI, null);
				controlador.accion(contexto);
			}
		});
		homePanel.add(turnosBtn, c);
		
		c.gridx++;
		JButton gruposBtn = createMenuButton("resources/icons/home/grupos-de-trabajo.png", new Color(0, 176, 80));
		gruposBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosMenu.MOSTRAR_GRUPO_GUI, null);
				controlador.accion(contexto);
			}
		});
		homePanel.add(gruposBtn, c);
		
		c.gridx++;
		JButton empleadosBtn = createMenuButton("resources/icons/home/empleados.png", new Color(0, 112, 192));
		empleadosBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Context contexto = new Context(EventosMenu.MOSTRAR_EMPLEADO_GUI, null);
				controlador.accion(contexto);
			}
		});
		homePanel.add(empleadosBtn, c);
		
		mainPanel.add(homePanel, name);
		mainPanel.add(clientesPanel, clientesPanel.getName());
		mainPanel.add(productosPanel, productosPanel.getName());
		mainPanel.add(facturasPanel, facturasPanel.getName());
		mainPanel.add(turnosPanel, turnosPanel.getName());
		mainPanel.add(gruposPanel, gruposPanel.getName());
		mainPanel.add(empleadosPanel, empleadosPanel.getName());
		cl.show(mainPanel, name);

		add(topBar);
		add(pathPanel);
		add(mainPanel);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton createPathButton(String name) {
		JButton pathBtn = new JButton(name);
		pathBtn.setFocusPainted(false);
		pathBtn.setBorderPainted(false);
		pathBtn.setFont(new Font("Arial", Font.PLAIN, 18));
		pathBtn.setBackground(new Color(230,230,230));
		pathBtn.setMaximumSize(new Dimension(170, 50));
		return pathBtn;
	}

	public void addPathSeparator() {
		JLabel pathSeparator = new JLabel(">", SwingConstants.CENTER);
		pathSeparator.setOpaque(false);
		pathSeparator.setFont(new Font("Arial", Font.BOLD, 22));
		pathSeparator.setMaximumSize(new Dimension(50, 50));
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


	public void actualizarPath() {
		pathPanel.removeAll();

		pathPanel.add(Box.createRigidArea(new Dimension(35,0)));

		JButton homePathBtn = createPathButton(name);
		homePathBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mostrar();
			}
		});
		pathPanel.add(homePathBtn);

		pathPanel.revalidate();
		pathPanel.repaint();
	}

	public void mostrar() {
		actualizarPath();
		cl.show(mainPanel, name);
	}

	public void actualizar(int evento, Object datos) {
		switch (evento){

			//--- ACTUALIZAR MENU ---//

			case EventosMenu.MOSTRAR_HOME_GUI:
				mostrar();
				System.out.println("Retornando a MainGUI");
				break;

			case EventosMenu.MOSTRAR_CLIENTE_GUI:
				addPathSeparator();

				JButton clientesPathBtn = createPathButton(clientesPanel.getName());
				clientesPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						clientesPanel.mostrar();
					}
				});
				pathPanel.add(clientesPathBtn);

				clientesPanel.mostrar();
				cl.show(mainPanel, clientesPanel.getName());
				System.out.println("ClienteGUI");
				break;
			case EventosMenu.MOSTRAR_PRODUCTO_GUI:
				addPathSeparator();

				JButton productosPathBtn = createPathButton(productosPanel.getName());
				productosPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						productosPanel.mostrar();
					}
				});
				pathPanel.add(productosPathBtn);

				productosPanel.mostrar();
				cl.show(mainPanel, productosPanel.getName());
				System.out.println("ProductoGUI");
				break;
			case EventosMenu.MOSTRAR_FACTURA_GUI:
				addPathSeparator();

				JButton facturasPathBtn = createPathButton(facturasPanel.getName());
				facturasPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						facturasPanel.mostrar();
					}
				});
				pathPanel.add(facturasPathBtn);

				facturasPanel.mostrar();
				cl.show(mainPanel, facturasPanel.getName());
				System.out.println("FacturaGUI");
				break;
			case EventosMenu.MOSTRAR_TURNO_GUI:
				addPathSeparator();

				JButton turnosPathBtn = createPathButton(turnosPanel.getName());
				turnosPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						turnosPanel.mostrar();
					}
				});
				pathPanel.add(turnosPathBtn);

				turnosPanel.mostrar();
				cl.show(mainPanel, turnosPanel.getName());
				System.out.println("TurnoGUI");
				break;
			case EventosMenu.MOSTRAR_GRUPO_GUI:
				addPathSeparator();

				JButton gruposPathBtn = createPathButton(gruposPanel.getName());
				gruposPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						gruposPanel.mostrar();
					}
				});
				pathPanel.add(gruposPathBtn);

				gruposPanel.mostrar();
				cl.show(mainPanel, gruposPanel.getName());
				System.out.println("GrupoGUI");
				break;
			case EventosMenu.MOSTRAR_EMPLEADO_GUI:
				addPathSeparator();

				JButton empleadosPathBtn = createPathButton(empleadosPanel.getName());
				empleadosPathBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						empleadosPanel.mostrar();
					}
				});
				pathPanel.add(empleadosPathBtn);

				empleadosPanel.mostrar();
				cl.show(mainPanel, empleadosPanel.getName());
				System.out.println("EmpleadoGUI");
				break;

			//--- ACTUALIZAR CLIENTE ---//

			case EventosCliente.ANADIR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.ANADIR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.BAJA_CLIENTE_OK:{
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				};
				break;
			case EventosCliente.BAJA_CLIENTE_KO:{
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				};
				break;
			case EventosCliente.MOSTRAR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MOSTRAR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.LISTAR_CLIENTES_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.LISTAR_CLIENTES_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_BUSCAR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_CLIENTE_OK:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.MODIFICAR_CLIENTE_KO:
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
				break;
			case EventosCliente.LISTAR_CLIENTES_POR_FECHA_ALTA_OK:{
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
			}; break;
			case EventosCliente.LISTAR_CLIENTES_POR_FECHA_ALTA_KO:{
				clientesPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel clientes");
			}; break;

			//--- ACTUALIZAR PRODUCTO ---//

			case EventosProducto.ANADIR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.ANADIR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MODIFICAR_BUSCAR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MODIFICAR_BUSCAR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MODIFICAR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MODIFICAR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.BAJA_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.BAJA_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MOSTRAR_PRODUCTO_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.MOSTRAR_PRODUCTO_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.LISTAR_PRODUCTOS_OK:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;
			case EventosProducto.LISTAR_PRODUCTOS_KO:
				productosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel productos");
				break;

			//--- ACTUALIZAR FACTURA ---//

			case EventosFactura.MOSTRAR_FACTURA_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.MOSTRAR_FACTURA_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ABRIR_FACTURA_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ABRIR_FACTURA_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.CERRAR_FACTURA_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.CERRAR_FACTURA_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ANADIR_PRODUCTO_A_F_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.ANADIR_PRODUCTO_A_F_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.BORRAR_PRODUCTO_OK:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.BORRAR_PRODUCTO_KO:
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
				break;
			case EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_OK:{
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
			};
			break;
			case EventosFactura.LISTAR_PRODUCTOS_COMPRADOS_POR_FECHA_KO:{
				facturasPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel facturas");
			};
			break;
			
			//--- ACTUALIZAR TURNO ---//
			
			case EventosTurno.ANADIR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.ANADIR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.MODIFICAR_BUSCAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.MODIFICAR_BUSCAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.MODIFICAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.MODIFICAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.BAJA_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.BAJA_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.MOSTRAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.MOSTRAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.LISTAR_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.LISTAR_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.ANADIR_EMPLEADO_A_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.ANADIR_EMPLEADO_A_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.BAJA_EMPLEADO_A_TURNO_OK:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
			case EventosTurno.BAJA_EMPLEADO_A_TURNO_KO:
				turnosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel turno");
				break;
				
				//--- ACTUALIZAR GRUPO ---//
				
			case EventosGrupo.ANADIR_GRUPO_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.ANADIR_GRUPO_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.MODIFICAR_BUSCAR_GRUPO_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.MODIFICAR_BUSCAR_GRUPO_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.MODIFICAR_GRUPO_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.MODIFICAR_GRUPO_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.BAJA_GRUPO_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.BAJA_GRUPO_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.MOSTRAR_GRUPO_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.MOSTRAR_GRUPO_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.LISTAR_GRUPOS_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.LISTAR_GRUPOS_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.ANADIR_EMPLEADO_A_GRUPO_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.ANADIR_EMPLEADO_A_GRUPO_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.BAJA_EMPLEADO_DE_GRUPO_OK:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
			case EventosGrupo.BAJA_EMPLEADO_DE_GRUPO_KO:
				gruposPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel grupo");
				break;
				
				//--- ACTUALIZAR EMPLEADO ---//
				
			case EventosEmpleado.ANADIR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.ANADIR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.MODIFICAR_BUSCAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.BAJA_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.BAJA_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.LISTAR_EMPLEADO_OK:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
			case EventosEmpleado.LISTAR_EMPLEADO_KO:
				empleadosPanel.actualizar(evento, datos);
				System.out.println("Delegando a panel empleado");
				break;
		}
	}
}
