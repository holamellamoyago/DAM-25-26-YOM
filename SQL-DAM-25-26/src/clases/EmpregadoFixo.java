package clases;

import java.util.Date;

public class EmpregadoFixo extends Empregado{
    private String salario, categoria;
    private Date dataAlta;

    public EmpregadoFixo(String nome, String apelido1, String apelido2, String nss, String rua, String piso, String cp, String localidade, String provincia, String dataNacemento, String NSSSupervisa, int numCalle, int numDepartamentoPertenece, char sexo, String salario, String categoria, Date dataAlta) {
        super(nome, apelido1, apelido2, nss, rua, piso, cp, localidade, provincia, dataNacemento, NSSSupervisa, numCalle, numDepartamentoPertenece, sexo);
        this.salario = salario;
        this.categoria = categoria;
        this.dataAlta = dataAlta;
    }

    public EmpregadoFixo(String nome, String apelido1, String apelido2, String nss, String rua, String piso, String cp, String localidade, String provincia, String dataNacemento, String NSSSupervisa, int numCalle, int numDepartamentoPertenece, char sexo) {
        super(nome, apelido1, apelido2, nss, rua, piso, cp, localidade, provincia, dataNacemento, NSSSupervisa, numCalle, numDepartamentoPertenece, sexo);
    }


}
