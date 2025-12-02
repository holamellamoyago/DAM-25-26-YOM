package PSP.Tema1.Ejercicio5;

import java.util.Random;

public abstract class Persona extends Thread {
        Oficina ofi;
    String nombre;

    public Persona(String nombre, Oficina ofi) {
        this.ofi = ofi;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random rdm = new Random();
        try {
            Thread.sleep(rdm.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
    }

  
}
