package AD.Tema1.Actividad6;

import AD.Tema1.Actividad6.Clases.Corredores;

public class CorredoresJAXB {
    public static Corredores leerCorredores(String rutaArchivo) {
        return XmlJaxbUtils.unmarshall(Corredores.class, rutaArchivo);
    }
}
