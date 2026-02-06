package PSP.Tema2.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Principal {
    public static void main(String[] args) {

        mostrarMenu();

        // URL url = iniciarURL();
        // System.out.println(url);

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
            System.out.println("\t1 -> INICIAR URL's");
            System.out.println("\t2 -> GET CLIENTES");
            System.out.println("\t9 -> Salir");

            System.out.println("\nEscribe tú opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    URL url = iniciarURL();
                    System.out.println(url);
                }
                    break;
                case 2: {
                    getClientes();
                }
                case 9:
                    return;

                default:
                    System.out.println("\nSelecciona una opción correcta");
                    break;
            }

        }
    }

    private static void getClientes() {

        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/clientes/rest.php/clientes";

        try {
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                while ((linea = bufferIn.readLine()) != null)
                    json += linea;
                bufferIn.close();

                /*
                 * Analizamos el JSON devuelto, que sabemos que es un array de objetos cliente
                 */

                JSONArray datos = new JSONArray(json);
                for (int i = 0; i < datos.length(); i++) {
                    JSONObject cliente = datos.getJSONObject(i);
                    String nombre = cliente.getString("nombre");
                    boolean vip = (cliente.getString("vip").equals("1"));
                    int codProvincia = cliente.getInt("codProvincia");
                    System.out.printf("%s de %d %s es VIP\n", nombre, codProvincia, vip ? "" : "no");
                }
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }
}
