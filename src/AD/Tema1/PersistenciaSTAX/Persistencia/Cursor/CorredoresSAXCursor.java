package AD.Tema1.PersistenciaSTAX.Persistencia.Cursor;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import AD.Tema1.PersistenciaSTAX.model.Corredor;
import AD.Tema1.PersistenciaSTAX.model.Fondista;
import AD.Tema1.PersistenciaSTAX.model.Velocista;

public class CorredoresSAXCursor {
    private static String etiquetaActual = "";
    private static Corredor corredor;

    public static void leerCorredor(XMLStreamReader reader) throws XMLStreamException {
        if (reader == null)
            return;

        System.out.println("INICIO PROCESAMIENTO");

        while (reader.hasNext()) {
            int next = reader.next();

            switch (next) {
                case XMLStreamConstants.START_ELEMENT:
                    etiquetaActual = reader.getLocalName();

                    if (etiquetaActual.equals("velocista") || etiquetaActual.equals("fondista")) {
                        crearCorredor(reader);
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT: {
                    break;
                }
                case XMLStreamConstants.START_DOCUMENT: {
                    break;
                }
                case XMLStreamConstants.END_DOCUMENT: {
                    break;
                }

                default:
                    break;
            }
        }
    }

    private static Corredor crearCorredor(XMLStreamReader reader) {
        if (etiquetaActual.equals("velocista")) {
            corredor = new Velocista();
        }

        if (etiquetaActual.equals("fondista")) {
            
        }


        String codigo = reader.getAttributeValue(null, "codigo");
        System.out.println("Velocista: " + codigo);

        return null;
    }
}
