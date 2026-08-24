package AD.Tema1.PersistenciaSTAX.Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import AD.Tema1.PersistenciaSTAX.model.TipoValidacion;

public class ConfiguracionStAX {
    public static XMLInputFactory configurarYCrearReader(String rutaArchivo, TipoValidacion tipoValidacion) {
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
            SchemaFactory schemaFactory = SchemaFactory.newInstance(null);
            Schema schema = schemaFactory.newSchema();
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}