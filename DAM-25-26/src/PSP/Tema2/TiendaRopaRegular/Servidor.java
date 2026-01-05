package PSP.Tema2.TiendaRopaRegular;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static boolean salir = false;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(Config.NUM_PUERTO);
        } catch (IOException e) {
            System.out.println("Error al levantar el servidor");
            e.printStackTrace();
        }

        while (!salir) {
            try {
                Socket socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Problema al aceptar la conexión");
                e.printStackTrace();
            }
        }

    }
}
