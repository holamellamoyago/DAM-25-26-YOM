package AD.Tema1.Ejercicio3;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Operaciones {
    private static Map<String, Integer> contador = new TreeMap<>();

    public static void main(String[] args) {

        System.out.println(args.length);

        for (String archivo : args) {
            contarPalabras(archivo, "escoba");
            
        }
        
        System.out.println("Aparece un total de: " + buscarPalabra("escoba"));


    }

    private static void contarPalabras(String ficheroEntrada, String palabraBuscar) {
        lecturaTexto entrada = new lecturaTexto(ficheroEntrada);
        entrada.abrirArchivo();

        String linea = entrada.leerLinea();

        while (linea != null) {

            // String limpia = linea.replaceAll("[\\p[Punct]]", " ");

            String[] palabras = linea.split(" ");

            for (String palabra : palabras) {
                añadirPalabra(palabra);
            }

            linea = entrada.leerLinea();
        }


    }

    private static void añadirPalabra(String palabra) {
        if (contador.containsKey(palabra)) {
            contador.put(palabra, contador.get(palabra) + 1);
        } else {
            contador.put(palabra, 1);
        }
    }

    private static int buscarPalabra(String palabra) {
        if (!contador.containsKey(palabra)) {
            return 0;
        } else {
            return contador.get(palabra);
        }

    }
}
