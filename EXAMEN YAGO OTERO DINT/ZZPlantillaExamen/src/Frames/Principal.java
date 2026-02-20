package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.GestorJaspersoft;
import dialogs.AsignarCursosAlumno;
import dialogs.Estadisticas;
import dialogs.EvaluarAlumno;
import dialogs.RegistroAlumnos;
import dialogs.ResgistroCursos;
import dialogs.Tablas;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnregistrarCurso;
	private JButton btnRegistroAlumno;
	private JButton btnAsignarCurso;
	private JButton btnEvaluarAlumno;
	private JButton btnMostrarEstadsticas;
	private JMenuItem btnEvaluar2;
	private JMenu mnNewMenu_1;
	private JMenuItem btnAsignar2;
	private JMenuItem btnRegistrarCursos2;
	private JMenuItem btnregistrarAlumnos2;
	private JMenu mnNewMenu_2;
	private JButton btnInformeCursos;
	private JButton btnInformeGlobal;

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
		
		// Inicializo los dialogs que utilizará mi usuario
		ResgistroCursos dlgRegistroCursos = new ResgistroCursos(this, false);
		RegistroAlumnos dlgRegistroAlumnos = new RegistroAlumnos(this, false);
		EvaluarAlumno dlgEvaluarAlumno = new EvaluarAlumno(this, false);
		AsignarCursosAlumno dlgAsignarCursosAlumno = new AsignarCursosAlumno(this, false);
		Estadisticas dlgEstadisiticas = new Estadisticas(this, false);
		Tablas dlgTablas = new Tablas(this, false);
		//Tablas2 dlgTablas2 = new Tablas2(this, false);
		
		
		btnregistrarCurso.addActionListener(e -> dlgRegistroCursos.setVisible(true));
		btnEvaluarAlumno.addActionListener(e -> dlgEvaluarAlumno.setVisible(true));
		btnRegistroAlumno.addActionListener(e -> dlgRegistroAlumnos.setVisible(true));
		btnAsignarCurso.addActionListener(e -> dlgAsignarCursosAlumno.setVisible(true));
		btnMostrarEstadsticas.addActionListener(e -> dlgTablas.setVisible(true));
		
		
		btnRegistrarCursos2.addActionListener(e -> dlgRegistroCursos.setVisible(true));
		btnEvaluar2.addActionListener(e -> dlgEvaluarAlumno.setVisible(true));
		btnregistrarAlumnos2.addActionListener(e -> dlgRegistroAlumnos.setVisible(true));
		btnAsignar2.addActionListener(e -> dlgAsignarCursosAlumno.setVisible(true));
		btnEvaluarAlumno.addActionListener(e -> dlgEvaluarAlumno.setVisible(true));
		
		
		btnInformeCursos.addActionListener(e -> GestorJaspersoft.mostrarReporte(GestorJaspersoft.generarReporteCursos()));
		btnInformeGlobal.addActionListener(e -> GestorJaspersoft.mostrarReporte(GestorJaspersoft.generarReporteGlobal()));
		
		
		



		
		
		
		
		


	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 591);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Registrar");
		menuBar.add(mnNewMenu);
		
		btnregistrarAlumnos2 = new JMenuItem("Alumnos");
		mnNewMenu.add(btnregistrarAlumnos2);
		
		btnRegistrarCursos2 = new JMenuItem("Cursos");
		mnNewMenu.add(btnRegistrarCursos2);
		
		mnNewMenu_1 = new JMenu("Asignar");
		menuBar.add(mnNewMenu_1);
		
		btnAsignar2 = new JMenuItem("Cursos a alumnos");
		mnNewMenu_1.add(btnAsignar2);
		
		mnNewMenu_2 = new JMenu("Evaluar");
		menuBar.add(mnNewMenu_2);
		
		btnEvaluar2 = new JMenuItem("Evaluar");
		mnNewMenu_2.add(btnEvaluar2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Buenos día, que te gustaría hacer? ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Registro de cursos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pulsa en el botón para registrar un curso");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		btnregistrarCurso = new JButton("Registrar curso");
		GridBagConstraints gbc_btnregistrarCurso = new GridBagConstraints();
		gbc_btnregistrarCurso.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnregistrarCurso.insets = new Insets(0, 0, 5, 0);
		gbc_btnregistrarCurso.gridx = 1;
		gbc_btnregistrarCurso.gridy = 2;
		contentPane.add(btnregistrarCurso, gbc_btnregistrarCurso);
		
		JLabel lblNewLabel_1_1 = new JLabel("Registro de alumnos");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 4;
		contentPane.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Pulsa en el botón para registrar un curso");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 5;
		contentPane.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		btnRegistroAlumno = new JButton("Registrar alumnos");
		GridBagConstraints gbc_btnRegistroAlumno = new GridBagConstraints();
		gbc_btnRegistroAlumno.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistroAlumno.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegistroAlumno.gridx = 1;
		gbc_btnRegistroAlumno.gridy = 5;
		contentPane.add(btnRegistroAlumno, gbc_btnRegistroAlumno);
		
		JLabel lblNewLabel_1_2 = new JLabel("Asignación de cursos a alumnos");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 0;
		gbc_lblNewLabel_1_2.gridy = 7;
		contentPane.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Pulsa en el botón para registrar un curso");
		GridBagConstraints gbc_lblNewLabel_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1.gridy = 8;
		contentPane.add(lblNewLabel_2_1_1, gbc_lblNewLabel_2_1_1);
		
		btnAsignarCurso = new JButton("Asignar curso");
		GridBagConstraints gbc_btnAsignarCurso = new GridBagConstraints();
		gbc_btnAsignarCurso.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAsignarCurso.insets = new Insets(0, 0, 5, 0);
		gbc_btnAsignarCurso.gridx = 1;
		gbc_btnAsignarCurso.gridy = 8;
		contentPane.add(btnAsignarCurso, gbc_btnAsignarCurso);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Evaluar alumno");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_2_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2_1.gridx = 0;
		gbc_lblNewLabel_1_2_1.gridy = 10;
		contentPane.add(lblNewLabel_1_2_1, gbc_lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Pulsa en el botón para registrar un curso");
		GridBagConstraints gbc_lblNewLabel_2_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1_1.gridy = 11;
		contentPane.add(lblNewLabel_2_1_1_1, gbc_lblNewLabel_2_1_1_1);
		
		btnEvaluarAlumno = new JButton("Evaluar Alumno");
		GridBagConstraints gbc_btnEvaluarAlumno = new GridBagConstraints();
		gbc_btnEvaluarAlumno.insets = new Insets(0, 0, 5, 0);
		gbc_btnEvaluarAlumno.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEvaluarAlumno.gridx = 1;
		gbc_btnEvaluarAlumno.gridy = 11;
		contentPane.add(btnEvaluarAlumno, gbc_btnEvaluarAlumno);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("MOSTAR ESTADÍSTICAS");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_2_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_2_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2_1_1.gridx = 0;
		gbc_lblNewLabel_1_2_1_1.gridy = 13;
		contentPane.add(lblNewLabel_1_2_1_1, gbc_lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Pulsa en el botón para registrar un curso");
		GridBagConstraints gbc_lblNewLabel_2_1_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1_1_1.gridy = 14;
		contentPane.add(lblNewLabel_2_1_1_1_1, gbc_lblNewLabel_2_1_1_1_1);
		
		btnMostrarEstadsticas = new JButton("MOSTRAR ESTADÍSTICAS");
		btnMostrarEstadsticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnMostrarEstadsticas = new GridBagConstraints();
		gbc_btnMostrarEstadsticas.gridheight = 2;
		gbc_btnMostrarEstadsticas.insets = new Insets(0, 0, 5, 0);
		gbc_btnMostrarEstadsticas.fill = GridBagConstraints.BOTH;
		gbc_btnMostrarEstadsticas.gridwidth = 2;
		gbc_btnMostrarEstadsticas.gridx = 0;
		gbc_btnMostrarEstadsticas.gridy = 15;
		contentPane.add(btnMostrarEstadsticas, gbc_btnMostrarEstadsticas);
		
		btnInformeCursos = new JButton("Mostrar informe cursos");
		GridBagConstraints gbc_btnInformeCursos = new GridBagConstraints();
		gbc_btnInformeCursos.insets = new Insets(0, 0, 0, 5);
		gbc_btnInformeCursos.gridx = 0;
		gbc_btnInformeCursos.gridy = 17;
		contentPane.add(btnInformeCursos, gbc_btnInformeCursos);
		
		btnInformeGlobal = new JButton("Mostrar informe global");
		GridBagConstraints gbc_btnInformeGlobal = new GridBagConstraints();
		gbc_btnInformeGlobal.gridx = 1;
		gbc_btnInformeGlobal.gridy = 17;
		contentPane.add(btnInformeGlobal, gbc_btnInformeGlobal);
		
	}

}
