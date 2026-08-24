package clases;

import java.io.Serializable;

public class Jugador implements Serializable{
    static final long serialVersionUID = 33L;
    private int dorsal;
    private String nombre, nacionalidad, posicion;

    public Jugador(int dorsal, String nombre, String nacionalidad, String posicion) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
    }

    public Jugador() {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "\n" + nombre + "(" + dorsal + ") - " + posicion + " - " + nacionalidad;
    }

}
