package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import supabase.SupabaseAuth;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class RegisterScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField textField;
	private JButton btnCrearCuenta;
	private StringBuilder strBuilder = new StringBuilder();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	
	

	
	
	
	public RegisterScreen() {
		initWidgets();
		
		btnCrearCuenta.addActionListener(e -> crearCuenta());
		
		

	}
	
	
	private void crearCuenta() {
        String signupResult;

        
			
			try {
				signupResult = SupabaseAuth.register(txtEmail.getText().toString(), txtPassword.getText().toString());
				System.out.println("Registro:\n" + signupResult);
				
				if (signupResult.contains("code")) {
					String[] error = signupResult.split(",");
					
						char[] letras = error[2].toCharArray();
						ArrayList<String> arrayError = new ArrayList<String>();
						
						for (int i = 0; i < letras.length; i++) {
							if (letras[i] != '"') {
								strBuilder.append(letras[i]);
							}
						}
						
						mostrarMensajeError(strBuilder.toString());
						 
				} else {
					MainScreen screen = new MainScreen();
					screen.setVisible(true);
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	
	
	private void mostrarMensajeError(String error) {
		ImageIcon icona=new ImageIcon(getClass().getResource("/image/icono.png")); 
		JOptionPane.showMessageDialog(this, error, "Error",JOptionPane.WARNING_MESSAGE, icona); 
	}
		
	
	private void initWidgets() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_2 = new JLabel("EL SCAPEROOM DEL NANO FIAUUUUUUUUUU");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Registrarse");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 1;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel img = new JLabel("");
		img.setIcon(new ImageIcon(LoginScreen.class.getResource("/image/martin.jpeg")));
		GridBagConstraints gbc_img = new GridBagConstraints();
		gbc_img.gridwidth = 2;
		gbc_img.insets = new Insets(0, 0, 5, 5);
		gbc_img.gridx = 1;
		gbc_img.gridy = 2;
		contentPane.add(img, gbc_img);
		
		JLabel lblNewLabel = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 3;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 5;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 5;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 4;
		contentPane.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Confirmar contraseña");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnCrearCuenta = new JButton("Crear cuenta");
		GridBagConstraints gbc_btnCrearCuenta = new GridBagConstraints();
		gbc_btnCrearCuenta.gridwidth = 2;
		gbc_btnCrearCuenta.fill = GridBagConstraints.BOTH;
		gbc_btnCrearCuenta.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrearCuenta.gridx = 1;
		gbc_btnCrearCuenta.gridy = 6;
		contentPane.add(btnCrearCuenta, gbc_btnCrearCuenta);
		
	}

}
