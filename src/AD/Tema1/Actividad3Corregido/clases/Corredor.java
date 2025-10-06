package AD.Tema1.Actividad3Corregido.clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Corredor implements Serializable {
    private static final long serialVersionUID = 1L;
    private int dorsal, equipo;
    private String nombre;
    private LocalDate fechaNacimiento;
    private ArrayList<Puntuacion> puntuaciones;

    

    public Corredor(int dorsal, int equipo, String nombre, LocalDate fechaNacimiento,
            ArrayList<Puntuacion> puntuaciones) {
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.puntuaciones = puntuaciones;
    }



    @Override
    public String toString() {
        return "Corredor [" + dorsal + "] " + nombre;
    }


    

    
}