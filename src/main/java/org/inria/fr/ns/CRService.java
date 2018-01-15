package org.inria.fr.ns;


import org.inria.fr.ns.models.CentreDeRecherche;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.xquery.XQCommonHandler;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQResultSequence;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.TEXT_HTML;


@Path("cr")
public class CRService {
    private XQueryUtil queryUtil;

    @GET
    @Path("participants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Participant> getAllParticipants() {
         queryUtil = new XQueryUtil();
        XQResultSequence res = null;
        List<Participant> participants = new ArrayList<>();
        try {
            queryUtil.connect();
            queryUtil.setXQueryReq("for $x in doc('/db/raweb/algo.xml')/raweb/identification/team/participants/person return $x");
            res = queryUtil.getResult();

            JAXBContext jaxbContext = JAXBContext.newInstance(Participant.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            while(res.next()){
                XQItem item = res.getItem();
                System.out.println(item.getItemAsString(null));
                Participant thisParticipant = (Participant) jaxbUnmarshaller.unmarshal(item.getNode());
                participants.add(thisParticipant);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return  participants;
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
            queryUtil.setXQueryReq("for $i in doc('/db/raweb/bastriCris.xml')//cr return <infoCentre>{$i/numnatstructrep,$i/date_ouverture,$i/sigle, $i/libelle,$i/idgef,<AddGeo>{$i/adressegeographique/ville,$i/adressegeographique/latitude,$i/adressegeographique/longitude}</AddGeo>,<nbPers>{count($i/responsable)}</nbPers>}</infoCentre>");
            res = queryUtil.getResult();

            JAXBContext jaxbContext = JAXBContext.newInstance(CentreDeRecherche.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            while(res.next()){
                XQItem item = res.getItem();
                System.out.println(item.getItemAsString(null));
                CentreDeRecherche thisCr = (CentreDeRecherche) jaxbUnmarshaller.unmarshal(item.getNode());
                cr.add(thisCr);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return  cr;
    }

}
