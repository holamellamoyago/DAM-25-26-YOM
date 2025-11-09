package PSP.Tema1.EjercicioTierraCorregido;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HWWC {
    private static List<Meteorito> meteoritos = new ArrayList<>();
    private static List<Nave> naves = new ArrayList<>();
    private static Random rdm = new Random();

    public static void addMeteorito(Meteorito m) {
        meteoritos.add(m);
    }

    public static void addNave(Nave n) {
        naves.add(n);
    }

    public static synchronized List<Nave> getNaves() {
        return naves;
    }

    public synchronized static Meteorito getMeteorito(int position) {
        return meteoritos.get(position);
    }

    public synchronized static boolean hayMeteoritos() {
        if (meteoritos.isEmpty()) {
            return false;
        }

        return true;
    }

    public synchronized static void quitarMeteorito(Meteorito position) {
        if (!meteoritos.contains(position)) {
            System.out.println("Se intento quitar el meteo " + position + " pero no existe");
            return;
        }

        meteoritos.remove(position);
    }

    public static void taladrar() {
        if (!hayMeteoritos()) {
            return;
        }

        Meteorito meteorito = getMeteorito(rdm.nextInt(meteoritos.size()));

        if (meteorito.isTaladrado() || meteorito.isExplotado()) {
            System.out.println("Se intentó taladrar al " + meteorito.getReferencia());
            return;
        }

        meteorito.taladrar();

    }

    public static void explotar() {
        if (!hayMeteoritos()) {
            return;
        }

        Meteorito meteorito = getMeteorito(rdm.nextInt(meteoritos.size()));

        if (!meteorito.isTaladrado() || meteorito.isExplotado()) {
            return;
        }

        if (meteorito.explotar())
            quitarMeteorito(meteorito);
    }

}
