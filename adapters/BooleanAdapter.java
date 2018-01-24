package org.inria.fr.ns.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanAdapter extends XmlAdapter<String,Boolean> {
    public Boolean unmarshal(String value) {
        return (javax.xml.bind.DatatypeConverter.parseBoolean(value));
    }

    public String marshal(Boolean value) {
        return (javax.xml.bind.DatatypeConverter.printBoolean(value));
    }
}
