package AD.Tema1Resumen.ArchivosRandom.Clases;

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
