package POJOS;

public class Habilidad {
    private int codigo;
    private String habilidad;
    
    public Habilidad() {
    }

    public Habilidad(int codigo, String habilidad) {
        this.codigo = codigo;
        this.habilidad = habilidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    
}