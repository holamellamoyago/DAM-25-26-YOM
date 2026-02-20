package dialogs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Tablas extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable table_cursos;
	private JTable table_alumnos;
	private JTable table_curso_alumno;

	private javax.swing.table.DefaultTableModel modelCursos;
	private javax.swing.table.DefaultTableModel modelAlumnos;
	private javax.swing.table.DefaultTableModel modelCursoAlumno;

	public Tablas(java.awt.Frame parent, boolean modal) {
		super(parent, modal);


		initialize();
		//cargarCombos(); // Cargar combos al iniciar
		actualizarTablas(); // Actualizar tablas al iniciar
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

	private void initialize() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());


		// --- Tablas ---
		JScrollPane scrollCursos = new JScrollPane();
		scrollCursos.setBounds(448, 33, 584, 107);
		getContentPane().add(scrollCursos);

		table_cursos = new JTable();
		modelCursos = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Nº Sesiones" });
		table_cursos.setModel(modelCursos);
		scrollCursos.setViewportView(table_cursos);

		JScrollPane scrollAlumnos = new JScrollPane();
		scrollAlumnos.setBounds(448, 170, 584, 107);
		getContentPane().add(scrollAlumnos);

		table_alumnos = new JTable();
		modelAlumnos = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "DNI", "Nombre", "Apellidos" });
		table_alumnos.setModel(modelAlumnos);
		scrollAlumnos.setViewportView(table_alumnos);

		JScrollPane scrollCursoAlumno = new JScrollPane();
		scrollCursoAlumno.setBounds(448, 308, 584, 224);
		getContentPane().add(scrollCursoAlumno);

		table_curso_alumno = new JTable();
		modelCursoAlumno = new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Curso ID", "DNI Alumno", "Nota EV1", "Nota EV2", "Nota EV3", "Faltas" });
		table_curso_alumno.setModel(modelCursoAlumno);
		scrollCursoAlumno.setViewportView(table_curso_alumno);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);

		// --- Acciones botones ---

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
