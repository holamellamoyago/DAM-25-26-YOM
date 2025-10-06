package AD.Tema1.Actividad3Corregido.logica;

import java.io.File;

import AD.Tema1.Actividad3.dominio.Corredor;
import AD.Tema1.Actividad3.dominio.Fondista;
import AD.Tema1.Actividad3.dominio.Velocista;
import AD.Tema1.Actividad3Corregido.persistencia.CorredorReader;
import AD.Tema1.Actividad3Corregido.persistencia.CorredorWrite;

public class GestorCorredores {
    String rutaArchivo;

    public GestorCorredores(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarCorredor(Corredor c) {
        CorredorWrite write = new CorredorWrite(new File(rutaArchivo));
        CorredorReader reader = new CorredorReader(new File(rutaArchivo));

        int ultDorsal = reader.obtenerultDorsal();
        c.setDorsal(ultDorsal + 1);

        write.abrirArchivo();
        if (write.escribir(c)) {
            System.out.println("Corredor guardado: "  + c.getNombre());
        }
        write.cerrarArchivo();
    }

    public void mostrar(Corredor c ){
        String tipoCorredor = "";

        if (c instanceof Fondista) {
            tipoCorredor = "CORREDOR FONDISTA";
        }

        if (c instanceof Velocista) {
            tipoCorredor = "COREDOR VELOCISTA";
        }
    }
}
