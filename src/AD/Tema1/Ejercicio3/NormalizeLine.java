package AD.Tema1.Ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class NormalizeLine {

    public static void main(String[] args) {
        normalizarLinea("hola a camnción?!!");
    }

    public static List<String> normalizarLinea(String linea) {
        String limpia = linea.replaceAll("[\\p{Punct}]", " ");

        System.out.println(limpia);

        return new ArrayList<>();
    }
}
