package Dialogs;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Frames.InterfazPrincipal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Usuario;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgReporte extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JasperPrint jasperPrint;

	private DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][] {},
			new String[] { "Item", "Valor" }) {
		boolean[] columnEditables = new boolean[] { false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};

	ArrayList<Usuario> usuarios = InterfazPrincipal.usuarios;
	private final JButton btnVisualizarReporte = new JButton("Visualizar reporte");
	private final JButton btnImprimirReporte = new JButton("Imprimir reporte");

	/**
	 * Launch the application.
	 */
	public DlgReporte(java.awt.Frame parent, boolean modal) {
		super(parent, modal);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				InterfazPrincipal.registroVentanas.numVentanasReporte--;

			}
		});

		initComponents();
		InterfazPrincipal.registroVentanas.numVentanasReporte++;

		poblarTable();
		//defaultTableModel.addRow(new String[] {"a", "a"});
		
		// Yago 06/02
		btnVisualizarReporte.addActionListener(e -> visualizarReporte());
		btnImprimirReporte.addActionListener(e -> imprimirReporte());
		
		
		
	}
	

	private void imprimirReporte() {
		// 5️⃣ Exportar a PDF
		// Guarda el reporte generado en un archivo PDF en la carpeta del proyecto
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "ReportePedidos.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Reporte generado correctamente.");
	}

	private void visualizarReporte() {
		generarReporte1();

		JasperViewer view1 = new JasperViewer(jasperPrint, false);
		view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		view1.setVisible(true);
	}

	public void generarReporte1() {
		try {
			File jasperFile = new File("TareaInformes1.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());

			Map<String, Object> parameters = new HashMap<String, Object>();
			
			Collection<Map<String, ?>> data = new ArrayList<>();
			Map<String, Object> row1;
			for (Usuario u : InterfazPrincipal.usuarios) {
				row1 = new HashMap<>();
				row1.put("DNI", u.getDni());
				row1.put("Nombre", u.getNombre());
				row1.put("Apellidos", u.getApellidos());
				row1.put("Edad", u.getEdad());
				
				data.add(row1);
			}

			JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	private void poblarTable() {
		defaultTableModel
				.addRow(new String[] { "Nº de usuarios totales", String.valueOf((InterfazPrincipal.usuarios.size())) });
		defaultTableModel.addRow(new String[] { "Nº usuario en el  gimnasio ", calcularnumeroPersonasGimnasio() });
		defaultTableModel.addRow(new String[] { "Edad media usuarios registrados: ", calcularEdadMedia() });
		defaultTableModel.addRow(new String[] { "Edad median usuarios en el gimnasio: ", calcularEdadMediaGimnasio() });

	}

	private String calcularEdadMedia() {
		if (usuarios.isEmpty()) {
			return "0";
		}

		int contadorEdad = 0;

		for (int i = 0; i < usuarios.size(); i++) {
			contadorEdad += usuarios.get(i).getEdad();
		}

		return String.valueOf(contadorEdad / usuarios.size());

	}

	private String calcularEdadMediaGimnasio() {
		if (usuarios.isEmpty()) {
			return "0";
		}

		int contadorEdad = 0;
		int contadorPersonas = 0;

		for (int i = 0; i < usuarios.size(); i++) {

			if (usuarios.get(i).isDentro()) {
				contadorEdad += usuarios.get(i).getEdad();
				contadorPersonas++;
			}

		}

		if (contadorPersonas == 0) {
			return "0";
		}

		return String.valueOf(contadorEdad / contadorPersonas);

	}

	private String calcularnumeroPersonasGimnasio() {
		int contador = 0;

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).isDentro()) {
				contador++;
			}
		}

		return String.valueOf(contador);

	}

	private void mostrarAvisoSeleccion() {
		String item = (String) defaultTableModel.getValueAt(table.getSelectedRow(), 0);
		String valor = (String) defaultTableModel.getValueAt(table.getSelectedRow(), 1);

		JOptionPane.showConfirmDialog(this, "Item: " + item + "\nValor: " + valor, "Detalle de la selección",
				JOptionPane.INFORMATION_MESSAGE);
		// JOptionPane.showOptionDialog(contentPanel, valor, item, ALLBITS, ABORT, null,
		// getComponentListeners(), valor)

	}

	private void initComponents() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(
				new TitledBorder(null, "Tabla de reportes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 2;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						mostrarAvisoSeleccion();
					}
				});
				table.setModel(defaultTableModel);
				scrollPane.setViewportView(table);
			}
		}
		{
			GridBagConstraints gbc_btnVisualizarReporte = new GridBagConstraints();
			gbc_btnVisualizarReporte.insets = new Insets(0, 0, 0, 5);
			gbc_btnVisualizarReporte.gridx = 0;
			gbc_btnVisualizarReporte.gridy = 1;
			btnVisualizarReporte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			contentPanel.add(btnVisualizarReporte, gbc_btnVisualizarReporte);
		}
		{
			GridBagConstraints gbc_btnImprimirReporte = new GridBagConstraints();
			gbc_btnImprimirReporte.gridx = 1;
			gbc_btnImprimirReporte.gridy = 1;
			contentPanel.add(btnImprimirReporte, gbc_btnImprimirReporte);
		}

	}

}
