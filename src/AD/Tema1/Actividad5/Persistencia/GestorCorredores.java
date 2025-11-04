package AD.Tema1.Actividad5.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import com.azul.crs.internal.asm.Handle;

import AD.Tema1.Actividad5.model.Corredor;
import AD.Tema1.Actividad5.model.TipoValidacion;

public class GestorCorredores {
    String rutaFichero;
    TipoValidacion tipoValidacion;

    ManejadorCorredores handler;
    XMLReader xmlReader;
    XMLSAXUtils xmlsaxUtils;

    public XMLReader abrirDocumento(String rutaFichero, TipoValidacion tipoValidacion) throws SAXNotRecognizedException,
            SAXNotSupportedException, SAXException, ParserConfigurationException, IOException {

        this.rutaFichero = rutaFichero;
        this.tipoValidacion = tipoValidacion;

        xmlsaxUtils = new XMLSAXUtils(rutaFichero, tipoValidacion, handler = new ManejadorCorredores());
        xmlReader = xmlsaxUtils.cargarDocumentoSAX().getXMLReader();

        System.out.println("Documento cargado correctamente");
        return xmlReader;
    }

    public ArrayList<Corredor> cargarCorredores() throws SAXException, ParserConfigurationException, IOException {
        if (handler == null) {
            System.out.println("Primero debes abrir el documento");
            return new ArrayList<>();
        }

        return handler.getCorredores();
    }

    public ArrayList<Corredor> cargarCorredoresEquipo() throws SAXException, ParserConfigurationException, IOException {
        if (handler == null) {
            System.out.println("Primero debes abrir el documento");
            return new ArrayList<>();
        }

        return handler.getCorredores();
    }

    public Corredor mostrarInformacionCorredor(String codigo)
            throws SAXException, ParserConfigurationException, IOException {
        for (Corredor c : cargarCorredores()) {
            if (c.getCodigo().equals(codigo)) {
                System.out.println("Nombre: " + c.getNombre());
                System.out.println("Fecha de nacimiento: " + c.getFechaNacimiento());
                System.out.println("Historial: " + c.getPuntuaciones());
                return c;
            }
        }

        return null;
    }

    public ArrayList<Corredor> corredoresPorEquipo(String equipo){
        Handler handler = new ManejadorCorredoEquipo();
                xmlsaxUtils = new XMLSAXUtils(rutaFichero, tipoValidacion, handler = new ManejadorCorredores());
        xmlReader = xmlsaxUtils.cargarDocumentoSAX().getXMLReader();
    }
}
