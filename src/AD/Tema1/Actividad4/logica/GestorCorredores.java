package AD.Tema1.Actividad4.logica;

import org.w3c.dom.Document;

import AD.Tema1.Actividad4.persistencia.CorredorXML;
import AD.Tema1.Actividad4.persistencia.TipoValidacion;




public class GestorCorredores {
    private final CorredorXML gestor;
    private Document documentoXML;

    public GestorCorredores() { 
        this.gestor = new CorredorXML();
    }

    public void cargarDocumento(String rutaXML, TipoValidacion validacion){
        try {
            this.documentoXML = gestor.cargarDocumentoDOM(rutaXML, validacion);
            System.out.println("Documento xml cargado correctamente");
        } catch (Exception e) {
            System.out.println("Problemas al cargar el XML");
        }
    }
    
}
