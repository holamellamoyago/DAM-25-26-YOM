package AD.Tema1.Actividad3.Actividad3Intento2.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Velocista extends Corredor{
        private static final long serialVersionUID = 1L;


    private float velocidadMedia;

    public Velocista(int dorsal, int equipo, String nombre, LocalDate fechaNacimiento,
            ArrayList<Puntuacion> puntuaciones, float velocidadMedia) {
        super(dorsal, equipo, nombre, fechaNacimiento, puntuaciones);
        this.velocidadMedia = velocidadMedia;
    }

    public Velocista(String nombre, LocalDate fechaNacimiento, int dorsal, float velocidadMedia){
        this(dorsal, dorsal, nombre, fechaNacimiento, new ArrayList<>(), velocidadMedia);
    }
    
    
}
