package AD.Tema1.PersistenciaSTAX.Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.xml.sax.XMLReader;

import AD.Tema1.PersistenciaSTAX.Persistencia.Cursor.CorredoresSAXCursor;
import AD.Tema1.PersistenciaSTAX.model.TipoValidacion;

public class GestorCorredores {
    String rutaArchivo;

    public GestorCorredores(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void leerCorredoresCursor() {
        try {
            XMLInputFactory factory = ConfiguracionStAX.configurarYCrearReader("Archivos/Corredores.xml", TipoValidacion.NO_VALIDAR);
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(rutaArchivo)));
            CorredoresSAXCursor.leerCorredor(reader);
            

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

    }


}
