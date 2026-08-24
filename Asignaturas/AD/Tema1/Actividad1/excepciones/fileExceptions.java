package AD.Tema1.Actividad1.excepciones;

public class fileExceptions extends Exception {
    public static void noExiste(String e) {
        throw new ArithmeticException("No exixte el directorio");
    }
}
