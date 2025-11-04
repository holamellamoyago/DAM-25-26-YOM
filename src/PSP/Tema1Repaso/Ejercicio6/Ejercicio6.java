package PSP.Tema1Repaso.Ejercicio6;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio6 {
    static BufferedWriter writter;
    static final int NUM_PESCADORES = 3;
    static int numeroKilos = 1;
    static boolean pescaDisponible = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        writter = new BufferedWriter(new FileWriter("Pescadores.txt"));
        Pescador p1 = new Pescador("Manolo");
        Pescador p2 = new Pescador("Pepe");

        p1.start();
        p2.start();

        p1.join();
        p2.join();

    }

    public static  void sumarKilo(){
        numeroKilos++;
    }

    public static synchronized void escribir(String nombre) throws IOException{
        pescaDisponible = false;
        writter.write( nombre + ": Yo pesque un pez de " + numeroKilos + " kilos");
        sumarKilo();
        pescaDisponible = true;
    }
}
