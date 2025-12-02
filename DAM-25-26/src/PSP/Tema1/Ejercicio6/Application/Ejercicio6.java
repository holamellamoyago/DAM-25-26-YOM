package PSP.Tema1.Ejercicio6.Application;

import java.util.ArrayList;
import java.util.List;

import PSP.Tema1.Ejercicio6.Controller.ArchivoController;
import PSP.Tema1.Ejercicio6.Model.Linea;
import PSP.Tema1.Ejercicio6.Model.LineaImpar;
import PSP.Tema1.Ejercicio6.Model.LineaPar;

public class Ejercicio6 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Linea> threads = new ArrayList<>();

        final int LINEAS = 50;

        for (int i = 0; i < LINEAS; i++) {
            LineaImpar impar = new LineaImpar();
            LineaPar par = new LineaPar();

            impar.start();
            par.start();

            threads.addAll(List.of(impar, par));
        }

        for (Linea linea : threads) {
            linea.join();
        }

        System.out.println("Hilos terminados");

    }
}
