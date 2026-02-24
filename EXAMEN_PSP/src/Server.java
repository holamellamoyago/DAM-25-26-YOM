
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread {
    private ServerSocket serverSocket;
    private GestorConexion gestorConexion;
    private boolean servidorEncendido = true;

    public static void main(String[] args) {
        new Server().start();
    }

    @Override
    public void run() {
        iniciarServidor();

        while (servidorEncendido) {
            try {
                Socket socket = serverSocket.accept();
                new ConexionServidor(gestorConexion, socket).start();

            } catch (IOException ex) {
                System.out.println("Servidor abajo");
                return;
            }
        }

    }

    private void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(Config.PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Servidor arriba, esperando conexiones");
        gestorConexion = new GestorConexion(serverSocket);

    }

}
