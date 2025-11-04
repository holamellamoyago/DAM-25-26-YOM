package PSP.Tema1Repaso.Ejercicio6;

import java.io.IOException;

public class Pescador extends Thread {
    String nombre;

    public Pescador(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {

            synchronized (Ejercicio6.class) {
                while (!Ejercicio6.pescaDisponible) {
                    wait();
                }
            }

            Ejercicio6.escribir(nombre);

            synchronized (Ejercicio6.class) {
                notifyAll();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
