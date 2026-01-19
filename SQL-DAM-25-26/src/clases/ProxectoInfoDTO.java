package clases;

public class ProxectoInfoDTO {

    String nome, lugar;
    int numDepartamento;
    public ProxectoInfoDTO(String nome, String lugar, int numDepartamento) {
        this.nome = nome;
        this.lugar = lugar;
        this.numDepartamento = numDepartamento;
    }
    @Override
    public String toString() {
        return "ProxectoInfoDTO [nome=" + nome + ", lugar=" + lugar + ", numDepartamento=" + numDepartamento + "]";
    }

    

    

}