package AD.Tema1.PersistenciaSTAX.Persistencia.Eventos;

import java.beans.EventHandler;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.tree.ExpandVetoException;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.XMLReader;

import AD.Tema1.PersistenciaSTAX.Persistencia.Cursor.*;
import AD.Tema1.PersistenciaSTAX.model.TipoValidacion;

public class EquiposSTAXEventos {
    public static Map<String, Double> devolverMap(String rutaArchivo, TipoValidacion tipoValidacion) {
        XMLEventReader reader = XMLSTAXUtilsEventos.cargarDocumentoSTAX(rutaArchivo, tipoValidacion);

        Map<String, Double> mapaDonaciones = new TreeMap<>();

        Double donacion = 0.0;
        String nombrePatrocinador = "";
        boolean esPatrocinador = false;

        try {
            while (reader.hasNext()) {
                XMLEvent tipo = reader.nextEvent();

                if (tipo.isStartElement()) {
                    // TODO
                }

            }
        } catch (XMLStreamException e) {
            // throw new ExpandVetoException(null, nombrePatrocinador)
            e.printStackTrace();
        }

        return mapaDonaciones;
    }



    public static void escribirDoancionesTotales(String rutaSalida, Map<String, Double> mapaDonaciones) {
        XMLEventWriter writer = null;
        writer = XMLSTAXUtilsEventos.crearWritterSTAX(rutaSalida);
        XMLEventFactory factory = XMLSTAXUtilsEventos.crearFactoryEventos();

        XMLSTAXUtilsEventos.addDeclaracionXML(writer, factory);
        XMLSTAXUtilsEventos.addSaltoDeLinea(writer , 0 , factory);
    }

}
