package POJOS;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HABILIDADES")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NumHabilidad")
    private int numHabilidad;

    @Column(name = "Habilidad", length = 60, nullable = false, unique = true)
    private String habilidad;

    @ManyToMany(mappedBy = "habilidades")
    private Set<Empregado> empregados = new HashSet<>();

    public Habilidad() {
    }

    public int getNumHabilidad() {
        return numHabilidad;
    }

    public void setNumHabilidad(int numHabilidad) {
        this.numHabilidad = numHabilidad;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

}
