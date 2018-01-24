package org.inria.fr.ns;

import org.inria.fr.ns.cache.Cache;
import org.inria.fr.ns.cr.Crs;
import org.inria.fr.ns.models.CentreInfo;
import org.inria.fr.ns.models.Stat;
import org.inria.fr.ns.sr.StructureInria;
import org.inria.fr.ns.sr.StructuresInria;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQResultSequence;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Path("stat")
public class STATService {

    @GET
    @Path("nbpers-par-centre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stat> getInfo() throws XQException, JAXBException, JSONException {
        return Cache.getNbPersonneCentreCache();
    }

    @GET
    @Path("nbequipe-par-centre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stat> getInfoBis() throws XQException, JAXBException, JSONException {
        return  Cache.getNbEquipeCentreCache();
    }

    @GET
    @Path("equipe-par-centre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CentreInfo> getInfoGen() throws XQException, JAXBException, JSONException {
        return  Cache.getEquipesParCentreCache();
    }
}
