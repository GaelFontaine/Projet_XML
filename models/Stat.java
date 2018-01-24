package org.inria.fr.ns.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "info")
public class Stat {
    protected String parent;
    protected int occurrence;

    @XmlElement(name = "parent", required = true)
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @XmlElement(name = "occurrence", required = true)
    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int nombre) {
        this.occurrence = nombre;
    }


}
