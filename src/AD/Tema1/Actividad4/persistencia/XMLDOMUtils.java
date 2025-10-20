package AD.Tema1.Actividad4.persistencia;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLDOMUtils {
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    public static Document cargarDocumentoXML(String rutaFichero, TipoValidacion validacion) {
        try {
            // 1. Crear y configurar la factoría
            DocumentBuilderFactory dbf = configurarFactory(validacion);

            // Crear el parser
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Establecer un mensaje del manejador de errores
            if (validacion != TipoValidacion.NO_VALIDAR) {
                db.setErrorHandler(new SimpleErrorHandler());
            }

            // Cargar el documento en memoria 
            Document documento = db.parse(new File(rutaFichero));

            // El getDocumentElement es para conseguir para la raiz del documento
            documento.getDocumentElement().normalize();
            return documento;

        } catch (Exception e) {
            throw new ExcepcionXML("Error de lectura en el XML");
        }
    }

    private static DocumentBuilderFactory configurarFactory(TipoValidacion validacion) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        switch (validacion) {
            case DTD -> {
                dbf.setValidating(true);
                dbf.setIgnoringElementContentWhitespace(true);
            }

            case XSD -> {
                dbf.setNamespaceAware(true);
                dbf.setIgnoringElementContentWhitespace(true);
                dbf.setValidating(true);
                dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
            }

            case NO_VALIDAR -> {
                dbf.setValidating(false);
            }
        }

        return dbf;
    }

    public static String obtenerTexto(Element padre, String etiqueta) {
        NodeList lista = padre.getElementsByTagName(etiqueta);

        if (lista.getLength() > 0 ) {
          return lista.item(0).getTextContent();  
        }

        return "";
    }

    
}