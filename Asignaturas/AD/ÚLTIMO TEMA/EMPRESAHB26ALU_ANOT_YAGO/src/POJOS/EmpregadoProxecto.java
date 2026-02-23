package POJOS;

import javax.persistence.Column;


public class EmpregadoProxecto implements java.io.Serializable {

    
    private EmpregadoProxectoId id;
    
    
    private Empregado empregado;
    
        private Proxecto proxecto;

    @Column(name = "Horas")
    private Integer horas;

    public EmpregadoProxecto() {
    }

    public EmpregadoProxecto(EmpregadoProxectoId id, Empregado empregado, Proxecto proxecto) {
        this.id = id;
        this.empregado = empregado;
        this.proxecto = proxecto;
    }

    public EmpregadoProxecto(EmpregadoProxectoId id, Empregado empregado, Proxecto proxecto, Integer horas) {
        this.id = id;
        this.empregado = empregado;
        this.proxecto = proxecto;
        this.horas = horas;
    }

    public EmpregadoProxectoId getId() {
        return this.id;
    }

    public void setId(EmpregadoProxectoId id) {
        this.id = id;
    }

    public Empregado getEmpregado() {
        return this.empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public Proxecto getProxecto() {
        return this.proxecto;
    }

    public void setProxecto(Proxecto proxecto) {
        this.proxecto = proxecto;
    }

    public Integer getHoras() {
        return this.horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

}
