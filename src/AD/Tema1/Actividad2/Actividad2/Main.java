package AD.Tema1.Actividad2.Actividad2;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        contarLineas(args, "log.txt");
    }

    public static void contarLineas(String[] ficheros, String nombreSalida) {
        escribirTexto salida = new escribirTexto(new File(nombreSalida));
        lecturaTexto entrada;

        System.out.println(ficheros.length);

        int cont = 0;

        for (String fichero : ficheros) {
            entrada = new lecturaTexto(new File(fichero));

            if (entrada.existe()) {
                // for (lecturaTexto e : entrada.abrirArchivo(nombreSalida);) {
                    
                // }
            } else {
                System.out.println("No existe el fichero");
            }

        }
    }

    
}
