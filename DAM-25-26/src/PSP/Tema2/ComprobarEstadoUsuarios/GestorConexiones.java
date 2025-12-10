package PSP.Tema2.ComprobarEstadoUsuarios;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorConexiones {
    private ServerSocket serverSocket;
    private ArrayList<Usuario> datos;
    private ArrayList<ConexionServidor> listaConexiones;

    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.datos = new ArrayList<>();
        this.listaConexiones = new ArrayList<>();
    }

    
}
