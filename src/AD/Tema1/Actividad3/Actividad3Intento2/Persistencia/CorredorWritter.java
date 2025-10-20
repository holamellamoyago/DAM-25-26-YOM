package AD.Tema1.Actividad3.Actividad3Intento2.Persistencia;

import java.io.*;

import AD.Tema1.Actividad3.Actividad3Intento2.Clases.Corredor;

public class CorredorWritter extends Archivo {
    ObjectOutputStream archivo;

    public CorredorWritter(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirarchivo() {
        boolean existe = existe() && file.length()> 0;
        try {
            if (existe) {
                archivo = new AppendObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, existe)));
            } else {
                archivo = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, existe)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escribir(Corredor c) {
         if (archivo == null) {
            System.out.println("Problema al abrir el archivo");
            return;
         }
         
         try {
            archivo.writeObject(c);
            System.out.println("Se escribió el corredor " + c.getNombre());
        } catch (IOException e) {
            System.out.println("Hubo un problema al guardar al corredor");
            e.printStackTrace();
        }
    }

    @Override
    public void cerararchivo() {
        if (archivo != null) {
            try {
                archivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
