package PSP.Tema1.Ejercicio7Ampliado;

import java.util.ArrayList;
import java.util.Random;

public class Empleado extends Thread implements Comparable<Empleado>{
    final int NUM_APUESTAS_EMPLEADO  = 5;
    Random rdm = new Random();
    int numeroEmpleado;

    ArrayList<Apuesta> apuestas = new ArrayList<>();

    public Empleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    @Override
    public void run() {

        esperar();

        generarApuestas();

        super.run();
    }

    private void esperar() {
        try {
            sleep(rdm.nextInt(300) + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generarApuestas(){
        for (int i = 0; i < NUM_APUESTAS_EMPLEADO; i++) {
            apuestas.add(new Apuesta());
        }
    }


    public int getDineroApostado(){
        int total = 0;
        for (Apuesta apuesta : apuestas) {
            total += apuesta.getPrecio();
        }

        return total;
    }


    @Override
    public String toString() {
        return "Empleado " + numeroEmpleado + ":\n" + apuestas;
    }

    @Override
    public int compareTo(Empleado o) {
        return o.getName().compareTo(this.getName());
    }

}
