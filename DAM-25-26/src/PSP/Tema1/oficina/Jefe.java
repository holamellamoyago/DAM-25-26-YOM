package PSP.Tema1.oficina;

import java.util.Random;

public class Jefe extends Thread {

    private Oficina oficina;

    public Jefe(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public void run() {
        try {
            sleep(new Random().nextInt(5000));
            
            oficina.llegaJefe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
