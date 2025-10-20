package AD.Tema1.Actividad4.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Velocista extends Corredor{
    private float velocidadMedia;

    public Velocista(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento,
             float velocidadMedia) {
        super(codigo, dorsal, equipo, nombre, fechaNacimiento);
        this.velocidadMedia = velocidadMedia;
    }

    
    


    
}
