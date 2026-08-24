package AD.Tema1Resumen.STAX;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.xml.sax.XMLReader;

import AD.Tema1Resumen.Clases.Corredor;
import AD.Tema1Resumen.Clases.Fondista;
import AD.Tema1Resumen.Clases.Puntuacion;
import AD.Tema1Resumen.Clases.Velocista;

public class CorredoresSTAX {
    private static Corredor corredor;
    private static Puntuacion puntuacion;
    private static String elementoActual = "";

    private static ArrayList<Puntuacion> puntuaciones = new ArrayList<>();
    private static ArrayList<Corredor> corredores = new ArrayList<>();

    public static ArrayList<Corredor> leerCorrredores(XMLStreamReader reader) {
        try {
            while (reader.hasNext()) {
                int evento = reader.next();

                switch (evento) {
                    case XMLStreamConstants.START_DOCUMENT:
                        System.out.println("Se comienza a leer el documento");
                        break;

                    case XMLStreamConstants.END_DOCUMENT:
                        System.out.println("Se termina a leer el documento");
                        break;

                    case XMLStreamConstants.START_ELEMENT:
                        elementoActual = reader.getLocalName();
                        System.out.println("Se comienza a leer el elemento: " + reader.getLocalName());

                        switch (reader.getLocalName()) {
                            case "velocista":
                                corredor = new Velocista();
                                getAtributosCorredor(reader);
                                break;
                            case "fondista":
                                corredor = new Fondista();
                                getAtributosCorredor(reader);
                                break;
                            case "puntuacion":
                                puntuacion = new Puntuacion();
                                puntuacion.setAnio(Integer.parseInt(reader.getAttributeValue(null, "anio")));
                                System.out.println("** Puntuacion empieza a leerse");
                            default:
                                break;
                        }

                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        elementoActual = reader.getLocalName();

                        switch (elementoActual) {
                            case "fondista":
                                Fondista fondista = (Fondista) corredor;
                                fondista.setPuntuaciones(new ArrayList<>(puntuaciones));
                                corredores.add(fondista);
                                corredor = null;
                                puntuaciones.clear();
                                break;
                            case "velocista":
                                Velocista velocista = (Velocista) corredor;
                                velocista.setPuntuaciones(new ArrayList<>(puntuaciones));
                                System.out.println("** Set puntuaciones");
                                corredores.add(velocista);
                                corredor = null;
                                puntuaciones.clear();
                                break;
                            case "puntuacion":
                                puntuaciones.add(puntuacion);
                                System.out.println("** Puntuacion termina a leerse");
                                System.out.println("** Puntuaciones: " + puntuaciones);
                                break;

                            default:
                                break;
                        }

                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String texto = reader.getText().trim();
                        switch (elementoActual) {
                            case "nombre":
                                if (!texto.isEmpty()) {
                                    corredor.setNombre(texto);
                                }
                                break;
                            case "puntuacion":
                                if (!texto.isEmpty()) {
                                    System.out.println("** Puntuacion: " + texto);
                                    puntuacion.setPuntos(Float.valueOf(texto));
                                }
                                break;

                            default:
                                break;
                        }
                        break;

                    default:
                        break;
                }

            }

            return corredores;
        } catch (XMLStreamException e) {
            throw new ArithmeticException(e.toString());
        }
    }

    private static void getAtributosCorredor(XMLStreamReader reader) {
        corredor.setCodigo(reader.getAttributeValue(null, "codigo"));
        corredor.setDorsal(Integer.valueOf(reader.getAttributeValue(null, "dorsal")));
        corredor.setEquipo(reader.getAttributeValue(null, "equipo"));
    }
}
