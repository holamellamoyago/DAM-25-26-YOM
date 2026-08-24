package clases;

import java.util.Date;

public class EmpregadoTemporal extends  Empregado{
    private Date dataInicio, dataFin;
    private double costeHora,numHoras ;

    public EmpregadoTemporal(String nome, String apelido1, String apelido2, String nss, String rua, String piso, String cp, String localidade, String provincia, String dataNacemento, String NSSSupervisa, int numCalle, int numDepartamentoPertenece, char sexo, Date dataInicio, Date dataFin, double costeHora, double numHoras) {
        super(nome, apelido1, apelido2, nss, rua, piso, cp, localidade, provincia, dataNacemento, NSSSupervisa, numCalle, numDepartamentoPertenece, sexo);
        this.dataInicio = dataInicio;
        this.dataFin = dataFin;
        this.costeHora = costeHora;
        this.numHoras = numHoras;
    }
}
