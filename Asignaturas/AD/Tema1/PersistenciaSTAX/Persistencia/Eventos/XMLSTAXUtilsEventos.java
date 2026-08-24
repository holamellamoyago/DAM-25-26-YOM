package AD.Tema1.PersistenciaSTAX.Persistencia.Eventos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import AD.Tema1.Actividad5.ManejadorErrores.ExcepcionXML;
import AD.Tema1.PersistenciaSTAX.Persistencia.ConfiguracionStAX;
import AD.Tema1.PersistenciaSTAX.model.TipoValidacion;

public class XMLSTAXUtilsEventos {
    public static XMLEventReader cargarDocumentoSTAX(String rutaArchivo, TipoValidacion tipoValidacion) {
        try {
            XMLInputFactory factory = ConfiguracionStAX.configurarYCrearReader(rutaArchivo, tipoValidacion);
            return factory.createXMLEventReader(new FileInputStream(new File(rutaArchivo)));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static XMLEventWriter crearWritterSTAX(String rutaSalida) {
        try {
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            return outputFactory.createXMLEventWriter(new FileWriter(rutaSalida));
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static XMLEventFactory crearFactoryEventos() {
        return XMLEventFactory.newInstance();
    }

    public static void addDeclaracionXML(XMLEventWriter writer, XMLEventFactory factory) {
        try {
            writer.add(factory.createStartDocument("UTF-8", " 1.0"));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addElemento(XMLEventWriter writer, String nombre, String valor, XMLEventFactory factory) {
        try {
            writer.add(factory.createStartElement("", "", valor));

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static String leerTexto(XMLEvent event) {
        String texto = event.isCharacters() ? event.asCharacters().getData().trim() : "";
        return texto;
    }

    public static void addSaltoDeLinea(XMLEventWriter writer, int nivel, XMLEventFactory factory) {
        try {
            String identiacion = "\n" + "   ".repeat(nivel);
            writer.add(factory.createCharacters(identiacion));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

}
