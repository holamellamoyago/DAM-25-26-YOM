package AD.Tema1Resumen.Clases;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Puntuacion {
    // private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "anio", required = true)
    private int anio;

    @XmlValue
    private float puntos;

    

    public Puntuacion() {
    }

    public Puntuacion(int anio, float puntos) {
        this.anio = anio;
        this.puntos = puntos;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Puntuacion " + anio + ", puntos=" + puntos + "]";
    }

}
