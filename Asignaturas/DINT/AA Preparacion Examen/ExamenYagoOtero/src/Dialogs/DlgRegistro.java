package Dialogs;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;

import Clases.Usuario;
import Frames.InterfazPrincipal;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class DlgRegistro extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtEdad;
	private JCheckBox chValidacion;

	private ArrayList<JTextField> textfields = new ArrayList<JTextField>();
	private JButton btnValidar;
	private JButton btnRegistrar;
	private JMenuItem mnValidar;
	private JMenuItem mnRegistrar;

	/**
	 * Launch the application.
	 */
	public DlgRegistro(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		anhadirListenerFields();
		
		InterfazPrincipal.registroVentanas.numVentanasRegistro++;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				InterfazPrincipal.registroVentanas.numVentanasRegistro--;

			}
		});
		
		

		btnValidar.addActionListener(e -> validarDatos());
		btnRegistrar.addActionListener(e -> registrar());
		
		
		mnRegistrar.addActionListener(e -> registrar());
		mnValidar.addActionListener(e -> validarDatos());
		
		

	}

	/**
	 * Create the dialog.
	 */

	private void validarDatos() {

		String dni = txtDNI.getText().toString();
		String nombre = txtNombre.getText().toString();
		String apellidos = txtApellidos.getText().toString();
		String edad = txtEdad.getText().toString();

		if (!comprobarDNI()) {
			JOptionPane.showMessageDialog(this, "Error comprobación DNI", "Registro persona",
					JOptionPane.ERROR_MESSAGE);

		}

		if (!comprobarCamposVacios()) {
			JOptionPane.showMessageDialog(this, "Debes rellar todos los campos vacios", "Validar datos",
					JOptionPane.ERROR_MESSAGE);
		}

		if (convertirEdad(edad) != null) {
			btnRegistrar.setEnabled(true);
			mnRegistrar.setEnabled(true);

		}

	}

	private void anhadirListenerFields() {
		for (int i = 0; i < textfields.size(); i++) {
			textfields.get(i).addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// *******************************************************************
					btnRegistrar.setEnabled(false);
					mnRegistrar.setEnabled(false);

				}
			});

		}

	}

	public void registrar() {
		String dni = txtDNI.getText().toString();
		String nombre = txtNombre.getText().toString();
		String apellidos = txtApellidos.getText().toString();
		int edad = convertirEdad(txtEdad.getText().toString());

		Usuario usuario = new Usuario(dni, nombre, apellidos, edad);
		InterfazPrincipal.usuarios.add(usuario);
		InterfazPrincipal.poblarComboBox();
		InterfazPrincipal.registroVentanas.numVentanasRegistro--;

		this.setVisible(false);
	}

	private Integer convertirEdad(String edad) {
		try {
			return Integer.valueOf(edad);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Escribe una edad correcta", "Validar datos",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	// Devuelve false si no matchea
	private boolean comprobarDNI() {
		final Pattern PATTERN_DNI = Pattern.compile("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$");
		Matcher matcher = PATTERN_DNI.matcher(txtDNI.getText().toString().toUpperCase());

		if (!matcher.matches()) {
			return false;
		}

		return true;
	}

	// Devuelve false si queda algunbo vacio
	private boolean comprobarCamposVacios() {

		for (int i = 0; i < textfields.size(); i++) {
			String texto = textfields.get(i).getText().toString().trim();
			if (texto.equals("") || texto == null) {
				return false;
			}

		}

		return true;
	}

	private void initComponents() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(
				new TitledBorder(null, "Registro usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("DNI");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtDNI = new JTextField();
			txtDNI.addInputMethodListener(new InputMethodListener() {
				public void caretPositionChanged(InputMethodEvent event) {
				}

				public void inputMethodTextChanged(InputMethodEvent event) {
					btnRegistrar.setEnabled(true);
					mnRegistrar.setEnabled(true);
				}
			});
			GridBagConstraints gbc_txtDNI = new GridBagConstraints();
			gbc_txtDNI.insets = new Insets(0, 0, 5, 0);
			gbc_txtDNI.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDNI.gridx = 1;
			gbc_txtDNI.gridy = 0;
			contentPanel.add(txtDNI, gbc_txtDNI);
			txtDNI.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			txtNombre = new JTextField();

			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 1;
			gbc_txtNombre.gridy = 1;
			contentPanel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Apellidos");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			txtApellidos = new JTextField();
			GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
			gbc_txtApellidos.insets = new Insets(0, 0, 5, 0);
			gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtApellidos.gridx = 1;
			gbc_txtApellidos.gridy = 2;
			contentPanel.add(txtApellidos, gbc_txtApellidos);
			txtApellidos.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Edad");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 3;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			txtEdad = new JTextField();
			GridBagConstraints gbc_txtEdad = new GridBagConstraints();
			gbc_txtEdad.insets = new Insets(0, 0, 5, 0);
			gbc_txtEdad.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEdad.gridx = 1;
			gbc_txtEdad.gridy = 3;
			contentPanel.add(txtEdad, gbc_txtEdad);
			txtEdad.setColumns(10);
		}
		{
			chValidacion = new JCheckBox("Forzar validación exitosa");
			chValidacion.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
						mostrarMensajeValidacion();
				}
			});
			GridBagConstraints gbc_chValidacion = new GridBagConstraints();
			gbc_chValidacion.insets = new Insets(0, 0, 5, 0);
			gbc_chValidacion.anchor = GridBagConstraints.EAST;
			gbc_chValidacion.gridx = 1;
			gbc_chValidacion.gridy = 4;
			contentPanel.add(chValidacion, gbc_chValidacion);
		}
		{
			btnValidar = new JButton("VALIDAR DATOS");
			GridBagConstraints gbc_btnValidar = new GridBagConstraints();
			gbc_btnValidar.gridwidth = 2;
			gbc_btnValidar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnValidar.insets = new Insets(0, 0, 5, 0);
			gbc_btnValidar.gridx = 0;
			gbc_btnValidar.gridy = 5;
			contentPanel.add(btnValidar, gbc_btnValidar);
		}
		{
			btnRegistrar = new JButton("REGISTRAR");
			btnRegistrar.setEnabled(false);
			GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
			gbc_btnRegistrar.gridwidth = 2;
			gbc_btnRegistrar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnRegistrar.gridx = 0;
			gbc_btnRegistrar.gridy = 6;
			contentPanel.add(btnRegistrar, gbc_btnRegistrar);
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnNewMenu = new JMenu("Menu");
				menuBar.add(mnNewMenu);
				{
					mnValidar = new JMenuItem("Validar datos");
					mnValidar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
					mnNewMenu.add(mnValidar);
				}
				{
					mnRegistrar = new JMenuItem("Registrar");
					mnRegistrar.setEnabled(false);
					mnRegistrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
					mnNewMenu.add(mnRegistrar);
				}
			}
		}

		textfields.add(txtDNI);
		textfields.add(txtNombre);
		textfields.add(txtApellidos);
		textfields.add(txtEdad);

	}

	protected void mostrarMensajeValidacion() {
		if (chValidacion.isEnabled()) {
			JOptionPane.showMessageDialog(this, "Datos validados a la fuerza", "Validacion forzada", JOptionPane.QUESTION_MESSAGE);
			btnRegistrar.setEnabled(true);
			mnRegistrar.setEnabled(true);
		}
		
		
	}

}
