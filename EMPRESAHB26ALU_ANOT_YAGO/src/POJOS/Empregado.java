package POJOS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EMPREGADO", schema = "dbo", catalog = "EMPRESAHB26")
@Inheritance(strategy = InheritanceType.JOINED)
public class Empregado implements java.io.Serializable {

    @Id
    @Column(name = "NSS", length = 15, nullable = false)
    private String nss;

    @Column(name = "Nome", length = 25, nullable = false)
    private String nome;

    @Column(name = "Apelido1", length = 25, nullable = false)
    private String apelido1;

    @Column(name = "Apelido2", length = 25)
    private String apelido2;
    // El tipo de dato Date o Calendar hay que a�adir
    // @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DataNacemento", columnDefinition = "DATE") // columnDefinition = "DATE" es opcional, si gueremos que
                                                               // se guarde en bd coomo date
    private LocalDate dataNacemento;

    @Column(name = "Sexo", length = 1)
    private Character sexo;
    // Guardamos o enderezo como un componente
    @Embedded
    private Enderezo enderezo;

    // mapear los telefonos
    @ElementCollection
    @CollectionTable(name = "TELEFONO", joinColumns = @JoinColumn(name = "NSS"))
    @MapKeyColumn(name = "Telefono")
    @Column(name = "Informacion")
    private Map<String, String> telefonos = new HashMap();

    // mapear familiares
    @ElementCollection
    @CollectionTable(name = "FAMILIAR", joinColumns = @JoinColumn(name = "NSS_empregado"))
    // @OrderColumn(name = "Numero") //Nota: en anotaciones el indice comienza en 0
    // y no se puede modificar.
    // si queremos que empiece en 1 esta anotaci�n no se podria y lo generar�amos
    // por c�digo
    private List<Familiar> familiares = new ArrayList();

    // Yago 03/02
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumDepartamentoPertenece")
    private Departamento departamento;

    // Yago 09/02

    @ManyToMany
    @JoinTable(name = "EMPREGADO_HABILIDADE", joinColumns = @JoinColumn(name = "NSS"), inverseJoinColumns = @JoinColumn(name = "IDHABILIDADE"))
    private Set<Habilidad> habilidades = new HashSet<>();

    public Empregado() {
    }

    public Empregado(String nss, String nome, String apelido1, String apelido2, LocalDate dataNacemento,
            Character sexo) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.dataNacemento = dataNacemento;
        this.sexo = sexo;
    }

    public Empregado(String nss) {
        this.nss = nss;
    }

    public Empregado(String nss, String nome, String apelido1) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
    }

    public String getNss() {
        return this.nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return this.apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return this.apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public LocalDate getDataNacemento() {
        return this.dataNacemento;
    }

    public Set<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public void setDataNacemento(LocalDate dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Map<String, String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Map<String, String> telefonos) {
        this.telefonos = telefonos;
    }

    public Enderezo getEnderezo() {
        return enderezo;
    }

    public void setEnderezo(Enderezo enderezo) {
        this.enderezo = enderezo;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
