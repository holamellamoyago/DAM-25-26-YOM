package POJOS;

import java.util.HashSet;
import java.util.Set;

public class Departamento implements java.io.Serializable {

    private int numDepartamento;
    private String nomeDepartamento;
    private Set<String> funciones = new HashSet<>();

    private Set<Proxecto> proxectos = new HashSet<>();

    // Mapeo de los empregados que pertenecen al departamento
    Set<Empregado> empregados = new HashSet<>();

    public Departamento() {
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public Set<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(Set<String> funciones) {
        this.funciones = funciones;
    }

    @Override
    public String toString() {
        return "Departamento [numDepartamento=" + numDepartamento + ", nomeDepartamento=" + nomeDepartamento
                + ", \nFunciones=" + funciones + "]\n + Empregados: " + empregados ;
    }

    public Set<Proxecto> getProxectos() {
        return proxectos;
    }

    public void setProxectos(Set<Proxecto> proxectos) {
        this.proxectos = proxectos;
    }

    public Set<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<Empregado> empregados) {
        this.empregados = empregados;
    }


    

    

    

}
