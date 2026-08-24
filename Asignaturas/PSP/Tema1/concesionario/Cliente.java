package PSP.Tema1.concesionario;

import java.util.Random;

public class Cliente extends Thread {
    private Concesionario concesionario;
    private int numero;

    private boolean haComprado;

    public Cliente(Concesionario concesionario, int numero) {
        this.concesionario = concesionario;
        this.numero = numero;
        this.haComprado = false;
    }

    @Override
    public void run() {
        try {
            while (!haComprado) {
                Coche coche;
                while ((coche = concesionario.agendarCita(this)) == null) {
                    synchronized (concesionario) {
                        concesionario.wait();
                    }
                }

                coche.sumarVisita();
                sleep(200);

                if (!haComprado && coche.numeroVisitas > (new Random().nextInt(100) + 1)) {
                    concesionario.comprarCoche(this, coche);
                    haComprado = true;

                } else {
                    concesionario.liberarCoche(this, coche);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Cliente " + numero;
    }

}
