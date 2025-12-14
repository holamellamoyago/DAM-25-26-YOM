package PSP.Tema2.TiendaRopa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        try {
            socket = new Socket(Config.IP_SERVIDOR, Config.NUM_PUERTO);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            String mensaje = "";
            boolean salir = false;
            while (!salir) {
                System.out.println("Manda un mensaje: ");
                mensaje = sc.nextLine();

                out.writeUTF(mensaje);

                if (mensaje.equals(Config.CERRAR)) salir = true;
            }
        } catch (IOException e) {
            System.out.println("Problemas al conectar con el servidor");
            e.printStackTrace();
        }


    }
}