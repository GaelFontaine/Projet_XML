package org.inria.fr.ns;

import org.inria.fr.ns.cr.Crs;
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
    private XQueryUtil queryUtil;

    @GET
    @Path("nbpers-par-centre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stat> getInfo() throws XQException {
        queryUtil = new XQueryUtil();
        XQResultSequence res = null;
        List<Stat> list = new ArrayList<>();
        try {
            queryUtil.connect();
            queryUtil.setXQueryReq("for $x in distinct-values(collection(\"/db/raweb\")//research-centre) for $i in $x let $nbp:=  count(for $x in collection(\"/db/raweb\")//team where $x/person/research-centre=$i return $x) return <info><parent>{$x}</parent><occurrence>{$nbp}</occurrence></info>");
            res = queryUtil.getResult();

            JAXBContext jaxbContext = JAXBContext.newInstance(Stat.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            while(res.next()){
                XQItem item = res.getItem();
                System.out.println(item.getItemAsString(null));
                Stat thisStat = (Stat) jaxbUnmarshaller.unmarshal(item.getNode());

                list.add(thisStat);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return  list;
    }

    @GET
    @Path("nbequipe-par-centre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stat> getInfoBis() throws XQException {
        queryUtil = new XQueryUtil();
        XQResultSequence res = null;
        List<Stat> list = new ArrayList<>();
        try {
            queryUtil.connect();
            queryUtil.setXQueryReq("for $x in distinct-values(collection(\"/db/raweb\")//research-centre) for $i in $x let $nbp:=  count(for $x in collection(\"/db/raweb\")//team where $x/person/research-centre=$i return $x) return <info><parent>{$x}</parent><occurrence>{$nbp}</occurrence></info> ");
            res = queryUtil.getResult();

            JAXBContext jaxbContext = JAXBContext.newInstance(Stat.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            while(res.next()){
                XQItem item = res.getItem();
                System.out.println(item.getItemAsString(null));
                Stat thisStat = (Stat) jaxbUnmarshaller.unmarshal(item.getNode());

                list.add(thisStat);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return  list;
    }

    

    /*
    @GET
    @Path("/nb-personne-par-centre")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response getPersonne() throws JAXBException, JSONException {
        JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.sr");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StructuresInria equipes = (StructuresInria) unmarshaller.unmarshal(new File("src/main/resources/xml/bastri.xml"));


        JAXBContext jc1 = JAXBContext.newInstance("org.inria.fr.ns.cr");
        Unmarshaller unmarshaller1 = jc1.createUnmarshaller();
        Crs centres = (Crs) unmarshaller.unmarshal(new File("src/main/resources/xml/bastriCris.xml"));

        String result ="";
        for (StructureInria e : equipes.getStructureinria()) {
            result += "<equipe>" ;
            result += "<nb>"+e
            result += "<sigle>"+e.getSigle()+"</sigle>";
            result += "<centre>"+e.getEntite().get(0).getAdressegeographique().getCri().getValue()+"</centre>";
            result += "<domaine>"+e.getDomaine().get(0).getValue()+"</domaine>";
            result += "<theme>"+e.getTheme().get(0).getValue()+"</theme>";
            result += "</equipe>";
        }
        JSONObject obj= XML.toJSONObject(result);
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }*/
}
