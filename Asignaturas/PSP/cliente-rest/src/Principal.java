
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


import clases.Cliente;

public class Principal {
    private static Scanner sc = new Scanner(System.in);
    private static String baseURL = "http://localhost/clientes/index.php/clientes";
    private static String URI_GET_CLIENTE = "http://localhost/clientes/index.php/cliente/";

    private static HttpClient client;
    private static HttpRequest request;

    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.println("\nSELECCIONA LA OPCIÓN QUE TE GUSTARÍA:");
            System.out.println("\t1.Listar clientes");
            System.out.println("\t2.Registrar clientes");
            System.out.println("\t3.Borrar cliente");
            System.out.println("\t4.Obtener un cliente");
            System.out.println("\t5.Actualizar nombre de un cliente");
            // System.out.println("\t3.Actualizar clientes");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    obtenerClientes();
                    break;

                case 2:
                    anadirCliente();
                    break;

                case 3:
                    borrarCliente();
                    break;

                case 4:
                    System.out.println(obtenerCliente(5));
                    break;

                case 5:
                    actualizarNombreCliente();
                    break;

                default:
                    break;
            }
        }

    }

    private static void actualizarNombreCliente() {
        Scanner sc = new Scanner(System.in);

        Cliente cliente = null;
        while (cliente == null) {
            System.out.println("Que cliente te gustaría escoger? Escribe su código.");
            obtenerClientes().forEach(t -> t.toStringSimple());

            int codCliente = sc.nextInt();
            cliente = obtenerCliente(codCliente);

            if (cliente == null)
                System.out.println("Cliente no encuentrado, vuelve a intentarlo");
        }

        client = HttpClient.newHttpClient();
        JSONObject clienteJson = new JSONObject();

        // PATCH solo modifica el campo nombre

        System.out.println("Cual es el nuevo nombre para este cliente?");
        sc.nextLine();
        String nuevoNombre = sc.nextLine();

        clienteJson.put("nombre", nuevoNombre);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_GET_CLIENTE + cliente.getCodCliente()))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(clienteJson.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 200:
                    System.out.println("Nombre del cliente " + cliente.getCodCliente() + " actualizado correctamente");
                    System.out.println(response.body());
                    break;
                case 404:
                    System.out.println("Cliente " + cliente.getCodCliente() + " no encontrado");
                    break;
                case 400:
                    System.out.println("Error en la petición");
                    System.out.println(response.body());
                    break;
                case 500:
                    System.out.println("Error del servidor");
                    break;
                default:
                    System.out.println("Error inesperado: " + response.statusCode());
                    System.out.println(response.body());
            }
        } catch (Exception e) {
            System.out.println("Problemas");
        }
    }

    private static void borrarCliente() {
        // Atención aquí !!!!
        Scanner sc = new Scanner(System.in);
        String uri = "http://localhost/clientes/index.php/cliente/";

        System.out.println("De todos estos clientes, ¿Cual te gustaría eliminar? Escribe su código ");
        System.out.println(obtenerClientes());

        int codCliente = sc.nextInt(); // ID del cliente a eliminar
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + codCliente))
                .header("Accept", "application/json")
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 200:
                    System.out.println("Cliente " + codCliente + " eliminado correctamente");
                    System.out.println(response.body());
                    break;
                case 204:
                    System.out.println("Cliente " + codCliente + " eliminado correctamente");
                    break;
                case 404:
                    System.out.println("Cliente " + codCliente + " no encontrado");
                    break;
                case 500:
                    System.out.println("Error del servidor");
                    break;
                default:
                    System.out.println("Error inesperado: " + response.statusCode());
                    throw new RuntimeException(response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema a la hora de eliminar un clientes");

        }
    }

    private static void anadirCliente() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Dime un NIF");
        String nif = sc.nextLine();

        System.out.println("Dime un nombre");
        String nombre = sc.nextLine();

        System.out.println("Dime un apellido");
        String apellido = sc.nextLine();

        System.out.println("Selecciona una provincia de las siguientes: ");
        int codProvincia = sc.nextInt();

        client = HttpClient.newHttpClient();
        JSONObject clienteJson = new JSONObject();

        clienteJson.put("nombre", nombre);
        clienteJson.put("codProvincia", codProvincia);
        clienteJson.put("apellidos", apellido);
        clienteJson.put("NIF", nif);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(clienteJson.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 201:
                    JSONObject respuestaJson = new JSONObject(response.body());
                    int codCliente = respuestaJson.getInt("codCliente");
                    System.out.println("ID generado: " + codCliente);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema a la hora de añadir un cliente nuevo");
        }
    }

    private static Cliente obtenerCliente(int codCliente) {

        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_GET_CLIENTE + codCliente))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            switch (statusCode) {
                case 200:
                    return procesarCliente(response.body());
                case 400:
                    throw new RuntimeException("Error en la peticion");
                case 500:
                    throw new RuntimeException("Error en el servidor");
                default:
                    System.out.println("Error inesperado: " + statusCode);
                    System.out.println("Respuesta del servidor:");
                    throw new RuntimeException(response.body());
            }

        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema a la hora de solicitar los clientes");
        }

    }

    private static ArrayList<Cliente> obtenerClientes() {
        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            switch (statusCode) {
                case 200:
                    client.close();
                    return procesarClientes(response.body());
                case 400:
                    throw new RuntimeException("Error en la peticion");
                case 500:
                    throw new RuntimeException("Error en el servidor");
                default:
                    System.out.println("Error inesperado: " + statusCode);
                    System.out.println("Respuesta del servidor:");
                    throw new RuntimeException(response.body());
            }

        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema a la hora de solicitar los clientes");
        }

    }

    private static ArrayList<Cliente> procesarClientes(String json) {
        JSONArray arrayClientes = new JSONArray(json);
        ArrayList<Cliente> clientes = new ArrayList<>();

        for (int i = 0; i < arrayClientes.length(); i++) {
            JSONObject c = arrayClientes.getJSONObject(i);
            String nombre = c.getString("nombre");
            int codProvincia = c.getInt("codProvincia");
            String nif = c.getString("NIF");
            int codCliente = c.getInt("codCliente");

            // procesar los datos del cliente
            clientes.add(new Cliente(codCliente, nombre, codProvincia, nif));
        }

        clientes.forEach(System.out::println);
        return clientes;
    }

    private static Cliente procesarCliente(String json) {
        JSONObject c = new JSONObject(json);

        String nombre = c.getString("nombre");
        int codProvincia = c.getInt("codProvincia");
        String nif = c.getString("NIF");
        int codCliente = c.getInt("codCliente");

        // procesar los datos del cliente
        return new Cliente(codCliente, nombre, codProvincia, nif);
    }
}
