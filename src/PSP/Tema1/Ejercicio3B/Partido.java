package PSP.Tema1.Ejercicio3B;

import java.util.Comparator;

public class Partido implements Comparator<Partido> {
    private String nombre;
    private int contador;

    public Partido(String nombre) {
        this.nombre = nombre;
        this.contador = 0;
    }

    public synchronized void sumarVoto() {
        int x = contador;
        x++;

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        contador = x;

    }

    // public synchronized void sumarVoto(){
    // contador++;
    // }

    @Override
    public String toString() {
        return "Partido " + nombre + " " + contador;
    }

    // @Override
    // public int compareTo(Partido o) {
    //     return contador - o.contador;
    // }

    @Override
    public int compare(Partido o1, Partido o2) {
        return o1.contador-o2.contador;
    }

    public int getContador() {
        return contador;
    }

    

}
