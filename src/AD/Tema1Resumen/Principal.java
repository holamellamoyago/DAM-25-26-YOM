package AD.Tema1Resumen;

import java.time.LocalDate;

import AD.Tema1Resumen.Clases.TipoValidacion;
import AD.Tema1Resumen.Clases.Velocista;
import AD.Tema1Resumen.DOM.Gestores.GestorCorredorDOM;
import AD.Tema1Resumen.STAX.GestorCorredoresSTAX;

public class Principal {
    public static void main(String[] args) {
        final String rutaArchivo = "Archivos/Corredores.xml";

        // DOM
        GestorCorredorDOM gestorCorredoresDOM = new GestorCorredorDOM(rutaArchivo, TipoValidacion.DTD);

        // Cargar corredores
        // System.out.println(gestorCorredoresDOM.getCorredores().get(0).getPuntuaciones());

        // Añadir corredor
        // Velocista velocista = new Velocista("C03", "E1", "Yago Otero",
        // LocalDate.now(), Float.valueOf("10.4"));
        // gestorCorredoresDOM.anadirCorredor(velocista, "Archivos/corredores16.xml");

        // gestorCorredoresDOM.eliminarCorredor(3);

        // STAX ------------------------------------------
        GestorCorredoresSTAX gestorCorredoresSTAX = new GestorCorredoresSTAX(rutaArchivo, TipoValidacion.DTD);
        gestorCorredoresSTAX.leerCorredores();
    }
}
