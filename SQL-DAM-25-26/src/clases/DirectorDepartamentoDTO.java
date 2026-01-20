package clases;

/**
 * DirectorDepartamentoDTO
 */
public class DirectorDepartamentoDTO {
    private final int numDepartamento;
    private final String nomeDepartamento, apelido1, apelido2;
    
    public DirectorDepartamentoDTO(int numDepartamento, String nomeDepartamento, String apelido1, String apelido2) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
    }

    
}