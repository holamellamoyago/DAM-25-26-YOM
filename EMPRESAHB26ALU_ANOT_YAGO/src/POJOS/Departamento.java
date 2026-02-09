package POJOS;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DEPARTAMENTO", schema = "dbo", catalog = "EMPRESAHB26")
public class Departamento implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "increment") // para generar el valor de la clave primaria use un generador llamado
                                             // "increment"
    // �C�mo funciona increment?Hibernate lee el valor m�ximo de la columna en la
    // base de datos y le suma 1 para generar el siguiente valor.
    @GenericGenerator(name = "increment", strategy = "increment") // Aqu� se define el generador "increment".
    // La estrategia "increment" hace que Hibernate calcule el siguiente valor de la
    // PK
    @Column(name = "NumDepartamento")
    private int numDepartamento;

    @Column(name = "NomeDepartamento", length = 25, nullable = false, unique = true) // Define as propiedades da columna
                                                                                     // na t�boa da base de datos.
    private String nomeDepartamento;

    // Mapeo das funci�ns dun departamento
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "DEPARTAMENTOFUNCION", joinColumns = @JoinColumn(name = "NumDepartamento"))
    @Column(name = "Funcion")
    private Set<String> funciones = new HashSet<>();


    // Yago 03/02
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private Set<Empregado> empregados = new HashSet<>(0);

    public Departamento() {
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public Set<String> getFunciones() {
        return funciones;
    }

    public void setFunciones(Set<String> funciones) {
        this.funciones = funciones;
    }

}
