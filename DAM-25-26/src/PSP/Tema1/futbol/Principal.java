package PSP.Tema1.futbol;

import java.util.ArrayList;

public class Principal {
    
    public static void main(String[] args) {
        final int NUMERO_AFICIONADOS = 100;
        ArrayList<Aficionado> aficionados = new ArrayList<>();
        
        Estadio estadio = new Estadio();
        estadio.start();

        for (int i = 0; i < NUMERO_AFICIONADOS; i++) {
            Aficionado aficinado = new Aficionado(estadio, i);
            aficinado.start();
            aficionados.add(aficinado);
        }
    }
}
