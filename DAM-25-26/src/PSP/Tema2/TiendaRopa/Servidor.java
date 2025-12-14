package PSP.Tema2.TiendaRopa;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {
    private static ServerSocket serverSocket; 
    private static boolean salir = false;

    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(Config.NUM_PUERTO);
        } catch (IOException e) {
            System.out.println("Problemas al levantar el servidor");
            e.printStackTrace();
        }
        
        System.out.println("Servidor levantado");

        while (!salir) {
            try {
                new ConexionSevidorTeinda(serverSocket.accept()).start();
            } catch (IOException e) {
                System.out.println("Problemas al crear la conexion con un socket");
                e.printStackTrace();
            };
        }

        

    }
}
