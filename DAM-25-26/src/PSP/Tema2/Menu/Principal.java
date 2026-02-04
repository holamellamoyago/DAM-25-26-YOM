package PSP.Tema2.Menu;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        mostrarMenu();

        //URL url = iniciarURL();
        //System.out.println(url);

        System.out.println("Se termino el programa");

    }

    private static URL iniciarURL() {
        URL url = null;
        try {
            url = new URL("http://localhost/clientes/rest.php/clientes");
            System.out.printf("Protocolo: %s\n", url.getProtocol()); // http
            System.out.printf("Servidor: %s\n", url.getHost()); // ieschandomonte.edu.es
            System.out.printf("Puerto: %s\n", url.getPort()); // -1 .-
            System.out.printf("Archivo: %s\n", url.getFile()); // /concurso/prog.php?grupo=dam2&curso=1314
            System.out.printf("Ruta: %s\n", url.getPath()); // /concurso/prog.php
            System.out.printf("Cadena de búsqueda: %s\n", url.getQuery()); // grupo=dam2&curso=1314
            System.out.printf("Marcador: %s\n", url.getRef()); // ganadores

            return url;
        } catch (MalformedURLException ex) {
            System.out.println("Error en URL");
            throw new RuntimeException(ex);
        }

    }

    private static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        while (true) {
            System.out.println("MENU");
            System.out.println("\t1 -> GET CLIENTES");
            System.out.println("\t9 -> Salir");

            System.out.println("\nEscribe tú opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    URL url = iniciarURL();
                    System.out.println(url);
                }
                    break;
                case 9:
                    return;

                default:
                    System.out.println("\nSelecciona una opción correcta");
                    break;
            }

        }
    }
}
