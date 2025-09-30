package AD.Tema1.Actividad3.dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fondista extends Corredor {
    private float distanciaMax;

    public Fondista(int dorsal, int equipo, String nombre, LocalDate fechaNacimiento,
            ArrayList<Puntuacion> puntuaciones, float distanciaMax) {
        super(dorsal, equipo, nombre, fechaNacimiento, puntuaciones);
        this.distanciaMax = distanciaMax;
    }

    public Fondista(String nombre, LocalDate fechaNacimiento, int dorsal, float distanciaMax) {
        this(dorsal, dorsal, nombre, fechaNacimiento, new ArrayList<>(), distanciaMax);
    }

}
