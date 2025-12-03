package PSP.Tema2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMulti {
    static boolean encendido = true;
    static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        System.out.println("Servidor arriba");
        final int puerto = 7; // puerto ECHO

        serverSocket = new ServerSocket(puerto);
        Socket socket;
        try {
            while (true) {
                socket = serverSocket.accept(); // Esperamos por un cliente
                SocketHilo servidor = new SocketHilo(socket);
                servidor.start();
            }
        } catch (Exception e) {
            throw new ArithmeticException("SERVIDOR APAGADO");
        }

    }

    public static void apagarServidor() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new ArithmeticException("Se apago el servidor");
        }

    }
}