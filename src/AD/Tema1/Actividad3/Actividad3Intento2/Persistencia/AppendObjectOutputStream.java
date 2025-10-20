package AD.Tema1.Actividad3.Actividad3Intento2.Persistencia;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendObjectOutputStream extends ObjectOutputStream {

    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected AppendObjectOutputStream() throws SecurityException, IOException {
        super();
    }

    @Override
    protected void writeStreamHeader() throws IOException {
    }

}
