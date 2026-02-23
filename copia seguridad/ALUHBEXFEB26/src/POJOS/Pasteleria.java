package POJOS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Pasteleria implements Serializable {

    private int codigo;
    private String nome;
    private String categoria;
    private String localidade;

    // Asociacion con el dueno pastelero
    private Pastelero pastelero;

    // Relacion n:n con productos
    private Set<Producto> productos = new HashSet<>();

    public Pasteleria() {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Pastelero getPastelero() {
        return pastelero;
    }

    public void setPastelero(Pastelero pastelero) {
        this.pastelero = pastelero;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Pasteleria [codigo=" + codigo + ", nome=" + nome + ", categoria=" + categoria + ", localidade="
                + localidade + "] Pastelero " + pastelero.getCodigo();
    }

}
