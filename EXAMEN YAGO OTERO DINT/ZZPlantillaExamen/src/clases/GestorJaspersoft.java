package clases;

import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.view.*;

import java.io.*;

public class GestorJaspersoft {

	
	public static JasperPrint generarReporteCursos() {
		File jasperFile = new File("InformeCursos2.jrxml");
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());
			Map<String, Object> parameters = new HashMap<>();

			return JasperFillManager.fillReport(jasperReport, parameters,
					GestorConexion.getConnection("examen_final_dint_centro", "root", ""));
			
			
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static JasperPrint generarReporteGlobal() {
		File jasperFile = new File("Global.jrxml");
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile.getAbsolutePath());
			Map<String, Object> parameters = new HashMap<>();

			return JasperFillManager.fillReport(jasperReport, parameters,
					GestorConexion.getConnection("examen_final_dint_centro", "root", ""));
			
			
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}


	public static void mostrarReporte(JasperPrint reporte) {
		JasperViewer view1 = new JasperViewer(reporte, false);
		view1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		view1.setVisible(true);
	}

	public static void exportarPDF(JasperPrint reporte) {
		try {
			JasperExportManager.exportReportToPdfFile(reporte, "ReportePedidos.pdf");
			System.out.println("Reporte generado correctamente.");

		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
