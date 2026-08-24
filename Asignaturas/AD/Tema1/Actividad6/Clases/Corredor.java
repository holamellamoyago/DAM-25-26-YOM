package AD.Tema1.Actividad6.Clases;

import java.time.LocalDate;
import java.util.ArrayList;

import AD.Tema1.Actividad6.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

// @XmlSeeAlso(Velocista.class)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ Velocista.class, Fondista.class })
@XmlType(propOrder = { "nombre, fecha_nacimiento, historial" })
@XmlTransient
public abstract class Corredor {

    @XmlAttribute(name = "codigo", required = true)
    private String codigo;

    @XmlAttribute(name = "equipo", required = true)
    private String equipo;

    @XmlAttribute(name = "dorsal", required = true)
    private Integer dorsal;

    @XmlElement(name = "nombre", required = true)
    private String nombre;

    @XmlElement(name = "fecha_nacimiento", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNacimiento;

    @XmlElementWrapper(name = "historial")
    @XmlElement(name = "puntuacion")
    private ArrayList<Puntuacion> historial;

    public Corredor() {
        historial = new ArrayList<>();
    };

    public Corredor(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento,
            ArrayList<Puntuacion> puntuaciones) {
        this.codigo = codigo;
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.historial = puntuaciones;
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

    public ArrayList<Puntuacion> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Puntuacion> puntuaciones) {
        this.historial = puntuaciones;
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