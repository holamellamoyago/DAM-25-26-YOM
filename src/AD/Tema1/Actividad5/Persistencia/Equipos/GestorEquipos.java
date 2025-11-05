package AD.Tema1.Actividad5.Persistencia.Equipos;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import AD.Tema1.Actividad5.Persistencia.XMLSAXUtils;
import AD.Tema1.Actividad5.Persistencia.Corredores.ManejadorCorredores;
import AD.Tema1.Actividad5.model.Equipo;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class GestorEquipos {

    String rutaFichero;
    TipoValidacion tipoValidacion;

    ManejadorCorredores handler;
    XMLReader xmlReader;
    XMLSAXUtils xmlsaxUtils;

    public XMLReader abrirDocumento(String rutaFichero, TipoValidacion tipoValidacion) {

        this.rutaFichero = rutaFichero;
        this.tipoValidacion = tipoValidacion;

        try {
            xmlsaxUtils = new XMLSAXUtils(rutaFichero, tipoValidacion, handler = new ManejadorCorredores());
            xmlReader = xmlsaxUtils.cargarDocumentoSAX().getXMLReader();

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Documento cargado correctamente");
        return xmlReader;
    }

    public ArrayList<Equipo> cargarEquipos(){
        if (xmlReader == null) {
            abrirDocumento(rutaFichero, tipoValidacion);
        }

        return new ArrayList<>();
    }
}
