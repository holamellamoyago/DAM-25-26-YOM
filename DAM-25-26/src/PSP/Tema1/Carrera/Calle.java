package PSP.Tema1.Carrera;

public class Calle {
    int numero;
    Atleta atleta;

    public Calle(int numero, Atleta atleta) {
        this.numero = numero;
        this.atleta = atleta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }


}
