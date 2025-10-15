package AD.Tema1.Actividad3.Actividad3Intento2.Persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.Equipo;
import AD.Tema1.Actividad3.Actividad3Intento2.Clases.Patrocinador;

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

    public void guardarEquipo(Equipo e) throws IOException {
        randomFile.seek(cogerSiguientePosicion());

        if (comprobarExcendente(e) > TAMANHO_REGISTRO) {
            System.out.println("No se puede guardar el equipo debido a que excede el tamaño maximo");
            return;
        }

        guardarCamposEquipo(e);
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
            e.setIdEquipo(idEquipo);
            e.setNumPatrocinadores(patrocinadores.size());
            return e;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private long cogerUltimoRegistro() {
        return (int) Math.ceil((double) file.length() / TAMANHO_REGISTRO);
    }

    private long cogerSiguientePosicion() {
        boolean existe = existe() && file.length() > 0;

        if (!existe) {
            return 0;
        } else {
            return cogerUltimoRegistro() * TAMANHO_REGISTRO;
        }
    }

    private void guardarCamposEquipo(Equipo e) throws IOException {
        randomFile.writeInt(e.getIdEquipo());
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

        totalBytes += e.getIdEquipo();
        totalBytes += e.getNombre().length() + 2;
        totalBytes += e.getNumPatrocinadores();

        for (Patrocinador patro : e.getPatrocinadores()) {
            totalBytes += patro.getDonacion();
            totalBytes += patro.getFechaInicio().toString().length() + 2;
            totalBytes += patro.getNombre().toString().length() + 2;
        }

        return totalBytes;
    }

}
