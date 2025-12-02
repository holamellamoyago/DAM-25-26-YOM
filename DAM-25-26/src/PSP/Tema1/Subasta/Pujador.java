package PSP.Tema1.Subasta;

import java.util.Random;

public class Pujador extends Thread {
    private Galeria galeria;

    public Pujador(int i, Galeria galeria) {
        super("Pujador" + String.valueOf(i));
        this.galeria = galeria;
    }

    @Override
    public void run() {
        Obra obra = galeria.asignarObra(this);
        obra.sumarVisita();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (obra.nVisitas > new Random().nextInt(100)) {
            obra.adjudicada = true;
            obra.comprador = this;
            System.out.println("** " + this + " ha comrpadoo " + obra);
        }

        galeria.dejarDeMirar(this);

    }

    @Override
    public String toString() {
        return getName();
    }

}
