package dialogs;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class EvaluarAlumno extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public EvaluarAlumno(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEvaluarAUn = new JLabel("EVALUAR A UN ALUMNO");
		lblEvaluarAUn.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblEvaluarAUn = new GridBagConstraints();
		gbc_lblEvaluarAUn.gridwidth = 3;
		gbc_lblEvaluarAUn.insets = new Insets(0, 0, 5, 0);
		gbc_lblEvaluarAUn.gridx = 0;
		gbc_lblEvaluarAUn.gridy = 0;
		getContentPane().add(lblEvaluarAUn, gbc_lblEvaluarAUn);
		
		JLabel lblNewLabel_1 = new JLabel("Añade la información necesaria abajo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lb_curso_evaluar = new JLabel("Curso ID");
		GridBagConstraints gbc_lb_curso_evaluar = new GridBagConstraints();
		gbc_lb_curso_evaluar.anchor = GridBagConstraints.WEST;
		gbc_lb_curso_evaluar.insets = new Insets(0, 0, 5, 5);
		gbc_lb_curso_evaluar.gridx = 0;
		gbc_lb_curso_evaluar.gridy = 2;
		getContentPane().add(lb_curso_evaluar, gbc_lb_curso_evaluar);
		
		JComboBox<String> cb_curso_id_evaluar = new JComboBox<String>();
		GridBagConstraints gbc_cb_curso_id_evaluar = new GridBagConstraints();
		gbc_cb_curso_id_evaluar.gridwidth = 2;
		gbc_cb_curso_id_evaluar.insets = new Insets(0, 0, 5, 0);
		gbc_cb_curso_id_evaluar.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_curso_id_evaluar.gridx = 1;
		gbc_cb_curso_id_evaluar.gridy = 2;
		getContentPane().add(cb_curso_id_evaluar, gbc_cb_curso_id_evaluar);
		
		JLabel lb_dni_evaluar = new JLabel("DNI");
		GridBagConstraints gbc_lb_dni_evaluar = new GridBagConstraints();
		gbc_lb_dni_evaluar.anchor = GridBagConstraints.WEST;
		gbc_lb_dni_evaluar.insets = new Insets(0, 0, 5, 5);
		gbc_lb_dni_evaluar.gridx = 0;
		gbc_lb_dni_evaluar.gridy = 3;
		getContentPane().add(lb_dni_evaluar, gbc_lb_dni_evaluar);
		
		JComboBox<String> cb_dni_evaluar = new JComboBox<String>();
		GridBagConstraints gbc_cb_dni_evaluar = new GridBagConstraints();
		gbc_cb_dni_evaluar.gridwidth = 2;
		gbc_cb_dni_evaluar.insets = new Insets(0, 0, 5, 0);
		gbc_cb_dni_evaluar.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_dni_evaluar.gridx = 1;
		gbc_cb_dni_evaluar.gridy = 3;
		getContentPane().add(cb_dni_evaluar, gbc_cb_dni_evaluar);
		
		JLabel lb_nota_ev1 = new JLabel("Nota EV1");
		GridBagConstraints gbc_lb_nota_ev1 = new GridBagConstraints();
		gbc_lb_nota_ev1.insets = new Insets(0, 0, 5, 5);
		gbc_lb_nota_ev1.gridx = 0;
		gbc_lb_nota_ev1.gridy = 4;
		getContentPane().add(lb_nota_ev1, gbc_lb_nota_ev1);
		
		JLabel lb_nota_ev2 = new JLabel("Nota EV2");
		GridBagConstraints gbc_lb_nota_ev2 = new GridBagConstraints();
		gbc_lb_nota_ev2.insets = new Insets(0, 0, 5, 5);
		gbc_lb_nota_ev2.gridx = 1;
		gbc_lb_nota_ev2.gridy = 4;
		getContentPane().add(lb_nota_ev2, gbc_lb_nota_ev2);
		
		JLabel lb_nota_ev3 = new JLabel("Nota EV3");
		GridBagConstraints gbc_lb_nota_ev3 = new GridBagConstraints();
		gbc_lb_nota_ev3.insets = new Insets(0, 0, 5, 0);
		gbc_lb_nota_ev3.gridx = 2;
		gbc_lb_nota_ev3.gridy = 4;
		getContentPane().add(lb_nota_ev3, gbc_lb_nota_ev3);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 5;
		getContentPane().add(textField, gbc_textField);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 5;
		getContentPane().add(textField_1, gbc_textField_1);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 5;
		getContentPane().add(textField_2, gbc_textField_2);
		
		JLabel lb_faltas = new JLabel("Num. faltas");
		GridBagConstraints gbc_lb_faltas = new GridBagConstraints();
		gbc_lb_faltas.anchor = GridBagConstraints.WEST;
		gbc_lb_faltas.insets = new Insets(0, 0, 5, 5);
		gbc_lb_faltas.gridx = 0;
		gbc_lb_faltas.gridy = 6;
		getContentPane().add(lb_faltas, gbc_lb_faltas);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 2;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 6;
		getContentPane().add(textField_3, gbc_textField_3);
		
		JButton btn_evaluar_alumno = new JButton("Evaluar alumno");
		GridBagConstraints gbc_btn_evaluar_alumno = new GridBagConstraints();
		gbc_btn_evaluar_alumno.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_evaluar_alumno.gridwidth = 3;
		gbc_btn_evaluar_alumno.gridx = 0;
		gbc_btn_evaluar_alumno.gridy = 7;
		getContentPane().add(btn_evaluar_alumno, gbc_btn_evaluar_alumno);
		initComponents();
		

	}

	private void initComponents() {
		setBounds(100, 100, 450, 300);
		
	}

}
