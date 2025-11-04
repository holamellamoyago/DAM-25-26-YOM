package PSP.Tema1Repaso.Ejercicio2;

public class Hilo extends Thread {
    @Override
    public synchronized void run() {
        try {
            Thread.sleep(10);

            synchronized (Ejercicio2.class) {
                Ejercicio2.NUM_INCREMENTOS++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
