package AD.Tema1.Actividad6;

public class Principal {
    public static void main(String[] args) {
        GestorCorredoresJAXB gestorCorredores = new GestorCorredoresJAXB("Archivos/corredores.xml");
        gestorCorredores.getCorredores();

        // GestorEquiposJAXB gestorEquipos = new
        // GestorEquiposJAXB("Archivos/Equipos.xml");
        // gestorEquipos.mostrarTodosLosEquipos();

    }
}
