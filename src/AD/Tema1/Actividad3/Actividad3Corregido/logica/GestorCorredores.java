package AD.Tema1.Actividad3.Actividad3Corregido.logica;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import AD.Tema1.Actividad3Corregido.clases.Corredor;
import AD.Tema1.Actividad3Corregido.clases.Equipo;
import AD.Tema1.Actividad3Corregido.persistencia.CorredorReader;
import AD.Tema1.Actividad3Corregido.persistencia.CorredorWrite;
import AD.Tema1.Actividad3Corregido.persistencia.RandomFile;

public class GestorCorredores {
    String rutaArchivo;

    public GestorCorredores(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarCorredor(Corredor c) {
        CorredorWrite write = new CorredorWrite(new File(rutaArchivo));
        CorredorReader reader = new CorredorReader(new File(rutaArchivo));
        write.abrirArchivo();
        reader.abrirArchivo();

        int ultDorsal = reader.obtenerultDorsal();
        c.setDorsal(ultDorsal + 1);

        if (reader.obtenerTodosCorredores().contains(c)) {
            System.out.println("Ya existe el corredor en el archivo");
            return;
        }

        if (write.escribir(c)) {
            System.out.println("Corredor guardado: " + c.getNombre());
        }
        write.cerrarArchivo();
    }

    public void anhadirEquipo(int id, Equipo equipo) {
        CorredorReader reader = new CorredorReader(new File(rutaArchivo));
        CorredorWrite writter = new CorredorWrite(new File(rutaArchivo));
        reader.abrirArchivo();
        writter.abrirArchivo();

        ArrayList<Corredor> corredores = reader.obtenerTodosCorredores();
        Corredor corredor; 

        for (int i = 0; i < corredores.size(); i++) {
            if (corredores.get(i).getDorsal() == id) {
                corredor = corredores.get(i);
                corredor.setEquipo(equipo.getIdEquipo());
                System.out.println("Se añadió el equipo( + " + equipo.getIdEquipo() + ") a " + corredor.getNombre());
            }
        }

        for (Corredor corredorI : corredores) {
            writter.borrarFichero();
            writter.escribir(corredorI);
        }

        reader.cerrarArchivo();
        writter.cerrarArchivo();

    }

    public ArrayList<Corredor> getCorredores() {
        CorredorReader reader = new CorredorReader(new File(rutaArchivo));
        return reader.obtenerTodosCorredores();
    }

    public void mostrarCorredoresEquipo(){
        RandomFile archivoEquipos = new RandomFile("equipos.dat");
        archivoEquipos.abrirArchivo();

        CorredorReader archivoCorredores = new CorredorReader(new File("corredores.dat"));
        archivoCorredores.abrirArchivo();

        Map<String, List<String>> mapa = new LinkedHashMap<>();
        List<String> errores = new ArrayList<>();

        Corredor c ; 
        while (c = archivoCorredores.leer() != null) {
            Equipo equipo = archivoEquipos.cogerEquipo(c.getEquipo());

            if (equipo != null && !equipo.isBorrado()) {
                String nombreEquipo = equipo.getNombre();

                mapa.computeIfAbsent(nombreEquipo, t -> new ArrayList<>().add(c.getNombre() + " (" + c.getClass().getSimpleName() + ")");)
            }
        }
        

    }

}
