package AD.Tema1Resumen.STAX;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import AD.Tema1Resumen.Clases.TipoValidacion;

public class GestorCorredoresSTAX {
    XMLStreamReader reader;

    public GestorCorredoresSTAX(String rutaArchivo, TipoValidacion tipoValidacion) {
        XMLInputFactory factory = ConfiguracionSTAX.configurarSTAX(rutaArchivo, tipoValidacion);
        reader = ConfiguracionSTAX.crearStreamReader(factory, rutaArchivo);
    }

    public void leerCorredores() {
        System.out.println("\n" + CorredoresSTAX.leerCorrredores(reader));

    }

}
