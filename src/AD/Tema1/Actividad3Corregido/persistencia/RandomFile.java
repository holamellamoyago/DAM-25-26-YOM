package AD.Tema1.Actividad3Corregido.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import AD.Tema1.Actividad3Corregido.clases.Equipo;
import AD.Tema1.Actividad3Corregido.clases.Patrocinador;

public class RandomFile extends Archivo {

    RandomAccessFile rdmFile;
    final int ESPACIO_INDIVIDUAL = 200;

    public RandomFile(String ruta) {
        super(new File(ruta));
    }

    public boolean existeEquipo() {
        return false;
    };

    public boolean guardarEquipo(Equipo e) {
        try {
            // Si esta vacio:
            if (rdmFile.length() <= 0) {
                rdmFile.seek(0);

                // Que no supere los 200 bytes
                if (calcularTamanhoEquipo(e)) {
                    // Guarda cada campo
                    guardarCamposEquipo(e);
                }

            } else {
                // Calcula la siguiente posicion, SI LA POSICION ES 2 la 3 empieza en 200
                rdmFile.seek((cogerUltPosicion()) * ESPACIO_INDIVIDUAL);

                e.setIdEquipo(cogerUltPosicion() + 1);
                if (calcularTamanhoEquipo(e)) {
                    // Guarda cada campo
                    guardarCamposEquipo(e);
                }
            }

        } catch (IOException o) {
            o.printStackTrace();
        }

        return true;

    }

    private void guardarCamposEquipo(Equipo e) throws IOException {
        rdmFile.writeInt(e.getIdEquipo());
        rdmFile.writeInt(e.getNumPatrocinadores());
        System.out.println("Los n " + e.getNumPatrocinadores());
        rdmFile.writeUTF(e.getNombre());
        rdmFile.writeBoolean(e.isBorrado());

        for (Patrocinador p : e.getPatrocinadores()) {
            rdmFile.writeUTF(p.getNombre());
            rdmFile.writeFloat(p.getDonacion());
            rdmFile.writeUTF(String.valueOf(p.getFechaInicio()));
        }
    }

    private Equipo leerCampos() {
        try {
            int idEquipo = rdmFile.readInt();
            int numPatrocinadores = rdmFile.readInt();
            String nombre = rdmFile.readUTF();
            boolean borrado = rdmFile.readBoolean();

            if (borrado) {
                throw new ArithmeticException("El equipo indicado esta borrado");
            }

            Set<Patrocinador> patrocinadores = new TreeSet<>();
            for (int i = 0; i < numPatrocinadores; i++) {

                String nombrePAtrocinador = rdmFile.readUTF();
                Float donacionPatrocinador = rdmFile.readFloat();
                LocalDate date = LocalDate.parse(rdmFile.readUTF());

                Patrocinador pa = new Patrocinador(nombrePAtrocinador, donacionPatrocinador, date);
                patrocinadores.add(pa);
                System.out.println("Se leyó el patrocinador: " + pa);

            }

            Equipo e = new Equipo(nombre, patrocinadores);
            e.setIdEquipo(idEquipo);
            e.setNumPatrocinadores(patrocinadores.size());
            return e;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private boolean calcularTamanhoEquipo(Equipo e) throws UnsupportedEncodingException {
        int total = 0;
        total += e.getIdEquipo();
        total += e.getNumPatrocinadores();
        total += e.getNombre().getBytes("UTF-8").length + 2;

        for (Patrocinador patrocinador : e.getPatrocinadores()) {

            total += patrocinador.getDonacion();
            total += patrocinador.getFechaInicio().toString().getBytes("UTF-8").length + 2;
            total += patrocinador.getNombre().getBytes("UTF-8").length + 2;
        }

        if (total > ESPACIO_INDIVIDUAL) {
            return true;
        }

        return false;

    }

    @Override
    public void abrirArchivo() {
        try {
            rdmFile = new RandomAccessFile(archivo, "rw");
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

    public Equipo cogerEquipo(int id) {

        System.out.println("La id es: " + id);
        System.out.println("La ult pos " + cogerUltPosicion());

        if (id > cogerUltPosicion()) {
            throw new ArithmeticException("No existe ese equipo");
        }

        try {
            int posicionEquipo = id * ESPACIO_INDIVIDUAL;
            System.out.println("La pos es: " + posicionEquipo);

            rdmFile.seek(posicionEquipo);

            return leerCampos();

        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }

    }

    public int cogerUltPosicion() {
        try {
            return (int) Math.ceil((double) rdmFile.length() / ESPACIO_INDIVIDUAL);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
