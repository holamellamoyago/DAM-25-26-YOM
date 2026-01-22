package clases;

import java.sql.Date;

public abstract class Fotografia {
    private String nome, medidas;
    private int codigo , codFotografo, codExposicion;
    private char color;
    private Date data;
    public Fotografo fotografo;

    public Fotografia() {
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMedidas() {
        return medidas;
    }
    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getCodFotografo() {
        return codFotografo;
    }
    public void setCodFotografo(int codFotografo) {
        this.codFotografo = codFotografo;
    }
    public int getCodExposicion() {
        return codExposicion;
    }
    public void setCodExposicion(int codExposicion) {
        this.codExposicion = codExposicion;
    }
    public char getColor() {
        return color;
    }
    public void setColor(char color) {
        this.color = color;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
