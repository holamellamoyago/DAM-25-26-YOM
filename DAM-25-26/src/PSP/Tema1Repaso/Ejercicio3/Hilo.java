package PSP.Tema1Repaso.Ejercicio3;

import java.util.Random;

public class Hilo extends Thread {
    Random rdm = new Random();

    @Override
    public void run() {
        int nRandom = rdm.nextInt(Ejercicio3Repaso.partidos.length);

        Partido partido =  Ejercicio3Repaso.partidos[nRandom];
        partido.sumarVoto();
    }
}
