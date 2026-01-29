package POJOS;


import java.util.Date;


public class Empregadofixo extends Empregado implements java.io.Serializable {


    private Double salario;
    private Date dataAlta;
    private String categoria;
    

    public Empregadofixo() {
    }



    public Double getSalario() {
        return this.salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Date getDataAlta() {
        return this.dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

   

}
