package PSP.Tema3.STOCK;

import PSP.Tema3.STOCK.Clases.Tallas;

public class PrendaRopa {
    private String nombre;
    private Tallas talla;

    PrendaRopa(String nombre, Tallas talla, int stock) {
        this.nombre = nombre;
        this.talla = talla;
    }

    PrendaRopa(String nombre) {
        this.nombre = nombre;
    }

    

    public String getNombre() {
        return nombre;
    }


    public Tallas getTalla() {
        return talla;
    }



    @Override
    public String toString() {
        return "\n" + nombre + " (" + talla + ")";
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
        PrendaRopa other = (PrendaRopa) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }



}
