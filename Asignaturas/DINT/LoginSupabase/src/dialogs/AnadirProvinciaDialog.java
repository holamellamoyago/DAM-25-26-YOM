package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentation.Trabajadores;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.awt.event.ActionEvent;

public class AnadirProvinciaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProvincia;
	private JButton btnAnadir;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	
	
	
	public AnadirProvinciaDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		
		btnAnadir.addActionListener(e -> anadirProvincia());
	}
	
	
	
	

	private void anadirProvincia() {
		String text = txtProvincia.getText().toString();
		if (text.isEmpty() || text == null) {
			return;
		}
		
		Trabajadores.provinciasComboBoxModel.addElement(text);
		setVisible(false);
	}





	private void initComponents() {
		setBounds(100, 100, 450, 101);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Nombre de la nueva provincia");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtProvincia = new JTextField();
			GridBagConstraints gbc_txtProvincia = new GridBagConstraints();
			gbc_txtProvincia.gridwidth = 2;
			gbc_txtProvincia.insets = new Insets(0, 0, 5, 0);
			gbc_txtProvincia.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtProvincia.gridx = 1;
			gbc_txtProvincia.gridy = 0;
			contentPanel.add(txtProvincia, gbc_txtProvincia);
			txtProvincia.setColumns(10);
		}
		{
			btnAnadir = new JButton("Añadir provincia");
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			{
				btnCancelar = new JButton("Cancelar");
				GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
				gbc_btnCancelar.anchor = GridBagConstraints.EAST;
				gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
				gbc_btnCancelar.gridx = 1;
				gbc_btnCancelar.gridy = 1;
				contentPanel.add(btnCancelar, gbc_btnCancelar);
			}
			GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
			gbc_btnAnadir.anchor = GridBagConstraints.EAST;
			gbc_btnAnadir.gridx = 2;
			gbc_btnAnadir.gridy = 1;
			contentPanel.add(btnAnadir, gbc_btnAnadir);
		}
			
	}

}
