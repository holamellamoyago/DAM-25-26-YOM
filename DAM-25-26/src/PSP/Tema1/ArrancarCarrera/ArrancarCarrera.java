

public class ArrancarCarrera {
    public static final Object lock = new Object();
    final static int NUM_HILOS = 10;

    static Hilo[] hilos = new Hilo[NUM_HILOS];
    public static int numHilosEsperando = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < NUM_HILOS; i++) {
            hilos[i] = new Hilo(i);
            hilos[i].start();
        }

        Thread.sleep(100);

        while (numHilosEsperando != NUM_HILOS) {
            Thread.sleep(1);
        }

        iniciarCarrera();



        System.out.println("Arranca carrera");
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }

        System.out.println("Termino la carrera");

        
    }

    private static void iniciarCarrera() {
        for (int i = 0; i < hilos.length; i++) {
            synchronized(lock){
                lock.notifyAll();
            }
        }
    }
}