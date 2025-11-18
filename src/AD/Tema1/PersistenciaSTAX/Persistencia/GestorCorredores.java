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
    XMLStreamReader reader;

    public GestorCorredores(String rutaArchivo, TipoValidacion tipoValidacion) {
        try {
            this.rutaArchivo = rutaArchivo;
            XMLInputFactory factory = ConfiguracionStAX.configurarYCrearReader(rutaArchivo, tipoValidacion);
			reader = factory.createXMLStreamReader(new FileInputStream(new File(rutaArchivo)));
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
    }

    public void leerCorredoresCursor() {
        try {
            CorredoresSAXCursor.leerCorredor(reader);
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }


}
