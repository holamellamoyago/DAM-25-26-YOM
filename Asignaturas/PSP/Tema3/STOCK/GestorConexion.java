package PSP.Tema3.STOCK;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

import PSP.Tema3.STOCK.Clases.Usuario;

public class GestorConexion {

    private GestorAlmacenamiento gestorAlmacenamiento;
    private ServerSocket serverSocket;

    private int numConexiones;
    private static final int MAX_CONEXIONES = 3;

    public GestorConexion(ServerSocket serverSocket) {
        this.gestorAlmacenamiento  = GestorAlmacenamiento.getInstance();
        this.serverSocket = serverSocket;
        this.numConexiones = 0;
    }

    public boolean apagar() {
        if (numConexiones > 1)
            return false;

        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Servidor abajo");
        }
        return true;
    }

    // private synchronized void insertaLog(String usuario, String linea) {
    //     usuarios.get(usuario).append(linea).append("\n");
    // }

    public synchronized boolean login(String usuario) {
        String strLog = "Login";

        boolean loginPermitido = ++numConexiones < MAX_CONEXIONES;

        // Si el servidor le quedan conexiones devuelve true
        if (!loginPermitido) {
            strLog += " error." + Config.STR_CONEXIONES_ACTIVAS;
            numConexiones--;
            return false;
        }

        gestorAlmacenamiento.getUsuarios().add(new Usuario(usuario));

        return loginPermitido;
    }

    public synchronized void logout(PSP.Tema3.STOCK.Clases.Usuario usuario) {
        insertaLog(usuario, "Logout");
        numConexiones--;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        for (HashMap.Entry<String, StringBuilder> usuario : usuarios.entrySet())
            sb.append(usuario.getKey() + ":\n" + usuario.getValue().toString());
        return sb.toString();
    }


}
