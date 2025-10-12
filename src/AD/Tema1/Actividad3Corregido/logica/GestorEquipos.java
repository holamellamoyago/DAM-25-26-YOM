package AD.Tema1.Actividad3Corregido.logica;

import java.io.File;

import AD.Tema1.Actividad3Corregido.clases.Equipo;
import AD.Tema1.Actividad3Corregido.persistencia.RandomFile;

public class GestorEquipos {
    RandomFile rdmFile;

    public GestorEquipos(String ruta) {
        this.rdmFile = new RandomFile(ruta);
    }

    public void guardarEquipo(Equipo e) {

        rdmFile.abrirArchivo();

        // e.setIdEquipo(randomFile.cogerIdUltimoEquipo()-1);

        rdmFile.guardarEquipo(e);

        rdmFile.cerrarArchivo();
    }

    public Equipo cogerEquipo(int id) {
        Equipo equipo;
        rdmFile.abrirArchivo();
        equipo = rdmFile.cogerEquipo(id);
        rdmFile.cerrarArchivo();

        return equipo;
    }

    public void borrarArchivo(){
        rdmFile.borrarFichero();
    }

}
