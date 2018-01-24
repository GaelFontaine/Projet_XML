package org.inria.fr.ns.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "AddGeo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "ville",
        "latitude",
        "longitude"
})
public class AdresseGeographique {
    @XmlElement(required = true)
    protected String ville;
    @XmlElement(required = true)
    protected String latitude;
    @XmlElement(required = true)
    protected String longitude;

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
