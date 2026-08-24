package AD.Tema1.Actividad7.Persistencia;

public class GestorRegistro {
    String rutaArchivo;

    public GestorRegistro(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void leerRegistros() {
        System.out.println(RegistroJAXB.leerRegistros(rutaArchivo));
    }

}
