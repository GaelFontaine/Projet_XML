package org.inria.fr.ns.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "liste_equipes_centre")
public class CentreInfo {

    protected String name;
    protected List<EquipeInfo> equipes;

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setNom(String nom) {
        this.name = nom;
    }

    @XmlElement(name = "equipe", required = true)
    public List<EquipeInfo> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<EquipeInfo> equipes) {
        this.equipes = equipes;
    }
}
