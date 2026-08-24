package gestorDatRandom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Date;
import java.util.ArrayList;

public class DatRandomUtils {
    final static private int TAMANHO_REGISTRO = 100;

    /*
     * + abrir archivo
     * 
     * - leer alumno
     * - leer campos alumno
     * - guardar alumno
     * 
     * - cogerSiguientePosicion (seek)
     * - cogerUltimoRegistro (num / id)
     * 
     * 
     */

    public static RandomAccessFile abrirArchivoRandom(File file) throws ArchivoNoExistente {
        try {
            return new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            throw new ArchivoNoExistente();

        }
    }

    public static void guardarAlumno(RandomAccessFile rdmFile, Alumno alumno) throws IOException {
        alumno.setNumero(cogerUltimoRegistro(rdmFile) + 1);

        if (comprobarExcente(alumno)) {
            System.out.println("El alumno excede el tamaño");
            return;
        }

        rdmFile.seek(cogerSiguientePosicion(rdmFile));

        rdmFile.writeInt(alumno.getNumero());
        rdmFile.writeUTF(alumno.getNombre().getNombre());
        rdmFile.writeUTF(alumno.getNombre().getApellido1());
        rdmFile.writeUTF(alumno.getNombre().getApellido2());
        rdmFile.writeLong(alumno.getFechaNac().getTime());
        rdmFile.writeInt(alumno.getTelefono().size());

        for (int i = 0; i < alumno.getTelefono().size(); i++) {
            rdmFile.writeUTF(alumno.getTelefono().get(i));
        }

        System.out.println("Se añadió el " + alumno);

    }

    private static boolean comprobarExcente(Alumno alumno) {
        int bytes = 0;

        bytes += alumno.getNombre().toString().length() + 2;
        bytes += alumno.getNumero();
        bytes += alumno.getFechaNac().toString().length() + 2;
        bytes += alumno.getTelefono().size();

        for (String str : alumno.getTelefono()) {
            bytes += str.length() + 2;
        }

        if (bytes > TAMANHO_REGISTRO) {
            System.out.println(bytes);
            return true;
        }

        return false;
    }

    public static ArrayList<Alumno> leerAlumnos(RandomAccessFile rdmFile) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Alumno alumno;

        try {

            int i = 0;
            rdmFile.seek(0);

            while ((alumno = leerCamposAlumno(rdmFile)) != null) {
                alumnos.add(alumno);

                i++;
                rdmFile.seek(i * TAMANHO_REGISTRO);
            }

            return alumnos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Alumno leerCamposAlumno(RandomAccessFile rdmFile) throws IOException {

        try {
            int id = rdmFile.readInt();
            String nombre = rdmFile.readUTF();
            String apellido1 = rdmFile.readUTF();
            String apellido2 = rdmFile.readUTF();
            long fecha = rdmFile.readLong();

            int contadorTelefonos = rdmFile.readInt();
            ArrayList<String> numerosTlfn = new ArrayList<>();

            for (int i = 0; i < contadorTelefonos; i++) {
                numerosTlfn.add(rdmFile.readUTF());
            }

            Nombre nombreCompleto = new Nombre(nombre, apellido1, apellido2);
            Alumno al = new Alumno(id, nombreCompleto, new Date(fecha), numerosTlfn, false);
            return al;
        } catch (Exception e) {
            return null;
        }

    }

    public static long cogerSiguientePosicion(RandomAccessFile rdmFile) throws IOException {
        boolean existe = rdmFile.length() > 0;

        if (!existe) {
            return 0;
        } else {
            return cogerUltimoRegistro(rdmFile) * TAMANHO_REGISTRO;
        }
    }

    public static int cogerUltimoRegistro(RandomAccessFile rdmFile) throws IOException {
        return (int) Math.ceil((double) rdmFile.length() / TAMANHO_REGISTRO);
    }

    public static Alumno cogerAlumnoPorNumero(RandomAccessFile randomFile, int numero) {

        try {
            randomFile.seek((numero - 1) * TAMANHO_REGISTRO);
            return leerCamposAlumno(randomFile);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
