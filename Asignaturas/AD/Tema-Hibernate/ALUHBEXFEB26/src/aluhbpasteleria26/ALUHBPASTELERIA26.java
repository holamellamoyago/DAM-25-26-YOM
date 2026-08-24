
package aluhbpasteleria26;

import LOGICA.GestionHBPasteleria;
import POJOS.Pasteleria;
import POJOS.Pastelero;

public class ALUHBPASTELERIA26 {

    public static void main(String[] args) {
        GestionHBPasteleria.comprobarConexion();

        System.out.println("\nEjercicio 1");
        System.out.println("________PASTELEROS_______");

        System.out.println(GestionHBPasteleria.obtenerPasteleros());
        Pastelero p = GestionHBPasteleria.obtenerPastelero("P001");
        Pasteleria pasteleria = GestionHBPasteleria.obtenerPasteleria(12);


        // GestionHBPasteleria.actualizarDuenoPasteleria("Julianico", pasteleria.getNome());



    }

}
