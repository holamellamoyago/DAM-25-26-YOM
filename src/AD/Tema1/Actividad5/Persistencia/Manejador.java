package AD.Tema1.Actividad5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import AD.Tema1.Actividad5.model.Corredor;
import AD.Tema1.Actividad5.model.Fondista;
import AD.Tema1.Actividad5.model.Velocista;

public class Manejador extends DefaultHandler {
    ArrayList<Corredor> corredores = new ArrayList<>();
    String contenidoActual;

    String tipoCorredor = null;
    String codigo = null;
    Integer dorsal = null;
    String equipo = null;
    LocalDate fechaNacimiento = null;
    Float especial = null;

    Integer anio = null;
    Float puntos = null;


    @Override
    public void startDocument() throws SAXException {
        System.out.println("El documento comienza a leerse");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Terminando documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("velocista")) {
            tipoCorredor = "velocista";
            cogerAtributosCorredor(attributes);
        }

        if (qName.equals("fondista")) {
            tipoCorredor = "fondista";
            cogerAtributosCorredor(attributes);
        }

        if (qName.equals("puntuacion")) {
            cogerAtributosPuntuacion(attributes);
        }

        contenidoActual = "";
        System.out.println("Empezando un elemento " + qName);

    }

    private void cogerAtributosCorredor(Attributes attributes) {
            // System.out.println();
            // System.out.println("Empezando a leer atributo: " + attributes.getQName(i));
            // System.out.println("Tiene un valor de: " + attributes.getValue(i));
            // System.out.println();

            codigo = attributes.getValue(codigo);
            dorsal = Integer.valueOf(attributes.getValue(dorsal));
            equipo = attributes.getValue(equipo);

        
    }

    private void cogerAtributosPuntuacion(Attributes attributes) {
        anio = Integer.valueOf(attributes.getValue("anio"));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        contenidoActual;
        System.out.println("Terminando el dodocumento " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoActual = String.valueOf(ch, start, length).trim();
        // TODO aqui viene contenido 
        System.out.println(contenidoActual);
    }

    public List<Corredor> getCorredores() {

        return new ArrayList<>();
    }
}
