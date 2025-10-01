package PSP.Tema1.Ejercicio3B;

import java.util.Random;

public class Votante extends Thread {
    Random rdm = new Random();

    @Override
    public void run() {
        Partido partido = Votacion.partidos.get(rdm.nextInt(Votacion.NUM_PARTIDOS));

        partido.sumarVoto();
    }
}
