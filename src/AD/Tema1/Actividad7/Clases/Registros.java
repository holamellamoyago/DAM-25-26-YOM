package AD.Tema1.Actividad7.Clases;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "Registro")
@XmlAccessorType(XmlAccessType.FIELD)
public class Registros {
    @XmlElement(name = "Registro", required = true)
    ArrayList<Registro> registros;

    public Registros() {
        registros = new ArrayList<>();
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        return "Registros " + registros;
    }

    
    
    
    
}
