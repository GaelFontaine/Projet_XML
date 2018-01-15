package org.inria.fr.ns;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "person")
public class Participant {
    protected String firstname;
    protected String lastname;
    protected String affiliation;
    protected String categoryPro;
    protected String moreinfo;
    protected boolean hdr;

    @XmlElement
    public String getFirstname() {
        return firstname;
    }

    @XmlElement
    public String getLastname() {
        return lastname;
    }

    @XmlElement
    public String getAffiliation() {
        return affiliation;
    }

    @XmlElement
    public String getCategoryPro() {
        return categoryPro;
    }

    @XmlElement
    public String getMoreinfo() {
        return moreinfo;
    }

    @XmlElement
    public boolean isHdr() {
        return hdr;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setCategoryPro(String categoryPro) {
        this.categoryPro = categoryPro;
    }

    public void setMoreinfo(String moreinfo) {
        this.moreinfo = moreinfo;
    }

    public void setHdr(boolean hdr) {
        this.hdr = hdr;
    }
}

