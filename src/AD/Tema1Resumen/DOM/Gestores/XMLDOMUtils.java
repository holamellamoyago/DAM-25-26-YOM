package AD.Tema1Resumen.DOM.Gestores;

import java.io.File;
import java.io.FileWriter;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.io.*;

import AD.Tema1Resumen.Clases.TipoValidacion;
import AD.Tema1Resumen.DOM.Clases.ExcepcionXML;
import AD.Tema1Resumen.DOM.Clases.SimpleErrorHandler;

public class XMLDOMUtils {
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    public static Document cargarDocumentoXML(String rutaFichero, TipoValidacion validacion) {
        try {
            // 1. Crear y configurar la factoría
            DocumentBuilderFactory dbf = configurarFactory(validacion);

            // Crear el parser
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Establecer un mensaje del manejador de errores
            if (validacion != TipoValidacion.NO_VALIDAR) {
                db.setErrorHandler(new SimpleErrorHandler());
            }

            // Cargar el documento en memoria
            Document documento = db.parse(new File(rutaFichero));

            // El getDocumentElement es para conseguir para la raiz del documento
            documento.getDocumentElement().normalize();

            return documento;

        } catch (Exception e) {
            throw new ExcepcionXML("Error de lectura en el XML");
        }
    }

    private static DocumentBuilderFactory configurarFactory(TipoValidacion validacion) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        switch (validacion) {
            case DTD -> {
                dbf.setValidating(true);
                dbf.setIgnoringElementContentWhitespace(true);
                dbf.setIgnoringComments(true);
            }

            case XSD -> {
                dbf.setNamespaceAware(true);
                dbf.setIgnoringElementContentWhitespace(true);
                dbf.setValidating(true);
                dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
            }

            case NO_VALIDAR -> {
                dbf.setValidating(false);
            }
        }

        return dbf;
    }

    public static String obtenerTexto(Element padre, String etiqueta) {
        NodeList lista = padre.getElementsByTagName(etiqueta);

        if (lista.getLength() > 0) {
            return lista.item(0).getTextContent();
        }

        return "";
    }

    public static void guardarDocumentoXML(Document doc, String rutaDestino, String rutaValidacion) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, rutaValidacion);
            // transformer.setOutputProperty(OutputKeys.INDENT, "yes" );
            // transformer.setOutputProperty("(http://xml.apache.org/xslt) indent-amount",
            // "4");

            doc.getDocumentElement().normalize();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(rutaDestino));
            transformer.transform(source, result);

        } catch (Exception e) {
            throw new ExcepcionXML("Error al guardar");
        }
    }

    
    public static String getAtributo(Element element, String nombreAtributo) {
        return element.getAttribute(nombreAtributo);
    }

    /*
        CARGAR Y GUARDAR DOCUMENTO

     *  GETTERS: 
     *      getAtributo
     * 
     *  SETTERS:
     *      addAtributoId
     *      addElement
     *      eliminarElemento
     *      modificarAtributo
     *      modificarValorElemento
     *      buscarelementoPorId
     * 
     */

    public static Attr addAtributo(Document doc, String nombre, String valor, Element elemento) {
        Attr attrib = doc.createAttribute(nombre);
        attrib.setValue(valor);
        elemento.setAttributeNode(attrib);

        return attrib;

    }

    public static Attr addAtributoId(Document doc, String nombre, String valor, Element elemento) {
        Attr attribId = doc.createAttribute(nombre);
        attribId.setValue(valor);
        elemento.setAttributeNode(attribId);

        elemento.setIdAttributeNode(attribId, true);

        return attribId;

    }

    public static Element addElement(Document doc, String nombre, Element padre) {
        Element elemento = doc.createElement(nombre);
        // Text texto = doc.createTextNode(valor);

        padre.appendChild(elemento);
        // elemento.appendChild(texto);

        return elemento;
    }

    public static boolean eliminarElemento(Element elemento) {
        if (elemento != null && elemento.getParentNode() != null) {
            elemento.getParentNode().removeChild(elemento);
            return true;
        }
        return false;
    }


    public static void modificarAtributo(Element elemento, String nombre, Object valor) {
        String valorSTR = String.valueOf(valor);

        elemento.setAttribute(nombre, valorSTR);

    }

    public static void modificarValorElemento(Element elemento, Object valor) {
        elemento.setTextContent(String.valueOf(valor));
    }

    public static Element buscarelementoPorId(Document doc, String idValor) {
        return doc.getElementById(idValor);
    }

    private static Object evaluarXPath(Object contexto, String expresion, QName tipoResultado) {
        XPath xPath = XPathFactory.newInstance().newXPath();

        try {
            return xPath.evaluate(expresion, contexto, tipoResultado);
        } catch (XPathExpressionException e) {
            throw new ExcepcionXML(e.toString());
        }
    }

    public static boolean evaluarXPathBoolean(Object contexto, String expression) {
        return (boolean) evaluarXPath(expression, expression, XPathConstants.BOOLEAN); // ns si es asi
    }

    public static Node evaluarXPathNode(Object contexto, String expression) {
        return (Node) evaluarXPath(expression, expression, XPathConstants.NODE); // ns si es asi
    }

    public static NodeList evaluarXPathNodeList(Object contexto, String expression) {
        return (NodeList) evaluarXPath(expression, expression, XPathConstants.NODESET); // ns si es asi
    }

    public static double evaluarXPathDouble(Object contexto, String expression) {
        return (double) evaluarXPath(expression, expression, XPathConstants.NUMBER); // ns si es asi
    }



}