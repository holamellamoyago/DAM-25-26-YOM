package clases;

import java.sql.Date;

public class Fotografo {
    private int codigo, codInfluencer, codEstudio, numfotografias;
    private String nombre, Localidade, Pais;
    private Date dataNacemento, dataFallecemento;

    public Fotografo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidade() {
        return Localidade;
    }

    public void setLocalidade(String localidade) {
        Localidade = localidade;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodInfluencer() {
        return codInfluencer;
    }

    public void setCodInfluencer(int codInfluencer) {
        this.codInfluencer = codInfluencer;
    }

    public int getCodEstudio() {
        return codEstudio;
    }

    public void setCodEstudio(int codEstudio) {
        this.codEstudio = codEstudio;
    }

    public int getNumfotografias() {
        return numfotografias;
    }

    public void setNumfotografias(int numfotografias) {
        this.numfotografias = numfotografias;
    }

    public Date getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(Date dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public Date getDataFallecemento() {
        return dataFallecemento;
    }

    public void setDataFallecemento(Date dataFallecemento) {
        this.dataFallecemento = dataFallecemento;
    }

    @Override
    public String toString() {
        return "Fotografo: " + nombre;
    }


}
