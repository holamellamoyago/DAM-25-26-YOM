package AD.Tema1Resumen.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Velocista extends Corredor{
    private float velocidadMedia;

    public Velocista () {}

    public Velocista(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento,
             float velocidadMedia) {
        super(codigo, dorsal, equipo, nombre, fechaNacimiento);
        this.velocidadMedia = velocidadMedia;
    }

    public Velocista(String codigo, String equipo, String nombre, LocalDate fechaNacimiento,
             float velocidadMedia) {
        super(codigo, equipo, nombre, fechaNacimiento);
        this.velocidadMedia = velocidadMedia;
    }

        

    public float getVelocidadMedia() {
        return velocidadMedia;
    }

    public void setVelocidadMedia(float velocidadMedia) {
        this.velocidadMedia = velocidadMedia;
    }


    
    
    


    
}
