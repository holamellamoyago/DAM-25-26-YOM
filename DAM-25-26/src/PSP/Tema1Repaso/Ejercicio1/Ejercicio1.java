package PSP.Tema1Repaso.Ejercicio1;

public class Ejercicio1 {
    public static void main(String[] args) throws InterruptedException {
        final int MAX_HILOS = 100;
        Hilo[] hilos = new Hilo[MAX_HILOS];
        Hilo hilo;

        for (int i = 0; i < MAX_HILOS; i++) {
            hilo = new Hilo(i);
            hilos[i] = hilo;
            hilo.start();
            // Thread.sleep(50);
        }


        for (Hilo h : hilos) {
            h.join();
        }
    }
}
