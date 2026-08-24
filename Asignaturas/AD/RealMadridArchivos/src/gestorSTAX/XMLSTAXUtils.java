package gestorSTAX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
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

import org.xml.sax.XMLReader;

import clases.TipoValidacion;
import manejadorErrores.ExcepcionXML;
import manejadorErrores.SimpleErrorHandler;

public class XMLSTAXUtils {

    /*
     * crearWritterSTAX
     * 
     * + addStartDocument
     * - addEndDocument
     * 
     * + addStartElemento
     * - addEndElement
     * 
     * + obtenerNombreEtiqueta
     * + leerTexto
     * + leerAtributo
     * + addSaltoDeLinea
     * + addAtributo
     * + addElemento
     * + addElementoVacio
     * 
     */

    public static XMLStreamReader crearStreamReader(String rutaArchivo, TipoValidacion tipoValidacion) {
        try {
            XMLInputFactory inputFactory = crearInputFactory(rutaArchivo, tipoValidacion);
            return inputFactory.createXMLStreamReader(new FileInputStream(new File(rutaArchivo)));
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ArithmeticException(e.toString());
        }

    }

    public static XMLEventReader crearEventReader(String rutaArchivo, TipoValidacion tipoValidacion) {
        XMLInputFactory inputFactory = crearInputFactory(rutaArchivo, tipoValidacion);
        try {
            return inputFactory.createXMLEventReader(new FileInputStream(new File(rutaArchivo)));
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ArithmeticException(e.toString());
        }

    }

    public static XMLEventWriter crearEventWritter(String rutaArchivo, TipoValidacion tipoValidacion) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try {
            return outputFactory.createXMLEventWriter(new FileOutputStream(new File(rutaArchivo)));
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ArithmeticException(e.toString());
        }

    }

    public static XMLStreamWriter crearStreamWritter(XMLOutputFactory outputFactory, String rutaArchivo) {
        try {
            return outputFactory.createXMLStreamWriter(new FileWriter(rutaArchivo));
        } catch (IOException | XMLStreamException e) {
            throw new ArithmeticException(e.toString());
        }

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

    public static String obtenerNombreEtiqueta(XMLStreamReader reader) {
        if (reader.isStartElement() || reader.isEndElement()) {
            return reader.getLocalName();
        } else {
            return null;
        }
    }

    public static void addStartDocument(XMLStreamWriter writter) {
        try {
            writter.writeStartDocument("UTF-8", "1.0");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    public static String leerTexto(XMLStreamReader reader) throws XMLStreamException {
        return reader.getEventType() == XMLStreamConstants.CHARACTERS ? reader.getText().trim()
                : reader.getElementText();
    }

    public static String leerAtributo(XMLStreamReader reader, String nombreAtributo) {
        return reader.getAttributeValue(null, nombreAtributo);
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

    public static void addTextoElemento(XMLStreamWriter writter, String texto) {
        try {
            if (texto != null) {
                writter.writeCharacters(texto);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    public static void addEndElement(XMLStreamWriter writter) {
        try {
            writter.writeEndElement();
        } catch (XMLStreamException e) {
            throw new ExcepcionXML(e.toString());
        }
    }

    public static void addStartElemento(XMLStreamWriter writter, String string) {
        try {
            writter.writeStartElement(string);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addEndDocument(XMLStreamWriter writter) {
        try {
            writter.writeEndDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------------------

    private static XMLInputFactory crearInputFactory(String rutaArchivo, TipoValidacion tipoValidacion) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            // 2. CONFIGURAR PROPIEDADES CLAVE (Validación y Seguridad)
            // a) Activar la validación del esquema (DTD o XSD)
            // factory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.TRUE); NO FUNCIONA
            // b) Activar el procesamiento seguro (anti-XXE)

            // factory.setProperty(XMLConstants.FEATURE_SECURE_PROCESSING,
            // Boolean.TRUE);

            File file = new File(rutaArchivo);

            switch (tipoValidacion) {
                case XSD:
                    validarConXSD(file);
                    break;
                case DTD:
                    validarConDTD(file);
                    break;
                case NO_VALIDAR:
                    break;
                default:
                    break;
            }

            // 3. CREAR el lector (Reader)
            // XMLStreamReader reader = factory.createXMLStreamReader(new
            // FileReader(rutaArchivo));
            return factory;
        } catch (IllegalArgumentException e) {
            System.err.println("ERROR: La implementación del Parser no soporta alguna propiedad configurada.");
        }
        return null; // Devuelve null si falla la creación
    }

    public static XMLOutputFactory crearOutputFactory() {
        return XMLOutputFactory.newInstance();
    }

    private static void validarConXSD(File file) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema();
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void validarConDTD(File file) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            saxParserFactory.setValidating(true);
            saxParserFactory.setNamespaceAware(true);

            SAXParser parser = saxParserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            // Schema schema = saxParserFactory.newSAXParser();
            // Validator validator = schema.newValidator();

            reader.setErrorHandler(new SimpleErrorHandler()); // Tu manejador
            // reader.parse(new InputSource(new FileInputStream(file)));

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // ----------------------- Eventos

    public static void addStartDocument(XMLEventWriter writter) {
        try {
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent startDocument = eventFactory.createStartDocument("UTF-8", "1.0");
            writter.add(startDocument);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addEndDocument(XMLEventWriter writter) {
        try {
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent endDocument = eventFactory.createEndDocument();
            writter.add(endDocument);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addStartElemento(XMLEventWriter writter, String nombre) {
        try {
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent startElement = eventFactory.createStartElement("", "", nombre);
            writter.add(startElement);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addEndElement(XMLEventWriter writter, String nombre) {
        try {
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent endElement = eventFactory.createEndElement("", "", nombre);
            writter.add(endElement);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addTextoElemento(XMLEventWriter writter, String texto) {
        try {
            if (texto != null) {
                XMLEventFactory eventFactory = XMLEventFactory.newInstance();
                XMLEvent characters = eventFactory.createCharacters(texto);
                writter.add(characters);
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void addAtributo(XMLEventWriter writter, String nombre, String valor) {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        javax.xml.stream.events.Attribute atributo = eventFactory.createAttribute(nombre, valor);
        try {
            writter.add(atributo);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        // Los atributos se añaden al crear el StartElement

    }

}
