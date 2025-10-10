package PSP.Tema1.EjercicioPaciencia;

import java.util.Random;

public class Trabajador extends Thread {
    int paciencia;

    public Trabajador(int paciencia) {
        this.paciencia = paciencia;
    }

    @Override
    public void run() {
        System.out.println("El trabajador comienza a trabajar ... ");
        boolean numEncontrado = false;

        try {
            while (!numEncontrado) {
                numEncontrado = terminarPaciencia();
            }
            interrupt();
        } catch (InterruptedException e) {
            paciencia--;

            if (paciencia == 0) {
                interrupt();
            }
        }
        super.run();
    }

    private boolean terminarPaciencia() {
        Random rdm = new Random();
        int nRandom = rdm.nextInt(1000);

        if (nRandom == 6) {
            return true;
        }

        return false;

    }

    public int getPaciencia() {
        return paciencia;
    }

    public void setPaciencia(int paciencia) {
        this.paciencia = paciencia;
    }
}
