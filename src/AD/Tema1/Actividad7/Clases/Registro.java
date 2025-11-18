package AD.Tema1.Actividad7.Clases;

import java.time.LocalDate;

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

    public Registro() {
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
        return "Registro [version=" + version + ", fechaCreacion=" + fechaCreacion + "]";
    }

    
    
}
