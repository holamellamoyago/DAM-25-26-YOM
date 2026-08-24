package PSP.Tema2.ComprobarEstadoUsuarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import PSP.Tema2.ComprobarEstadoUsuarios.Config.AutorizarConexion;

public class Cliente {
    // private static String nombre;
    // private static String

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Socket socket = null;

        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket("localhost", Config.PUERTO);

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            System.out.println("Error al conectar con el servidor");
        }

        try {
            inicioSesion(sc, output, input);

            cerrarSocket(socket);
        } catch (Exception e) {
            System.out.println("Se cerro la conexión");
        }

    }

    private static void inicioSesion(Scanner sc, DataOutputStream out, DataInputStream in) throws IOException {
        System.out.println("Introduce el nombre: ");
        String nombre = sc.nextLine();
        out.writeUTF(nombre);

        System.out.println("Ahora la contraseña: ");
        String contrasena = sc.nextLine();
        out.writeUTF(contrasena);

        boolean loguinOK = in.readBoolean();

        if (loguinOK) {
            boolean salir = false;

            do {
                System.out.printf("[%s]> ", nombre);

                String comando = sc.nextLine();
                out.writeUTF(comando);

                switch (comando) {
                    case Config.CMD_APAGAR:
                    case Config.CMD_SALIR:
                        salir = true;
                        break;

                    default:
                        System.out.println(in.readUTF());
                        break;
                }

            } while (!salir);
        } else {
            // TODO El cliente se queda aquí esperando a recibir una respuesta
            int codRespuesta = in.readInt();
            String error = "Error no especificado";

            switch (AutorizarConexion.values()[codRespuesta]) {
                case CredencialesIncorrectas:
                    error = "Credenciales incorrectas";
                    break;
                case UsuarioYaConectado:
                    error = "Estás conectado desde otro ordenador";
                    break;

                default:
                    break;
            }

            System.out.println(error);
        }

    }

    private static void cerrarSocket(Socket socket) {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión");
        }
    }
}
