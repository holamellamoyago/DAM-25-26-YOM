package Interfaces;

import java.awt.*;

import javax.help.*;
import javax.swing.*;
import javax.swing.border.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.view.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.Map;


public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPilotos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		initComponents();
		//btnPilotos.addActionListener(e -> imprimirPilotos());

	}
	
	private void imprimirPilotos() {
        
        try {
        	File jasperFile = new File("Pilotos2.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());
			
	        Map<String, Object> parameters = new HashMap<>();
	        
	         String url = "jdbc:mysql://localhost:3306/f1_history";   // Cambiar 'mi_base_de_datos' por vuesta base de datos
	         String username = "root";                                      // Cambiar si usan otro usuario
	         String password = "";                                  // Cambiar la contraseña
	         
	         Connection conn = DriverManager.getConnection(url, username, password);
	        
	     	// 4. Llenar reporte con datos
	        // JasperFillManager genera el reporte combinando la plantilla, parámetros y conexión a la DB.
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
	        
	        JasperViewer view1 = new JasperViewer(jasperPrint, false);
	        view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	        view1.setVisible(true);
			
			
		} catch (JRException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		btnPilotos = new JButton("Imprimir pilotos");
		GridBagConstraints gbc_btnPilotos = new GridBagConstraints();
		gbc_btnPilotos.gridx = 0;
		gbc_btnPilotos.gridy = 0;
		contentPane.add(btnPilotos, gbc_btnPilotos);
	}
	
	public void generarReporte() {
	    try {
	        // 1. Compilar el archivo JRXML
	        // JRXML es la plantilla del reporte diseñada con JasperReports.
	        // Debe existir en la ruta especificada.
	        File jasperFile = new File("reportes/template/Prueba2.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

	        // 2. Crear parámetros
	        // Los parámetros son valores que se pasan al reporte, como filtros o títulos.
	        // Vamos a pasar ID de pedido y un título. 
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("id_pedido", "11076");
	        parameters.put("p_titulo", "Mi Reporte");
	        
	        // OPCION 1. 
	        // 3. Crear los datos que se vana  mostrar, cada una de las filas. Importa el tipo de dato debe coincidir con el del informe.
	        Collection<Map<String, ?>> data = new ArrayList<>();
	        Map<String, Object> row1 = new HashMap<>();
	        row1.put("id_pedido", 11076);
	        row1.put("producto", "Producto A");
	        data.add(row1);
	        Map<String, Object> row2 = new HashMap<>();
	        row2.put("id_pedido", 11076);
	        row2.put("producto", "Producto B");
	        data.add(row2);
	        
	        // 4. Llenar reporte con datos
	        // JasperFillManager genera el reporte combinando la plantilla, parámetros y datos de entrada.
	        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        

	        // OPCION 2.
	        // 3. Crear conexión a la base de datos

	        
	        
	        // Mostrar el reporte en pantalla
	        JasperViewer view1 = new JasperViewer(jasperPrint, false);
	        view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	        view1.setVisible(true);

	        // 5️⃣ Exportar a PDF
	        // Guarda el reporte generado en un archivo PDF en la carpeta del proyecto
	        JasperExportManager.exportReportToPdfFile(jasperPrint, "ReportePedidos.pdf");
	        System.out.println("Reporte generado correctamente.");

	    } catch (JRException e) {
	        e.printStackTrace();
	    } 
	    //catch (SQLException e) {
	    //    e.printStackTrace();
	    //}
	}
	
	public void generateHelp() {
		try {
		File fichero = new File("resources/help"+File.separator+"helpset.hs");
		URL hsURL = fichero.toURI().toURL();
		HelpSet hs = new HelpSet(getClass().getClassLoader(), hsURL);
		HelpBroker hb = hs.createHelpBroker();
		
		// Se lanza la ayuda al pulsar al botón.
		hb.enableHelpOnButton(btnPilotos,"intro", hs);
		hb.enableHelpOnButton(btnPilotos,"limpiar", hs);
		hb.enableHelpOnButton(btnPilotos,"registrar", hs);
		hb.enableHelpOnButton(btnPilotos,"menu", hs);
		// F1 general (por defecto) - Panel principal
		hb.enableHelpKey(getRootPane(), "intro", hs);
		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (HelpSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}


}
