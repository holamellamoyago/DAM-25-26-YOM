package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Font;

public class RegistroAlumnos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tf_alumno_nombre;
	private JTextField tf_alumno_dni;
	private JTextField tf_alumno_apellido;
	private JButton btn_registrar_alumno;



	public RegistroAlumnos(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		
		
        // Registrar Alumno
		btn_registrar_alumno.addActionListener(e -> {
			String dni = tf_alumno_dni.getText();
			String nombre = tf_alumno_nombre.getText();
			String apellidos = tf_alumno_apellido.getText();
			
			if (dni.length() < 9) {
				JOptionPane.showMessageDialog(null, "el DNI debe ser correcto", "Error de validación", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (nombre.isEmpty() || apellidos.isEmpty()) {
				JOptionPane.showMessageDialog(null, "debe de tener un nobre y apellidos", "Error de validación", JOptionPane.ERROR_MESSAGE);
				return;

			}
			
            try (Connection con = getConnection()) {
                String sql = "INSERT INTO alumnos (dni, nombre, apellidos) VALUES (?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, dni);
                pst.setString(2, nombre);
                pst.setString(3, apellidos);
                pst.executeUpdate();
                System.out.println("Alumno registrado correctamente");
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
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblRegistrarUnAlumno = new JLabel("REGISTRAR UN ALUMNO");
		lblRegistrarUnAlumno.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblRegistrarUnAlumno = new GridBagConstraints();
		gbc_lblRegistrarUnAlumno.gridwidth = 2;
		gbc_lblRegistrarUnAlumno.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegistrarUnAlumno.gridx = 0;
		gbc_lblRegistrarUnAlumno.gridy = 0;
		contentPanel.add(lblRegistrarUnAlumno, gbc_lblRegistrarUnAlumno);
		
		JLabel lblNewLabel_1 = new JLabel("Añade la información necesaria abajo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lb_curso_nombre_1 = new JLabel("DNI");
		GridBagConstraints gbc_lb_curso_nombre_1 = new GridBagConstraints();
		gbc_lb_curso_nombre_1.anchor = GridBagConstraints.WEST;
		gbc_lb_curso_nombre_1.insets = new Insets(0, 0, 5, 5);
		gbc_lb_curso_nombre_1.gridx = 0;
		gbc_lb_curso_nombre_1.gridy = 2;
		contentPanel.add(lb_curso_nombre_1, gbc_lb_curso_nombre_1);
		
		tf_alumno_dni = new JTextField();
		GridBagConstraints gbc_tf_alumno_dni = new GridBagConstraints();
		gbc_tf_alumno_dni.insets = new Insets(0, 0, 5, 0);
		gbc_tf_alumno_dni.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_alumno_dni.gridx = 1;
		gbc_tf_alumno_dni.gridy = 2;
		contentPanel.add(tf_alumno_dni, gbc_tf_alumno_dni);
		
		JLabel lb_curso_nombre = new JLabel("Nombre");
		GridBagConstraints gbc_lb_curso_nombre = new GridBagConstraints();
		gbc_lb_curso_nombre.anchor = GridBagConstraints.WEST;
		gbc_lb_curso_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_lb_curso_nombre.gridx = 0;
		gbc_lb_curso_nombre.gridy = 3;
		contentPanel.add(lb_curso_nombre, gbc_lb_curso_nombre);
		
		tf_alumno_nombre = new JTextField();
		GridBagConstraints gbc_tf_alumno_nombre = new GridBagConstraints();
		gbc_tf_alumno_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_alumno_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_tf_alumno_nombre.gridx = 1;
		gbc_tf_alumno_nombre.gridy = 3;
		contentPanel.add(tf_alumno_nombre, gbc_tf_alumno_nombre);
		
		JLabel lb_curso_nombre_2 = new JLabel("Apellidos");
		GridBagConstraints gbc_lb_curso_nombre_2 = new GridBagConstraints();
		gbc_lb_curso_nombre_2.anchor = GridBagConstraints.EAST;
		gbc_lb_curso_nombre_2.insets = new Insets(0, 0, 5, 5);
		gbc_lb_curso_nombre_2.gridx = 0;
		gbc_lb_curso_nombre_2.gridy = 4;
		contentPanel.add(lb_curso_nombre_2, gbc_lb_curso_nombre_2);
		
		tf_alumno_apellido = new JTextField();
		GridBagConstraints gbc_tf_alumno_apellido = new GridBagConstraints();
		gbc_tf_alumno_apellido.insets = new Insets(0, 0, 5, 0);
		gbc_tf_alumno_apellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_alumno_apellido.gridx = 1;
		gbc_tf_alumno_apellido.gridy = 4;
		contentPanel.add(tf_alumno_apellido, gbc_tf_alumno_apellido);
		
		btn_registrar_alumno = new JButton("Registrar Alumno");
		GridBagConstraints gbc_btn_registrar_alumno = new GridBagConstraints();
		gbc_btn_registrar_alumno.gridwidth = 2;
		gbc_btn_registrar_alumno.insets = new Insets(0, 0, 5, 0);
		gbc_btn_registrar_alumno.gridx = 1;
		gbc_btn_registrar_alumno.gridy = 5;
		contentPanel.add(btn_registrar_alumno, gbc_btn_registrar_alumno);
	}

}
