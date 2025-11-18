package AD.Tema1.Actividad7.Clases;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.*;

public class Trabajador extends Persona {

    @XmlElementWrapper(name = "Telefonos")
    @XmlElement(name = "Telefono", required = true)
    private ArrayList<String> telefonos;

    @XmlElement(name = "Salario")
    private float salario;

    public Trabajador() {
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    
}
