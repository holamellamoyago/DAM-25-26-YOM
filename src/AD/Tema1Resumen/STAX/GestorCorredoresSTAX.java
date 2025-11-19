package AD.Tema1Resumen.STAX;

import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import AD.Tema1Resumen.Clases.*;
import AD.Tema1Resumen.Clases.TipoValidacion;
import AD.Tema1Resumen.Clases.Velocista;

public class GestorCorredoresSTAX {

    private String rutaArchivo;
    private TipoValidacion tipoValidacion;

    XMLStreamReader reader;
    XMLStreamWriter writter;

    public GestorCorredoresSTAX(String rutaArchivo, TipoValidacion tipoValidacion) {
        this.rutaArchivo = rutaArchivo;
        this.tipoValidacion = tipoValidacion;
    }

    public ArrayList<Corredor> leerCorredores() {
        if (reader == null) {
            XMLInputFactory factory = ConfiguracionSTAX.crearInputFactory(rutaArchivo, tipoValidacion);
            reader = ConfiguracionSTAX.crearStreamReader(factory, rutaArchivo);

        }

        return CorredoresSTAX.leerCorrredores(reader);
    }

    public void escribirCorredores(ArrayList<Corredor> corredores) {
        XMLOutputFactory outputFactory;
        if (writter == null) {
            outputFactory = XMLOutputFactory.newInstance();
            writter = ConfiguracionSTAX.crearStreamWritter(outputFactory, rutaArchivo);
        }

        try {
            writter.writeStartDocument("UTF-8", "1.0");
            writter.writeStartElement("corredores"); // ← Abrir elemento con contenido

            for (Corredor corredor : corredores) {
                if (corredor instanceof Velocista) {
                    escribirVelocista((Velocista) corredor);
                } else if (corredor instanceof Fondista) {
                    // escribirFondista((Fondista) corredor);
                }
            }

            writter.writeEndElement(); // ← Cerrar <corredores>
            writter.writeEndDocument();
            writter.close(); // ← Cerrar el writer
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    // Métodos auxiliares para escribir cada tipo
    private void escribirVelocista(Velocista v) throws XMLStreamException {
        writter.writeStartElement("velocista");
        writter.writeAttribute("codigo", v.getCodigo());
        writter.writeAttribute("dorsal", String.valueOf(v.getDorsal()));
        writter.writeAttribute("equipo", v.getEquipo());

        writter.writeStartElement("nombre");
        writter.writeCharacters(v.getNombre());
        writter.writeEndElement();

        // Agrega más elementos: fecha_nacimiento, velocidad_media, historial, etc.
        writter.writeEndElement();
    }

    private void escribirFondista(Fondista f) throws XMLStreamException {
        // Similar a escribirVelocista, pero con distancia_max
    }

}
