package POJOS;

public class Direccion {
    private String rua;
    private Integer numeroCalle;
    private String piso;
    private String cp;
    private String localidade;
    private String provincia;

    public Direccion() {
    }

    

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(Integer numeroCalle) {
        this.numeroCalle = numeroCalle;
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



    @Override
    public String toString() {
        return "Direccion [rua=" + rua + ", numeroCalle=" + numeroCalle + ", piso=" + piso + ", cp=" + cp
                + ", localidade=" + localidade + ", provincia=" + provincia + "]";
    }

    

}
