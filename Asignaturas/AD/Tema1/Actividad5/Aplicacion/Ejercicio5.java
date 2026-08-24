package AD.Tema1.Actividad5.Aplicacion;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import AD.Tema1.Actividad5.Persistencia.Corredores.GestorCorredores;
import AD.Tema1.Actividad5.Persistencia.Corredores.ManejadorCorredores;
import AD.Tema1.Actividad5.Persistencia.Equipos.GestorEquipos;
import AD.Tema1.Actividad5.Persistencia.Equipos.ManejadorEquipos;
import AD.Tema1.Actividad5.model.Equipo;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class Ejercicio5 {
    public static void main(String[] args) {
        // GestorCorredores gestorCorredores = new
        // GestorCorredores("Archivos/corredores.xml", TipoValidacion.DTD);
        // GestorEquipos gestorEquiposModificados = new
        // GestorEquipos("Archivos/ActualizacionesEquipos.xml", TipoValidacion.DTD);
        // GestorEquipos gestorEquiposOriginal = new
        // GestorEquipos("Archivos/Equipos.xml", TipoValidacion.DTD);

        System.out.println();
        // ArrayList<Equipo> equiposModificados =
        // gestorEquiposModificados.cargarEquipos(new ManejadorEquipos());

        System.out.println();
        // gestorEquiposOriginal.modi
        // gestorEquipos.abrirDocumentoDOM("Archivos/Equipos.xml", TipoValidacion.DTD);

        GestorCorredores gestor = new GestorCorredores("Archivos/Corredores.xml", TipoValidacion.DTD);
        try {
            System.out.println(gestor.cargarCorredoresEquipo("E1"));
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
