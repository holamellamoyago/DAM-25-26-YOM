package PSP.Tema2.TiendaRopa;

public class Inventario {
    private static Producto c1 = new Producto("Cami Guaparda", "Impresiona a las chicas con esta camiseta", 20, "c1.png");
    private static Producto c2 = new Producto("Pantalon traje", "¿Quieres ir guapo? Escoge este!", 200, "p1.png");
    private static Producto c3 = new Producto("Cazadora Anti-ChanDoMonte", "No volverás a pasar frio", 10, "cazadora1.png");

    public static Producto[] productos = {c1,c2,c3};
}
