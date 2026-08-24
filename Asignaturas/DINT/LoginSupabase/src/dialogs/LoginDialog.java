package dialogs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentation.RegisterScreen;
import supabase.SupabaseAuth;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class LoginDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JButton btnRegistrarse;
	private JButton btnLogin;
	private JButton btnRecuperarContraseña;
	private JLabel lblError;
	private StringBuilder strBuilder = new StringBuilder();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog frame = new LoginDialog();
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
	public LoginDialog() {
		initWidgets();
		
		RegisterScreen registerScreen = new RegisterScreen();
		btnRegistrarse.addActionListener(e -> registerScreen.setVisible(true));
		btnLogin.addActionListener(e -> iniciarSesion());
		
		
		
		
	}
	
	private void iniciarSesion() {
		try {
			String loginResult = SupabaseAuth.login(txtEmail.getText().toString(), txtPassword.getText().toString());
			System.out.println("Login:\n" + loginResult);
			
			if (loginResult.contains("code")) {
				String[] error = loginResult.split(",");
				
					char[] letras = error[2].toCharArray();
					ArrayList<String> arrayError = new ArrayList<String>();
					
					for (int i = 0; i < letras.length; i++) {
						if (letras[i] != '"') {
							strBuilder.append(letras[i]);
						}
					}
					lblError.setText(strBuilder.toString());
			}	
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private void initWidgets() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblNewLabel_3 = new JLabel("Para jugar, inicia sesión");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 1;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 2;
		contentPane.add(lblError, gbc_lblError);
		
		JLabel img = new JLabel("");
		img.setIcon(new ImageIcon(LoginDialog.class.getResource("/image/martin.jpeg")));
		GridBagConstraints gbc_img = new GridBagConstraints();
		gbc_img.gridwidth = 2;
		gbc_img.insets = new Insets(0, 0, 5, 5);
		gbc_img.gridx = 1;
		gbc_img.gridy = 3;
		contentPane.add(img, gbc_img);
		
		JLabel lblNewLabel = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 4;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 5;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 5;
		contentPane.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		btnRegistrarse = new JButton("¿No tienes cuenta?");
		GridBagConstraints gbc_btnRegistrarse = new GridBagConstraints();
		gbc_btnRegistrarse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistrarse.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrarse.gridx = 1;
		gbc_btnRegistrarse.gridy = 6;
		contentPane.add(btnRegistrarse, gbc_btnRegistrarse);
		
		btnLogin = new JButton("Iniciar sesión");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 6;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		btnRecuperarContraseña = new JButton("Recuperar contraseña");
		GridBagConstraints gbc_btnRecuperarContraseña = new GridBagConstraints();
		gbc_btnRecuperarContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRecuperarContraseña.gridwidth = 4;
		gbc_btnRecuperarContraseña.gridx = 0;
		gbc_btnRecuperarContraseña.gridy = 7;
		contentPane.add(btnRecuperarContraseña, gbc_btnRecuperarContraseña);
		

	}

}
