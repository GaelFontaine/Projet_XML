package org.inria.fr.ns.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAdapter extends XmlAdapter<String,LocalDateTime> {
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v,formatter);
    }
    @Override
    public String marshal(LocalDateTime v) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
