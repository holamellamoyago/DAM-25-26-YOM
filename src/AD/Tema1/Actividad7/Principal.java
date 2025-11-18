package AD.Tema1.Actividad7;

import AD.Tema1.Actividad7.Persistencia.GestorRegistro;

public class Principal {
    public static void main(String[] args) {
        GestorRegistro gestorRegistro = new GestorRegistro("Archivos/Registro.xml");
        gestorRegistro.leerRegistros();
    }
}
