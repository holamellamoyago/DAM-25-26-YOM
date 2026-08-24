    package ejercicio2;

    import java.net.URI;
    import java.net.http.HttpClient;
    import java.net.http.HttpRequest;
    import java.net.http.HttpResponse;

    public class DeleteOne {
        public static void main(String[] args) {
            // Atención aquí !!!!
            String uri = "http://localhost/clientes/index.php/cliente/";
            int codCliente = 3; // ID del cliente a eliminar

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
                        System.out.println(response.body());
                }
            } catch (Exception e) {
                System.out.println("Problemas");
            }
        }
    }
