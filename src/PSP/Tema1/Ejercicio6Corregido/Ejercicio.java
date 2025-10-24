package PSP.Tema1.Ejercicio6Corregido;

public class Ejercicio {
    public static void main(String[] args) {
        String rutaArchivo = "./Archivos/conversacion.txt";
        Archivo archivo;

        try {
            archivo = new Archivo(rutaArchivo);
        } catch (Exception e) {
            System.out.println("Archivo no encontrado");
        }

        
    }
}
