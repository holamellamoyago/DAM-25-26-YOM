package AD.Tema1.VisualizarContenido.servicio;

import java.io.File;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class operacionesIO {
    public static String visualizarContenido(String ruta) {
        File archivo = new File(ruta);
        String txt = "";

        if (archivo.isDirectory()) {
            txt += "<DIR> ";
        } else {
            txt += "<FICHERO > " + archivo.getTotalSpace() + "Kbytes ";
        }

        Date fecha3 = new Date(archivo.lastModified());
        DateFormat dateFormat = DateFormat.getDateInstance(3);
        String fechaFormateada = dateFormat.format(fecha3);
        txt += fechaFormateada;

        return txt;
    }
}
