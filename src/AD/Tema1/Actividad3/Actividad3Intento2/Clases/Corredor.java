package AD.Tema1.Actividad3.Actividad3Intento2.Clases;

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

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Corredor other = (Corredor) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

}