package PSP.Tema1.futbol;

import java.util.Random;

public class Aficionado extends Thread {
    private Estadio estadio;
    private int numero;

    public Aficionado(Estadio estadio, int n) {
        this.estadio = estadio;
        this.numero = n;
    }

    @Override
    public void run() {
        try {

            synchronized (estadio) {
                while (!estadio.puertasAbiertas) {
                    System.out.println("El " + this + " está esperando a que habrá el estadio");
                    estadio.wait();
                }
            }

            Puerta puerta = estadio.escogerPuerta(this);

            puerta.entrarAlEstadio(this);

            sleep(new Random().nextLong(5000));

            puerta.salirDelEstadio(this);
            puerta.cola.remove(this);

            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Aficionado " + numero;
    }

}
