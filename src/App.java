import AD.Tema1.Actividad1.excepciones.fileExceptions;
import AD.Tema1.Actividad1.servicio.operacionesIO;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // operacionesIO.visualizarContenido(".");
            // operacionesIO.recorrerRecursivo("src/");
            operacionesIO.filtrarPorExtension("src/", ".txt");

        } catch (Exception e) {
            fileExceptions.noExiste(e.toString());
        }
    }
}
