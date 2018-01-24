package org.inria.fr.ns;

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

    @GET
    @Path("equipe-par-centre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CentreInfo> getInfoGen() throws XQException {
        queryUtil = new XQueryUtil();
        XQResultSequence res = null;
        List<CentreInfo> list = new ArrayList<>();
        try {
            queryUtil.connect();
            queryUtil.setXQueryReq("<liste_equipes_centre><centre nom=\"Bordeaux\">{ for $i in (collection(\"/db/raweb\")//identification)where $i/UR [@name=\"Bordeaux\"]return <equipe><nom>{data($i/shortname)}</nom><nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme> <domaine>{data($i/domaine-de-recherche)}</domaine></equipe>} </centre><centre nom=\"Grenoble\">{for $i in (collection(\"/db/raweb\")//identification)where $i/UR [@name=\"Grenoble\"]return <equipe><nom>{data($i/shortname)}</nom><nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme><domaine>{data($i/domaine-de-recherche)}</domaine></equipe>}</centre><centre nom=\"Lille\">{for $i in (collection(\"/db/raweb\")//identification) where $i/UR [@name=\"Lille\"]return  <equipe><nom>{data($i/shortname)}</nom><nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme><domaine>{data($i/domaine-de-recherche)}</domaine></equipe>}</centre> <centre nom=\"Nancy\">{for $i in (collection(\"/db/raweb\")//identification)where $i/UR [@name=\"Nancy\"]return <equipe><nom>{data($i/shortname)}</nom><nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme><domaine>{data($i/domaine-de-recherche)}</domaine></equipe>}</centre> <centre nom=\"Paris\">{for $i in (collection(\"/db/raweb\")//identification)where $i/UR [@name=\"Paris\"]return <equipe> <nom>{data($i/shortname)}</nom> <nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme> <domaine>{data($i/domaine-de-recherche)}</domaine> </equipe>}</centre><centre nom=\"Rennes\">{for $i in (collection(\"/db/raweb\")//identification)where $i/UR [@name=\"Rennes\"] return <equipe><nom>{data($i/shortname)}</nom><nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme><domaine>{data($i/domaine-de-recherche)}</domaine> </equipe>}</centre> <centre nom=\"Saclay\">  {  for $i in (collection(\"/db/raweb\")//identification)  where $i/UR [@name=\"Saclay\"] return <equipe><nom>{data($i/shortname)}</nom><nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme><domaine>{data($i/domaine-de-recherche)}</domaine></equipe> }</centre><centre nom=\"Sophia\">{for $i in (collection(\"/db/raweb\")//identification) where $i/UR [@name=\"Sophia\"]return <equipe><nom>{data($i/shortname)}</nom><nom_projet>{data($i/projectName)}</nom_projet><theme>{data($i/theme-de-recherche)}</theme><domaine>{data($i/domaine-de-recherche)}</domaine></equipe>}</centre></liste_equipes_centre>");

            res = queryUtil.getResult();

            JAXBContext jaxbContext = JAXBContext.newInstance(CentreInfo.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            while(res.next()){
                XQItem item = res.getItem();
                System.out.println(item.getItemAsString(null));
                CentreInfo thisStat = (CentreInfo) jaxbUnmarshaller.unmarshal(item.getNode());

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
