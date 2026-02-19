package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import Clases.*;
import Dialogs.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnEntra;
	private JButton btnSale;
	private JButton btnLimpiarHistorial;
	private JTextPane textPaneHistorial;
	private JButton btnRegistro;
	private JButton btnReporte;

	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static RegistroVentanas registroVentanas = new RegistroVentanas();

	private static DefaultComboBoxModel<Usuario> comboBoxModel = new DefaultComboBoxModel<Usuario>();

	private StringBuilder strPersonasGimnasio = new StringBuilder();
	private StringBuilder strHistorial = new StringBuilder();
	private JTextPane tetxPanePersonas;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenuItem mnRegistro;
	private JMenuItem mnReporte;
	private JMenuItem mnLimpiarHistorial;
	private JButton btnVerHistorial;
	private JButton btnDescargarHistorial;

	private JasperPrint jasperPrint;

	// Yago 09/02
	private Connection connecction;
	private JButton btnReporteIndividual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal frame = new InterfazPrincipal();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazPrincipal() {
		initComponents();
		connecction = GestorConexion.getConnection("Gimnasio", "root", "");

		usuarios.add(new Usuario("12345678A", "Yago", "Otero", 22));
		poblarComboBox();

		btnRegistro.addActionListener(e -> abrirVentanaRegistro());
		// btnGenerarReporte.addActionListener(e -> generarReporte());
		btnReporte.addActionListener(e -> abrirVentanaReporte());

		btnEntra.addActionListener(e -> entrarUsuario());
		btnSale.addActionListener(e -> salirUsuario());

		btnLimpiarHistorial.addActionListener(e -> limpiarHistorial());

		// Menu
		mnRegistro.addActionListener(e -> abrirVentanaRegistro());
		mnLimpiarHistorial.addActionListener(e -> limpiarHistorial());
		mnReporte.addActionListener(e -> abrirVentanaReporte());

		// usuarios.add(new Usuario("39511342X", "Yago", "Otero", 22));
		
		btnVerHistorial.addActionListener(e -> visualizarHistorial());
		btnDescargarHistorial.addActionListener(e -> exportarPDF());
		
		btnReporteIndividual.addActionListener(e -> generarReporteiNDIVIDUAL());
	}

	private void visualizarHistorial() {
		JasperPrint jas = generarReporte();
		
        JasperViewer view1 = new JasperViewer(jas, false);
        view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        view1.setVisible(true);

	}
	
	private void exportarPDF() {
		generarReporte();
        try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "ReporteHistorial.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
        System.out.println("Reporte generado correctamente.");
	}
	
	public void generarReporteiNDIVIDUAL() {
			Usuario u = (Usuario)comboBoxModel.getSelectedItem();
		
		    try {
		        // 1. Compilar el archivo JRXML
		        // JRXML es la plantilla del reporte diseñada con JasperReports.
		        // Debe existir en la ruta especificada.
		        File jasperFile = new File("TarefausuarioIndividual.jrxml");
		        JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

		        // 2. Crear parámetros
		        // Los parámetros son valores que se pasan al reporte, como filtros o títulos.
		        // Vamos a pasar ID de pedido y un título. 
		        Map<String, Object> parameters = new HashMap<>();
		        parameters.put("DNI",u.getDni() );
		        
		        
		        // 4. Llenar reporte con datos
		        // JasperFillManager genera el reporte combinando la plantilla, parámetros y datos de entrada.
		        //JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
		        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		        

		        // OPCION 2.
		        // 3. Crear conexión a la base de datos
		        // String url = "jdbc:mysql://localhost:3306/mi_base_de_datos";   // Cambiar 'mi_base_de_datos' por vuesta base de datos
		        // String username = "root";                                      // Cambiar si usan otro usuario
		        // String password = "password";                                  // Cambiar la contraseña
		        // Connection conn = DriverManager.getConnection(url, username, password);
		        
		     	// 4. Llenar reporte con datos
		        // JasperFillManager genera el reporte combinando la plantilla, parámetros y conexión a la DB.
		         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connecction);
		        
		        
		        
		        // Mostrar el reporte en pantalla
		        JasperViewer view1 = new JasperViewer(jasperPrint, false);
		        view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		        view1.setVisible(true);

		        // 5️⃣ Exportar a PDF
		        // Guarda el reporte generado en un archivo PDF en la carpeta del proyecto
		        JasperExportManager.exportReportToPdfFile(jasperPrint, "ReporteIndividual.pdf");
		        System.out.println("Reporte generado correctamente.");

		    } catch (JRException e) {
		        e.printStackTrace();
		    } 
		    //catch (SQLException e) {
		    //    e.printStackTrace();
		    //}
	}	

	public JasperPrint generarReporte() {
	    try {
	        // 1. Compilar el archivo JRXML
	        // JRXML es la plantilla del reporte diseñada con JasperReports.
	        // Debe existir en la ruta especificada.
	        File jasperFile = new File("TarefaHistorial.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

	        // 2. Crear parámetros
	        // Los parámetros son valores que se pasan al reporte, como filtros o títulos.
	        // Vamos a pasar ID de pedido y un título. 
	        Map<String, Object> parameters = new HashMap<>();

	        
	        // 4. Llenar reporte con datos
	        // JasperFillManager genera el reporte combinando la plantilla, parámetros y datos de entrada.
	        //JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
	        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        

	        // OPCION 2.
	        // 3. Crear conexión a la base de datos
	         
	        
	     	// 4. Llenar reporte con datos
	        // JasperFillManager genera el reporte combinando la plantilla, parámetros y conexión a la DB.
	         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connecction);
	         return jasperPrint;
	        
	        
	        
	        // Mostrar el reporte en pantalla

	        // 5️⃣ Exportar a PDF


	    } catch (JRException e) {
	        e.printStackTrace();
	        return null;
	   } 
	    //catch (SQLException e) {
	    //    e.printStackTrace();
	    //}
	}

	private void imprimirReporte() {
		generarReporte2();

		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "ReportePedidos.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Reporte generado correctamente.");
	}

	private void visualizarReporte() {
		generarReporte2();

		JasperViewer view1 = new JasperViewer(jasperPrint, false);
		view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		view1.setVisible(true);
	}

	public void generarReporte2() {
		try {

			File jasperFile = new File("TareaInformes2.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());
			Map<String, Object> parameters = new HashMap<>();

			// OPCION 2.
			// 3. Crear conexión a la base de datos
			String url = "jdbc:mysql://localhost:3306/mi_base_de_datos"; // Cambiar 'mi_base_de_datos' por vuesta base
																			// de datos
			String username = "root"; // Cambiar si usan otro usuario
			String password = "password"; // Cambiar la contraseña
			Connection conn;
			try {
				conn = DriverManager.getConnection(url, username, password);

				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

				// Mostrar el reporte en pantalla
				JasperViewer view1 = new JasperViewer(jasperPrint, false);
				view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
				view1.setVisible(true);

				// Guarda el reporte generado en un archivo PDF en la carpeta del proyecto
				JasperExportManager.exportReportToPdfFile(jasperPrint, "ReportePedidos.pdf");
				System.out.println("Reporte generado correctamente.");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (JRException e) {
			e.printStackTrace();
		}
		// catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	private void limpiarHistorial() {
		textPaneHistorial.setText("");
		strHistorial.delete(0, strHistorial.length());
	}

	private void poblarHistorial() {
		textPaneHistorial.setText(strHistorial.toString());
	}

	private void poblarPersonasGimnasio() {
		strPersonasGimnasio.delete(0, strPersonasGimnasio.length());
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);

			if (usuario.isDentro()) {
				strPersonasGimnasio.append(usuario + "\n");
			}
		}

		tetxPanePersonas.setText(strPersonasGimnasio.toString());

	}

	private void salirUsuario() {
		Usuario usuario = (Usuario) comboBox.getSelectedItem();

		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuarioLista = usuarios.get(i);
			if (usuarioLista.getDni().equals(usuario.getDni())) {
				usuarioLista.setDentro(false);

				String sql = """
						INSERT INTO historial (DNI, Accion) VALUES (?, 'SALE')
						""";

				GestorConexion.ejecutarSentencia(connecction, sql, usuario.getDni());

				btnEntra.setEnabled(true);
				// btnSale.setEnabled(false);

				strHistorial.append(usuarioLista.toString() + "\n");
				poblarHistorial();

			}
		}

		poblarPersonasGimnasio();
		comboBox.setSelectedIndex(-1);
	}

	private void entrarUsuario() {
		Usuario usuario = (Usuario) comboBox.getSelectedItem();

		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuarioLista = usuarios.get(i);
			if (usuarioLista.getDni().equals(usuario.getDni())) {
				usuarioLista.setDentro(true);

				btnEntra.setEnabled(false);
				// btnSale.setEnabled(true);
				
				String sql = """
						INSERT INTO historial (DNI, Accion) VALUES (?, 'ENTRA')
						""";

				GestorConexion.ejecutarSentencia(connecction, sql, usuario.getDni());

				strHistorial.append(usuarioLista.toString() + "\n");
				poblarHistorial();

			}
		}
		poblarPersonasGimnasio();
		comboBox.setSelectedIndex(-1);

	}

	protected void comprobarBotonesEntrarSalir() {
		if (comboBox.getSelectedIndex() < 0) {
			btnEntra.setEnabled(false);
			btnSale.setEnabled(false);
			return;
		}

		Usuario usuario = (Usuario) comboBox.getSelectedItem();

		if (usuario.isDentro()) {
			btnSale.setEnabled(true);
			btnEntra.setEnabled(false);

			// Teine que salir si esta dentor5
		} else {
			btnEntra.setEnabled(true);
			btnSale.setEnabled(false);

		}

	}

	public static void poblarComboBox() {
		comboBoxModel.removeAllElements();

		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			comboBoxModel.addElement(usuario);

		}
	}

	private void abrirVentanaRegistro() {
		if (registroVentanas.numVentanasRegistro > 0) {
			JOptionPane.showMessageDialog(this, "Error no puedews abrir mas dialogs de registro",
					"Abrir vetana registro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		DlgRegistro dlgRegistro = new DlgRegistro(this, false);
		dlgRegistro.setVisible(true);
	}

	private void abrirVentanaReporte() {
		if (registroVentanas.numVentanasReporte > 0) {
			JOptionPane.showMessageDialog(this, "Error no puedews abrir mas dialogs de reporte",
					"Abrir vetana registro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		DlgReporte dlgRegistro = new DlgReporte(this, false);
		dlgRegistro.setVisible(true);
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 420);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		mnNewMenu_1 = new JMenu("Control de acceso");
		mnNewMenu.add(mnNewMenu_1);

		mnRegistro = new JMenuItem("Registro");
		mnRegistro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mnRegistro);

		mnReporte = new JMenuItem("Reporte");
		mnReporte.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
		mnNewMenu_1.add(mnReporte);

		mnLimpiarHistorial = new JMenuItem("Limpiar el historial");
		mnLimpiarHistorial.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mnLimpiarHistorial);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Control de acceso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		comboBox = new JComboBox();

		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.setSelectedIndex(-1);
				comprobarBotonesEntrarSalir();
			}
		});

		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				comprobarBotonesEntrarSalir();

			}
		});

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);

		btnEntra = new JButton("ENTRA");
		btnEntra.setEnabled(false);
		GridBagConstraints gbc_btnEntra = new GridBagConstraints();
		gbc_btnEntra.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEntra.insets = new Insets(0, 0, 5, 5);
		gbc_btnEntra.gridx = 0;
		gbc_btnEntra.gridy = 1;
		panel.add(btnEntra, gbc_btnEntra);

		btnSale = new JButton("SALE");
		btnSale.setEnabled(false);
		GridBagConstraints gbc_btnSale = new GridBagConstraints();
		gbc_btnSale.insets = new Insets(0, 0, 5, 0);
		gbc_btnSale.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSale.gridx = 1;
		gbc_btnSale.gridy = 1;
		panel.add(btnSale, gbc_btnSale);

		btnReporte = new JButton("REPORTE");
		GridBagConstraints gbc_btnReporte = new GridBagConstraints();
		gbc_btnReporte.insets = new Insets(0, 0, 5, 0);
		gbc_btnReporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReporte.gridwidth = 2;
		gbc_btnReporte.gridx = 0;
		gbc_btnReporte.gridy = 2;
		panel.add(btnReporte, gbc_btnReporte);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Historial", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		textPaneHistorial = new JTextPane();
		GridBagConstraints gbc_textPaneHistorial = new GridBagConstraints();
		gbc_textPaneHistorial.gridwidth = 2;
		gbc_textPaneHistorial.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneHistorial.fill = GridBagConstraints.BOTH;
		gbc_textPaneHistorial.gridx = 0;
		gbc_textPaneHistorial.gridy = 0;
		panel_1.add(textPaneHistorial, gbc_textPaneHistorial);

		btnLimpiarHistorial = new JButton("LIMPIAR HISTORIAL");
		GridBagConstraints gbc_btnLimpiarHistorial = new GridBagConstraints();
		gbc_btnLimpiarHistorial.gridwidth = 2;
		gbc_btnLimpiarHistorial.insets = new Insets(0, 0, 5, 5);
		gbc_btnLimpiarHistorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimpiarHistorial.gridx = 0;
		gbc_btnLimpiarHistorial.gridy = 1;
		panel_1.add(btnLimpiarHistorial, gbc_btnLimpiarHistorial);

		btnVerHistorial = new JButton("Ver historial");
		GridBagConstraints gbc_btnVerHistorial = new GridBagConstraints();
		gbc_btnVerHistorial.insets = new Insets(0, 0, 0, 5);
		gbc_btnVerHistorial.gridx = 0;
		gbc_btnVerHistorial.gridy = 2;
		panel_1.add(btnVerHistorial, gbc_btnVerHistorial);

		btnDescargarHistorial = new JButton("Descargar historial");
		GridBagConstraints gbc_btnDescargarHistorial = new GridBagConstraints();
		gbc_btnDescargarHistorial.gridx = 1;
		gbc_btnDescargarHistorial.gridy = 2;
		panel_1.add(btnDescargarHistorial, gbc_btnDescargarHistorial);

		btnRegistro = new JButton("REGISTRO");
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistro.gridx = 0;
		gbc_btnRegistro.gridy = 1;
		contentPane.add(btnRegistro, gbc_btnRegistro);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Personas en el gimnasio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		tetxPanePersonas = new JTextPane();
		GridBagConstraints gbc_tetxPanePersonas = new GridBagConstraints();
		gbc_tetxPanePersonas.fill = GridBagConstraints.BOTH;
		gbc_tetxPanePersonas.gridx = 0;
		gbc_tetxPanePersonas.gridy = 0;
		panel_2.add(tetxPanePersonas, gbc_tetxPanePersonas);

		comboBox.setModel(comboBoxModel);
		comboBox.setSelectedIndex(-1);
		
		btnReporteIndividual = new JButton("Reporte usuario individual");
		btnReporteIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnReporteIndividual = new GridBagConstraints();
		gbc_btnReporteIndividual.gridwidth = 2;
		gbc_btnReporteIndividual.insets = new Insets(0, 0, 0, 5);
		gbc_btnReporteIndividual.gridx = 0;
		gbc_btnReporteIndividual.gridy = 3;
		panel.add(btnReporteIndividual, gbc_btnReporteIndividual);

	}

}
