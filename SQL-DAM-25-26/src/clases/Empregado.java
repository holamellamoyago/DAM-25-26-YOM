package clases;

public class Empregado {
    private String nome,apelido1, apelido2, nss, rua, piso, cp, localidade,provincia,dataNacemento, NSSSupervisa;
    private int numCalle, numDepartamentoPertenece;
    private char sexo;
    private Proxecto proxecto;

    public Empregado(String nome, String apelido1, String apelido2, String nss, String rua, String piso, String cp, String localidade, String provincia, String dataNacemento, String NSSSupervisa, int numCalle, int numDepartamentoPertenece, char sexo) {
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.nss = nss;
        this.rua = rua;
        this.piso = piso;
        this.cp = cp;
        this.localidade = localidade;
        this.provincia = provincia;
        this.dataNacemento = dataNacemento;
        this.NSSSupervisa = NSSSupervisa;
        this.numCalle = numCalle;
        this.numDepartamentoPertenece = numDepartamentoPertenece;
        this.sexo = sexo;
    }
}
