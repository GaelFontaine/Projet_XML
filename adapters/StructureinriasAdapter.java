package org.inria.fr.ns.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StructureinriasAdapter extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String v) throws Exception {
        StringBuilder result = new StringBuilder(v);
        result.setCharAt(9,'I');
        return result.toString();
    }

    @Override
    public String marshal(String v) throws Exception {
        return null;
    }
}
