package PSP.Tema1.Ejercicio2;

public class Ejercicio2 {

    public static void main(String[] args) {
        Contador contador = new Contador(0);
        final int NUM_HILOS = 2;

        Hilo h = null;
        Hilo[] hilos = new Hilo[NUM_HILOS];

        for (int i = 0; i < NUM_HILOS; i++) {
            h = new Hilo(contador);
            h.start();
            hilos[i] = h;
        }

        for (Hilo hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(contador);

    }
}
