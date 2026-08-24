package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dialogs.AnadirProvinciaDialog;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Trabajadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApe1;
	private JTextField txtApe2;
	private JButton btnEliminarProvincia;
	private JTextField txtProfesiones;
	private JButton btnAnadirprofesion;
	private JButton btnEliminarProfesion;
	private JTable table;
	private JButton btnAñadirTrabajador;
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private JComboBox comboBox;
	private JButton btnAnadirProvincia;
	private JList list;
	
	public static DefaultComboBoxModel<String> provinciasComboBoxModel = new DefaultComboBoxModel<>();
	public static DefaultListModel<String> profesionesListModel = new DefaultListModel<String>();

	private String profesionSeleccionada;
	
	private DefaultTableModel tableModel = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre y apellidos", "Provincia", "Profesi\u00F3n"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trabajadores frame = new Trabajadores();
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
	public Trabajadores() {
		initComponents();
		comprobarEstadoBoton();
		anhadirListenerTextFields();
		
		
		AnadirProvinciaDialog dialog = new AnadirProvinciaDialog(this,false);
		btnAnadirProvincia.addActionListener(e -> dialog.setVisible(true));
		
		btnAnadirprofesion.addActionListener(e -> anadirProfesion());
		btnEliminarProfesion.addActionListener(e -> eliminarProfesion());
		btnAñadirTrabajador.addActionListener(e -> anadirTrabajador());
		

	}
	

	
	
	
	
	
	
	
	
	
	
	
	private void anadirTrabajador() {
		StringBuilder strBiulder = new StringBuilder();
		
		 final Pattern PATTERN_DNI = Pattern.compile("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$");
		 Matcher matcher = PATTERN_DNI.matcher(txtDNI.getText().toString().toUpperCase()); 
		 
		 if (!matcher.matches()) {
			 strBiulder.append("Error en la validación del DNI");
		}
		 
		if (list.getSelectedIndex() == -1) {
			strBiulder.append("\nDebes seleccionar una profesion");
		}
		
		if (comboBox.getSelectedItem() == null) {
			strBiulder.append("\nDebes seleccionar una provincia");
		}
		
		if (strBiulder.length() > 0) {
			JOptionPane.showMessageDialog(
			        this,  strBiulder.toString(), 
			        "añadir trabajador", 
			        JOptionPane.ERROR_MESSAGE
					);
		} else {
			tableModel.addRow(new String[]{txtNombre.getText().toString(),(String)comboBox.getSelectedItem(), (String)list.getSelectedValue()});
		}
	}
	
	
	
	private void eliminarProfesion() {
		profesionesListModel.remove(list.getSelectedIndex());
		btnEliminarProfesion.setEnabled(false);
	}
	
	
	private void anadirProfesion() {
		String text = txtProfesiones.getText().toString();
		
		if (profesionesListModel.contains(text)) {
			JOptionPane.showMessageDialog(
			        this,  "Problema al añadir una nueva profesión.\nProfesión ya está en la lista.", 
			        "Añadir profesión nueva", 
			        JOptionPane.ERROR_MESSAGE
			    );
			return;
			}
		
		if (!text.isEmpty()) {
			profesionesListModel.addElement(text);
			txtProfesiones.setText("");
		}
	}

	private void comprobarDNI() {
		StringBuilder problemas = new StringBuilder();
		String dni = txtDNI.getText().toString().toUpperCase();
		char[] letras = dni.toCharArray();
		
		if (letras.length != 8) {
			problemas.append("\n El DNI debe tener al menos 9 dígitos");
		}
		
		for (int i = 0; i < letras.length; i++) {
			char c = letras[i];
			
			if (i == letras.length) {
				if (!Character.isAlphabetic(c)) {
					problemas.append("\n - El último digito no es una letra ");
				}
				
			} else {
				if (!Character.isDigit(c)) {
					// No es válido
					problemas.append("\n - Los 8 primeros dígitos no son un número");
				}	
			}
		}
		
		if (problemas.isEmpty()) {
			JOptionPane.showConfirmDialog(null, "Validación exitosa");
		} else {	
			JOptionPane.showInputDialog(problemas);
		}
	}
	
	private void anhadirListenerTextFields() {
		for (JTextField field : fields) {
			field.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					comprobarEstadoBoton();
					
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
				}
			});;
		}
	}
	
	private boolean comprobarEstadoBoton() {
		for (JTextField jTextField : fields) {
			if (jTextField.getText().toString().trim().isEmpty()) {
				btnAñadirTrabajador.setEnabled(false);
				return false;
			}
		}
		btnAñadirTrabajador.setEnabled(true);
		
		return true;
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 153, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelTrabajador = new JPanel();
		panelTrabajador.setBorder(new TitledBorder(null, "Identificaci\u00F3n del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelTrabajador = new GridBagConstraints();
		gbc_panelTrabajador.insets = new Insets(0, 0, 5, 5);
		gbc_panelTrabajador.fill = GridBagConstraints.BOTH;
		gbc_panelTrabajador.gridx = 0;
		gbc_panelTrabajador.gridy = 0;
		contentPane.add(panelTrabajador, gbc_panelTrabajador);
		GridBagLayout gbl_panelTrabajador = new GridBagLayout();
		gbl_panelTrabajador.columnWidths = new int[]{0, 0, 0};
		gbl_panelTrabajador.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelTrabajador.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelTrabajador.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTrabajador.setLayout(gbl_panelTrabajador);
		
		JLabel lblNewLabel = new JLabel("DNI");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelTrabajador.add(lblNewLabel, gbc_lblNewLabel);
		
		txtDNI = new JTextField();
		GridBagConstraints gbc_txtDNI = new GridBagConstraints();
		gbc_txtDNI.insets = new Insets(0, 0, 5, 0);
		gbc_txtDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDNI.gridx = 1;
		gbc_txtDNI.gridy = 0;
		panelTrabajador.add(txtDNI, gbc_txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panelTrabajador.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 1;
		panelTrabajador.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido 1");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panelTrabajador.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtApe1 = new JTextField();
		GridBagConstraints gbc_txtApe1 = new GridBagConstraints();
		gbc_txtApe1.insets = new Insets(0, 0, 5, 0);
		gbc_txtApe1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApe1.gridx = 1;
		gbc_txtApe1.gridy = 2;
		panelTrabajador.add(txtApe1, gbc_txtApe1);
		txtApe1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido 2");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panelTrabajador.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		txtApe2 = new JTextField();
		GridBagConstraints gbc_txtApe2 = new GridBagConstraints();
		gbc_txtApe2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApe2.gridx = 1;
		gbc_txtApe2.gridy = 3;
		panelTrabajador.add(txtApe2, gbc_txtApe2);
		txtApe2.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Trabajadores disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(104);
		scrollPane.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Provincia del trabajdor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		contentPane.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel_4.add(comboBox, gbc_comboBox);
		
		btnEliminarProvincia = new JButton("Eliminar provincia");
		GridBagConstraints gbc_btnEliminarProvincia = new GridBagConstraints();
		gbc_btnEliminarProvincia.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminarProvincia.gridx = 1;
		gbc_btnEliminarProvincia.gridy = 0;
		panel_4.add(btnEliminarProvincia, gbc_btnEliminarProvincia);
		
		btnAnadirProvincia = new JButton("Añadir provincia");
		GridBagConstraints gbc_btnAnadirProvincia = new GridBagConstraints();
		gbc_btnAnadirProvincia.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAnadirProvincia.gridwidth = 2;
		gbc_btnAnadirProvincia.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnadirProvincia.gridx = 0;
		gbc_btnAnadirProvincia.gridy = 1;
		panel_4.add(btnAnadirProvincia, gbc_btnAnadirProvincia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Profesi\u00F3n del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e != null) {
					btnEliminarProfesion.setEnabled(true);
					
				}
			}
		});
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		panel_2.add(list, gbc_list);
		
		btnEliminarProfesion = new JButton("Eliminar profesión");
		GridBagConstraints gbc_btnEliminarProfesion = new GridBagConstraints();
		gbc_btnEliminarProfesion.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminarProfesion.anchor = GridBagConstraints.NORTH;
		gbc_btnEliminarProfesion.gridx = 1;
		gbc_btnEliminarProfesion.gridy = 0;
		panel_2.add(btnEliminarProfesion, gbc_btnEliminarProfesion);
		
		txtProfesiones = new JTextField();
		txtProfesiones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtProfesiones.getText().length()>0) {
					btnAnadirprofesion.setEnabled(true);
				}else {
					btnAnadirprofesion.setEnabled(false);
				}
			}
		});
		GridBagConstraints gbc_txtProfesiones = new GridBagConstraints();
		gbc_txtProfesiones.insets = new Insets(0, 0, 0, 5);
		gbc_txtProfesiones.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProfesiones.gridx = 0;
		gbc_txtProfesiones.gridy = 1;
		panel_2.add(txtProfesiones, gbc_txtProfesiones);
		txtProfesiones.setColumns(10);
		
		btnAnadirprofesion = new JButton("Añadir profesión");
		GridBagConstraints gbc_btnAnadirprofesion = new GridBagConstraints();
		gbc_btnAnadirprofesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAnadirprofesion.gridx = 1;
		gbc_btnAnadirprofesion.gridy = 1;
		panel_2.add(btnAnadirprofesion, gbc_btnAnadirprofesion);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Detalle del trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 3;
		contentPane.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JTextPane textPane = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 0;
		panel_3.add(textPane, gbc_textPane);
		
		btnAñadirTrabajador = new JButton("Añadir trabajador");
		GridBagConstraints gbc_btnAñadirTrabajador = new GridBagConstraints();
		gbc_btnAñadirTrabajador.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAñadirTrabajador.insets = new Insets(0, 0, 0, 5);
		gbc_btnAñadirTrabajador.gridx = 0;
		gbc_btnAñadirTrabajador.gridy = 4;
		contentPane.add(btnAñadirTrabajador, gbc_btnAñadirTrabajador);
		
		fields.add(txtDNI);
		fields.add(txtNombre);
		fields.add(txtApe1);
		fields.add(txtApe2);
		
		
		provinciasComboBoxModel.addElement("Pontevedra");
		comboBox.setModel(provinciasComboBoxModel);
		comboBox.setSelectedIndex(0);
		
		list.setModel(profesionesListModel);
		
		btnAnadirprofesion.setEnabled(false);
		btnEliminarProfesion.setEnabled(false);
		
		
		
	}

}
