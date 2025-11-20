package AD.Tema1.Actividad7.Persistencia;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XMLJAXBUtils {
    public static <T> void marshall(T objeto, String rutaArchivo) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(objeto.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(objeto, new File(rutaArchivo));

        } catch (JAXBException e) {
            System.out.println("JAXB EXCEPTION " + e);
            Throwable linked = e.getLinkedException();
            if (linked != null) {
                System.out.println("LINKED EXCEPTION: " + linked.getMessage());
                linked.printStackTrace();
            }
        }
    }

    public static <T> T unmarshall(Class<T> clase, String rutaArchivo) {
        try {
            JAXBContext context = JAXBContext.newInstance(clase);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            return clase.cast(unmarshaller.unmarshal(new File(rutaArchivo)));
        } catch (JAXBException e) {
            System.out.println("JAXB EXCEPTION " + e);
            Throwable linked = e.getLinkedException();
            if (linked != null) {
                System.out.println("LINKED EXCEPTION: " + linked.getMessage());
                linked.printStackTrace();
            }

            return null;
        }

    }
}
