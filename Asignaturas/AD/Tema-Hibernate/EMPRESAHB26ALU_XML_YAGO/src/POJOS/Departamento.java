package POJOS;

import java.util.Set;
import java.util.TreeSet;

public class Departamento implements java.io.Serializable {

    private int numDepartamento;
    private String nomeDepartamento;
    private Set<String> funciones = new TreeSet<>();

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
        return nomeDepartamento;
    }

}
