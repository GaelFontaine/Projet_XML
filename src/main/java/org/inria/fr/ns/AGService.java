package org.inria.fr.ns;

import org.inria.fr.ns.sr.Adressegeographique;
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

@Path("ag")
public class AGService {
    private XQueryUtil queryUtil;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Adressegeographique> getAllAdressegeographique() {
        queryUtil = new XQueryUtil();
        XQResultSequence res = null;
        List<Adressegeographique> ads = new ArrayList<>();
        try {
            queryUtil.connect();
            queryUtil.setXQueryReq("for $i in doc('/db/raweb/bastriCris.xml')//cr return $i/adressegeographique");
            res = queryUtil.getResult();

            JAXBContext jaxbContext = JAXBContext.newInstance(Adressegeographique.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            while(res.next()){
                XQItem item = res.getItem();
                System.out.println(item.getItemAsString(null));
                Adressegeographique thisAdressegeographique = (Adressegeographique) jaxbUnmarshaller.unmarshal(item.getNode());
                ads.add(thisAdressegeographique);
            }
        } catch (XQException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return  ads;
    }
}
