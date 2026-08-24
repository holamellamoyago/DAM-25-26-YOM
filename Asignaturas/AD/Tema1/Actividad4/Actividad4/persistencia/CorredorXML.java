package AD.Tema1.Actividad4.Actividad4.persistencia;

import java.time.LocalDate;
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

public class CorredorXML {
    String rutaXML;

    // Cargar documento
    public Document cargarDocumentoDOM(String rutaXML, TipoValidacion validacion) throws ExcepcionXML {
        return XMLDOMUtils.cargarDocumentoXML(rutaXML, validacion);
    }

    public boolean guardarDocumento(Document doc) {
        try {
            XMLDOMUtils.guardarDocumentoXML(doc, "Archivos/corredores.xml", "corredoresDTD.dtd");
            System.out.println("Documento guardado");
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    public List<Corredor> cargarCorredores(Document doc) {
        List<Corredor> lista = new ArrayList<>();
        Element raiz = doc.getDocumentElement();
        NodeList nodos = raiz.getChildNodes();

        for (int i = 0; i < nodos.getLength(); i++) {
            if (nodos.item(i) instanceof Element corredorElem) {
                Corredor corredor = crearCorredor(corredorElem);

                if (corredor != null) {
                    lista.add(corredor);
                }
            }

        }

        return lista;

    }

    public Corredor crearCorredor(Element corredorElem) {
        String codigo = corredorElem.getAttribute("codigo");
        int dorsal = Integer.parseInt(corredorElem.getAttribute("dorsal"));
        String equipo = corredorElem.getAttribute("equipo");

        String nombre = XMLDOMUtils.obtenerTexto(corredorElem, "nombre");
        LocalDate fecha = LocalDate.parse(XMLDOMUtils.obtenerTexto(corredorElem, "fecha_nacimiento"));

        Corredor corredor = switch (corredorElem.getTagName()) {
            case "fondista" -> {
                float distancia = Float.parseFloat(XMLDOMUtils.obtenerTexto(corredorElem, "distancia_max"));
                yield new Fondista(codigo, dorsal, equipo, nombre, fecha, distancia);
            }

            case "velocista" -> {
                float velocidad = Float.parseFloat(XMLDOMUtils.obtenerTexto(corredorElem, "velocidad_media"));
                yield new Velocista(codigo, dorsal, equipo, nombre, fecha, velocidad);
            }

            default -> null;
        };

        if (corredor != null) {
            corredor.setPuntuaciones(cargarHistorial(corredorElem));
        }

        return corredor;
    }

    private ArrayList<Puntuacion> cargarHistorial(Element corredorElem) {
        ArrayList<Puntuacion> historial = new ArrayList<>();
        Element historialElem = (Element) corredorElem.getElementsByTagName("historial").item(0);

        if (historialElem != null) {
            NodeList puntuaciones = historialElem.getElementsByTagName("puntuacion");
            for (int i = 0; i < puntuaciones.getLength(); i++) {
                Element punt = (Element) puntuaciones.item(i);
                int anio = Integer.parseInt(punt.getAttribute("anio"));
                float puntos = Float.parseFloat(punt.getTextContent());

                Puntuacion p = new Puntuacion(anio, puntos);
                historial.add(p);
            }
        }

        return historial;
    }

    public void insertarCorredor(Corredor corredor, Document doc) {
        Element raiz = doc.getDocumentElement();

        String tipo = corredor instanceof Velocista ? "velocista" : "fondista";

        Element nodoCorredor = XMLDOMUtils.addElement(doc, tipo, raiz);

        XMLDOMUtils.addAtributoId(doc, "codigo", corredor.getCodigo(), nodoCorredor);
        XMLDOMUtils.addAtributoId(doc, "dorsal", String.valueOf(corredor.getDorsal()), nodoCorredor);
        XMLDOMUtils.addAtributoId(doc, "equipo", corredor.getEquipo(), nodoCorredor);
        XMLDOMUtils.addAtributoId(doc, "codigo", corredor.getCodigo(), nodoCorredor);

        // Anhadir subelementos comunes

    }

    public int obetenerSiguienteDorsal(Document doc) {
        Element raiz = doc.getDocumentElement();
        NodeList hijos = raiz.getChildNodes();

        for (int i = hijos.getLength(); i >= 0; i--) {
            Node nodo = hijos.item(i);

            if (nodo instanceof Element corredorElement) {
                String dorsalStr = corredorElement.getAttribute("dorsal");

                if (dorsalStr != null && !dorsalStr.isBlank()) {
                    try {
                        return Integer.parseInt(dorsalStr) + 1;
                    } catch (Exception e) {
                        throw new ExcepcionXML("Dorsal inválido");
                    }
                }
            }
        }

        return 0;
    }

    public boolean eliminarCorredorPorDorsal(Document doc, String dorsal) {
        Element raiz = doc.getDocumentElement();
        NodeList corredores = raiz.getChildNodes();

        for (int i = 0; i < corredores.getLength(); i++) {
            Element corredor = (Element) corredores.item(i);
            if (corredor.getAttribute("dorsal").equals(dorsal)) {
                XMLDOMUtils.eliminarElemento(corredor);
                guardarDocumento(doc);
                return true;
            }

        }

        return false;

    }

}
