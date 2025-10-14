package AD.Tema1.Actividad3.Actividad3Corregido.clases;

import java.util.Set;

public class Equipo {
    private int idEquipo, numPatrocinadores;
    private String nombre;
    private boolean borrado;

    Set<Patrocinador> patrocinadores;

    public Equipo(String nombre, Set<Patrocinador> patrocinadores) {
        this.nombre = nombre;
        this.patrocinadores = patrocinadores;
        this.borrado = false;
        this.numPatrocinadores = patrocinadores.size();
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
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
        return "Equipo " + idEquipo + "(" + nombre + "), numPatrocinadores=" + numPatrocinadores;
    }

    

}
