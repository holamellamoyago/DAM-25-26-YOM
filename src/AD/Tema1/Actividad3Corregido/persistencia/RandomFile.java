package AD.Tema1.Actividad3Corregido.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import AD.Tema1.Actividad3Corregido.clases.Equipo;

public class RandomFile extends Archivo {

    public RandomFile(File ruta) {
        super(ruta);
    }

    String ruta;
    RandomAccessFile rdmFile = null;

    public boolean existeEquipo(){return false;};

    public boolean guardarEquipo(Equipo e){
        abrirArchivo();

        try {
            if (rdmFile.length() <= 0) {
                rdmFile.seek(0);

                rdmFile.writeInt(e.getIdEquipo());
                rdmFile.writeInt(e.getNumPatrocinadores());
                rdmFile.writeUTF(e.getNombre());
                rdmFile.writeBoolean(e.isBorrado());

                return true;

            } else {
                rdmFile.seek();
            }
        } catch (IOException o) {
            o.printStackTrace();
            return false;
        }

        cerrarArchivo();
    }

    public float cogerSiguientePosicion(){
        return 0; 
    }

    public float cogerUltimoEquipo(){
        return 0; 
    }


    @Override
    public void abrirArchivo() {
        try {
            rdmFile = new RandomAccessFile(new File(ruta), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerrarArchivo() {
        if (rdmFile != null) {
            try {
                rdmFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
