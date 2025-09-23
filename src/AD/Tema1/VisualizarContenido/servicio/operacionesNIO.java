package AD.Tema1.VisualizarContenido.servicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/*
 * Para cada Walk hay que poner un try que analice si 
 *  Files.isRegularFile && 
 *  Files.isreadable
 */

public class operacionesNIO {
    public static void main(String[] args) {
        // visualizarContenido("src");
        // recorrerRecursivo("src");
        // filtrarPorExtension("src", ".java");
        // recorrerRecursivofuncional("src");
        // filtrarPorExtensionFuncional("src/", ".txt");
        // filtrarPorSubcadena("src/", "xt");
        filtrarPorExtensionYOrdenar("src/", ".txt");

        // filtrarPorExtensionRecursivoFuncional(".", ".txt");
    }

    private static void copiarArchivo(String origen, String destino) {
        Path archivo = Paths.get(origen);

        if (Files.isDirectory(archivo)) {
            throw new ArithmeticException("aa");
        }
        ;

    }

    public static void filtrarPorExtensionYOrdenar(String ruta, String ext) {
        Path dir = Paths.get(ruta);

        try (Stream<Path> stream = Files.list(dir)) {

            Stream<Path> filtrados = stream.filter(t -> t.getFileName().toString().endsWith(ext));
            filtrados.forEach(t -> {
                System.out.println(t.getFileName());
            });


        } catch (Exception e) {
            // TODO: handle exception
        }

        // El segundo parámetro de DirectoryStream es de comodines
        // try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*" + ext))
        // {

        // // Comparator<Path> comp = Comparator.comparing(t -> t.get );
        // // Comparator<Path> comp = Comparator.comparing(t -> t.getFileName().)

        // // Comparator<Path> comp1 = Comparator.comparing((p1, p2) -> p1. )

        // Comparator<Path> comp = new Comparator<Path>() {
        // @Override
        // public int compare(Path o1, Path o2) {
        // return o1.getFileName().toString().compareTo(o2.getFileName().toString());
        // }
        // };

        // for (Path path : stream) {
        // System.out.println(path.getFileName().toString());
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

    }

    public static List<Path> filtrarPorSubcadena(String ruta, String subcadena) {
        Path dir = Paths.get(ruta);
        ArrayList<Path> totalPaths = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

            stream.forEach(t -> {
                if (t.getFileName().toString().contains(subcadena)) {
                    totalPaths.add(t);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(totalPaths);
        return totalPaths;
    }

    public static void filtrarPorExtensionFuncional(String ruta, String ext) {
        Path dir = Paths.get(ruta);

        // El segundo parámetro de DirectoryStream es de comodines
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*" + ext)) {
            for (Path path : stream) {
                System.out.println(path.getFileName().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<Path> filtrarPorExtensionRecursivoFuncional(String ruta, String ext) {
        ArrayList<Path> totalPaths = new ArrayList<>();
        Path dir = Paths.get(ruta);

        try (Stream<Path> files = Files.walk(dir)) {

            totalPaths.addAll(files.filter(t -> t.getFileName().toString().endsWith(ext)).toList());

            System.out.println(totalPaths);

            files.close();
        } catch (AccessDeniedException e) {
            System.err.println("Acceso denegado en " + dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalPaths;

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
                totalPaths.add(p);
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
