package org.inria.fr.ns;

import org.inria.fr.ns.cache.Cache;
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
import java.io.File;

@Path("ep")
public class EPService {
    @GET
    @Path("/equipe-par-centre-domaine-et-theme")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response getEquipe() throws JAXBException, JSONException {
        JSONObject obj= XML.toJSONObject(Cache.getEquipesParCentreDomaineThemeCache());
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }

/*

for $i in collection('raweb')/raweb
return <infoEq>{
    <nomEq>{$i/identification/shortname}</nomEq>,
    <nbPers>{count($i/team/person)}</nbPers>,
}</infoEq>
*
*
* **/
}
