package org.inria.fr.ns;


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
        JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.cr");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Crs centres = (Crs) unmarshaller.unmarshal(new File("src/main/resources/xml/bastriCris.xml"));

        String result ="";
        for (Crs.Cr c : centres.getCr()) {
            result += "<Centre>"+c.getLibelle() +"</Centre>\n" ;
        }
        JSONObject obj= XML.toJSONObject(result);
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }

    @GET
    @Path("/centres-adresses")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response getAdresseCentres()throws JAXBException, JSONException{
        JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.cr");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Crs centres = (Crs) unmarshaller.unmarshal(new File("src/main/resources/xml/bastriCris.xml"));

        String result ="";
        for (Crs.Cr c : centres.getCr()) {
            result += "\n<Ville>"+c.getAdressegeographique().getVille()+"</Ville>\n" ;
            result += "\n<Latitude>"+c.getAdressegeographique().getLatitude() +"</Latitude>\n" ;
            result += "\n<Longitude>"+c.getAdressegeographique().getLongitude() +"</Longitude>\n" ;
        }
        JSONObject obj= XML.toJSONObject(result);
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }


    @GET
    @Path("infoCentre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CentreDeRecherche> getAllCR(){
        queryUtil = new XQueryUtil();
        XQResultSequence res = null;
        List<CentreDeRecherche> cr = new ArrayList<>();
        try {
            queryUtil.connect();
            queryUtil.setXQueryReq("for $i in doc('db/raweb/bastriCris.xml')//cr return <infoCentre>{$i/numnatstructrep,$i/date_ouverture,$i/sigle, $i/libelle,$i/idgef,<AddGeo>{$i/adressegeographique/ville,$i/adressegeographique/latitude,$i/adressegeographique/longitude}</AddGeo>,<nbPers>{count($i/responsable)}</nbPers>}</infoCentre>");
            res = queryUtil.getResult();

            JAXBContext jaxbContext = JAXBContext.newInstance(CentreDeRecherche.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            while(res.next()){
                XQItem item = res.getItem();
                System.out.println(item.getItemAsString(null));
                CentreDeRecherche thisCr = (CentreDeRecherche) jaxbUnmarshaller.unmarshal(item.getNode());
/*
                NodeList childNodes = item.getNode().getChildNodes();

                final Node childNode = childNodes.item(3);
                thisCr.setDate_ouverture(new Date(childNode.getTextContent()));
                */
                cr.add(thisCr);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return  cr;
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
