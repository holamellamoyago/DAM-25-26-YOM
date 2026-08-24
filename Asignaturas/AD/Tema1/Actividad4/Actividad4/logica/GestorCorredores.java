package AD.Tema1.Actividad4.Actividad4.logica;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import AD.Tema1.Actividad4.Actividad4.model.Corredor;
import AD.Tema1.Actividad4.Actividad4.model.Fondista;
import AD.Tema1.Actividad4.Actividad4.model.Puntuacion;
import AD.Tema1.Actividad4.Actividad4.model.Velocista;
import AD.Tema1.Actividad4.Actividad4.persistencia.CorredorXML;
import AD.Tema1.Actividad4.Actividad4.persistencia.TipoValidacion;
import AD.Tema1.Actividad4.Actividad4.persistencia.XMLDOMUtils;

public class GestorCorredores {
    private final CorredorXML gestor;
    private Document documentoXML;

    public GestorCorredores() {
        this.gestor = new CorredorXML();
    }

    public void cargarDocumento(String rutaXML, TipoValidacion validacion) {
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
        Fondista fondista = null;
        Velocista velocista = null;

        if (corredor instanceof Fondista) {
            fondista = (Fondista) corredor;
        } else {
            velocista = (Velocista) corredor;
        }

        String tipoCorredor = corredor.getClass().getSimpleName().toLowerCase();

        Element corredorAnadido = XMLDOMUtils.addElement(documentoXML, tipoCorredor, documentoXML.getDocumentElement());

        corredorAnadido.setAttribute("codigo", corredor.getCodigo());
        corredorAnadido.setAttribute("dorsal", String.valueOf(corredor.getDorsal()));
        corredorAnadido.setAttribute("equipo", corredor.getEquipo());

        Element nombre = XMLDOMUtils.addElement(documentoXML, "nombre", corredorAnadido);
        nombre.setTextContent(corredor.getNombre());

        Element fechaNacimiento = XMLDOMUtils.addElement(documentoXML, "fecha_nacimiento", corredorAnadido);
        fechaNacimiento.setTextContent(corredor.getFechaNacimiento().toString());

        if (fondista != null) {
            Element distancia_max = XMLDOMUtils.addElement(documentoXML, "distancia_max", corredorAnadido);
            distancia_max.setTextContent(String.valueOf(fondista.getDistanciaMax()));
        } else {
            Element velocidad_media = XMLDOMUtils.addElement(documentoXML, "velocidad_media", corredorAnadido);
            velocidad_media.setTextContent(String.valueOf(velocista.getVelocidadMedia()));
        }

        if (corredor.getPuntuaciones() != null) {
            for (Puntuacion puntuacion : corredor.getPuntuaciones()) {
                Element puntuacioElement = XMLDOMUtils.addElement(documentoXML, "Puntuacion", corredorAnadido);
                puntuacioElement.setTextContent(String.valueOf(puntuacion.getPuntos()));
                puntuacioElement.setAttribute("anio", String.valueOf(puntuacion.getAnio()));
            }
        }

        gestor.guardarDocumento(documentoXML);
    }

    public void eliminarCorredor(String dorsal) {
        if (gestor.eliminarCorredorPorDorsal(documentoXML, dorsal)) {
            System.out.println("Corredor eliminador correctamente");
        } else {
            System.out.println("Error al eliminar el corredor");
        }
    }

    public int siguienteCodigoCorredor() {
        Node parent = documentoXML.getDocumentElement();
        NodeList corredores = parent.getChildNodes();
        Element ultCorredor = (Element) corredores.item(corredores.getLength() - 1);

        String[] codigos = ultCorredor.getAttribute("codigo").split("C");

        return Integer.valueOf(codigos[1]) + 1 ;
    }
}
