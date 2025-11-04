package PSP.Tema1Repaso.Ejercicio2;

public class Ejercicio2 {
    static int NUM_INCREMENTOS = 0;

    public static void main(String[] args) throws InterruptedException {
        final int NUM_HILOS = 100;
        Hilo[] hilos = new Hilo[NUM_HILOS];
        Hilo hilo;

        for (int i = 0; i < NUM_HILOS; i++) {
            hilo = new Hilo();
            hilos[i] = hilo;
            hilo.start();
        }

        for (Hilo h : hilos) {
            h.join();
        }

        System.out.println(NUM_INCREMENTOS);
    }

}
