package PSP.Tema1.Ejercicio2;

public class Hilo extends Thread {
    Contador contador;

    public Hilo(Contador contador) {
        this.contador = contador;
    }

    final int NUM_INCREMENTOS = 2;

    @Override
    public void run() {
        for (int i = 0; i < NUM_INCREMENTOS; i++) {
            contador.aumentarcontador();
        }
    }

}
