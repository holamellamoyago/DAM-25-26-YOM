package gestorSAX;

import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;

import clases.*;

public class XMLSAXUtils {
    File rutaFichero;
    TipoValidacion validacion;
    DefaultHandler manejador;

    public XMLSAXUtils(String rutaFichero, TipoValidacion validacion, DefaultHandler manejador) {

        if (rutaFichero.equals("") || rutaFichero == null) {
            System.out.println("La ruta no debe de ser vacía");
            return;
        }

        if (validacion == null) {
            System.out.println("La validación no puede ser null");
            return;
        }

        this.rutaFichero = new File(rutaFichero);

        if (!existe()) {
            System.out.println("El XML no existe");
            return;
        }

        this.validacion = validacion;
        this.manejador = manejador;
    }

    private boolean existe() {
        return rutaFichero.exists();
    }

    public SAXParser cargarDocumentoSAX() {
        try {
            SAXParser parser = configurarSAX().newSAXParser();
            parser.parse(rutaFichero, manejador);
            return parser;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ArithmeticException(e.toString());
        }

    }

    private SAXParserFactory configurarSAX()
            throws SAXNotRecognizedException, SAXNotSupportedException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        String val = "http://xml.org/sax/features/validation";
        String namespaces = "http://xml.org/sax/features/namespaces";
        String esquemas = "http://apache.org/xml/features/validation/schema";

        switch (validacion) {
            case DTD:
                factory.setValidating(true);
                factory.setFeature(val, true);
                break;
            case XSD:
                factory.setValidating(true);
                factory.setNamespaceAware(true);

                factory.setFeature(val, true);
                factory.setFeature(namespaces, true);
                factory.setFeature(esquemas, true);
            default:
                factory.setValidating(false);
                break;
        }

        return factory;
    }

}
