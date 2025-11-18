package AD.Tema1.Actividad7.Clases;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Trabajador.class, Estudiante.class})
public abstract class Persona {
    @XmlElement(name = "Nombre", required = true)
    private String nombre;

    @XmlElement(name = "FechaNacimiento", required = true)
    private LocalDate fechaNacimiento;

    @XmlElement(name = "Email")
    private String email;

    public Persona() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    



}
