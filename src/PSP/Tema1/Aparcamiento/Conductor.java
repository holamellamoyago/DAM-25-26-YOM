package PSP.Tema1.Aparcamiento;

import java.util.Random;

public class Conductor extends Thread {
    Aparcamiento aparcamiento;

    public Conductor(int i, Aparcamiento aparcamiento) {
        super("Conductor" + String.valueOf(i));
        this.aparcamiento = aparcamiento;
    }

    @Override
    public void run() {
        Plaza plaza;

        while ((plaza = aparcamiento.obtenerPlaza(this)) == null) {
            try {
                synchronized (aparcamiento) {
                    aparcamiento.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            sleep(new Random().nextInt(500) + 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        aparcamiento.devolverPlaza(this, plaza.numeroPlaza);

    }

    @Override
    public String toString() {
        return getName();
    }

}
