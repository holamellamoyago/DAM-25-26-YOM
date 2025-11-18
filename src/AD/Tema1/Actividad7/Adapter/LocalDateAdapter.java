package AD.Tema1.Actividad7.Adapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YY");

    @Override
    public String marshal(LocalDate v) throws Exception {
        if (v == null) {
            return null;
        }

        return v.format(formatter);
    }

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty()) {
            return null;
        }

        return LocalDate.parse(v,formatter);
    }
}
