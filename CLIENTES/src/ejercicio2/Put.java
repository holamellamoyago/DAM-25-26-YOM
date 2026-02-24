package ejercicio2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class Put {
    public static void main(String[] args) {
        String uri = "http://localhost/clientes/index.php/clientes/";
        int codCliente = 1; // ID del cliente a reemplazar

        HttpClient client = HttpClient.newHttpClient();
        JSONObject clienteJson = new JSONObject();

        // PUT reemplaza TODOS los campos del cliente
        clienteJson.put("nombre", "Carlos");
        clienteJson.put("apellidos", "García");
        clienteJson.put("NIF", "12345678A");
        clienteJson.put("codProvincia", 3);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + codCliente))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(clienteJson.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 200:
                    System.out.println("Cliente " + codCliente + " actualizado correctamente");
                    System.out.println(response.body());
                    break;
                case 404:
                    System.out.println("Cliente " + codCliente + " no encontrado");
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
}
