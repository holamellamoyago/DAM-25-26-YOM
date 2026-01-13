package clases;

public class Empregado {
    private String nome,apelido1, apelido2, nss, rua, piso, cp, localidade,provincia,dataNacemento, NSSSupervisa;
    private int numCalle, numDepartamentoPertenece;
    private char sexo;
    private Proxecto proxecto;

    

    public Empregado() {
    }



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



    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getApelido1() {
        return apelido1;
    }



    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }



    public String getApelido2() {
        return apelido2;
    }



    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }



    public String getNss() {
        return nss;
    }



    public void setNss(String nss) {
        this.nss = nss;
    }



    public String getRua() {
        return rua;
    }



    public void setRua(String rua) {
        this.rua = rua;
    }



    public String getPiso() {
        return piso;
    }



    public void setPiso(String piso) {
        this.piso = piso;
    }



    public String getCp() {
        return cp;
    }



    public void setCp(String cp) {
        this.cp = cp;
    }



    public String getLocalidade() {
        return localidade;
    }



    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }



    public String getProvincia() {
        return provincia;
    }



    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }



    public String getDataNacemento() {
        return dataNacemento;
    }



    public void setDataNacemento(String dataNacemento) {
        this.dataNacemento = dataNacemento;
    }



    public String getNSSSupervisa() {
        return NSSSupervisa;
    }



    public void setNSSSupervisa(String nSSSupervisa) {
        NSSSupervisa = nSSSupervisa;
    }



    public int getNumCalle() {
        return numCalle;
    }



    public void setNumCalle(int numCalle) {
        this.numCalle = numCalle;
    }



    public int getNumDepartamentoPertenece() {
        return numDepartamentoPertenece;
    }



    public void setNumDepartamentoPertenece(int numDepartamentoPertenece) {
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }



    public char getSexo() {
        return sexo;
    }



    public void setSexo(char sexo) {
        this.sexo = sexo;
    }



    public Proxecto getProxecto() {
        return proxecto;
    }



    public void setProxecto(Proxecto proxecto) {
        this.proxecto = proxecto;
    }

    @Override
    public String toString() {
        return nome + " " + apelido1;
    }
}
