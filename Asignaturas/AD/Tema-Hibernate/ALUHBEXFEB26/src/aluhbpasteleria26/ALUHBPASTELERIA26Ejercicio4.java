
package aluhbpasteleria26;

import java.util.List;

import LOGICA.GestionHBPasteleria;
import POJOS.Pasteleria;
import POJOS.Pastelero;
import POJOS.Producto;

public class ALUHBPASTELERIA26Ejercicio4 {

    public static void main(String[] args) {
        GestionHBPasteleria.comprobarConexion();

        List<Pastelero> pastelerosVarones = GestionHBPasteleria.obtenerPastelerosVarones();
        System.out.println("\n---INFORME DE PASTELEROS (HOMBRES) ---");
        String formato = "%-6s | %-27s | %-10s | %-25s | %-10s%n";

        for (Pastelero p : pastelerosVarones) {
            // System.out.printf(formato, p.getCodigo(), p.getNome() + " " + p.getApelidos()
            // + p.getCertificacion().getNumlicencia() + p.getContacto().getEmail(),
            // p.getTecnicas().size());
            String str = p.getCodigo() + " | " + p.getNome() + " " + p.getApelidos() + " | "
                    + p.getCertificacion().getNumlicencia() + " | " + p.getContacto().getEmail() + " | "
                    + String.valueOf(p.getTecnicas().size());

                    System.out.println(str);
            // System.out.println("\n" + p.getCodigo(), p.getNome() + " " + p.getApelidos()
            // + p.getCertificacion().getNumlicencia() + p.getContacto().getEmail(), );
        }

    }

}
