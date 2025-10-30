package AD.Tema1.Actividad5.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fondista extends Corredor {
    private static final long serialVersionUID = 1L;

    private float distanciaMax;

        public Fondista(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento,
            float distanciaMax) {
        super(codigo, dorsal, equipo, nombre, fechaNacimiento);
        this.distanciaMax = distanciaMax;
    }


    public Fondista(int dorsal, String equipo, String nombre, LocalDate fechaNacimiento,
            float distanciaMax) {
        super(null, dorsal, equipo, nombre, fechaNacimiento);
        this.distanciaMax = distanciaMax;
    }

    public float getDistanciaMax() {
        return distanciaMax;
    }

    

    // public Fondista(int codigo, String nombre, LocalDate fechaNacimiento, int
    // dorsal, float distanciaMax) {
    // this(codigo, dorsal, dorsal, nombre, fechaNacimiento, new ArrayList<>(),
    // distanciaMax);
    // }

}
