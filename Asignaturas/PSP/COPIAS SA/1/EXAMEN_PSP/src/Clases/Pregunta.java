package Clases;

public class Pregunta {
    private String dueno;
    private String pregunta, respuesta;

    public Pregunta(String dueno, String pregunta, String respuesta) {
        this.dueno = dueno;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getDueno() {
        return dueno;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    @Override
    public String toString() {
        return "Pregunta: " + pregunta + " = " + respuesta + "(" + dueno + ")";
    }

}
