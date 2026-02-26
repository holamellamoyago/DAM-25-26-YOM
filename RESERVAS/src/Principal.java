import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;

import clases.Habitacion;
import clases.Reserva;

public class Principal {
    private static Scanner sc = new Scanner(System.in);
    private final static String BASE_URI_HABITACIONES = "http://localhost/reservas/index.php/habitaciones";
    private final static String BASE_URI_RESERVAS = "http://localhost/reservas/index.php/reservas";

    private final static String URI_RESERVA_HABITACION = "http://localhost/reservas/index.php/reserva-habitacion/";
    private final static String URI_HABITACION = "http://localhost/reservas/index.php/habitacion/";
    private final static String URI_RESERVA = "http://localhost/reservas/index.php/reserva/";

    private static HttpClient client;
    private static HttpRequest request;

    /* 
        COMENTARIO: 
        Utilicé los verbos GET, PUT , POST Y NO ME DIO TIEMPO A DELETE 
        ... 
    
    */

    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.println("\nSELECCIONA LA OPCIÓN QUE TE GUSTARÍA:");
            System.out.println("\t1.MOSTRAR TODAS LAS RESERVAS DE UNA HABITACIÓN CONCRETA");
            System.out.println("\t2.INSERTAR UNA RESERVA NUEVA");
            System.out.println("\t3.ELIMINAR UNA RESERVA");


            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    obtenerReservasHabitacion();
                    break;

                case 2:
                    realizarReserva();
                    // anadirCliente();
                    break;

                case 3:
                // TODO No me dió tiempo a terminarlo
                    // eliminarUnaReserva();
                    // borrarCliente();
                    break;

