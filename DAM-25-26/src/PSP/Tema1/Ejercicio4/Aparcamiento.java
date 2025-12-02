package PSP.Tema1.Ejercicio4;

import java.util.ArrayList;

/* 
 * Clases:
 *  Plaza : en ella guardaré cada numero que tiene y su estado actual "ocupada" (boolean)
 *  Conductor: hilo que cuando inicia busca una plaza, cuando la tiene , entra sincronizando, lo mismo saliendo
 * 
 */

public class Aparcamiento {
    final static int NUM_PLAZAS = 3;
    static Conductor[] conductores = new Conductor[10];
    static Plaza[] plazas = new Plaza[NUM_PLAZAS];
    static Object objeto = new Object();

    public static void main(String[] args) throws InterruptedException {

        iniciarPlazasAparcamiento();

        objeto.wait();

        for (int i = 0; i < conductores.length; i++) {
            conductores[i] = new Conductor(i);
            conductores[i].start();
        }

        // Espero a que todos los hilos terminen
        for (Conductor hilo : conductores) {
            hilo.join();
        }
    }

    private static void iniciarPlazasAparcamiento() {
        for (int i = 0; i < plazas.length; i++) {
            plazas[i] = new Plaza(i);
        }
    }

}
