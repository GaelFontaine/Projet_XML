package org.inria.fr.ns;


import org.inria.fr.ns.cache.Cache;
import org.inria.fr.ns.cr.Crs;
import org.inria.fr.ns.models.AdresseGeographique;
import org.inria.fr.ns.models.CentreDeRecherche;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;


@Path("cr")
public class CRService {
    private XQueryUtil queryUtil;

    @GET
    @Path("/centres")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response getCentre() throws JAXBException, JSONException {
        JSONObject obj= XML.toJSONObject(Cache.getCentreCache());
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }

    @GET
    @Path("/centres-adresses")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response getAdresseCentres()throws JAXBException, JSONException{
        JSONObject obj= XML.toJSONObject(Cache.getAdresseCentresCache());
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }


    @GET
    @Path("infoCentre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CentreDeRecherche> getAllCR() throws JAXBException, JSONException {
        return  Cache.getAllCRCache();
    }

    /*
    @GET
    @Path("/c-d")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public List<Crs.Cr.Adressegeographique> void getAdresse()throws JAXBException, JSONException{
        JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.cr");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Crs centres = (Crs) unmarshaller.unmarshal(new File("src/main/resources/xml/bastriCris.xml"));

        List<Crs.Cr.Adressegeographique> res = new ArrayList<Crs.Cr.Adressegeographique>();

        for (Crs.Cr c: centres.getCr()) {
            System.out.println(c.getAdressegeographique().);
            //res.add((Crs.Cr.Adressegeographique)centres.getCr().get(i));
            result += "\n<Ville>"+c.getAdressegeographique().getVille()+"</Ville>\n" ;
            result += "\n<Latitude>"+c.getAdressegeographique().getLatitude() +"</Latitude>\n" ;
            result += "\n<Longitude>"+c.getAdressegeographique().getLongitude() +"</Longitude>\n" ;
        }
        //return res ;
    }*/





}
