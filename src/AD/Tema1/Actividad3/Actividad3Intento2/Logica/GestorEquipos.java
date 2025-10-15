package AD.Tema1.Actividad3.Actividad3Intento2.Logica;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.Equipo;
import AD.Tema1.Actividad3.Actividad3Intento2.Persistencia.EquiposRandom;

public class GestorEquipos {
    private String ruta;

    public GestorEquipos(String ruta) {
        this.ruta = ruta;
    }

    public void guardarEquipo(Equipo e){
        EquiposRandom gestorEquipos = new EquiposRandom(ruta);
        gestorEquipos.abrirarchivo();

        
    }
}
