package AD.Tema1.Actividad7.Clases;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Estudiante extends Persona {
    @XmlElement(name = "Carrera", required = true)
    private String carrera;

    @XmlElement(name = "Universidad", required = true)
    private String universidad;

    public Estudiante() {
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

        @Override
    public String toString() {
        return "\nEstudiante: " + getNombre() + ", carrera: " + carrera + "$, email: " + getEmail();
    }
}
