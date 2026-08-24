package PSP.Tema2.ComprobarEstadoUsuarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import PSP.Tema2.ComprobarEstadoUsuarios.Config.AutorizarConexion;

public class ConexionServidor extends Thread {
    private GestorConexiones gestorConexiones;
    private Socket socket;

    private DataOutputStream out;
    private DataInputStream in;

    public ConexionServidor(GestorConexiones gestorConexiones, Socket socket) {
        this.gestorConexiones = gestorConexiones;
        this.socket = socket;

        conectarConServidor(socket);
    }

    @Override
    public void run() {
        String nombreUsuario = "";
        try {
            nombreUsuario = in.readUTF();
            String password = in.readUTF();
            AutorizarConexion autorizacion = gestorConexiones.autorizarNuevaConextion(nombreUsuario, password);

            boolean loginOK = (autorizacion == AutorizarConexion.OK);
            out.writeBoolean(loginOK);

            if (loginOK) {
                System.out.println("Conectado " + nombreUsuario);
                boolean salir = false;

                do {
                    String comando = in.readUTF();
                    switch (comando) {
                        case Config.CMD_APAGAR: {
                            gestorConexiones.apagar();
                            salir = true;
                        }
                            break;

                        case Config.CMD_SALIR: {
                            gestorConexiones.cortarConextion(nombreUsuario, this);
                            salir = true;
                        }
                            break;

                        default:
                            out.writeUTF(gestorConexiones.getInfoUsuario(nombreUsuario));
                            break;
                    }
                } while (!salir);

                cerrarCliente();

            } else {
                out.writeInt(autorizacion.ordinal());
            }

        } catch (Exception e) {
            System.out.printf("Conexión con %s finalizada por el servidor\n", nombreUsuario);
        }
    }

    public void cerrarCliente() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Problemas cerrndo cliente");
        }
    }

    private void conectarConServidor(Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("No se pudo conectar con el servidor");
        }
    }

}
