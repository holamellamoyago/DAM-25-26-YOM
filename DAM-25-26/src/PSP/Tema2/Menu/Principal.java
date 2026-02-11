package PSP.Tema2.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Principal {
        private static URL url = null;
        private static HttpURLConnection con = null;
        private static String json = "";
        private static final String baseURL = "http://localhost/clientes/rest.php";
    // private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {

        mostrarMenu();

        // URL url = iniciarURL();
        // System.out.println(url);

        System.out.println("\nSe termino el programa");

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
            System.out.println("\t3 -> ACTUALIZAR CLIENTES");
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
                    break;

                case 3: {
                    patchProvinciaCliente();
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

    private static void patchProvinciaCliente() {
        Scanner sc = new Scanner(System.in);

        Cliente cliente = null;
        do {
            System.out.println("De que cliente te gustaría actualizar la provincia? (Introduce el nombre)");
            ArrayList<Cliente> clientes = getClientes();
            System.out.println(clientes);

            String nombre = sc.nextLine();

            for (Cliente c : clientes) {
                if (c.getNombre().equals(nombre)) {
                    cliente = c;
                    break;
                }
            }

            if (cliente == null) {
                System.out.println("Cliente no encontrado, vuelva a introducirlo.");
            } else {
                con = 
            }

        } while (cliente == null);

    }

    private static ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        baseURL += "clientes";

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
                    int codCliente = cliente.getInt("codCliente");
                    String nombre = cliente.getString("nombre");
                    boolean vip = (cliente.getInt("vip") == 1);
                    int codProvincia = cliente.getInt("codProvincia");
                    clientes.add(new Cliente(codCliente, codProvincia, nombre, vip));
                    System.out.printf("%s de %d %s es VIP\n", nombre, codProvincia, vip ? "" : "no");
                }
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }

            return clientes;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
