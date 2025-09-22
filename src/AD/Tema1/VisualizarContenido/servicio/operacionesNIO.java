package AD.Tema1.VisualizarContenido.servicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class operacionesNIO {
    public static void main(String[] args) {
        // visualizarContenido("src");
        // recorrerRecursivo("src");
        // filtrarPorExtension("src", ".java");
        recorrerRecursivofuncional("src");
    }

    public static List<Path> recorrerRecursivofuncional(String ruta) {
        ArrayList<Path> totalPaths = new ArrayList<>();
        Path dir = Paths.get(ruta);

        try {
            Stream<Path> files = Files.walk(dir);

            files.forEach(p -> {
                String sangria = retornarGuiones(p.getNameCount());
                String frase = sangria + p.getFileName();

                System.out.println(frase);
            });

            files.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalPaths;
    }

    public static ArrayList<Path> filtrarPorExtension(String ruta, String ext) {
        ArrayList<Path> archivosFiltrados = new ArrayList<>();
        Path directorio = Paths.get(ruta);

        try {
            Stream<Path> files = Files.list(directorio);
            Iterator<Path> it = files.iterator();

            while (it.hasNext()) {
                Path archivo = it.next();
                String name = archivo.getFileName().toString();

                if (name.endsWith(ext)) {
                    System.out.println("Filtrado: " + name);
                }

                archivosFiltrados.add(archivo);
            }

            files.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return archivosFiltrados;
    }

    public static void visualizarContenido(String ruta) {
        Path dir = Paths.get(ruta);

        // try {
        // DirectoryStream<Path> stream = Files.newDirectoryStream(dir);

        // for (Path path : stream) {
        // System.out.println(mostrarInformacion(path.toFile()));
        // }

        // stream.close();

        // } catch (Exception e) {
        // System.out.println(e);
        // }

        // Programación funcional

        try {
            Stream<Path> s = Files.list(dir);
            s.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Path> recorrerRecursivo(String ruta) {
        ArrayList<Path> totalPaths = new ArrayList<>();
        Path dir = Paths.get(ruta);

        try {
            Stream<Path> files = Files.walk(dir);

            Iterator<Path> it = files.iterator();

            while (it.hasNext()) {
                Path path = it.next();
                int guionCount = path.getNameCount();
                File file = path.toFile();

                System.out.println(retornarGuiones(guionCount) + " " + mostrarInformacion(file));

                totalPaths.add(path);
            }

            files.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalPaths;
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

            Date fecha3 = new Date(file.lastModified());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

            String fechaFormateada = formato.format(fecha3);
            txt += fechaFormateada;
        }

        DateFormat dateFormat = DateFormat.getDateInstance(3);

        return txt;

    }
}
