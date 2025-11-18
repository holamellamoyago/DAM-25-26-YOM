package AD.Tema1.PersistenciaSTAX.Aplicacion;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import AD.Tema1.PersistenciaSTAX.Persistencia.ConfiguracionStAX;
import AD.Tema1.PersistenciaSTAX.Persistencia.GestorCorredores;
import AD.Tema1.PersistenciaSTAX.model.TipoValidacion;

public class PersistenciaSTAX {
    public static void main(String[] args) throws XMLStreamException {
        GestorCorredores gestorCorredores = new GestorCorredores("Archivos/Corredores.xml", TipoValidacion.NO_VALIDAR);
        gestorCorredores.leerCorredoresCursor();

    }
}
