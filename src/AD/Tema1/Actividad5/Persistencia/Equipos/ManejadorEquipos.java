package AD.Tema1.Actividad5.Persistencia.Equipos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import AD.Tema1.Actividad5.model.Equipo;
import AD.Tema1.Actividad5.model.Patrocinador;

public class ManejadorEquipos extends DefaultHandler {

    ArrayList<Equipo> equipos = new ArrayList<>();
    Set<Patrocinador> patrocinadores = new HashSet<>();

    private Equipo equipo;
    private Patrocinador patrocinador;

    String contenidoElemento;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Patrocinador":
                equipo = new Equipo();
                patrocinador = new Patrocinador();
                getAtributosPatrocinador(attributes);
                break;
            case "Donacion":
                patrocinador.setFechaInicio(LocalDate.parse(attributes.getValue("fecha")));
            default:
                break;
        }

    }

    private void getAtributosPatrocinador(Attributes atributos) {
        String idEquipo = atributos.getValue("idEquipo");
        String nombreEquipo = atributos.getValue("nombreEquipo");

        equipo.setIdEquipo(idEquipo);
        equipo.setNombre(nombreEquipo);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "nombre":
                patrocinador.setNombre(contenidoElemento);
                break;
            case "donacion":
                patrocinador.setDonacion(Float.valueOf(contenidoElemento));
                break;
            case "Patrocinador":
                patrocinadores.add(patrocinador);
                equipo.setPatrocinadores(patrocinadores);
                equipos.add(equipo);
                patrocinadores = new HashSet<>();
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoElemento = String.valueOf(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
    }

    public ArrayList<Equipo> cargarEquipos() {
        return equipos;
    }

}
