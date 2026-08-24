package AD.Tema1.PersistenciaSTAX.Persistencia.Cursor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import AD.Tema1.PersistenciaSTAX.Persistencia.ConfiguracionStAX;
import AD.Tema1.PersistenciaSTAX.model.TipoValidacion;

public class XMLSTAXUtilsCursor {
    public static XMLStreamReader cargarDocumentoSTAX(String rutaArchivo, TipoValidacion tipoValidacion) {
        try {
            XMLInputFactory factory = ConfiguracionStAX.configurarYCrearReader(rutaArchivo, tipoValidacion);
            return factory.createXMLStreamReader(new FileInputStream(new File(rutaArchivo)));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String obtenerNombreEtiqueta(XMLStreamReader reader) {
        if (reader.isStartElement() || reader.isEndElement()) {
            return reader.getLocalName();
        } else {
            return null;
        }
    }

    public static String leerTexto(XMLStreamReader reader) {
        return reader.getEventType() == XMLStreamConstants.CHARACTERS ? reader.getText().trim() : "";
    }

    public static String leerAtributo(XMLStreamReader reader, String nombreAtributo) {
        return "";
    }

    public static void validarConXSD() {

    }

    public static XMLStreamWriter crearWritterSTAX(String rutaSalida) {
        try {
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            return outputFactory.createXMLStreamWriter(new FileWriter(rutaSalida));
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static void addSaltoDeLinea(XMLStreamWriter writter, int nivel) {
        try {
            String identiacion = "\n" + "   ".repeat(nivel);
            writter.writeCharacters(identiacion);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addAtributo(XMLStreamWriter writter, String nombre, String valor) {
        try {
            writter.writeAttribute(nombre, valor);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addElemento(XMLStreamWriter writter, String nombre, String valor) {
        try {
            writter.writeStartElement(nombre, valor);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addElementoVacio(XMLStreamWriter writter, String nombre) {
        try {
            writter.writeEmptyElement(nombre);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    // public static void addElemento (XMLStreamWriter writter, String nombre ,
    // String)

    public static void addTextoElemento(XMLStreamWriter writter, String texto) {
        try {
            if (texto != null) {
                writter.writeCharacters(texto);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        ;

    }

    public static void addEndElement(XMLStreamWriter writter) {
        try {
            writter.writeEndElement();
        } catch (XMLStreamException e) {
            throw new ExcepcionXML(e.toString());
        }
    }

    public static void addStartElemento(XMLStreamWriter writter, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStartElemento'");
    }

    public static void addEndDocument(XMLStreamWriter writter) {
        try {
            writter.writeEndDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

}
