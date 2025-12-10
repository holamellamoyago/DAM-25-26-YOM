package PSP.Tema2.ComprobarEstadoUsuarios;

import java.net.Socket;

public class ConexionServidor extends Thread {
    private GestorConexiones gestorConexiones;
    private Socket socket;

    public ConexionServidor(GestorConexiones gestorConexiones, Socket socket) {
        this.gestorConexiones = gestorConexiones;
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
    }
    
}
