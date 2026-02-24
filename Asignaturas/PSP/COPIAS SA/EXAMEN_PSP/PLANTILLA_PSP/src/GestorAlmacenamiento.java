
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

import Clases.PrendaRopa;
import Clases.Usuario;

public class GestorAlmacenamiento {
    private static GestorAlmacenamiento gestor;
    private static HashMap<PrendaRopa, Integer> stock = new HashMap<>();
    private static HashSet<Usuario> usuarios = new HashSet<>();

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
        if (stock.containsKey(prenda)) {
            System.out.println("El material existe");
            if (stock.get(prenda) > 0) {
                System.out.println("pero no hay stock");
            }

            return true;
        }

        return false;
    }

    public int get(Usuario usuario, PrendaRopa prenda, int cantidad) {
        if (!existeMaterial(prenda))
            return Config.COD_NO_ENCONTRADO;

        if (stock.get(prenda) < cantidad)
            return Config.COD_NO_STOCK;

        return put(usuario, prenda, -cantidad);

    }

    public synchronized int put(Usuario usuario, PrendaRopa prenda, int cantidad) {
        if (stock.containsKey(prenda)) {
            stock.put(prenda, stock.get(prenda) + cantidad);
            System.out.println("Cantidad de " + prenda + " actualziada A : " + stock.get(prenda));
        } else {
            System.out.println("La prenda no existia, se añade con tu stock");
            stock.put(prenda, cantidad);
        }

        insertaLog(usuario, String.format("%s %s %d", (cantidad < 0) ? "GET" : "PUT", prenda, cantidad));

        return Config.COD_TODO_CORRECTO;
    }

    public static synchronized void insertaLog(Usuario usuario, String linea) {
        for (Usuario u : usuarios) {
            if (u.equals(usuario)) {
                u.getStrBuilder().append(linea).append("\n");
                return;
            }
        }
    }

    public Usuario buscarUsuario(String codUsuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(codUsuario)) {
                return u;
            }
        }

        throw new RuntimeException("No se encontro el usuairo");
    }

    public String getInfoUsuario(Usuario usuario) {
        return String.format("%s %s", usuario.getUsuario(), usuario.getStrBuilder().toString());
    }

    public String delete(Usuario usuario, PrendaRopa prendaRopa) {
        if (!existeMaterial(prendaRopa))
            return Config.STR_MATERIAL_DESCONOCIDO;

        stock.remove(prendaRopa);
        return "Material eliminado";

    }

    public static String stockToJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"stock\": [\n");

        int i = 0;
        int total = stock.size();

        for (Map.Entry<PrendaRopa, Integer> entry : stock.entrySet()) {
            sb.append("    {\n");
            sb.append("      \"prenda\": \"").append(entry.getKey()).append("\",\n");
            sb.append("      \"cantidad\": ").append(entry.getValue()).append("\n");
            sb.append("    }");

            if (i < total - 1) {
                sb.append(",");
            }

            sb.append("\n");
            i++;
        }

        sb.append("  ]\n");
        sb.append("}");

        return sb.toString();
    }

    public static String buildResponse(int codigoEstado, String mensaje) {
        StringBuilder sb = new StringBuilder();

        // HEADER
        sb.append("== HEADER ==\n");
        sb.append("Codigo: ").append(codigoEstado).append("\n");
        sb.append("Mensaje: ").append(mensaje).append("\n");
        sb.append("Fecha: ").append(java.time.LocalDateTime.now()).append("\n");
        sb.append("============\n\n");

        // BODY
        sb.append("== BODY ==\n");
        sb.append("{\n");
        sb.append("  \"codigo\": ").append(codigoEstado).append(",\n");
        sb.append("  \"mensaje\": \"").append(mensaje).append("\",\n");
        sb.append("  \"stock\": [\n");

        int i = 0;
        int total = stock.size();

        for (Map.Entry<PrendaRopa, Integer> entry : stock.entrySet()) {
            sb.append("    {\n");
            sb.append("      \"prenda\": \"").append(entry.getKey()).append("\",\n");
            sb.append("      \"cantidad\": ").append(entry.getValue()).append("\n");
            sb.append("    }");

            if (i < total - 1) {
                sb.append(",");
            }

            sb.append("\n");
            i++;
        }

        sb.append("  ]\n");
        sb.append("}\n");
        sb.append("==========");

        return sb.toString();
    }


}
