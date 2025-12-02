package PSP.Tema1.Ejercicio3;

import java.util.Random;

public class Contador {
    // String nombre;
    private int contador;

    

    public Contador() {
        this.contador = 0;
    }


    @Override
    public String toString() {
        return contador + "";
    }

    // TODO sincronizar aqui 
    public int aumentarcontador() {
        contador++;
        System.out.println("Se aumento " + contador);
        return contador;
    }
}
