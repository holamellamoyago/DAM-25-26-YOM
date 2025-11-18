package AD.Tema1.Actividad6;

import java.time.LocalDate;

import AD.Tema1.Actividad6.Clases.Fondista;
import AD.Tema1.Actividad6.Clases.Velocista;

public class GestorCorredoresJAXB {
    String rutaArchivo;

    public GestorCorredoresJAXB(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void getCorredores() {
        System.out.println(CorredoresJAXB.leerCorredores(rutaArchivo));
    }

    public void anadirCorredores() {
        Velocista velocista = new Velocista("C99", "E1", "Yago Otero", LocalDate.parse("2003-11-02"), 33.0f);
        Fondista fondista = new Fondista("C33",33, "E2", "Maria Karey", LocalDate.parse("2003-11-02"), 33.0f);
    }

    
}
