package clases;

public class EmpregadoInfo {
    private String NSS, NomeCompleto , localidade;
    private Double salario;
    public EmpregadoInfo() {
    }

    
    public EmpregadoInfo(String nSS, String nomeCompleto, String localidade, Double salario) {
        NSS = nSS;
        NomeCompleto = nomeCompleto;
        this.localidade = localidade;
        this.salario = salario;
    }


    public String getNSS() {
        return NSS;
    }
    public void setNSS(String nSS) {
        NSS = nSS;
    }
    public String getNomeCompleto() {
        return NomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        NomeCompleto = nomeCompleto;
    }
    public String getLocalidade() {
        return localidade;
    }
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    
}
