package org.inria.fr.ns;


import jersey.repackaged.com.google.common.cache.CacheBuilder;
import jersey.repackaged.com.google.common.cache.CacheLoader;
import jersey.repackaged.com.google.common.cache.LoadingCache;
import org.inria.fr.ns.adapters.DateAdapter;
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
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Path("cr")
public class CRService {

    @GET
    @Path("/centres")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response getCentre() throws JAXBException, JSONException {
        JSONObject obj= XML.toJSONObject(Cache.getCentreCache());
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }


    @GET
    @Path("/infoCentre")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response getAdresseCentres()throws JAXBException, JSONException{
        JSONObject obj= XML.toJSONObject(Cache.getAdresseCentresCache());
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }
}
