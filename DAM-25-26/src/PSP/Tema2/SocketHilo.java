package PSP.Tema2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketHilo extends Thread {
    final String FIN = "fin";
    private Socket socket;

    public SocketHilo(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            SocketAddress clientAddress = socket.getRemoteSocketAddress();
            System.out.println("Ha conectado " + clientAddress);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String str = in.readUTF();
                out.writeUTF(str);
                if (str.equalsIgnoreCase(FIN)) {
                    System.out.println("Recibi FIN ");
                    ServidorMulti.apagarServidor();
                } else {
                    System.out.println("Servidor retransmite: " + str);
                    System.out.println("****************************");
                }
            }

        } catch (IOException e) {
            throw new ArithmeticException("No se encuentra el servidor");
        }
    }
}
