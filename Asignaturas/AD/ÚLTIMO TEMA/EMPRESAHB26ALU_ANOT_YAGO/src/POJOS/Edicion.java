package POJOS;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EDICION", schema = "dbo", catalog = "EMPRESAHB26")
public class Edicion implements java.io.Serializable {

    @EmbeddedId
    private EdicionId id;
    //el curso al que pertenece la edicion

   
    @Column(name = "Data")    
    private LocalDate data;

    @Column(name = "Lugar", length = 25)
    private String lugar;


    public Edicion() {
       
    }

    public Edicion(LocalDate  data, String lugar) {
        this.data = data;
        this.lugar = lugar;    
       
        }
    

    public EdicionId getId() {
        return this.id;
    }

    public void setId(EdicionId id) {
        this.id = id;
    }

   

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate  data) {
        this.data = data;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

}
