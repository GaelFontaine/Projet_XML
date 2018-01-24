package org.inria.fr.ns.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "centre")
public class CentreInfo {

    @XmlAttribute(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(name = "equipes", required = true)
    public List<EquipeInfo> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<EquipeInfo> equipes) {
        this.equipes = equipes;
    }

    protected String nom;

    protected List<EquipeInfo> equipes;
}
