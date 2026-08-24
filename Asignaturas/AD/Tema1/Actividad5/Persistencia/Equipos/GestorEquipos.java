package AD.Tema1.Actividad5.Persistencia.Equipos;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import AD.Tema1.Actividad5.ManejadorErrores.ExcepcionXML;
import AD.Tema1.Actividad5.Persistencia.XMLDOMUtils;
import AD.Tema1.Actividad5.Persistencia.XMLSAXUtils;
import AD.Tema1.Actividad5.Persistencia.Corredores.ManejadorCorredores;
import AD.Tema1.Actividad5.model.Equipo;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class GestorEquipos {

    private String rutaFichero;
    private TipoValidacion tipoValidacion;
    
    private Document documentXML; 

    public GestorEquipos(String rutaFichero, TipoValidacion tipoValidacion){
        this.rutaFichero = rutaFichero;
        this.tipoValidacion = tipoValidacion;
    }

    private void abrirDocumentoSAX(DefaultHandler defaultHandler) {

        try {
            XMLSAXUtils xmlsaxUtils = new XMLSAXUtils(rutaFichero, tipoValidacion, defaultHandler);
            xmlsaxUtils.cargarDocumentoSAX().getXMLReader();

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Documento cargado correctamente");
    }

    private void abrirDocumentoDOM() throws ExcepcionXML {
        // this.documentXML = XMLDOMUtils.cargarDocumentoXML(rutaXML, validacion);
        this.documentXML = XMLDOMUtils.cargarDocumentoXML(rutaFichero, tipoValidacion);
    }

    public ArrayList<Equipo> cargarEquipos(ManejadorEquipos handler){
        abrirDocumentoSAX(handler);
        return handler.cargarEquipos();
    }

    public ArrayList<Equipo> modificarEquipos(Equipo nuevosEquipo) {
        abrirDocumentoDOM();

        return documentXML.

        return new ArrayList<>();
    }
}
