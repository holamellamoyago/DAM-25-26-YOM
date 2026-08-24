package POJOS;


import java.time.LocalDate;
import javax.persistence.Column;


public class Empregadofixo extends Empregado implements java.io.Serializable {

   @Column(name = "Salario")
    private Double salario;

    @Column(name = "DataAlta")
    private LocalDate dataAlta;

    @Column(name = "Categoria", length = 20)
    private String categoria;
    
   
    

    public Empregadofixo() {
    }
  public Empregadofixo(String nss) {
        super(nss);
    }

    public Empregadofixo(String nss, String nome, String apelido1) {
        super(nss, nome, apelido1);
    }

    public Empregadofixo(Double salario, LocalDate  dataAlta, String categoria, String nss, String nome, String apelido1) {
        super(nss, nome, apelido1);
        this.salario = salario;
        this.dataAlta = dataAlta;
        this.categoria = categoria;
    }



    public Double getSalario() {
        return this.salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate  getDataAlta() {
        return this.dataAlta;
    }

    public void setDataAlta(LocalDate  dataAlta) {
        this.dataAlta = dataAlta;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    

}
