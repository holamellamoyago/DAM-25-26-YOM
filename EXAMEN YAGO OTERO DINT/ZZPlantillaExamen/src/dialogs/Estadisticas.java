package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Estadisticas extends JDialog {

	private JTable table_cursos;
	private JTable table_alumnos;
	private JTable table_curso_alumno;
	private javax.swing.table.DefaultTableModel modelCursos;
	private javax.swing.table.DefaultTableModel modelAlumnos;
	private javax.swing.table.DefaultTableModel modelCursoAlumno;

	private static final long serialVersionUID = 1L;

	public Estadisticas(java.awt.Frame parent, boolean modal) {
		super(parent, modal);

		initComponents();
		actualizarTablas(); // Actualizar tablas al iniciar

	}

	private void initComponents() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// --- Tablas ---
		JScrollPane scrollCursos = new JScrollPane();
		scrollCursos.setBounds(448, 33, 584, 107);
		GridBagConstraints gbc_scrollCursos = new GridBagConstraints();
		gbc_scrollCursos.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollCursos.insets = new Insets(0, 0, 5, 5);
		gbc_scrollCursos.gridx = 0;
		gbc_scrollCursos.gridy = 0;
		getContentPane().add(scrollCursos, gbc_scrollCursos);

		table_cursos = new JTable();
		modelCursos = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Nº Sesiones" });
		table_cursos.setModel(modelCursos);
		scrollCursos.setViewportView(table_cursos);
		modelAlumnos = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "DNI", "Nombre", "Apellidos" });
		modelCursoAlumno = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Curso ID", "DNI Alumno", "Nota EV1", "Nota EV2", "Nota EV3", "Faltas" });

		JScrollPane scrollAlumnos = new JScrollPane();
		scrollAlumnos.setBounds(448, 170, 584, 107);
		GridBagConstraints gbc_scrollAlumnos = new GridBagConstraints();
		gbc_scrollAlumnos.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollAlumnos.insets = new Insets(0, 0, 5, 5);
		gbc_scrollAlumnos.gridx = 0;
		gbc_scrollAlumnos.gridy = 1;
		getContentPane().add(scrollAlumnos, gbc_scrollAlumnos);

		table_alumnos = new JTable();
		table_alumnos.setModel(modelAlumnos);
		scrollAlumnos.setViewportView(table_alumnos);

		JScrollPane scrollCursoAlumno = new JScrollPane();
		scrollCursoAlumno.setBounds(448, 308, 584, 224);
		GridBagConstraints gbc_scrollCursoAlumno = new GridBagConstraints();
		gbc_scrollCursoAlumno.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollCursoAlumno.insets = new Insets(0, 0, 5, 5);
		gbc_scrollCursoAlumno.gridx = 0;
		gbc_scrollCursoAlumno.gridy = 2;
		getContentPane().add(scrollCursoAlumno, gbc_scrollCursoAlumno);

		table_curso_alumno = new JTable();
		table_curso_alumno.setModel(modelCursoAlumno);
		scrollCursoAlumno.setViewportView(table_curso_alumno);

		JMenuBar menuBar = new JMenuBar();
		// frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);

	}

	private Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/examen_final_dint_centro", "root", // usuario
					"" // contraseña
			);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void actualizarTablas() {
		try (Connection con = getConnection()) {

			// --- Cursos ---
			modelCursos.setRowCount(0);
			ResultSet rsCursos = con.createStatement().executeQuery("SELECT * FROM cursos");
			while (rsCursos.next()) {
				modelCursos.addRow(new Object[] { rsCursos.getString("id"), rsCursos.getString("nombre"),
						rsCursos.getInt("num_total_sesiones") });
			}

			// --- Alumnos ---
			modelAlumnos.setRowCount(0);
			ResultSet rsAlumnos = con.createStatement().executeQuery("SELECT * FROM alumnos");
			while (rsAlumnos.next()) {
				modelAlumnos.addRow(new Object[] { rsAlumnos.getString("dni"), rsAlumnos.getString("nombre"),
						rsAlumnos.getString("apellidos") });
			}

			// --- Curso_Alumno ---
			modelCursoAlumno.setRowCount(0);
			ResultSet rsCA = con.createStatement().executeQuery("SELECT * FROM curso_alumno");
			while (rsCA.next()) {
				modelCursoAlumno.addRow(new Object[] { rsCA.getString("id_curso"), rsCA.getString("dni_alumno"),
						rsCA.getInt("nota_ev1"), rsCA.getInt("nota_ev2"), rsCA.getInt("nota_ev3"),
						rsCA.getInt("faltas") });
			}

			// Actualizar combos
			// cargarCombos();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
