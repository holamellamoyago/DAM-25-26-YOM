package AD.Tema1.VisualizarContenido.excepciones;

public class fileExceptions extends Exception {
    public static void noExiste(String e) {
        throw new ArithmeticException("No exixte el directorio");
    }
}
