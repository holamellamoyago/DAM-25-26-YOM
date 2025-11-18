package AD.Tema1.Actividad7.Persistencia;


import AD.Tema1.Actividad7.Clases.Registro;
import AD.Tema1.Actividad7.Clases.Registros;
import jakarta.xml.bind.annotation.*;


public class RegistroJAXB {
    public static Registro leerRegistros(String rutaArchivo) {
        return XMLJAXBUtils.unmarshall(Registro.class, rutaArchivo);
    }
}
