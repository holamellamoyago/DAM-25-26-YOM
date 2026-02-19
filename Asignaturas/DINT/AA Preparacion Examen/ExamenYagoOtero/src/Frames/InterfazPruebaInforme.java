package Frames;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class InterfazPruebaInforme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnInforme1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPruebaInforme frame = new InterfazPruebaInforme();
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
	public InterfazPruebaInforme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		btnInforme1 = new JButton("Informe 1");
		GridBagConstraints gbc_btnInforme1 = new GridBagConstraints();
		gbc_btnInforme1.gridx = 0;
		gbc_btnInforme1.gridy = 0;
		contentPane.add(btnInforme1, gbc_btnInforme1);

		btnInforme1.addActionListener(e -> generarReporte1());

	}

	public void generarReporte1() {
		try {
			// 1. Compilar el archivo JRXML
			// JRXML es la plantilla del reporte diseñada con JasperReports.
			// Debe existir en la ruta especificada.
			File jasperFile = new File("TareaInformes1.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

			// 2. Crear parámetros
			// Los parámetros son valores que se pasan al reporte, como filtros o títulos.
			// Vamos a pasar ID de pedido y un título.
			Map<String, Object> parameters = new HashMap<String, Object>();
			// parameters.put("id_pedido", "11076");
			// parameters.put("p_titulo", "Mi Reporte");

			// OPCION 1.
			// 3. Crear los datos que se vana mostrar, cada una de las filas. Importa el
			// tipo de dato debe coincidir con el del informe.
			Collection<Map<String, ?>> data = new ArrayList<>();
			Map<String, Object> row1 = new HashMap<>();
			row1.put("DNI", "213456789");
			data.add(row1);

			// 4. Llenar reporte con datos
			// JasperFillManager genera el reporte combinando la plantilla, parámetros y
			// datos de entrada.
			JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// OPCION 2.
			// 3. Crear conexión a la base de datos
			// String url = "jdbc:mysql://localhost:3306/mi_base_de_datos"; // Cambiar
			// 'mi_base_de_datos' por vuesta base de datos
			// String username = "root"; // Cambiar si usan otro usuario
			// String password = "password"; // Cambiar la contraseña
			// Connection conn = DriverManager.getConnection(url, username, password);

			// 4. Llenar reporte con datos
			// JasperFillManager genera el reporte combinando la plantilla, parámetros y
			// conexión a la DB.
			// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
			// parameters, conn);

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
		// catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

}
