package PSP.Tema2.TiendaRopa;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

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

                String[] mensajes = mensaje.split(" ");

                switch (mensajes[0].toUpperCase()) {
                    case "STOCK":
                        out.writeUTF(transformarJSON());
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

    private String transformarJSON() {
        JSONArray jsonArray = new JSONArray(Inventario.productos.length);

        for (Producto pro : Inventario.productos) {
            JSONObject obj = new JSONObject(pro);

            obj.put("titulo", pro.getTitulo());
            obj.put("subtitulo", pro.getSubtitulo());
            obj.put("rutaImagen", pro.getRutaImagen());
            obj.put("precio", pro.getPrecio());

            jsonArray.put(obj);
        }

        return jsonArray.toString();
    }

}