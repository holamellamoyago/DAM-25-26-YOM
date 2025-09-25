
package PSP.Tema1.Ejercicio3;

import java.util.Random;

public class Hilo extends Thread {
    Random rdm = new Random();

    @Override
    public void run() {
        try {
            wait(rdm.nextInt(500));

            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}