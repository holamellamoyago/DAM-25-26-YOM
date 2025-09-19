package PSP.Tema1.Ejercicio2;

public class Ejercicio2 {

    public static void main(String[] args) {
        Contador contador = new Contador(0);
        final int NUM_HILOS = 2;

        for (int i = 0; i < NUM_HILOS; i++) {
            Hilo h = new Hilo(contador);
            h.start();
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(contador);

    }
}
