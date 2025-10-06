package AD.Tema1.Actividad3.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Corredor implements Serializable {

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



    public int getDorsal() {
        return dorsal;
    }



    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }



    public int getEquipo() {
        return equipo;
    }



    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }



    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



    public ArrayList<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }



    public void setPuntuaciones(ArrayList<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    


    

    
}