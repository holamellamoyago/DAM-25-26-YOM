package dto;

public class EmpregadoInfoProxectoDTO {

    private String nss;
    private String nomeCompleto;
    private String lugar;
    private int numDepartControla;

    public EmpregadoInfoProxectoDTO(String nss, String nomeCompleto,
                                    String lugar, int numDepartControla) {
        this.nss = nss;
        this.nomeCompleto = nomeCompleto;
        this.lugar = lugar;
        this.numDepartControla = numDepartControla;
    }

    @Override
    public String toString() {
        return nss + " - " + nomeCompleto + " - " + lugar +
                " (dep: " + numDepartControla + ")";
    }
}
