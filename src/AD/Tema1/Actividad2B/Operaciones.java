package AD.Tema1.Actividad2B;

public class Operaciones {
    public static void main(String[] args) {
        String[] ficheros = {"text.txt", "text2.txt"} ;

        for (String fichero : ficheros) {
            contarLineas(fichero);
        }
    }

    private static void contarLineas(String ruta){
        lecturaTexto entrada = new lecturaTexto(ruta);
        entrada.abrirFichero();

        int contador = 0;

        while (entrada.leerLinea() != null) {
            contador++;
        }

        System.out.println(contador);
    }
}
