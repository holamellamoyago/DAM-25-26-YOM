package dialogs;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AsignarCursosAlumno extends JDialog {

	private static final long serialVersionUID = 1L;


	public AsignarCursosAlumno(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblNewLabel = new JLabel("REGISTRAR A UN ALUMNO EN UN CURSO");
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
		
		JLabel lb_curso_asignar = new JLabel("Curso ID");
		GridBagConstraints gbc_lb_curso_asignar = new GridBagConstraints();
		gbc_lb_curso_asignar.anchor = GridBagConstraints.WEST;
		gbc_lb_curso_asignar.insets = new Insets(0, 0, 5, 5);
		gbc_lb_curso_asignar.gridx = 0;
		gbc_lb_curso_asignar.gridy = 2;
		contentPanel.add(lb_curso_asignar, gbc_lb_curso_asignar);
		
		JComboBox<String> cb_curso_id_asignar = new JComboBox<String>();
		GridBagConstraints gbc_cb_curso_id_asignar = new GridBagConstraints();
		gbc_cb_curso_id_asignar.insets = new Insets(0, 0, 5, 0);
		gbc_cb_curso_id_asignar.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_curso_id_asignar.gridx = 1;
		gbc_cb_curso_id_asignar.gridy = 2;
		contentPanel.add(cb_curso_id_asignar, gbc_cb_curso_id_asignar);
		
		JLabel lb_dni_asignar = new JLabel("DNI");
		GridBagConstraints gbc_lb_dni_asignar = new GridBagConstraints();
		gbc_lb_dni_asignar.anchor = GridBagConstraints.WEST;
		gbc_lb_dni_asignar.insets = new Insets(0, 0, 5, 5);
		gbc_lb_dni_asignar.gridx = 0;
		gbc_lb_dni_asignar.gridy = 3;
		contentPanel.add(lb_dni_asignar, gbc_lb_dni_asignar);
		
		JComboBox<String> cb_dni_asignar = new JComboBox<String>();
		GridBagConstraints gbc_cb_dni_asignar = new GridBagConstraints();
		gbc_cb_dni_asignar.insets = new Insets(0, 0, 5, 0);
		gbc_cb_dni_asignar.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_dni_asignar.gridx = 1;
		gbc_cb_dni_asignar.gridy = 3;
		contentPanel.add(cb_dni_asignar, gbc_cb_dni_asignar);
		
		JButton btn_asignar_curso_alumno = new JButton("Asignar curso/alumno");
		GridBagConstraints gbc_btn_asignar_curso_alumno = new GridBagConstraints();
		gbc_btn_asignar_curso_alumno.gridwidth = 2;
		gbc_btn_asignar_curso_alumno.insets = new Insets(0, 0, 5, 0);
		gbc_btn_asignar_curso_alumno.gridx = 0;
		gbc_btn_asignar_curso_alumno.gridy = 4;
		contentPanel.add(btn_asignar_curso_alumno, gbc_btn_asignar_curso_alumno);
		initComponents();
	}
	
	private void initComponents() {
		setBounds(100, 100, 450, 300);
		
	}

}
