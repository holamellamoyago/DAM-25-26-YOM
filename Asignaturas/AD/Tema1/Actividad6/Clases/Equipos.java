package AD.Tema1.Actividad6.Clases;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "equipos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipos {

    @XmlElement(name = "equipo", required = true)
    private ArrayList<Equipo> equipos;

    public Equipos() {
        equipos = new ArrayList<>();
    }

    public Equipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    @Override
    public String toString() {
        return "Equipos " + getEquipos() ;
    }

    

}
