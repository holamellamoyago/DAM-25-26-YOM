package PSP.Tema2.Menu;

import java.util.Scanner;

import org.json.JSONArray;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);



        int opcion;
        while (true) {
            System.out.println("MENU");
            System.out.println("\t1 -> Opción 1");

            System.out.println("\nEscribe tú opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:

                    break;

                default:
                    System.out.println("\nSelecciona una opción correcta");
                    break;
            }

        }

    


    }
}