                default:
                    break;
            }
        }

    }

    private static void eliminarUnaReserva() {
        Reserva reserva = null;
        while (reserva == null) {
            System.out.println("\nDe las reservas siguientes, Cual te gustaría eliminar?\n");
            obtenerReservasHabitacion().forEach(t -> t.toString());

            int idHabitacion = sc.nextInt();
            reserva = obtenerReserva(idHabitacion);

            if (reserva == null)
                System.out.println("reserva no encuentrado, vuelve a intentarlo");
        }

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_RESERVA + reserva.getCodReserva()))
                .header("Accept", "application/json")
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 200:
                    System.out.println("Reserva  eliminado correctamente");
                    System.out.println(response.body());
                    break;
                case 204:
                    // System.out.println("Cliente " + codCliente + " eliminado correctamente");
                    break;
                case 404:
                    // System.out.println("Cliente " + codCliente + " no encontrado");
                    break;
                case 500:
                    // System.out.println("Error del servidor");
                    break;
                default:
                    System.out.println("Error inesperado: " + response.statusCode());
                    throw new RuntimeException(response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema a la hora de eliminar un clientes");

        }
    }

    private static Reserva obtenerReserva(int idHabitacion) {
        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_RESERVA + idHabitacion))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            switch (statusCode) {
                case 200:
                    // client.close();
                    return procesarReserva(response.body());
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
            throw new RuntimeException("Hubo un problema a la hora de solicitar la habitacion " + idHabitacion);
        }
    }

    private static void realizarReserva() {
        Scanner sc = new Scanner(System.in);

        Habitacion habitacion = null;
        while (habitacion == null) {
            System.out.println("\nDe las habitaciones siguientes, ¿De cual te gustaría hacer la reserva?\n");
            obtenerHabitaciones().forEach(t -> t.toString());

            int idHabitacion = sc.nextInt();
            habitacion = obtenerHabitacion(idHabitacion);

            if (habitacion == null)
                System.out.println("Cliente no encuentrado, vuelve a intentarlo");
        }

        String nombreDescriptivo = "";
        while (nombreDescriptivo.isEmpty()) {
            System.out.println("\nIntroduce un nombre descriptivo, porfavor");
            nombreDescriptivo = sc.nextLine();

            if (nombreDescriptivo.isEmpty())
                System.out.println("Debes introducir un nombre válido");
        }

        Integer dia = null;
        while (dia == null) {
            System.out.println("\nIntroduce el día de comienzo, porfavor");
            dia = sc.nextInt();
        }

        Integer dias = null;
        while (dias == null) {
            System.out.println("\nIntroduce cuantos días durará la reserva, porfavor");
            dias = sc.nextInt();
        }

        JSONObject reservaJSON = new JSONObject();
        reservaJSON.put("idHabitacion", habitacion.getIdHabitacion());
        reservaJSON.put("nombre", nombreDescriptivo);
        reservaJSON.put("dia", dia);
        reservaJSON.put("numDias", dias);

        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URI_RESERVAS))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(reservaJSON.toString()))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 201:
                    System.out.println("Se añadió la nueva reserva");
                    System.out.println(response.body());
                    break;
                case 404:
                    System.out.println("404");
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
                    break;
            }

        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema a la hora de solicitar las habitaciones");
        }
    }

    private static ArrayList<Habitacion> obtenerHabitaciones() {
        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URI_HABITACIONES))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            switch (statusCode) {
                case 200:
                    return procesarHabitaciones(response.body());
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
            throw new RuntimeException("Hubo un problema a la hora de solicitar las habitaciones");
        }
    }

    private static ArrayList<Reserva> obtenerReservasHabitacion() {
        Scanner sc = new Scanner(System.in);

        Habitacion habitacion = null;
        while (habitacion == null) {
            System.out.println("De las habitaciones siguientes, ¿De cual te gustaría mirar las reservas historicas?");
            obtenerHabitaciones().forEach(t -> t.toString());

            int idHabitacion = sc.nextInt();
            habitacion = obtenerHabitacion(idHabitacion);

            if (habitacion == null)
                System.out.println("Cliente no encuentrado, vuelve a intentarlo");
        }

        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_RESERVA_HABITACION + habitacion.getIdHabitacion()))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            switch (statusCode) {
                case 200:
                    return procesarReservas(response.body());
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
            throw new RuntimeException(
                    "Hubo un problema a la hora de recoger las reservcas historicas de una habitacion");
        }
    }

    private static Habitacion obtenerHabitacion(int idHabitacion) {
        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(URI_HABITACION + idHabitacion))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            switch (statusCode) {
                case 200:
                    // client.close();
                    return procesarHabitacion(response.body());
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
            throw new RuntimeException("Hubo un problema a la hora de solicitar la habitacion " + idHabitacion);
        }
    }

    private static ArrayList<Habitacion> procesarHabitaciones(String json) {
        JSONArray arrayClientes = new JSONArray(json);
        ArrayList<Habitacion> habitaciones = new ArrayList<>();

        for (int i = 0; i < arrayClientes.length(); i++) {
            JSONObject c = arrayClientes.getJSONObject(i);
            int idHabitacion = c.getInt("idHabitacion");
            String nombre = c.getString("nombre");

            // procesar los datos del cliente
            habitaciones.add(new Habitacion(idHabitacion, nombre));
        }

        habitaciones.forEach(System.out::println);
        return habitaciones;
    }

    private static ArrayList<Reserva> procesarReservas(String json) {
        System.out.println(json);
        JSONArray arrayClientes = new JSONArray(json);
        ArrayList<Reserva> reservas = new ArrayList<>();

        for (int i = 0; i < arrayClientes.length(); i++) {
            JSONObject c = arrayClientes.getJSONObject(i);
            int codReserva = c.getInt("codReserva");
            int idHabitacion = c.getInt("idHabitacion");
            String nombre = c.getString("nombre");
            int dia = c.getInt("dia");
            int numDias = c.getInt("numDias");

            // procesar los datos del reservsa
            // int codReserva, int dia, int numDias, int idHabitacion, String nombre
            reservas.add(new Reserva(codReserva, dia, numDias, idHabitacion, nombre));
        }

        return reservas;
    }

    private static Habitacion procesarHabitacion(String json) {
        JSONObject c = new JSONObject(json);

        int idHabitacion = c.getInt("idHabitacion");
        String nombre = c.getString("nombre");

        // procesar los datos del cliente
        return new Habitacion(idHabitacion, nombre);
    }

    private static Reserva procesarReserva(String json) {
        JSONObject c = new JSONObject(json);

        int codReserva = c.getInt("codReserva");
        String nombre = c.getString("nombre");
        int dia = c.getInt("dia");
        int numDias = c.getInt("numDias");
        int idHabitacion = c.getInt("idHabitacion");

        // procesar los datos del cliente
        // int codReserva, int dia, int numDias, int idHabitacion, String nombre
        return new Reserva(codReserva, dia, numDias, idHabitacion, nombre);
    }

}
