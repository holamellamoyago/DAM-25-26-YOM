package AD.Tema1.Actividad5.Persistencia.Corredores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import AD.Tema1.Actividad5.Persistencia.XMLSAXUtils;
import AD.Tema1.Actividad5.model.Corredor;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class GestorCorredores {
    String rutaFichero;
    TipoValidacion tipoValidacion;

    DefaultHandler handler;
    XMLReader xmlReader;
    XMLSAXUtils xmlsaxUtils;

    public GestorCorredores(String rutaFichero, TipoValidacion tipoValidacion) {
        this.rutaFichero = rutaFichero;
        this.tipoValidacion = tipoValidacion;
    }

    public XMLReader abrirDocumento(DefaultHandler handler) {
        try {
            xmlsaxUtils = new XMLSAXUtils(rutaFichero, tipoValidacion, handler);
            xmlReader = xmlsaxUtils.cargarDocumentoSAX().getXMLReader();

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Documento cargado correctamente");
        return xmlReader;
    }

    public ArrayList<Corredor> cargarCorredores() throws SAXException, ParserConfigurationException, IOException {
        ManejadorCorredores manejadorCorredores = new ManejadorCorredores();
        xmlReader = abrirDocumento(manejadorCorredores);

        return manejadorCorredores.getCorredores();
    }

    public ArrayList<Corredor> cargarCorredoresEquipo(String equiposBuscar) throws SAXException{
        ManejadorCorredoEquipo manejadorCorredoEquipo = new ManejadorCorredoEquipo(equiposBuscar);
        xmlReader = abrirDocumento(manejadorCorredoEquipo);

        return manejadorCorredoEquipo.getCorredores();

    }

    public Corredor mostrarInformacionCorredor(String codigo)
            throws SAXException, ParserConfigurationException, IOException {
        for (Corredor c : cargarCorredores()) {
            if (c.getCodigo().equals(codigo)) {
                System.out.println("Nombre: " + c.getNombre());
                System.out.println("Equipo: " + c.getEquipo());
                System.out.println("Fecha de nacimiento: " + c.getFechaNacimiento());
                System.out.println("Historial: " + c.getPuntuaciones());
                return c;
            }
        }

        return null;
    }


}
