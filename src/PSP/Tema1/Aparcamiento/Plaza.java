package PSP.Tema1.Aparcamiento;

public class Plaza {
    Conductor conductor;
    boolean ocupada;
    int numeroPlaza;

    public Plaza(int i) {
        this.numeroPlaza = i;
        this.ocupada = false;
    }

    @Override
    public String toString() {
        return "Plaza" + String.valueOf(numeroPlaza);
    }


    

    
}
