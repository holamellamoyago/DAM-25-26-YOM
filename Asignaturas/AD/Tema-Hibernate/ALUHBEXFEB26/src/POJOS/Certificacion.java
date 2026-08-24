package POJOS;

import java.io.Serializable;
import java.time.LocalDate;

public class Certificacion implements Serializable {

    private String codpastelero;   
    private String numlicencia;
    private String titulacion;
    private LocalDate dataexpedicon;
    private String organismo;

    // Mapeo de la relacion con pastelero 
    private Pastelero pastelero;


    public Certificacion() {
    }

    public String getCodpastelero() {
        return codpastelero;
    }

    public void setCodpastelero(String codpastelero) {
        this.codpastelero = codpastelero;
    }

    public String getNumlicencia() {
        return numlicencia;
    }

    public void setNumlicencia(String numlicencia) {
        this.numlicencia = numlicencia;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public LocalDate getDataexpedicon() {
        return dataexpedicon;
    }

    public void setDataexpedicon(LocalDate dataexpedicon) {
        this.dataexpedicon = dataexpedicon;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public Pastelero getPastelero() {
        return pastelero;
    }

    public void setPastelero(Pastelero pastelero) {
        this.pastelero = pastelero;
    }

    

 
}
