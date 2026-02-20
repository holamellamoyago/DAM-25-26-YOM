package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Font;

public class ResgistroCursos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tf_curso_id;
	private JTextField tf_curso_nombre;
	private JTextField tf_curso_sesiones;
	private JButton btn_registrar_curso;

	/**
	 * Launch the application.
	 */

	public ResgistroCursos(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		
		
        btn_registrar_curso.addActionListener(e -> {
            String id = tf_curso_id.getText();
            String nombre = tf_curso_nombre.getText();
            int sesiones;
            try {
                sesiones = Integer.parseInt(tf_curso_sesiones.getText());
            } catch (NumberFormatException ex) {
                System.out.println("Número de sesiones inválido");
                return;
            }
            try (Connection con = getConnection()) {
                String sql = "INSERT INTO cursos (id, nombre, num_total_sesiones) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, nombre);
                pst.setInt(3, sesiones);
                pst.executeUpdate();
                System.out.println("Curso registrado correctamente");
                //actualizarTablas();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
	}
	
    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/examen_final_dint_centro",
                "root", // usuario
                ""      // contraseña
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

	private void initComponents() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblNewLabel = new JLabel("REGISTRAR UN NUEVO CURSO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Añade la información necesaria abajo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblCurso = new JLabel("ID Cursdo");
		GridBagConstraints gbc_lblCurso = new GridBagConstraints();
		gbc_lblCurso.anchor = GridBagConstraints.WEST;
		gbc_lblCurso.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurso.gridx = 0;
		gbc_lblCurso.gridy = 2;
		contentPanel.add(lblCurso, gbc_lblCurso);
		
		tf_curso_id = new JTextField();
		GridBagConstraints gbc_tf_curso_id = new GridBagConstraints();
		gbc_tf_curso_id.insets = new Insets(0, 0, 5, 0);
		gbc_tf_curso_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_curso_id.gridx = 1;
		gbc_tf_curso_id.gridy = 2;
		contentPanel.add(tf_curso_id, gbc_tf_curso_id);
		
		JLabel lb_curso_nombre = new JLabel("Nombre");
		GridBagConstraints gbc_lb_curso_nombre = new GridBagConstraints();
		gbc_lb_curso_nombre.anchor = GridBagConstraints.WEST;
		gbc_lb_curso_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_lb_curso_nombre.gridx = 0;
		gbc_lb_curso_nombre.gridy = 3;
		contentPanel.add(lb_curso_nombre, gbc_lb_curso_nombre);
		
		tf_curso_nombre = new JTextField();
		GridBagConstraints gbc_tf_curso_nombre = new GridBagConstraints();
		gbc_tf_curso_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_tf_curso_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_curso_nombre.gridx = 1;
		gbc_tf_curso_nombre.gridy = 3;
		contentPanel.add(tf_curso_nombre, gbc_tf_curso_nombre);
		
		JLabel lb_curso_sesiones = new JLabel("Nº sesiones");
		GridBagConstraints gbc_lb_curso_sesiones = new GridBagConstraints();
		gbc_lb_curso_sesiones.anchor = GridBagConstraints.WEST;
		gbc_lb_curso_sesiones.insets = new Insets(0, 0, 5, 5);
		gbc_lb_curso_sesiones.gridx = 0;
		gbc_lb_curso_sesiones.gridy = 4;
		contentPanel.add(lb_curso_sesiones, gbc_lb_curso_sesiones);
		
		tf_curso_sesiones = new JTextField();
		GridBagConstraints gbc_tf_curso_sesiones = new GridBagConstraints();
		gbc_tf_curso_sesiones.insets = new Insets(0, 0, 5, 0);
		gbc_tf_curso_sesiones.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_curso_sesiones.gridx = 1;
		gbc_tf_curso_sesiones.gridy = 4;
		contentPanel.add(tf_curso_sesiones, gbc_tf_curso_sesiones);
		
		btn_registrar_curso = new JButton("Registrar Curso");
		GridBagConstraints gbc_btn_registrar_curso = new GridBagConstraints();
		gbc_btn_registrar_curso.gridwidth = 2;
		gbc_btn_registrar_curso.insets = new Insets(0, 0, 5, 0);
		gbc_btn_registrar_curso.gridx = 0;
		gbc_btn_registrar_curso.gridy = 6;
		contentPanel.add(btn_registrar_curso, gbc_btn_registrar_curso);
	}
}
