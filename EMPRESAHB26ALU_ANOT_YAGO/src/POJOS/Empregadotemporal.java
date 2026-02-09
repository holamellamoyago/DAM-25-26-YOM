package POJOS;

import java.time.LocalDate;
import javax.persistence.Column;


public class Empregadotemporal extends Empregado implements java.io.Serializable {

    @Column(name = "DataInicio")
    private LocalDate  dataInicio;

    @Column(name = "DataFin")
    private LocalDate  dataFin;

    @Column(name = "CosteHora")
    private Double costeHora;

    @Column(name = "NumHoras")
    private Double numHoras;

    public Empregadotemporal() {
    }

    public Empregadotemporal(LocalDate  dataInicio, LocalDate  dataFin, Double costeHora, Double numHoras, String nss, String nome, String apelido1) {
        super(nss, nome, apelido1);
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.costeHora = costeHora;
        this.numHoras = numHoras;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate  dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate  getDataFin() {
        return this.dataFin;
    }

    public void setDataFin(LocalDate  dataFin) {
        this.dataFin = dataFin;
    }

    public Double getCosteHora() {
        return this.costeHora;
    }

    public void setCosteHora(Double costeHora) {
        this.costeHora = costeHora;
    }

    public Double getNumHoras() {
        return this.numHoras;
    }

    public void setNumHoras(Double numHoras) {
        this.numHoras = numHoras;
    }

}
