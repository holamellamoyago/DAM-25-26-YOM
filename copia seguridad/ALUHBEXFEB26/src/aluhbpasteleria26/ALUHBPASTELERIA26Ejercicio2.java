
package aluhbpasteleria26;

import LOGICA.GestionHBPasteleria;
import POJOS.Pasteleria;
import POJOS.Pastelero;

public class ALUHBPASTELERIA26Ejercicio2 {

    public static void main(String[] args) {
        GestionHBPasteleria.comprobarConexion();

        System.out.println("\nEjercicio 1");
        System.out.println("________PASTELEROS_______");

        Pastelero p = GestionHBPasteleria.obtenerPastelero("P003");
        Pasteleria pasteleria = GestionHBPasteleria.obtenerPasteleria(3);

        System.out.println(p.getPastelerias());


    }

}
