package AD.Tema1.Actividad5.Aplicacion;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import AD.Tema1.Actividad5.Persistencia.GestorCorredores;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class Ejercicio5 {
    public static void main(String[] args) {
        GestorCorredores gestor = new GestorCorredores();
        try {
            gestor.abrirDocumento("Archivos/corredores.xml", TipoValidacion.DTD);

            
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}
