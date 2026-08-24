package gestorSTAX;

import java.util.ArrayList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;

import clases.Jugador;
import clases.TipoValidacion;

public class GestorJugadoresEventosSTAX {
    private XMLEventReader xmlEventReader;
    private XMLEventWriter xmlEventWriter;

    private String rutaArchivo;
    private TipoValidacion tipoValidacion;

    public GestorJugadoresEventosSTAX(String rutaArchivo, TipoValidacion tipoValidacion) {
        this.rutaArchivo = rutaArchivo;
        this.tipoValidacion = tipoValidacion;
    }

    public ArrayList<Jugador> leerJugadores() throws XMLStreamException {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = new Jugador();
        String posicion = "";

        xmlEventReader = XMLSTAXUtils.crearEventReader(rutaArchivo, tipoValidacion);

        while (xmlEventReader.hasNext()) {
            XMLEvent evento = xmlEventReader.nextEvent();

            if (evento.isStartElement()) {
                StartElement startElement = evento.asStartElement();
                String localName = startElement.getName().getLocalPart();

                switch (localName) {
                    case "jugador":
                        Attribute dorsalAttr = startElement.getAttributeByName(new javax.xml.namespace.QName("dorsal"));
                        if (dorsalAttr != null) {
                            jugador.setDorsal(Integer.valueOf(dorsalAttr.getValue()));
                        }
                        break;
                    case "nombre":
                        evento = xmlEventReader.nextEvent();
                        jugador.setNombre(evento.asCharacters().getData());
                        break;
                    case "nacionalidad":
                        evento = xmlEventReader.nextEvent();
                        jugador.setNacionalidad(evento.asCharacters().getData());
                        break;
                    case "porteros":
                        posicion = "Portero";
                        break;
                    case "defensas":
                        posicion = "Defensa";
                        break;
                    case "centrocampistas":
                        posicion = "Centrocampista";
                        break;
                    case "delanteros":
                        posicion = "Delantero";
                        break;
                    default:
                        break;
                }
            }

            if (evento.isEndElement()) {
                EndElement endElement = evento.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (localName.equals("jugador")) {
                    jugador.setPosicion(posicion);
                    jugadores.add(jugador);
                    jugador = new Jugador();
                }
            }
        }

        System.out.println(jugadores);
        return jugadores;
    }

    public void escribirJugadores(ArrayList<Jugador> jugadores) {
        xmlEventWriter = XMLSTAXUtils.crearEventWritter(rutaArchivo, tipoValidacion);

        XMLSTAXUtils.addStartDocument(xmlEventWriter);
        XMLSTAXUtils.addStartElemento(xmlEventWriter, "jugadores");

        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);
            XMLSTAXUtils.addStartElemento(xmlEventWriter, j.getPosicion());
            XMLSTAXUtils.addAtributo(xmlEventWriter, "dorsal", String.valueOf(j.getDorsal()));

            XMLSTAXUtils.addStartElemento(xmlEventWriter, "nombreCompleto");
            XMLSTAXUtils.addTextoElemento(xmlEventWriter, j.getNombre());
            XMLSTAXUtils.addEndElement(xmlEventWriter, "nombreCompleto");

            XMLSTAXUtils.addEndElement(xmlEventWriter, j.getPosicion());

        }
        XMLSTAXUtils.addEndElement(xmlEventWriter, "jugadores");
        XMLSTAXUtils.addEndDocument(xmlEventWriter);

    }
}