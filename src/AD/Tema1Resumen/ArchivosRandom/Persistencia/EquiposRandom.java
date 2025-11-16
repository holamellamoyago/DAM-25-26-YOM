package AD.Tema1Resumen.ArchivosRandom.Persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import java.io.*;

import AD.Tema1Resumen.ArchivosRandom.Clases.AppendObjectOutputStream;
import AD.Tema1Resumen.ArchivosRandom.Clases.Archivo;
import AD.Tema1Resumen.Clases.Corredor;
import AD.Tema1Resumen.Clases.Equipo;
import AD.Tema1Resumen.Clases.Patrocinador;




public class EquiposRandom extends Archivo {
    RandomAccessFile randomFile;
    final private int TAMANHO_REGISTRO = 200;

    public EquiposRandom(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirarchivo() {
        try {
            randomFile = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerararchivo() {
        if (randomFile != null) {
            try {
                randomFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void guardarEquipo(Equipo equipo) {
        try {
            randomFile.seek(cogerSiguientePosicion());

            equipo.setIdEquipo(String.valueOf(cogerUltimoRegistro() + 1));

            if (leerEquipos().contains(equipo)) {
                System.out.println("No se añade al equipo debido a que ya existe");
                return;
            }

            if (comprobarExcendente(equipo) > TAMANHO_REGISTRO) {
                System.out.println("No se puede guardar el equipo debido a que excede el tamaño maximo");
                return;
            }

            guardarCamposEquipo(equipo);

            System.out.println("Se añadió el equipos " + equipo.getIdEquipo() + " en la posición "
                    + (cogerSiguientePosicion() - TAMANHO_REGISTRO));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Equipo> leerEquipos() {
        ArrayList<Equipo> equipos = new ArrayList<>();
        Equipo equipo;
        int i = 0;
        try {

            randomFile.seek(0);

            while ((equipo = leerCampos()) != null) {
                randomFile.seek(i * TAMANHO_REGISTRO);
                equipos.add(equipo);

                i++;
            }

            return equipos;

        } catch (IOException e) {
            throw new ArithmeticException(e.toString());
        }
    }

    private Equipo leerCampos() {
        try {
            int idEquipo = randomFile.readInt();
            int numPatrocinadores = randomFile.readInt();
            String nombre = randomFile.readUTF();
            boolean borrado = randomFile.readBoolean();

            if (borrado) {
                throw new ArithmeticException("El equipo indicado esta borrado");
            }

            Set<Patrocinador> patrocinadores = new TreeSet<>();
            for (int i = 0; i < numPatrocinadores; i++) {

                String nombrePAtrocinador = randomFile.readUTF();
                Float donacionPatrocinador = randomFile.readFloat();
                LocalDate date = LocalDate.parse(randomFile.readUTF());

                Patrocinador pa = new Patrocinador(nombrePAtrocinador, donacionPatrocinador, date);
                patrocinadores.add(pa);
                System.out.println("Se leyó el patrocinador: " + pa);

            }

            Equipo e = new Equipo(nombre, patrocinadores);
            e.setIdEquipo(String.valueOf(idEquipo));
            e.setNumPatrocinadores(patrocinadores.size());
            return e;

        } catch (IOException e) {
            return null;
        }

    }

    public int cogerUltimoRegistro() {
        return (int) Math.ceil((double) file.length() / TAMANHO_REGISTRO);
    }

    public long cogerSiguientePosicion() {
        boolean existe = existe() && file.length() > 0;

        if (!existe) {
            return 0;
        } else {
            return cogerUltimoRegistro() * TAMANHO_REGISTRO;
        }
    }

    private void guardarCamposEquipo(Equipo e) throws IOException {
        randomFile.writeUTF(e.getIdEquipo());
        randomFile.writeInt(e.getNumPatrocinadores());
        randomFile.writeUTF(e.getNombre());
        randomFile.writeBoolean(e.isBorrado());

        for (Patrocinador p : e.getPatrocinadores()) {
            randomFile.writeUTF(p.getNombre());
            randomFile.writeFloat(p.getDonacion());
            randomFile.writeUTF(String.valueOf(p.getFechaInicio()));
        }
    }

    private int comprobarExcendente(Equipo e) {
        int totalBytes = 0;

        totalBytes += e.getIdEquipo().length() + 2;
        totalBytes += e.getNombre().length() + 2;
        totalBytes += e.getNumPatrocinadores();

        for (Patrocinador patro : e.getPatrocinadores()) {
            totalBytes += patro.getDonacion();
            totalBytes += patro.getFechaInicio().toString().length() + 2;
            totalBytes += patro.getNombre().toString().length() + 2;
        }

        return totalBytes;
    }

    public Equipo cogerEquipoPorID(int id){

        try {
            randomFile.seek(id * TAMANHO_REGISTRO);
            return leerCampos();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
