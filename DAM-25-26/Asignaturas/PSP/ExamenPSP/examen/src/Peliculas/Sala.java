package Peliculas;

import java.util.Random;

public class Sala {
    final int AFORO_SALA = 20;
    public int aforoActual = 0;
    public int aforoSinEntrada = 0;

    public  Pelicula pelicula;
    private boolean ocupadada;
    private int numero;

    Random rdm = new Random();
    final int NUM_PORCENTAJE = 50;

    public Sala(int numero, Pelicula pelicula) {
        this.pelicula = pelicula;
        ocupadada = false;
        this.numero = numero;
    }

    public synchronized boolean quedanEntradas(Cinefilo cinefilo) {
        if (aforoActual >= AFORO_SALA) {
            System.out.println(cinefilo + " quiso comprar entradas en " + this + " pero no quedan");
            aforoSinEntrada++;
            return false;
        }

        return true;
    }

    public synchronized void hacerCola(Cinefilo cinefilo) {
        while (ocupadada) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized boolean comprarEntrada(Cinefilo cinefilo) {
        try {
            Thread.sleep(rdm.nextInt(10));
        } catch (InterruptedException e) {
        }

        // Devuelve que si compra la pelicula
        if (rdm.nextInt(100) > NUM_PORCENTAJE) {
            System.out.println(cinefilo + " compra la entrada de " + this);
            actualizarAforo();

            // Libero si compra
            pelicula.anhadirCinefilo(cinefilo);
            ocupadada = false;
            notify();
            return true;
        }

        System.out.println(cinefilo + " decide no comprar la entrada de " + this);

        // Libero si no compra
        ocupadada = false;
        notify();

        return false;
    }

    private void actualizarAforo() {
        aforoActual++;
    }

    @Override
    public String toString() {
        return "Sala" + numero;
    }




}
