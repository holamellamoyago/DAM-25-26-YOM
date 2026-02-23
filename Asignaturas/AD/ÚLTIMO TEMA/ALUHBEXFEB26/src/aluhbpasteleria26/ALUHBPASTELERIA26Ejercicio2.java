
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

            System.out.println("\n________ACTUALIZAR / ANADIR HABILIDAD _______");

        // Crear habilidad
        System.out.println("\nTecnicas anteriores: ");
        System.out.println(p.getTecnicas());

        GestionHBPasteleria.actualizarAnadirHabilidad("P001", "Pastelerisimo", "Medio");

        System.out.println("\nTencias después de actualziar / anadir ");
        p = GestionHBPasteleria.obtenerPastelero("P001");
        System.out.println(p.getTecnicas());

        // Aqui busco las pastelerias de un pastelero (para comprobar que esten las
        // asociaciones bien hechas)
        System.out.println("\n________PASTELERIAS DE UN DUEÑO_______");
        System.out.println(p.getPastelerias());

        // BBusco el dueño (pastelero) de una pasteleria
        System.out.println("\n________DUEÑO DE  UNA PASTELERIAS_______");
        System.out.println(pasteleria.getPastelero());

        // Método para cambiar el dueño de una pasteleria
        GestionHBPasteleria.actualizarDuenoPasteleria("Felipón", pasteleria.getNome());


    }

}
