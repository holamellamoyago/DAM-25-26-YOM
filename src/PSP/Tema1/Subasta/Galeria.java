package PSP.Tema1.Subasta;

import java.util.Random;

public class Galeria {
    public static Obra[] obras = new Obra[15];
    public static Pujador[] pujadores = new Pujador[60];

    private Random rdm = new Random();
    // Booleano que indíca si el vendedor esta atendiendo
    private boolean estaAtendiendo = false;

    public synchronized Obra asignarObra(Pujador pujador) {
        try {

            while (estaAtendiendo) {
                wait();
            }

            estaAtendiendo = true;
            Obra obra = obras[rdm.nextInt(obras.length)];
            System.out.println(pujador + " comienza a mirar la " + obra);

            return obra;
        } catch (InterruptedException e) {
            throw new ArithmeticException(e.toString());
        }
    }

    public synchronized void dejarDeMirar(Pujador pujador){
        estaAtendiendo = false;
        System.out.println(pujador + " deja de mirar");
        notify();
    }
}
