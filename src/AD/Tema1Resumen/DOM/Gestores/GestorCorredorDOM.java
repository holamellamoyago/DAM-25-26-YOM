package AD.Tema1Resumen.DOM.Gestores;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import AD.Tema1Resumen.Clases.Corredor;
import AD.Tema1Resumen.Clases.Fondista;
import AD.Tema1Resumen.Clases.Puntuacion;
import AD.Tema1Resumen.Clases.TipoValidacion;
import AD.Tema1Resumen.Clases.Velocista;

public class GestorCorredorDOM {
    private Document document;

    public GestorCorredorDOM(String file, TipoValidacion tipoValidacion) {
        this.document = XMLDOMUtils.cargarDocumentoXML(file, tipoValidacion);
    }

    public ArrayList<Corredor> getCorredores() {
        ArrayList<Corredor> corredores = new ArrayList<>();
        NodeList nodeList = document.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            // Compruebo que sea de tipo node
            // if (node.getNodeType() != Node.ELEMENT_NODE) {
            // break;
            // }

            if (node.getNodeName().equals("corredores")) {
                NodeList nodeListCorredores = node.getChildNodes();
                for (int j = 0; j < nodeListCorredores.getLength(); j++) {
                    Element tipoCorredor = (Element) nodeListCorredores.item(j);
                    corredores.add(getCorredor(tipoCorredor));

                }
            }

        }

        return corredores;
    }

    private Corredor getCorredor(Element padre) {
        NodeList elementos = padre.getChildNodes();
        Corredor corredor;

        if (padre.getNodeName().equals("velocista")) {
            corredor = new Velocista();
        } else {
            corredor = new Fondista();
        }

        corredor.setCodigo(XMLDOMUtils.getAtributo(padre, "codigo"));
        corredor.setDorsal(Integer.valueOf(XMLDOMUtils.getAtributo(padre, "dorsal")));
        corredor.setEquipo(XMLDOMUtils.getAtributo(padre, "equipo"));

        for (int i = 0; i < elementos.getLength(); i++) {
            Element element = (Element) elementos.item(i);

            if (element.getNodeName().equals("nombre")) {
                corredor.setNombre(element.getTextContent());
            }

            if (element.getNodeName().equals("fecha_nacimiento")) {
                corredor.setFechaNacimiento(LocalDate.parse(element.getTextContent()));
            }

            if (element.getNodeName().equals("velocidad_media")) {
                float velocidad = Float.valueOf(element.getTextContent());
                ((Velocista) corredor).setVelocidadMedia(velocidad);
            }

            if (element.getNodeName().equals("distancia_max")) {
                ((Fondista) corredor).setDistanciaMax(Float.valueOf(element.getTextContent()));
            }

            if (element.getNodeName().equals("historial")) {
                corredor.setPuntuaciones(getPuntuaciones(element));
            }

        }

        if (padre.getNodeName().equals("velocista")) {
            Velocista velocista = (Velocista) corredor;
            return velocista;
        } else {
            Fondista fondista = (Fondista) corredor;
            return fondista;
        }
    }

    private ArrayList<Puntuacion> getPuntuaciones(Element padre) {
        ArrayList<Puntuacion> puntuacionesAnadidas = new ArrayList<>();

        NodeList puntuaciones = padre.getChildNodes();
        for (int i = 0; i < puntuaciones.getLength(); i++) {
            Element puntuacion = (Element) puntuaciones.item(i);

            float resultado = Float.parseFloat(puntuacion.getTextContent());
            int anio = Integer.parseInt(puntuacion.getAttribute("anio"));

            puntuacionesAnadidas.add(new Puntuacion(anio, resultado));
        }

        return puntuacionesAnadidas;
    }

    public void anadirCorredor(Corredor corredor, String rutaDestino) {
        String tipo = corredor.getClass().getSimpleName().toLowerCase();
        corredor.setDorsal(calcularSiguienteDorsal());

        Element elementoAnadido = XMLDOMUtils.addElement(document, tipo, document.getDocumentElement());
        XMLDOMUtils.addAtributo(document, "codigo", corredor.getCodigo(), elementoAnadido);
        XMLDOMUtils.addAtributo(document, "dorsal", String.valueOf(corredor.getDorsal()), elementoAnadido);
        XMLDOMUtils.addAtributo(document, "equipo", corredor.getEquipo(), elementoAnadido);

        Element nombre = XMLDOMUtils.addElement(document, "nombre", elementoAnadido);
        XMLDOMUtils.modificarValorElemento(nombre, corredor.getNombre());

        Element fecha = XMLDOMUtils.addElement(document, "fecha_nacimiento", elementoAnadido);
        XMLDOMUtils.modificarValorElemento(fecha, corredor.getFechaNacimiento().toString());

        Element especial;
        if (tipo.equals("velocista")) {
            especial = XMLDOMUtils.addElement(document, "velocidad_media", elementoAnadido);
            XMLDOMUtils.modificarValorElemento(especial, ((Velocista) corredor).getVelocidadMedia());
        } else {
            especial = XMLDOMUtils.addElement(document, "distancia_max", elementoAnadido);
            XMLDOMUtils.modificarValorElemento(especial, ((Fondista) corredor).getDistanciaMax());
        }

        // for (int i = 0; i < corredores.getLength(); i++) {
        // System.out.println(corredores.item(i));
        // }

        XMLDOMUtils.guardarDocumentoXML(document, rutaDestino, "CorredoresDTD.dtd");

    }

    private int calcularSiguienteDorsal() {
        NodeList corredores = document.getDocumentElement().getChildNodes();
        Element ultimo = (Element) corredores.item(corredores.getLength() - 1);

        int siguienteDorsal = Integer.valueOf(XMLDOMUtils.getAtributo(ultimo, "dorsal")) + 1;
        return siguienteDorsal;
    }

}
