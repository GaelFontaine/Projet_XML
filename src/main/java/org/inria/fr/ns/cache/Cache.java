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

    public static void loadCache() throws JAXBException, JSONException {
        Cache.setTimestamp();
        if(Cache.cacheTimestamp == 0 || Cache.currentTimestamp - Cache.cacheTimestamp > 86400000){
            Cache.cacheTimestamp = Cache.currentTimestamp;
            // On réinitialise l'ensemble des variables du cache
            Cache.centresCache  = "";
            Cache.adressesCache = "";
            JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.cr");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Crs centres = (Crs) unmarshaller.unmarshal(new File("src/main/resources/xml/bastriCris.xml"));
            // On sauvegarde dans une variable static le contenu des centres qu'on fait persister dans la mémoire de l'ordinateur pendant 24h avant de le recharger
            for (Crs.Cr c : centres.getCr()) {
                centresCache += "<Centre>"+c.getDateOuverture() +"</Centre>\n" ;
            }
            // On sauvegarde dans une variable static le contenu des adresses qu'on fait persister dans la mémoire de l'ordinateur pendant 24h avant de le recharger
            for (Crs.Cr c : centres.getCr()) {
                Cache.adressesCache += "\n<numnatstructrep>"+c.getAdressegeographique().getVille()+"</numnatstructrep>\n" ;
                Cache.adressesCache += "\n<date_ouverture>"+c.getAdressegeographique().getVille()+"</date_ouverture>\n" ;
                Cache.adressesCache += "\n<sigle>"+c.getAdressegeographique().getVille()+"</sigle>\n" ;
                Cache.adressesCache += "\n<libelle>"+c.getAdressegeographique().getVille()+"</libelle>\n" ;
                Cache.adressesCache += "\n<idgef>"+c.getAdressegeographique().getVille()+"</idgef>\n" ;
                Cache.adressesCache += "\n<Latitude>"+c.getAdressegeographique().getLatitude() +"</Latitude>\n" ;
                Cache.adressesCache += "\n<Longitude>"+c.getAdressegeographique().getLongitude() +"</Longitude>\n" ;
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


    private static void setTimestamp(){
        Cache.currentTimestamp = System.currentTimeMillis();
    }
}
