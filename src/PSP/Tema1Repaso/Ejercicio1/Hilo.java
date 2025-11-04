package PSP.Tema1Repaso.Ejercicio1;

public class Hilo extends Thread {
    int numero;

    Hilo(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        System.out.println(getName());
    }
}
