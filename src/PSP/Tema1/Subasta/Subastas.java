package PSP.Tema1.Subasta;

import java.util.Random;

public class Subastas {

    public static void main(String[] args) {
        Random rdm = new Random();
        Galeria galeria = new Galeria();

        for (int i = 0; i < Galeria.obras.length; i++) {
            Galeria.obras[i] = new Obra(i, rdm.nextInt(3000) + 500);
        }

        for (int i = 0; i < Galeria.pujadores.length; i++) {
            Galeria.pujadores[i] = new Pujador(i, galeria);
            Galeria.pujadores[i].start();
        }

        for (int i = 0; i < Galeria.pujadores.length; i++) {
            try {
                Galeria.pujadores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
