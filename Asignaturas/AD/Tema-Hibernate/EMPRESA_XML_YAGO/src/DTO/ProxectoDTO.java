package DTO;

public class ProxectoDTO {
    private String nomeProxecto, lugar;
    private int numProxecto;

    public ProxectoDTO() {
    }

    

    public ProxectoDTO(String nomeProxecto, String lugar, int numProxecto) {
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
        this.numProxecto = numProxecto;
    }



    public String getNomeProxecto() {
        return nomeProxecto;
    }
    public void setNomeProxecto(String nombre) {
        this.nomeProxecto = nombre;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public int getNumProxecto() {
        return numProxecto;
    }
    public void setNumProxecto(int numero) {
        this.numProxecto = numero;
    }

    
}
