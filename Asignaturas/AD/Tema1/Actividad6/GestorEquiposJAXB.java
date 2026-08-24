package AD.Tema1.Actividad6;

public class GestorEquiposJAXB {
    private String rutaArchivo;

    public GestorEquiposJAXB(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void mostrarTodosLosEquipos(){
        System.out.println(EquiposJAXB.leerEquipos(rutaArchivo));
    }
}
