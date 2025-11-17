package AD.Tema1.Actividad6.Clases;

import java.util.HashSet;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public class Patrocinadores {
    @XmlAttribute(name = "numPatrocinadores", required = true)
    private int numPatrocinadores;

    @XmlElement(name = "patrocinadores", required = true)
    Set<Patrocinador> patrocinadores = new HashSet<>();

    public Patrocinadores() {
    }

    public Patrocinadores(int numPatrocinadores) {
        this.numPatrocinadores = numPatrocinadores;
    }

    public int getNumPatrocinadores() {
        return numPatrocinadores;
    }

    public void setNumPatrocinadores(int numPatrocinadores) {
        this.numPatrocinadores = numPatrocinadores;
    }

    public Set<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(Set<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }


    public void anadirPatrocinador(Patrocinador patrocinador) {
        this.patrocinadores.add(patrocinador);
    }

    public void anadirPatrocinadores(Set<Patrocinador> patrocinador) {
        for (Patrocinador patrocinador2 : patrocinador) {
            this.patrocinadores.add(patrocinador2);
        }
    }



}
