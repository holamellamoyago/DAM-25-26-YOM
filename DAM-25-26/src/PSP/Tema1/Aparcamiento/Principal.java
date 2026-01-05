package PSP.Tema1.aparcamiento;

public class Principal {

    public static void main(String[] args) {
        final int TOTAL_CONDUCTORES = 50;
        
        Aparcamiento aparcamiento = new Aparcamiento();
        System.out.println(aparcamiento.toString());

        Conductor[] conductores = new Conductor[TOTAL_CONDUCTORES];


        for (int i = 0; i < conductores.length; i++) {
            conductores[i] = new Conductor(i, aparcamiento);
            conductores[i].start();
        }

        

    }
}
