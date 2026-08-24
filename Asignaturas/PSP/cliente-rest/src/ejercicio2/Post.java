package ejercicio2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class Post {
    public static void main(String[] args) {
        String uri = "http://localhost/clientes/index.php/clientes";
        HttpClient client = HttpClient.newHttpClient();
        JSONObject clienteJson = new JSONObject();

        clienteJson.put("nombre", "Yago2");
        clienteJson.put("codProvincia", 2);
        clienteJson.put("apellidos", "Otero");
        clienteJson.put("NIF", "39511342X");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
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
            System.out.println("Problemas");
        }
    }
}
