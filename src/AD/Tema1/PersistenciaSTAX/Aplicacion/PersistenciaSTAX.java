package AD.Tema1.PersistenciaSTAX.Aplicacion;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import AD.Tema1.PersistenciaSTAX.Persistencia.ConfiguracionStAX;
import AD.Tema1.PersistenciaSTAX.Persistencia.GestorCorredores;

public class PersistenciaSTAX {
    public static void main(String[] args) throws XMLStreamException {
        GestorCorredores gestorCorredores = new GestorCorredores("Archivos/Corredores.xml");
        gestorCorredores.leerCorredoresCursor();
        
    }
}
