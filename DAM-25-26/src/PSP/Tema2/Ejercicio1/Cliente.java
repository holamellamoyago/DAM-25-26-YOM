package PSP.Tema2.Ejercicio1;

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
            socket = new Socket(Config.NOMBRE_SERVIDOR, Config.NUM_PUERTO);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            String mensaje = "";
            while (mensaje.equalsIgnoreCase(Config.FIN)) {
                mensaje = sc.nextLine();

                String strRecibido;

                // Enviamos el mensaje 
                out.writeUTF(mensaje);
                System.out.println("Cliente envia " + mensaje);

                // El servidor nos devuelve el mensaje
                strRecibido = in.readUTF();
                System.out.println("Cliente recibe " + strRecibido);

            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
