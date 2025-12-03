package PSP.Tema2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class GestorSocket {
    public static void main(String[] args) throws IOException {
        final int puerto = 7; // puerto ECHO
        final String FIN = "fin";

        final ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor arriba");

        Socket socket;
        socket = serverSocket.accept(); // Esperamos por un cliente

        SocketAddress clientAddress = socket.getRemoteSocketAddress();
        System.out.println("Ha conectado " + clientAddress);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        boolean salir = false;
        while (!salir) {
            String str = in.readUTF();
            out.writeUTF(str);
            if (str.equalsIgnoreCase(FIN))
                salir = true;
            else {
                System.out.println("Servidor retransmite: " + str);
                System.out.println("****************************");
            }
        }

        socket.close();
        serverSocket.close();
        System.out.println("Servidor abajo");
    }
}