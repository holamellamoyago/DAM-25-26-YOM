
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Clases.Pregunta;
import Clases.Usuario;

public class GestorAlmacenamiento {
    private static GestorAlmacenamiento gestor;
    private ArrayList<Pregunta> preguntas = new ArrayList<>();
    private HashMap<String, Integer> records = new HashMap<>();

    private GestorAlmacenamiento() {
        // stock.put(new PrendaRopa("CAMISETA"), 0);
        // stock.put(new PrendaRopa("PANTALON"), 1);
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    

    public HashMap<String, Integer> getRecords() {
        return records;
    }

    public static synchronized GestorAlmacenamiento getInstance() {
        if (gestor == null)
            gestor = new GestorAlmacenamiento();

        return gestor;
    }

    public void registrarRecord(String usuario, int numAciertos) {
        if (records.containsKey(usuario)) {
            if (numAciertos > records.get(usuario)) {
                records.put(usuario, numAciertos);
            }
        } else {
            records.put(usuario, numAciertos);
        }

    }

    // public static HashMap<PrendaRopa, Integer> getStock() {
    // return stock;
    // }

    // public Set<Usuario> getUsuarios() {
    // return usuarios;
    // }

    // public boolean existeMaterial(PrendaRopa prenda) {
    // if (stock.containsKey(prenda)) {
    // System.out.println("El material existe");
    // if (stock.get(prenda) > 0) {
    // System.out.println("pero no hay stock");
    // }

    // return true;
    // }

    // return false;
    // }

    // public String get(Usuario usuario, PrendaRopa prenda, int cantidad) {
    // if (!existeMaterial(prenda))
    // return Config.STR_MATERIAL_DESCONOCIDO;

    // if (stock.get(prenda) < cantidad)
    // return Config.STR_SIN_STOCK;

    // return put(usuario, prenda, -cantidad);

    // }

    // public synchronized String put(Usuario usuario, PrendaRopa prenda, int
    // cantidad) {
    // if (stock.containsKey(prenda)) {
    // stock.put(prenda, stock.get(prenda) + cantidad);
    // System.out.println("Cantidad de " + prenda + " actualziada A : " +
    // stock.get(prenda));
    // } else {
    // System.out.println("La prenda no existia, se añade con tu stock");
    // stock.put(prenda, cantidad);
    // }

    // insertaLog(usuario, String.format("%s %s %d", (cantidad < 0) ? "GET" : "PUT",
    // prenda, cantidad));

    // return prenda.toString();
    // }

    // public static synchronized void insertaLog(Usuario usuario, String linea) {
    // for (Usuario u : usuarios) {
    // if (u.equals(usuario)) {
    // u.getStrBuilder().append(linea).append("\n");
    // return;
    // }
    // }
    // }

    // public Usuario buscarUsuario(String codUsuario) {
    // for (Usuario u : usuarios) {
    // if (u.getUsuario().equals(codUsuario)) {
    // return u;
    // }
    // }

    // throw new RuntimeException("No se encontro el usuairo");
    // }

    // public String getInfoUsuario(Usuario usuario) {
    // return String.format("%s %s", usuario.getUsuario(),
    // usuario.getStrBuilder().toString());
    // }

    // public String delete(Usuario usuario, PrendaRopa prendaRopa) {
    // if (!existeMaterial(prendaRopa))
    // return Config.STR_MATERIAL_DESCONOCIDO;

    // stock.remove(prendaRopa);
    // return "Material eliminado";

    // }

}
