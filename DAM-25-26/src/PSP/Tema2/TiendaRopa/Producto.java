package PSP.Tema2.TiendaRopa;

public class Producto {
    private String titulo;
    private String subtitulo;
    private double precio;
    private String rutaImagen;

    public Producto(String titulo, String subtitulo, double precio, String rutaImagen) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    
    
}
