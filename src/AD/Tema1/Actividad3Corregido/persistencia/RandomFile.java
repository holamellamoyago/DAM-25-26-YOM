package AD.Tema1.Actividad3Corregido.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import AD.Tema1.Actividad3Corregido.clases.Equipo;
import AD.Tema1.Actividad3Corregido.clases.Patrocinador;

public class RandomFile extends Archivo {

    final int ESPACIO_INDIVIDUAL = 200;

    public RandomFile(File ruta) {
        super(ruta);
    }

    String ruta;
    RandomAccessFile rdmFile = null;

    public boolean existeEquipo() {
        return false;
    };

    public boolean guardarEquipo(Equipo e) {
        try {
            // Si no esta vacio:
            if (rdmFile.length() <= 0) {
                rdmFile.seek(0);

                // Que no supere los 200 bytes
                if (calcularTamanhoEquipo(e)) {
                    // Guarda cada campo 
                    guardarCamposEquipo(e);
                }


                
            } else {
                // Calcula la siguiente posicion
                rdmFile.seek(cogerSiguientePosicion());
            }
            
        } catch (IOException o) {
            o.printStackTrace();
            return false;
        }
        
        return true;
        
    }

    public long cogerSiguientePosicion() throws IOException {
        return (int) Math.ceil((double) rdmFile.length() / ESPACIO_INDIVIDUAL);
    }

    public int cogerIdUltimoEquipo() throws IOException {
        return String.valueOf(cogerSiguientePosicion()).toCharArray()[0];
    }

    private void guardarCamposEquipo(Equipo e) throws IOException {
        rdmFile.writeInt(e.getIdEquipo());
        rdmFile.writeInt(e.getNumPatrocinadores());
        rdmFile.writeUTF(e.getNombre());
        rdmFile.writeBoolean(e.isBorrado());
    }

    private boolean calcularTamanhoEquipo(Equipo e) throws UnsupportedEncodingException{
        int total = 0;
        total += e.getIdEquipo();
        total += e.getNumPatrocinadores();
        total += e.getNombre().getBytes("UTF-8").length +2;
        
        for (Patrocinador patrocinador : e.getPatrocinadores()) {

            total += patrocinador.getDonacion();
            total += patrocinador.getFechaInicio().toString().getBytes("UTF-8").length +2;
            total += patrocinador.getNombre().getBytes("UTF-8").length+2;
        }

        if (total > ESPACIO_INDIVIDUAL) {
            return true;
        }

        return false;

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
