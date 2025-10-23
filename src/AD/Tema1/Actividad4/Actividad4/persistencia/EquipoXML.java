package AD.Tema1.Actividad4.Actividad4.persistencia;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import AD.Tema1.Actividad4.Actividad4.model.Equipo;

public class EquipoXML {
        private Document documentXML ; 

    public void cargarDocumentoDOM(String rutaXML, TipoValidacion validacion) throws ExcepcionXML {
        this.documentXML = XMLDOMUtils.cargarDocumentoXML(rutaXML, validacion);
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        String xPath = String.format("//equipo[nombre='%s']", nombre);

        try {
            Element equipoElement = (Element) XMLDOMUtils.evaluarXPathNodo
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
