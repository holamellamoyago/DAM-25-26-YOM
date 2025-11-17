package AD.Tema1.Actividad6.Clases;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(Velocista.class)
public abstract class Corredor {
    private String codigo, equipo;
     private Integer dorsal ;
    private String nombre;
    private LocalDate fechaNacimiento;
    private ArrayList<Puntuacion> puntuaciones;

    public Corredor () {

    };

    public Corredor(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento,
            ArrayList<Puntuacion> puntuaciones) {
        this.codigo = codigo;
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.puntuaciones = puntuaciones;
    }

    

    public Corredor(String codigo, Integer dorsal, String equipo, String nombre, LocalDate fechaNacimiento) {
        this.codigo = codigo;
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Corredor(String codigo, String equipo, String nombre, LocalDate fechaNacimiento) {
        this.codigo = codigo;
        this.dorsal = null;
        this.equipo = equipo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
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
    public String getEquipo() {
        return equipo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }



    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

}