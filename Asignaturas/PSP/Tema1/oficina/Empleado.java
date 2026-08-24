package PSP.Tema1.oficina;

import java.util.Random;

public class Empleado extends Thread {
    public String nombre;
    public Oficina oficina;

    public Empleado(Oficina oficina, String nombre) {
        this.oficina = oficina;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            sleep(new Random().nextInt(5000));
            oficina.llegarOficina(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nombre;
    }

}
