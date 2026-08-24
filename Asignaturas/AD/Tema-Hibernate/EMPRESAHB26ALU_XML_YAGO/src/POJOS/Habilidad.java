package POJOS;
import java.util.*;

public class Habilidad {

    private int numHabilidad;
    private String habilidad;

    private Set<Empregado> empregados = new HashSet<>();

    public Habilidad() {
    }

    

    public Habilidad(int numHabilidad, String habilidad, Set<Empregado> empregados) {
        this.numHabilidad = numHabilidad;
        this.habilidad = habilidad;
        this.empregados = empregados;
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

    public Set<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<Empregado> empregados) {
        this.empregados = empregados;
    }

    

}
