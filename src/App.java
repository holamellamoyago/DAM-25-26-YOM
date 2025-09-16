
import AD.Tema1.VisualizarContenido.excepciones.fileExceptions;
import AD.Tema1.VisualizarContenido.servicio.operacionesIO;

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
