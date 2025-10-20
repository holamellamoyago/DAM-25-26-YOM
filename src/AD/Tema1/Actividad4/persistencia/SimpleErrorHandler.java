package AD.Tema1.Actividad4.persistencia;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

    // TODO Pepa todo esto lo tiene como si puede print + "mensae"

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + exception.toString());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + exception.toString());

    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + exception.toString());

    }

}
