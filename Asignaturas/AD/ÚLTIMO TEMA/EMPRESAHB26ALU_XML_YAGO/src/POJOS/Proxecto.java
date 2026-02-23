package POJOS;

import java.util.HashSet;
import java.util.Set;

public class Proxecto implements java.io.Serializable {

    private int numProxecto;
    private Departamento departamento;
    private String nomeProxecto;
    private String lugar;

    // Yago 10/02
    private Set<EmpregadoProxecto> empregados = new HashSet<>(0);
   
    public Proxecto() {
    }

    public Proxecto(int numProxecto, Departamento departamento, String nomeProxecto, String lugar) {
        this.numProxecto = numProxecto;
        this.departamento = departamento;
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
    }

    public Proxecto(int numProxecto, Departamento departamento, String nomeProxecto) {
        this.numProxecto = numProxecto;
        this.departamento = departamento;
        this.nomeProxecto = nomeProxecto;
    }

 

    public int getNumProxecto() {
        return this.numProxecto;
    }

    public void setNumProxecto(int numProxecto) {
        this.numProxecto = numProxecto;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNomeProxecto() {
        return this.nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Set<EmpregadoProxecto> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<EmpregadoProxecto> empregados) {
        this.empregados = empregados;
    }

    

   


}
