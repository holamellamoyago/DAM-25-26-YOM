package PSP.Tema1.Carrera;

import java.util.Random;

public class Juez extends Thread{
    private Carrera carrera;


    public Juez (Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(2000)+ 1000);

            carrera.comenzarCarrera();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
