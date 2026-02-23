package PSP.Tema3.STOCK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket;
        DataInputStream input = null;
        DataOutputStream output = null;

        Scanner sc = new Scanner(System.in);

        try {
            socket = new Socket("localhost", Config.PUERTO);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            System.out.println("Error al conectar con el servidor");
            return;
        }

        try {
            // boolean salir= !input.readBoolean();
            boolean servidorEncendido = input.readBoolean();

            if (!servidorEncendido)
                System.out.println(Config.STR_NO_CONEXION);

            while (servidorEncendido) {
                System.out.printf("[%s]> ", socket.getLocalSocketAddress());
                String comando = sc.nextLine().toUpperCase().trim();

                output.writeUTF(comando);
                switch (comando) {
                    case Config.CMD_SALIR:
                        System.out.println(input.readUTF());
                        servidorEncendido = false;
                        break;
                    case Config.CMD_APAGAR:
                    
                        // Si el servidor te duelve false es que se apagó
                        if (!input.readBoolean()) {
                            servidorEncendido = false;
                        }
                        else
                            System.out.println(Config.STR_CONEXIONES_ACTIVAS);
                        break;
                    default:
                        System.out.println(input.readUTF());
                }
            }
        } catch (IOException ex) {
            System.out.println("Problemas con la conexión.");
        }
        sc.close();
        socket.close();
    }
}
