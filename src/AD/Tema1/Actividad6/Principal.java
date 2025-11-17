package AD.Tema1.Actividad6;

public class Principal {
    public static void main(String[] args) {
        GestorCorredoresJAXB gestorCorredores;

        GestorEquiposJAXB gestorEquipos = new GestorEquiposJAXB("Archivos/Equipos.xml");
        gestorEquipos.mostrarTodosLosEquipos();

    }
}
