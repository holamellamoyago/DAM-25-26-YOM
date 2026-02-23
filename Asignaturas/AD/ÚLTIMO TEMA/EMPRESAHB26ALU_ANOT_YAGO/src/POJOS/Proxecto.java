package POJOS;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PROXECTO", schema = "dbo", catalog = "EMPRESAHB26")
public class Proxecto implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "NumProxecto")
    private int numProxecto;

    @Column(name = "NomeProxecto", unique = true, nullable = false, length = 25)
    private String nomeProxecto;

    @Column(name = "Lugar", nullable = false, length = 25)
    private String lugar;

    //mapear las fase de proyecto
    @ElementCollection (fetch = FetchType.LAZY) //es el comportameiento por defecto. 
    @CollectionTable(
            name = "PROXECTOFASE",
            joinColumns = @JoinColumn(name = "NumProxecto")
    )
    private Set<ProxectoFase> fases = new HashSet<>();


    public Proxecto() {
    }

    public Proxecto(int numProxecto,  String nomeProxecto, String lugar) {
        this.numProxecto = numProxecto;       
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
    }

   

    public int getNumProxecto() {
        return this.numProxecto;
    }

    public void setNumProxecto(int numProxecto) {
        this.numProxecto = numProxecto;
    }

    
    public String getNomeProxecto() {
        return this.nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
   
    public Set<ProxectoFase> getFases() {
        return fases;
    }

    public void setFases(Set<ProxectoFase> fases) {
        this.fases = fases;
    }

}
