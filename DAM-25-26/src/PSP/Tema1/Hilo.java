package PSP.Tema1;

public class Hilo extends Thread {

    @Override
    public void run() {
        final int VALOR = 10;

        for (int i = 0; i < VALOR; i++) {
            System.out.println(i);
            // try {
            // sleep(100);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
        }
        super.run();
    }

    public static void main(String[] args) {
        Hilo hilo1 = new Hilo();
        Hilo hilo2 = new Hilo();

        hilo1.start();
        System.out.println(99);
        hilo2.start();
        System.out.println(88);
    }
}
