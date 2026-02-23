
package LOGICA;

import java.util.List;

import PERSISTENCIA.HBPasteleriaDAO;
import POJOS.Pasteleria;
import POJOS.Pastelero;
import POJOS.Producto;

public class GestionHBPasteleria {

    public static void comprobarConexion() {
        int resultado = HBPasteleriaDAO.conectarHibernateDAO();

        if (resultado == 0) {
            System.out.println("Conexi�n correcta");

        } else {
            System.out.println("Error de conexi�n ");

        }
    }

    public static List<Pastelero> obtenerPasteleros() {
        List<Pastelero> pasteleros = HBPasteleriaDAO.obtenerPasteleros();
        return pasteleros;
    }

    public static void actualizarAnadirHabilidad(String codPastelero, String nombreHabilidad, String nivelAsociado) {
        Pastelero p = HBPasteleriaDAO.obtenerPastelero(codPastelero);

        if (p == null) {
            System.out.println("El pastelero no existe");
            return;
        }

        if (p.getTecnicas().containsKey(nombreHabilidad)) {
            if (p.getTecnicas().get(nombreHabilidad).equals(nivelAsociado)) {
                System.out.println("El pastelero ya tiene esa habilidady el nivel es el mismo");
                return;
            } else {
                System.out
                        .println("El pastelero ya tiene esa habilidad pero se actualzia el novel a: " + nivelAsociado);
                HBPasteleriaDAO.actualizarAnadirNivelHabilidad(p, nombreHabilidad, nivelAsociado);
            }
        } else {
            System.out.println("Esa habilidad no la tenía, se le añade al pastelero");
            HBPasteleriaDAO.actualizarAnadirNivelHabilidad(p, nombreHabilidad, nivelAsociado);
        }

    }

    public static Pastelero obtenerPastelero(String codPastelero) {
        return HBPasteleriaDAO.obtenerPastelero(codPastelero);
    }

    public static Pasteleria obtenerPasteleria(int codigo) {
        Pasteleria pasteleria = HBPasteleriaDAO.obtenerPasteleria(codigo);

        if (pasteleria == null) {
            System.out.println("No existe la pasteleria");
            return null;
        }

        return pasteleria;
    }

    public static void actualizarDuenoPasteleria(String aliasPasteleroNuevo, String nomePasteleria) {
        Pasteleria pasteleria = HBPasteleriaDAO.obtenerPasteleria(nomePasteleria);
        Pastelero pasteleroAnterior = HBPasteleriaDAO.obtenerPastelero(pasteleria.getPastelero().getCodigo());
        Pastelero pasteleroNuevo = HBPasteleriaDAO.obtenerPasteleroPorAlias(aliasPasteleroNuevo);

        System.out.println("\nDUEÑO ANTERIOR, (Pasteleria: " + pasteleria.getNome() + ") : "
                + pasteleria.getPastelero().getCodigo());

        if (pasteleria == null) {
            System.out.println("ERROR: Las pasteleria " + nomePasteleria + " no existe en la base de datos");
            return;
        }

        if (pasteleroNuevo == null) {
            System.out.println("ERROR: El nuevo alias del pastelero " + aliasPasteleroNuevo + " no existe.");
            return;
        }

        if (pasteleria.getPastelero().getAlias().equals(aliasPasteleroNuevo)) {
            System.out.println(
                    "\nAVISO: El pastelero " + pasteleroNuevo.getNome() + " ya es el propietario. no se hará nada");
            return;
        }

        HBPasteleriaDAO.actualizarDuenoPasteleria(pasteleria, pasteleroAnterior, pasteleroNuevo);

        pasteleria = obtenerPasteleria(pasteleria.getCodigo());

        System.out.println("\nDUEÑO NUEVO, (Pasteleria: " + pasteleria.getNome() + ") : "
                + pasteleria.getPastelero().getCodigo());
    }

    public static List<Producto> obtenerProductos() {
        return HBPasteleriaDAO.obtenerProductos();
    }

    public static void eliminarProducto(int codigo) {
        Producto pro =  HBPasteleriaDAO.obtenerProdcto(codigo);

        if (pro == null) {
            System.out.println("El producto no existe");
            return;
        }

        HBPasteleriaDAO.eliminarProducto(pro);
        System.err.println("PRODUCTO ELIMINADO");
        
    }

    public static List<Pastelero> obtenerPastelerosVarones() {
        return HBPasteleriaDAO.obtenerPastelerosVarones();
    }

}