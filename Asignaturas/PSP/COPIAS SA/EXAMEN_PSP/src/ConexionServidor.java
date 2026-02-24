
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Clases.Pregunta;
import Clases.PrendaRopa;
import Clases.Usuario;

public class ConexionServidor extends Thread {

    private String codCliente;
    private Usuario usuario;

    private GestorAlmacenamiento gestorAlmacenamiento;
    private GestorConexion gestorConexiones;

    private DataInputStream input;
    private DataOutputStream output;

    private boolean servidorEncendido = true;

    public ConexionServidor(GestorConexion gestorConexiones, Socket socket) {
        this.gestorAlmacenamiento = GestorAlmacenamiento.getInstance();
        this.codCliente = socket.getRemoteSocketAddress().toString();
        servidorEncendido = true;
        // this.usuario = gestorAlmacenamiento.buscarUsuario(codCliente);

        // Esto te devolviería algo como /192.168.1.100:54321

        this.gestorConexiones = gestorConexiones;

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al establecer la nueva conexion");
        }

    }

    @Override
    public void run() {
        try {
            output.writeBoolean(servidorEncendido);
            output.writeUTF(Config.MENSAJE_INICIAL);

            codCliente = input.readUTF();
            System.out.println("Inicio " + codCliente);

            while (servidorEncendido) {
                // Leo cada pregunta y respuesta
                String entradaUsuario = input.readUTF();
                String respuestaPregunta = "";

                if (entradaUsuario.equals(Config.CMD_SALIR)) {
                    // Pasar a jugar
                    System.out.println("El cliente " + codCliente + " empieza a jugar");

                    boolean respuestaFallada = false;
                    int numAciertos = 0;
                    while (!respuestaFallada) {
                        for (Pregunta pre : gestorAlmacenamiento.getPreguntas()) {
                            if (pre.getDueno().equalsIgnoreCase(codCliente)) {
                                return;
                            }

                            output.writeUTF(pre.getPregunta());
                            String respuestaRecibida = input.readUTF();

                            if (respuestaRecibida.equals(pre.getRespuesta())) {
                                // output.writeBoolean(true);
                                output.writeUTF(Config.COD_RESPUESTA_ACERTADA);
                                numAciertos++;
                            } else {
                                gestorAlmacenamiento.registrarRecord(codCliente, numAciertos);
                                output.writeBoolean(false);
                                output.writeUTF(gestorAlmacenamiento.getRecords().toString());
                                respuestaFallada = true;
                            }
                        }
                    }

                } else {
                    respuestaPregunta = input.readUTF();
                    Pregunta pregunta = new Pregunta(codCliente, entradaUsuario, respuestaPregunta);
                    gestorAlmacenamiento.getPreguntas().add(pregunta);
                    System.out.println("Se guardo la " + pregunta);
                }

                // switch (entradaUsuario) {
                // case Config.CMD_INFO:
                // output.writeUTF(gestorConexiones.getInfo());

                // break;
                // case Config.CMD_SALIR:
                // output.writeUTF(gestorAlmacenamiento.getInfoUsuario(gestorAlmacenamiento.buscarUsuario(codCliente)));
                // servidorEncendido = false;
                // break;
                // case Config.CMD_APAGAR:
                // boolean sePuedeApagar = gestorConexiones.apagar();
                // output.writeBoolean(sePuedeApagar);

                // if (sePuedeApagar)
                // servidorEncendido = false;
                // break;

                // default:
                // Pattern pattern = Pattern.compile(Config.CMD_REGEXP,
                // Pattern.CASE_INSENSITIVE);
                // Matcher matcher = pattern.matcher(entradaUsuario);

                // if (matcher.matches()) {
                // String comando = matcher.group(1).toUpperCase();
                // String prendaRopa = matcher.group(2).toUpperCase();
                // int cantidad;

                // try {
                // cantidad = Integer.parseInt(matcher.group(3));
                // } catch (NumberFormatException x) {
                // output.writeUTF(Config.STR_ERROR_CANTIDAD);
                // break;
                // }

                // switch (comando) {
                // case Config.CMD_GET:
                // output.writeUTF(gestorAlmacenamiento.get(gestorAlmacenamiento.buscarUsuario(codCliente),
                // new PrendaRopa(prendaRopa), cantidad));
                // break;
                // case Config.CMD_PUT:
                // output.writeUTF(gestorAlmacenamiento.put(usuario, new PrendaRopa(prendaRopa),
                // cantidad));
                // break;
                // case Config.CMD_DELETE:
                // output.writeUTF(gestorAlmacenamiento.delete(usuario, new
                // PrendaRopa(prendaRopa)));
                // break;
                // default:
                // output.writeUTF(Config.STR_FORMATO_COMANDOS);
                // }
                // } else
                // output.writeUTF(Config.STR_FORMATO_COMANDOS);
                // }
            }
        } catch (IOException ex) {
            System.out.printf("Problemas en el servidor");
        }

        // gestorConexiones.logout(gestorAlmacenamiento.buscarUsuario(codCliente));
    }
}
