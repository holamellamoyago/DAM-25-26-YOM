package PSP.Tema2.ComprobarEstadoUsuarios;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import PSP.Tema2.ComprobarEstadoUsuarios.Config.AutorizarConexion;

public class GestorConexiones {
    private ServerSocket serverSocket;
    private HashMap<String, Usuario> datos;
    private ArrayList<ConexionServidor> listaConexiones;

    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.datos = new HashMap<>();
        this.listaConexiones = new ArrayList<>();
    }

    public synchronized void nuevaConexionSinAutorizar(ConexionServidor nuevoHilo) {
        listaConexiones.add(nuevoHilo);
    }

    public AutorizarConexion autorizarNuevaConextion(String nombreUsuario, String password) {
        if (!credencialesOK(nombreUsuario, password))
            return AutorizarConexion.CredencialesIncorrectas;

        Usuario usuario = datos.get(nombreUsuario);

        if (usuario == null)
            datos.put(nombreUsuario, usuario = new Usuario(nombreUsuario));
        else {
            if (usuario.estaOnline())
                return AutorizarConexion.UsuarioYaConectado;
        }
        usuario.login();

        // Porque esto está abajo y no con el if de arriba?
        return AutorizarConexion.OK;
    }

    public void apagar() {
        try {
            Servidor.salir = true;
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Hubo un problema al cerrar el serverSocket para no permitir mas accept");
        }

        // Para que las demás conexiones no se cortarán solo deberíamos de comentar este bucle
        for (ConexionServidor conexion : listaConexiones) {
            conexion.cerrarCliente();
        }
    }

    public void cortarConextion(String nombreUsuario, ConexionServidor conexionServidor) {
        setUsuarioOffline(nombreUsuario);
        conexionServidor.cerrarCliente();
        listaConexiones.remove(conexionServidor);

    }

    private void setUsuarioOffline(String nombreUsuario) {
        Usuario usuario = datos.get(nombreUsuario);
        if (usuario != null) {
            usuario.logOut();
        }
    }

    public String getInfoUsuario(String nombreUsuario) {
        Usuario usuario = datos.get(nombreUsuario);
        if (usuario != null)
            return usuario.toString();

        return "Usuario no encontrado";
    }

    private boolean credencialesOK(String usuario, String password) {
        return password.equalsIgnoreCase(usuario + usuario.length());
    }

}
