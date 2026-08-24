package PSP.Tema2.TiendaRopa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GestorConexionesTienda {
    private ArrayList<String> tiendas;
    private Map<String, Integer> stock;

    private ArrayList<ConexionSevidorTeinda> conexiones;

    public GestorConexionesTienda() {
        tiendas = new ArrayList<>();
        stock = new HashMap<>();
        conexiones = new ArrayList<>();
    }


    public synchronized void aceptarConexion(ConexionSevidorTeinda conexion) {
        
    }

    
    
}
