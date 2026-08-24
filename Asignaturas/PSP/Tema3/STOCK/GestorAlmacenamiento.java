package PSP.Tema3.STOCK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import PSP.Tema2.ComprobarEstadoUsuarios.Usuario;
import PSP.Tema3.STOCK.Clases.Tallas;

public class GestorAlmacenamiento {
    private static GestorAlmacenamiento gestor;
    private static HashMap<PrendaRopa, Integer> stock = new HashMap<>();
    private Set<Usuario> usuarios = new HashSet<>();

    private GestorAlmacenamiento() {
        stock.put(new PrendaRopa("CAMISETA"), 0);
        stock.put(new PrendaRopa("PANTALON"), 1);
    }

    public static GestorAlmacenamiento getInstance() {
        if (gestor == null)
            gestor = new GestorAlmacenamiento();

        return gestor;
    }

    public static HashMap<PrendaRopa, Integer> getStock() {
        return stock;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean existeMaterial(PrendaRopa prenda) {
        if (stock.containsKey(prenda) && stock.get(prenda) > 0)
            return true;

        return false;
    }

    public String get(String usuario, PrendaRopa prenda, int cantidad) {

        if (existeMaterial(prenda))
            return Config.STR_MATERIAL_DESCONOCIDO;

        if (stock.get(prenda) < cantidad)
            return Config.STR_SIN_STOCK;

        return "a";
        // return put(usuario, prendaRopa, -cantidad);
    }

    public synchronized String put(String usuario, PrendaRopa prenda, int cantidad) {

        if (!existeMaterial(prenda)) {
            stock.put(prenda, 0);
        } else {
            System.out.println("Cantidad de " + prenda + " actualziada");
            stock.put(prenda, stock.get(prenda) + cantidad);
        }

        insertaLog(usuario, String.format("%s %s %d", (cantidad < 0) ? "GET" : "PUT", prenda, cantidad));
        return getInfoMaterial(prenda);
    }

    private synchronized void insertaLog(String usuario, String linea) {
        usuarios.get(usuario).append(linea).append("\n");
    }

}
