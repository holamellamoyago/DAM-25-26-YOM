package PSP.Tema1.Ejercicio7;

import java.util.Random;

public class Empleado extends Thread {
    Random rdm = new Random();
    int numeroEmpleado;
    Porra porra;

    public Empleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    @Override
    public void run() {

        esperar();

        porra = new Porra();

        super.run();
    }

    private void esperar() {
        try {
            sleep(rdm.nextInt(300) + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public int getDineroApostado(){
        return porra.getCantidadApuestas() * porra.getDineroApuesta();
    }


    @Override
    public String toString() {
        return "Empleado " + numeroEmpleado + ":\n" + porra;
    }

}
