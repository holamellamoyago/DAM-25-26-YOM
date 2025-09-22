package AD.Tema1.VisualizarContenido.servicio;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import AD.Tema1.VisualizarContenido.excepciones.fileExceptions;

public class operacionesIO {

    public static void main(String[] args) {
        // recorrerRecursivo("src/");
        System.out.println(filtrarPorExtensionYOrdenar("src/", ".java", false));
    }

    public static File[] filtrarPorExtensionYOrdenar(String ruta, String
    extension, boolean descendente){
        File[] archivos = filtrarPorExtension(ruta, extension);
        List<File> archivosOrdenados = new ArrayList<>(List.of(archivos));
        
        if (descendente) {
            archivosOrdenados.sort((o1, o2) -> o1.compareTo(o2));
        }

        System.out.println(archivosOrdenados);

        return archivosOrdenados.toArray(new File[0]);

    }

    public static File[] filtrarPorExtension(String ruta, String ext) {
        File directorio = new File(ruta);

        FilenameFilter filter = (dir, name) -> name.endsWith(ext);
        File[] archivos = directorio.listFiles(filter);

        for (File file : archivos) {
            System.out.println(mostrarInformacion(file));
        }

        return archivos;
    }

    public static void visualizarContenido(String ruta) {
        File[] archivos = new File(ruta).listFiles();

        for (File file : archivos) {
            mostrarInformacion(file);
        }

    }

    public static void recorrerRecursivo(String ruta) {
        File directorio = new File(ruta);
        File[] archivos = directorio.listFiles();

        for (int i = 0; i < archivos.length; i++) {
            File archivo = archivos[i];

            if (!archivo.isFile()) {
                int guiones = 2;

                mostrarContenidoCarpeta(directorio, archivo, guiones);
            } else {
                System.out.println(mostrarInformacion(archivo));
            }

        }

    }

    private static void mostrarContenidoCarpeta(File directorio, File archivo, int guiones) {

        File directorioAntiguo;

        directorioAntiguo = directorio;
        directorio = new File(archivo.getAbsolutePath());
        guiones++;

        File[] nuevosArchivos = directorio.listFiles();

        for (int i = 0; i < nuevosArchivos.length; i++) {
            String txt = retornarGuiones(guiones);

            if (!nuevosArchivos[i].isFile()) {
                txt += nuevosArchivos[i].getName();
                mostrarContenidoCarpeta(directorio, nuevosArchivos[i], guiones += 2);
            } else {
                txt += mostrarInformacion(nuevosArchivos[i]);
            }

            System.out.println(txt);
        }

        directorio = directorioAntiguo;
    }

    private static String retornarGuiones(int i) {
        String txt = "";
        for (int j = 0; j < i; j++) {
            txt += "-";
        }

        return txt;
    }

    private static String mostrarInformacion(File file) {

        String txt = "";

        txt += file.getName() + " ";

        if (file.isDirectory()) {
            txt += "<DIR> ";
        } else {
            txt += "<FICHERO> " + file.getTotalSpace() + "Kbytes ";
        }

        Date fecha3 = new Date(file.lastModified());
        DateFormat dateFormat = DateFormat.getDateInstance(3);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        String fechaFormateada = formato.format(fecha3);
        txt += fechaFormateada;

        return txt;

    }
}
