package POJOS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Producto implements Serializable {

    private int codigo;
    private String nome;
    private String descripcion;
    private Float precio;
    private String categoria;

    // Mapeo de pastelerias
    private Set<Pasteleria> pastelerias = new HashSet<>();

    public Producto() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Set<Pasteleria> getPastelerias() {
        return pastelerias;
    }

    public void setPastelerias(Set<Pasteleria> pastelerias) {
        this.pastelerias = pastelerias;
    }

    @Override
    public String toString() {
        return "\nProducto [codigo=" + codigo + ", nome=" + nome + ", descripcion=" + descripcion + ", precio=" + precio
                + ", categoria=" + categoria + "]";
    }

}
