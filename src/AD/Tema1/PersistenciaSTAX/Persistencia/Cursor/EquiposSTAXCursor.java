package AD.Tema1.PersistenciaSTAX.Persistencia.Cursor;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.XMLReader;

import AD.Tema1.PersistenciaSTAX.Persistencia.Cursor.*;
import AD.Tema1.PersistenciaSTAX.model.TipoValidacion;

public class EquiposSTAXCursor {
    public static Map<String, Double> devolverMap(String rutaArchivo, TipoValidacion tipoValidacion) {
        XMLStreamReader reader = XMLSTAXUtilsCursor.cargarDocumentoSTAX(rutaArchivo, tipoValidacion);

        Map<String, Double> mapaDonaciones = new TreeMap<>();

        Double donacion = 0.0;
        String nombrePatrocinador = "";
        boolean esPatrocinador = false;

        try {
            while (reader.hasNext()) {
                int tipo = reader.next();

                switch (tipo) {
                    case XMLStreamConstants.START_ELEMENT:
                        String nombreEtiqueta = XMLSTAXUtilsCursor.obtenerNombreEtiqueta(reader);
                        switch (nombreEtiqueta) {
                            case "patrocinador":
                                nombrePatrocinador = "";
                                donacion = Double.valueOf(XMLSTAXUtilsCursor.leerAtributo(reader, "donacion"));
                                esPatrocinador = true;
                                break;
                            default:
                                break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (esPatrocinador) {
                            nombrePatrocinador += XMLSTAXUtilsCursor.leerTexto(reader);
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        String nombreEtiquetaEnd = XMLSTAXUtilsCursor.obtenerNombreEtiqueta(reader);
                        switch (nombreEtiquetaEnd) {
                            case "patrocinador":
                                esPatrocinador = false;
                                nombrePatrocinador = nombrePatrocinador.trim();
                                mapaDonaciones.merge(nombreEtiquetaEnd, donacion, Double::sum);
                                donacion = 0.0;
                                break;

                            default:
                                break;
                        }
                        break;

                    default:
                        break;
                }

            }
        } catch (XMLStreamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mapaDonaciones;
    }

    public static void escribirDoancionesTotales(String rutaSalida, Map<String, Double> mapaDonaciones) {
        XMLStreamWriter writter = XMLSTAXUtilsCursor.crearWritterSTAX("Archivos/cambios.xml");

        XMLSTAXUtilsCursor.addStartDocument(writter, "donaciones");

        for (Map.Entry<String, Double> entrada : mapaDonaciones.entrySet()) {
            String nombre = entrada.getKey();
            String total = String.format("%.1f", entrada.getValue());

            XMLSTAXUtilsCursor.addSaltoDeLinea(writter, 1);
            XMLSTAXUtilsCursor.addStartElemento(writter, "patrocinador");
            XMLSTAXUtilsCursor.addAtributo(writter, "totalDonado", total);
            XMLSTAXUtilsCursor.addTextoElemento(writter);
        }

        XMLSTAXUtilsCursor.addSaltoDeLinea(writter, 0);
        XMLSTAXUtilsCursor.addEndElement(writter);

        XMLSTAXUtilsCursor.addEndDocument(writter);

        if (writter != null) {
            writter.close();
        }
    }
}
