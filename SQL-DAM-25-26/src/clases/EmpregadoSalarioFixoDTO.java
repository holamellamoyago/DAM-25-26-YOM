package clases;

public class EmpregadoSalarioFixoDTO {
    private String nombre, apelido1, apelido2;
    private int salario;
    
    public EmpregadoSalarioFixoDTO() {
    }
    public EmpregadoSalarioFixoDTO(String nombre, String apelido1, String apelido2, int salario) {
        this.nombre = nombre;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.salario = salario;
    }

    
}
