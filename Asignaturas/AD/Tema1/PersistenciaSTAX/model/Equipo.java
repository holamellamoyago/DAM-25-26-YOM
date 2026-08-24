package AD.Tema1.PersistenciaSTAX.model;

import java.util.Set;

public class Equipo {
    private String idEquipo;
    private int numPatrocinadores;
    private String nombre;
    private boolean borrado;

    Set<Patrocinador> patrocinadores;

    public Equipo(String nombre, Set<Patrocinador> patrocinadores) {
        this.nombre = nombre;
        this.patrocinadores = patrocinadores;
        this.borrado = false;
        this.numPatrocinadores = patrocinadores.size();
    }

    public Equipo() {

    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getNumPatrocinadores() {
        return numPatrocinadores;
    }

    public void setNumPatrocinadores(int numPatrocinadores) {
        this.numPatrocinadores = numPatrocinadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Set<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void anadirPatrocinador(Patrocinador patrocinador) {
        this.patrocinadores.add(patrocinador);
    }

    public void anadirPatrocinadores(Set<Patrocinador> patrocinador) {
        for (Patrocinador patrocinador2 : patrocinador) {
            this.patrocinadores.add(patrocinador2);
        }
    }

    public void setPatrocinadores(Set<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipo other = (Equipo) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Equipo " + idEquipo + "(" + nombre + "), patrocinadores: " + patrocinadores;
    }

}
