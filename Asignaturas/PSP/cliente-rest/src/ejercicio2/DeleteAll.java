package ejercicio2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteAll {
    public static void main(String[] args) {
        String uri = "http://localhost/clientes/index.php/clientes";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 200:
                    System.out.println("Todos los clientes han sido eliminados");
                    System.out.println(response.body());
                    break;
                case 204:
                    System.out.println("Todos los clientes han sido eliminados");
                    break;
                case 404:
                    System.out.println("No se encontraron clientes");
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
