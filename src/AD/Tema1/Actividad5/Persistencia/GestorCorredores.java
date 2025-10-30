package AD.Tema1.Actividad5.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import AD.Tema1.Actividad5.model.Corredor;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class GestorCorredores {
    Manejador handler;

    public XMLReader abrirDocumento(String rutaFichero, TipoValidacion tipoValidacion) throws SAXNotRecognizedException,
            SAXNotSupportedException, SAXException, ParserConfigurationException, IOException {
        XMLSAXUtils xmlsaxUtils = new XMLSAXUtils(rutaFichero, tipoValidacion, handler = new Manejador());
        XMLReader xmlReader = xmlsaxUtils.cargarDocumentoSAX().getXMLReader();

        System.out.println("Documento cargado correctamente");
        return xmlReader;
    }

    public ArrayList<Corredor> cargarCorredores() {
        if (handler == null) {
            System.out.println("Primero debes abrir el documento");
            return new ArrayList<>();
        }

        new ArrayList<>();
    }
}
