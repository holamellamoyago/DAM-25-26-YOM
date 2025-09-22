package AD.Tema1.VisualizarContenido.servicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import AD.Tema1.VisualizarContenido.excepciones.fileExceptions;

public class operacionesIO2 {
    public static void main(String[] args) {
        File ruta = new File("./src");

        // String[] archivos = ruta.list();

        // for (String string : archivos) {
        // System.out.println(string);
        // }

        // copiarArchivo("./src/text.txt", "./src/text2.txt");

        recorrerRecursivo("./src");
    }

    public static void copiarArchivo(String origen, String destino) {
        File archivoOrigen = new File(origen);
        validarArchivo(archivoOrigen);

        File archivoDestino = new File(destino);
        validarArchivo(archivoDestino);

        File directorioPadre = archivoDestino.getParentFile();
        // TODO Validar directorio )()

        try {
            InputStream in = new FileInputStream(archivoOrigen);
            OutputStream out = new FileOutputStream(archivoDestino);

            System.out.println("Información del archivo de origen: ");
            System.out.println(archivoOrigen.getParent());

            byte[] buffer = new byte[8192];
            int bytes;
            while ((bytes = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
            }

            System.out.println("\nInformación del archivo de destino: ");
            System.out.println(archivoDestino.getParent());

            in.close();
            out.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void moverArchivo(String origen, String destino) {
        File archivoOrigen = new File(origen);
        validarArchivo(archivoOrigen);

        File archivoDestino = new File(destino);
        validarArchivo(archivoDestino);

        copiarArchivo(archivoOrigen.getAbsolutePath(), archivoDestino.getAbsolutePath());

        // Solo es copiarlo y eliminar el origen

        if (!archivoOrigen.delete()) {
            // Si no se borra el archivo de origen eliminamos tambièn destino
            if (!archivoDestino.delete()) {
                throw new ArithmeticException("Error: nos e pudo mvoer");
            }
        }

    }

    public static void validarArchivo(File file) {
        if (!file.exists()) {
            throw new ArithmeticException("El archivo no existe");
        }
    }

    public static void copiarDirectorio(String origen, String destino) {

    }

    public static void recorrerRecursivo(String ruta) {
        File directorio = new File(ruta);
        File[] archivosDirectorio = directorio.listFiles();

        // ArrayList<File> archivosTotales = new ArrayList<>();

        int guiones = 0;
        String directorioAnterior = "";

        for (int i = 0; i < archivosDirectorio.length; i++) {
            if (archivosDirectorio[i].isDirectory()) {
                guiones += 2;
                recorrerCarpeta(archivosDirectorio[i].getAbsolutePath(), guiones);
            } else {
                System.out.println(retornarGuiones(guiones) + "Archivo: " + archivosDirectorio[i].getName());
            }
        }

    }

    public static void recorrerCarpeta(String ruta, int guiones) {
        File directorio = new File(ruta);
        File[] archivosDirectorio = directorio.listFiles();

        guiones += 2;

        for (int i = 0; i < archivosDirectorio.length; i++) {

            if (archivosDirectorio[i].isDirectory()) {
                System.out.println(retornarGuiones(guiones) + " Carpeta: " + archivosDirectorio[i].getName());

                recorrerCarpeta(archivosDirectorio[i].getAbsolutePath(), guiones);
                // directorioAnterior = directorio.getAbsolutePath();
                // directorio = new File(archivosDirectorio[i].getAbsolutePath());
            } else {
                System.out.println(retornarGuiones(i) + "Archivo: " + archivosDirectorio[i].getName());
            }
        }

    }

    private static String retornarGuiones(int i) {
        String txt = "";
        for (int j = 0; j < i; j++) {
            txt += "-";
        }

        return txt;
    }

    public static void crearDirectorio(File ruta) {
        if (!ruta.exists()) {
            ruta.mkdir();
        }
    }
}
