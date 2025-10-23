package AD.Tema1.Actividad4.Actividad4.logica;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import AD.Tema1.Actividad4.Actividad4.model.Corredor;
import AD.Tema1.Actividad4.Actividad4.persistencia.CorredorXML;
import AD.Tema1.Actividad4.Actividad4.persistencia.TipoValidacion;




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


    public void leerCorredores() {
        gestor.cargarCorredores(documentoXML);
    }

    public Corredor leerCorredorCodigo(String cod) {
 
        for (Corredor corredor : gestor.cargarCorredores(documentoXML)) {
            if (corredor.getCodigo().equals(cod)) {
                return corredor;
            }
        }

        return null;
    }

    public Corredor leerCorredorDorsal(int dorsal) {
 
        for (Corredor corredor : gestor.cargarCorredores(documentoXML)) {
            if (corredor.getDorsal() == dorsal) {
                return corredor;
            }
        }

        return null;
    }

    public void anhadirCorredor(Corredor corredor) {
        //  ExcepcionXML();
    }
    
}
