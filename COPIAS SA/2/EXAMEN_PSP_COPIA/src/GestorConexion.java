
import java.io.IOException;
import java.net.ServerSocket;


public class GestorConexion {

    private GestorAlmacenamiento gestorAlmacenamiento;
    private ServerSocket serverSocket;

    public GestorConexion(ServerSocket serverSocket) {
        this.gestorAlmacenamiento = GestorAlmacenamiento.getInstance();
        this.serverSocket = serverSocket;
    }

    public boolean apagar() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Servidor abajo");
        }
        return true;
    }

}
