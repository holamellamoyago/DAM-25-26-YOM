package PSP.Tema1.Ejercicio3;

public class Dejo {
    
    public static void main(String[] args) {
        String s = "";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 1000000000; i++) {
            s += "*";
        }

        // TODO votantes 

        // for (int i = 0; i < 1000000000; i++) {
        //     builder.append("*");
        // }



        // System.out.println(builder.toString());
        System.out.println(s);
    }
}
