package botones;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;

public class BotonEspectacular extends JButton{
	String CLAVE_SECRETA;
	private static int contador = 0;
	
	
	
	public BotonEspectacular(String texto) {
        super(texto + " " + String.valueOf(contador));
        setContentAreaFilled(false); // Quita el fondo por defecto
        setFocusPainted(false);      // Quita el borde de enfoque
        
        char[] letras = {'a', 'b', 'c'};
        String palabra = "";
        for (int i = 0; i < (new Random().nextInt(10) +1); i++) {
			palabra += letras[new Random().nextInt(letras.length)];
		}
        
        this.CLAVE_SECRETA = palabra;
        setPalabra(CLAVE_SECRETA);
    }
	
	
	@Override
	public void setText(String text) {
		super.setText(text);
	}
	
	@Override
	public String getText() {
		return super.getText();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    g2.setColor(getBackground()); 
	    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);


	    super.paintComponent(g);
	}
	
	@Override
	public void addActionListener(ActionListener l) {
		super.addActionListener(l);
	}
	
	// _____________________ Yago ____________________
	
	public void setPalabra(String palabra) {
		CLAVE_SECRETA = palabra;
		
		String txt = "";
		for (int i = 0; i < palabra.length(); i++) {
			txt += "-";
		}
		
		setText(txt);
	}
	
	public boolean completarVerificacion() {
		char[] chars = getText().toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != '-') {
				chars[i] = CLAVE_SECRETA.charAt(i);
				
			}
		}
	}
	
	public boolean isCompletada() {
		
	
	}
	}

}
