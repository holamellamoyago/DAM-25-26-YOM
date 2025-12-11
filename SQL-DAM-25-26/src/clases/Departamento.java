/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author usuario
 */
public class Departamento {
    private int numDepartamento;
    private String nomeDepartamento;
    private String nssDirector;

    public Departamento(int numDepartamento, String nomeDepartamento, String nssDirector) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.nssDirector = nssDirector;
    }

    public Departamento(String nomeDepartamento, String nssDirector) {
        this.nomeDepartamento = nomeDepartamento;
        this.nssDirector = nssDirector;
    }

    public Departamento() {}

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

    public String getNssDirector() {
        return nssDirector;
    }

    public void setNssDirector(String nssDirector) {
        this.nssDirector = nssDirector;
    }

    @Override
    public String toString() {
        return "DEPARTAMENTO: " + nomeDepartamento + "[" + numDepartamento + "] director: " + nssDirector;
    }
}
