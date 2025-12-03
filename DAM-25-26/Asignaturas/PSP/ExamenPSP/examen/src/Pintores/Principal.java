package Pintores;
public class Principal {

    public static void main(String[] args) {
        final int NUM_HILOS_PINTORES = 10;
        Pintor[] pintores = new Pintor[NUM_HILOS_PINTORES];
        final int TIEMPO_TRABAJADO = 1000;

        Casa casa = new Casa();
        for (int i = 0; i < pintores.length; i++) {
            pintores[i] = new Pintor(i, casa);
            pintores[i].start();
        }

        try {
            Thread.sleep(TIEMPO_TRABAJADO);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // for (Pintor pintor : pintores) {
        //     try {
        //         pintor.join();
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }




    }


    
}