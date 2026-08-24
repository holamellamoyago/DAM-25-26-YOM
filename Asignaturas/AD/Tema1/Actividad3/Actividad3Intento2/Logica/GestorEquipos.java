package AD.Tema1.Actividad3.Actividad3Intento2.Logica;

import java.util.ArrayList;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.Equipo;
import AD.Tema1.Actividad3.Actividad3Intento2.Persistencia.EquiposRandom;

public class GestorEquipos {
    EquiposRandom gestorEquipos;

    public GestorEquipos(String ruta) {
        this.gestorEquipos = new EquiposRandom(ruta);
    }

    public void guardarEquipo(Equipo e) {
        gestorEquipos.abrirarchivo();

        gestorEquipos.guardarEquipo(e);
        // System.out.println(gestorEquipos.leerEquipos());

        gestorEquipos.cerararchivo();

    }

    public Equipo cogerEquipoPorID(int id) {
        Equipo equipo = null;
        gestorEquipos.abrirarchivo();

        equipo = gestorEquipos.cogerEquipoPorID(id);

        gestorEquipos.cerararchivo();
        return equipo;
    }

    public ArrayList<Equipo> cogerTotalEquipos() {

        gestorEquipos.abrirarchivo();

        ArrayList<Equipo> equipos = gestorEquipos.leerEquipos();
        System.out.println(equipos);

        gestorEquipos.cerararchivo();

        return equipos;
    }
}
