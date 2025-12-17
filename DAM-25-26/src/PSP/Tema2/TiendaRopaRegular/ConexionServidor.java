package PSP.Tema2.TiendaRopaRegular;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

public class ConexionServidor extends Thread {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ConexionServidor(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("Servidor inicia conexion con " + socket);

        Pattern pattern = Pattern.cp,


        boolean salir = false;
        while (!salir) {
            
        }
    }
}
