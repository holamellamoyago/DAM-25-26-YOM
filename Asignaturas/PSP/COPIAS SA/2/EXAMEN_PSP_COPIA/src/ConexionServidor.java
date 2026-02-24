
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Clases.Pregunta;

public class ConexionServidor extends Thread {

    private String codCliente;

    private GestorAlmacenamiento gestorAlmacenamiento;

    private DataInputStream input;
    private DataOutputStream output;

    private boolean servidorEncendido = true;

    public ConexionServidor(GestorConexion gestorConexiones, Socket socket) {
        this.gestorAlmacenamiento = GestorAlmacenamiento.getInstance();
        this.codCliente = socket.getRemoteSocketAddress().toString();
        servidorEncendido = true;



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
                                output.writeUTF(Config.COD_RESPUESTA_ACERTADA);
                                numAciertos++;
                            } else {
                                gestorAlmacenamiento.registrarRecord(codCliente, numAciertos);
                                output.writeUTF(Config.COD_RESPUESTA_FALLADA);
                                output.writeUTF(gestorAlmacenamiento.getRecords().toString());
                                respuestaFallada = true;
                            }
                        }

                        output.writeUTF(Config.COD_NO_MAS_PREGUNAS);
                        gestorAlmacenamiento.registrarRecord(codCliente, numAciertos);
                        output.writeUTF(gestorAlmacenamiento.getRecords().toString());
                        respuestaFallada = true;
                        servidorEncendido = false;
                    }

                } else {
                    respuestaPregunta = input.readUTF();
                    Pregunta pregunta = new Pregunta(codCliente, entradaUsuario, respuestaPregunta);
                    gestorAlmacenamiento.getPreguntas().add(pregunta);
                    System.out.println("Se guardo la " + pregunta);
                }
            }
        } catch (IOException ex) {
            System.out.printf("Problemas en el servidor");
        }
    }
}
