package AD.Tema1.Actividad5.Persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import AD.Tema1.Actividad5.model.Corredor;
import AD.Tema1.Actividad5.model.Fondista;
import AD.Tema1.Actividad5.model.Puntuacion;
import AD.Tema1.Actividad5.model.Velocista;

public class ManejadorCorredoEquipo extends DefaultHandler {
    ArrayList<Corredor> corredores = new ArrayList<>();
    ArrayList<Puntuacion> historial = new ArrayList<>();

    String contenidoActual;
    String tipoCorredor = null;
    String codigo = null;
    Integer dorsal = null;
    String equipo = null;
    LocalDate fechaNacimiento = null;
    Float especial = 99f;
    Integer anio = null;
    Float puntos = null;
    String nombre;
    float contenidoPuntuacion;

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

        System.out.println("Empezando un elemento " + qName);

    }

    private void cogerAtributosCorredor(Attributes attributes) {
        codigo = attributes.getValue("codigo");
        dorsal = Integer.valueOf(attributes.getValue("dorsal"));
        equipo = attributes.getValue("equipo");

    }

    private void cogerAtributosPuntuacion(Attributes attributes) {
        anio = Integer.valueOf(attributes.getValue("anio"));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("velocista")) {
            Velocista v = new Velocista(codigo, dorsal, equipo, nombre, fechaNacimiento, especial);
            v.setPuntuaciones(historial);
            corredores.add(v);
            vaciarCampos();
        }

        if (qName.equals("fondista")) {
            Fondista f = new Fondista(codigo, dorsal, equipo, nombre, fechaNacimiento, especial);
            f.setPuntuaciones(historial);
            corredores.add(f);
            vaciarCampos();

        }

        if (qName.equals("puntuacion")) {
            Puntuacion p = new Puntuacion(anio, Float.parseFloat(contenidoActual));
            historial.add(p);
        }

        if (qName.equals("nombre")) {
            nombre = contenidoActual;
        }

        if (qName.equals("fecha_nacimiento")) {
            fechaNacimiento = LocalDate.parse(contenidoActual);
        }

        System.out.println("Terminando el dodocumento " + qName);

    }

    private void vaciarCampos() {
        contenidoActual = null;
        tipoCorredor = null;
        codigo = null;
        dorsal = null;
        equipo = null;
        fechaNacimiento = null;
        especial = 99f;
        anio = null;
        puntos = null;
        nombre = null;
        contenidoPuntuacion = 0;
        historial = new ArrayList<>();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoActual = String.valueOf(ch, start, length).trim();
    }

    public ArrayList<Corredor> getCorredores() throws SAXException {
        return corredores;
    }

    public ArrayList<Corredor> getCorredoresPorEquipo(String equipo) throws SAXException {
        corredores.removeIf(t -> t.getEquipo() != equipo);
        return corredores;
    }

}
