package AD.Tema1.Actividad7.Clases;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Personas {
    ArrayList<Persona> personas;

    public Personas() {
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
    
    

}
