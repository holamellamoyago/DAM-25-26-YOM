package AD.Tema1Resumen.STAX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import AD.Tema1Resumen.Clases.TipoValidacion;
import AD.Tema1Resumen.DOM.Clases.SimpleErrorHandler;

public class ConfiguracionSTAX {
    public static XMLInputFactory configurarSTAX(String rutaArchivo, TipoValidacion tipoValidacion) {
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
            reader.parse(new InputSource(new FileInputStream(file)));
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static XMLStreamReader crearStreamReader(XMLInputFactory factory, String rutaArchivo) {
        try {
            return factory.createXMLStreamReader(new FileInputStream(new File(rutaArchivo)));
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ArithmeticException(e.toString());
        }

    }
}