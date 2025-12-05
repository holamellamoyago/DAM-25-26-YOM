package PSP.Tema2.Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class ServidorMulti extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;

    public ServidorMulti(Socket socket) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                serverSocket = new ServerSocket(Config.NUM_PUERTO);
                Socket socket = serverSocket.accept();
                new ServidorMulti(socket);
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    public void apagarServidor(){
        se
    }
}
