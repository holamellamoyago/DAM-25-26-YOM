
package aluhbpasteleria26;

import java.util.List;

import LOGICA.GestionHBPasteleria;
import POJOS.Pasteleria;
import POJOS.Pastelero;
import POJOS.Producto;

public class ALUHBPASTELERIA26Ejercicio3 {

    public static void main(String[] args) {
        GestionHBPasteleria.comprobarConexion();

        System.out.println("\nEjercicio 3");
        System.out.println("________PRODUCTOS_______");

        List<Producto> productos =  GestionHBPasteleria.obtenerProductos();
        // System.out.println(productos);

        Pasteleria pasteleria = GestionHBPasteleria.obtenerPasteleria(1);
        // System.out.println("\n" + pasteleria.getProductos());

        System.out.println("______________ELIMINAR PRODUCTOS_________");
        System.out.println(productos);
        GestionHBPasteleria.eliminarProducto(1);
        System.out.println(productos);





    }

}
