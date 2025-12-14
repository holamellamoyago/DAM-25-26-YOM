package PSP.Tema2.TiendaRopa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionSevidorTeinda extends Thread {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ConexionSevidorTeinda(Socket socket) {
        this.socket = socket;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean salir = false;
        String mensaje = "";

        while (!salir) {
            try {
                mensaje = in.readUTF();

                String[] mensajes =  mensaje.split(" ");

                switch (mensajes[0].toUpperCase()) {
                    case "COMPRAR":
                        
                        break;
                
                    default:
                        break;
                }
                System.out.println(mensaje);


            } catch (IOException e) {
                System.out.println("Problemas leyendo el mensaje");
                e.printStackTrace();
            }
        }
    }



}