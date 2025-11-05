package AD.Tema1.Actividad5.Aplicacion;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import AD.Tema1.Actividad5.Persistencia.Corredores.GestorCorredores;
import AD.Tema1.Actividad5.Persistencia.Corredores.ManejadorCorredores;
import AD.Tema1.Actividad5.Persistencia.Equipos.GestorEquipos;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class Ejercicio5 {
    public static void main(String[] args) {
        GestorCorredores gestorCorredores = new GestorCorredores("Archivos/corredores.xml", TipoValidacion.DTD);

        try {
            // gestorCorredores.abrirDocumento();
            // gestorEquipos.abrirDocumento("Archivos/ActualizacionesEquipos.xml",
            // TipoValidacion.NO_VALIDAR);

            System.out.println();
            System.out.println(gestorCorredores.cargarCorredores());

            System.out.println();
            gestorCorredores.mostrarInformacionCorredor("C02");

            System.out.println();

            System.out.println("Corredores por equipo: ");
            System.out.println(gestorCorredores.cargarCorredoresEquipo("E1"));
            System.out.println(gestorCorredores.cargarCorredores().get(0).getEquipo());

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}
