package AD.Tema1.Actividad3Corregido.clases;

public class Puntuacion implements Comparable<Puntuacion> {
        private static final long serialVersionUID = 1L;

    private int anio;
    private float puntos;

    public Puntuacion(int anio, float puntos) {
        this.anio = anio;
        this.puntos = puntos;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    @Override
    public int compareTo(Puntuacion o) {
        return this.anio - o.anio;
    }

}
