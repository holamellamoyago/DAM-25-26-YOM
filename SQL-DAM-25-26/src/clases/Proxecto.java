/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Proxecto {
    private int numProxecto, numDepartControla;
    private String nomeProxecto, lugar;

    

    public Proxecto() {
    }

    public Proxecto(int numProxecto, int numDepartControla, String nomeProxecto, String lugar) {
        this.numProxecto = numProxecto;
        this.numDepartControla = numDepartControla;
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
    }

    public int getNumProxecto() {
        return numProxecto;
    }

    public void setNumProxecto(int numProxecto) {
        this.numProxecto = numProxecto;
    }

    public int getNumDepartControla() {
        return numDepartControla;
    }

    public void setNumDepartControla(int numDepartControla) {
        this.numDepartControla = numDepartControla;
    }

    public String getNomeProxecto() {
        return nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
