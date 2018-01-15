package org.inria.fr.ns.models;

import java.util.Date;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlRootElement(name = "infoCentre")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "numnatstructrep",
        "date_ouverture",
        "sigle",
        "libelle",
        "idgef",
        "AddGeo",
        "nbPers"
})
public class CentreDeRecherche {
    @XmlElement(required = true)
    protected String numnatstructrep;
    @XmlElement(required = true)
    protected String date_ouverture;
    @XmlElement(required = true)
    protected String sigle;
    @XmlElement(required = true)
    protected String libelle;
    @XmlElement(required = true)
    protected int idgef;
    @XmlElement(required = true)
    protected AdresseGeographique AddGeo;
    protected int nbPers;

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }


    public int getNbPers() {
        return nbPers;
    }

    public void setNbPers(int nbPers) {
        this.nbPers = nbPers;
    }

    public String getNumnatstructrep() {
        return numnatstructrep;
    }

    public void setNumnatstructrep(String numnatstructrep) {
        this.numnatstructrep = numnatstructrep;
    }

    public String getDate_ouverture() {
        return date_ouverture;
    }

    public void setDate_ouverture(String date_ouverture) {
        this.date_ouverture = date_ouverture;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getIdgef() {
        return idgef;
    }

    public void setIdgef(int idgef) {
        this.idgef = idgef;
    }


    public AdresseGeographique getAddGeo() {
        return AddGeo;
    }

    public void setAddGeo(AdresseGeographique addGeo) {
        AddGeo = addGeo;
    }

}
