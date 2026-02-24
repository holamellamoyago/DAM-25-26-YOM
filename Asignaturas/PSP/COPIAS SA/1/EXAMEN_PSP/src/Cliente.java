
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Socket socket;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket("localhost", Config.PUERTO);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            System.out.println("Error al conectar con el servidor");
            return;
        }

        try {
            boolean servidorEncendido = input.readBoolean();
            if (!servidorEncendido)
                System.out.println(Config.STR_NO_CONEXION);

            String mensajeInicial = input.readUTF();
            System.out.println(mensajeInicial);

            solicitarNombre(input, output);

            solicitarPreguntas(output);

            contestarPreguntas(input, output);


        } catch (IOException ex) {
            System.out.println("Problemas con la conexión.");
        }
        sc.close();
        socket.close();
    }

    private static void solicitarNombre(DataInputStream input, DataOutputStream output) throws IOException {
        String nombre = "";

        while (nombre.isEmpty()) {
            nombre = sc.nextLine();

            if (nombre.isEmpty()) {
                System.out.println(Config.STR_NOMBRE_VACIO);
            }
        }
        output.writeUTF(nombre);
    }

    private static void contestarPreguntas(DataInputStream in, DataOutputStream out) throws IOException {

        boolean respuestaFallada = false;
        while (!respuestaFallada) {
            String preguntaRecibida = in.readUTF();

            if (preguntaRecibida.equals(Config.COD_NO_MAS_PREGUNAS)) {
                System.out.println("\nGanaste la partida.");
                System.out.println("Records: " + in.readUTF());
                return;
            }

            System.out.println("Dime la respuesta de: " + preguntaRecibida);

            String respuestaEscrita = sc.nextLine();
            out.writeUTF(respuestaEscrita);

            switch (in.readUTF()) {
                case Config.COD_RESPUESTA_ACERTADA:
                    System.out.println("Acertaste la pregutna, siguiente.");
                    break;
                case Config.COD_RESPUESTA_FALLADA:
                    System.out.println("Fallaste la pregunta...");
                    respuestaFallada = true;
                    System.out.println("RECORDS: " + in.readUTF());
                    break;
                default:
                    break;
            }
        }

    }

    private static void solicitarPreguntas(DataOutputStream out) throws IOException {

        String pregunta = "";
        String respuesta = "";

        while (pregunta.isEmpty()) {
            System.out.println("Escribe una pregunta (vacio para no más)");
            pregunta = sc.nextLine();

            if (pregunta.isEmpty()) {
                out.writeUTF(Config.CMD_SALIR);
                return;
            }

            System.out.println("Ahora la respuesta de la pregunta");
            respuesta = sc.nextLine();

            while (respuesta.isEmpty()) {
                System.out.println("No puedes escribir una respuesta vacía a una pregnta");
                respuesta = sc.nextLine();
            }

            out.writeUTF(pregunta);
            out.writeUTF(respuesta);

            pregunta = "";
        }

    }
}
