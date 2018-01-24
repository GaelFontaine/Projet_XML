package org.inria.fr.ns.cache;

import org.inria.fr.ns.XQueryUtil;
import org.inria.fr.ns.cr.Crs;
import org.inria.fr.ns.models.CentreDeRecherche;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQResultSequence;
import java.io.File;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cache {

    private static long cacheTimestamp = 0;
    private static long currentTimestamp = System.currentTimeMillis();

    private static String centresCache = "";
    private static String adressesCache = "";
    private static ArrayList<CentreDeRecherche> infoCentreCache = new ArrayList<CentreDeRecherche>();

    public static void loadCache() throws JAXBException, JSONException {
        Cache.setTimestamp();
        if(Cache.cacheTimestamp == 0 || Cache.currentTimestamp - Cache.cacheTimestamp > 86400000){
            Cache.cacheTimestamp = Cache.currentTimestamp;
            // On réinitialise l'ensemble des variables du cache
            Cache.centresCache  = "";
            Cache.adressesCache = "";
            infoCentreCache.clear();
            JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.cr");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Crs centres = (Crs) unmarshaller.unmarshal(new File("src/main/resources/xml/bastriCris.xml"));
            // On sauvegarde dans une variable static le contenu des centres qu'on fait persister dans la mémoire de l'ordinateur pendant 24h avant de le recharger
            for (Crs.Cr c : centres.getCr()) {
                centresCache += "<Centre>"+c.getLibelle() +"</Centre>\n" ;
            }
            // On sauvegarde dans une variable static le contenu des adresses qu'on fait persister dans la mémoire de l'ordinateur pendant 24h avant de le recharger
            for (Crs.Cr c : centres.getCr()) {
                adressesCache += "\n<Ville>"+c.getAdressegeographique().getVille()+"</Ville>\n" ;
                adressesCache += "\n<Latitude>"+c.getAdressegeographique().getLatitude() +"</Latitude>\n" ;
                adressesCache += "\n<Longitude>"+c.getAdressegeographique().getLongitude() +"</Longitude>\n" ;
            }
            // On sauvegarde dans une variable static le contenu des infoCentre qu'on fait persister dans la mémoire de l'ordinateur pendant 24h avant de le recharger
            XQueryUtil queryUtil = new XQueryUtil();
            XQResultSequence res = null;
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
                    Cache.infoCentreCache.add(thisCr);
                }
            } catch (XQException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Le cache est encore d'actualité");
        }
    }

    public static String getCentreCache() throws JAXBException, JSONException {
        Cache.loadCache();
        return Cache.centresCache;
    }

    public static String getAdresseCentresCache() throws JAXBException, JSONException {
        Cache.loadCache();
        return Cache.adressesCache;
    }
    public static List<CentreDeRecherche> getAllCRCache() throws JAXBException, JSONException {
        Cache.loadCache();
        return Cache.infoCentreCache;
    }


    private static void setTimestamp(){
        Cache.currentTimestamp = System.currentTimeMillis();
    }
}
