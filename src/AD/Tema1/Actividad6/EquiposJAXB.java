package AD.Tema1.Actividad6;

import AD.Tema1.Actividad6.Clases.Equipos;

public class EquiposJAXB {
    public static Equipos leerEquipos(String rutaXML) {
        return XmlJaxbUtils.unmarshall(Equipos.class, rutaXML);
    }
}
