package gestorSTAX;

import java.util.ArrayList;

import javax.xml.stream.*;

import clases.Jugador;
import clases.TipoValidacion;

public class GestorJugadoresCursorSTAX {
    private XMLStreamReader xmlStreamReader;

    private String rutaArchivo;
    private TipoValidacion tipoValidacion;

    public GestorJugadoresCursorSTAX(String rutaArchivo, TipoValidacion tipoValidacion) {
        this.rutaArchivo = rutaArchivo;
        this.tipoValidacion = tipoValidacion;
    }

    public ArrayList<Jugador> leerJugadores() throws XMLStreamException {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador = new Jugador();
        String posicion = "";

        xmlStreamReader = XMLSTAXUtils.crearStreamReader(rutaArchivo, tipoValidacion);

        int evento;
        while (xmlStreamReader.hasNext()) {
            evento = xmlStreamReader.next();

            String localName;
            switch (evento) {
                case XMLStreamConstants.START_ELEMENT:
                    localName = XMLSTAXUtils.obtenerNombreEtiqueta(xmlStreamReader);
                    switch (localName) {
                        case "jugador":
                            jugador.setDorsal(Integer.valueOf(XMLSTAXUtils.leerAtributo(xmlStreamReader, "dorsal")));
                            break;
                        case "nombre":
                            jugador.setNombre(XMLSTAXUtils.leerTexto(xmlStreamReader));
                            // jugador.setNombre(xmlStreamReader);
                            break;
                        case "nacionalidad":
                            jugador.setNacionalidad(XMLSTAXUtils.leerTexto(xmlStreamReader));
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
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    localName = XMLSTAXUtils.obtenerNombreEtiqueta(xmlStreamReader);
                    switch (localName) {
                        case "jugador":
                            jugador.setPosicion(posicion);
                            jugadores.add(jugador);
                            jugador = new Jugador();
                            break;
                        default:
                            break;
                    }

                default:
                    break;
            }

        }

        System.out.println(jugadores);
        return jugadores;
    }
}
