package AD.Tema1.Actividad7.Clases;

import java.time.LocalDate;
import java.util.ArrayList;

import AD.Tema1.Actividad7.Adapter.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Registro")
@XmlAccessorType(XmlAccessType.FIELD)
public class Registro {
    @XmlAttribute(name = "version", required = true)
    private float version;

    @XmlAttribute(name = "fechaCreacion", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaCreacion;

    @XmlElementWrapper(name = "Personas")
    @XmlElements({
            @XmlElement(name = "Trabajador", type = Trabajador.class),
            @XmlElement(name = "Estudiante", type = Estudiante.class)
    })
    private ArrayList<Persona> personas;

    public Registro() {
        personas = new ArrayList<>();
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "\nRegistro [version=" + version + ", fechaCreacion=" + fechaCreacion + "\nPersonas: " + personas;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

}
