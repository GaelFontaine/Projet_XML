package org.inria.fr.ns.cache;

import org.inria.fr.ns.XQueryUtil;
import org.inria.fr.ns.cr.Crs;
import org.inria.fr.ns.models.CentreDeRecherche;
import org.inria.fr.ns.models.CentreInfo;
import org.inria.fr.ns.models.Stat;
import org.inria.fr.ns.sr.StructureInria;
import org.inria.fr.ns.sr.StructuresInria;
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
    private static String equipesParCentreDomaineThemeCache = "";
    private static List<Stat> nbPersonneCentreCache = new ArrayList<>();
    private static List<Stat> nbEquipeParCentreCache = new ArrayList<>();
    private static List<CentreInfo> equipesParCentreCache = new ArrayList<>();


    public static void loadCache() throws JAXBException, JSONException {
        Cache.setTimestamp();
        if(Cache.cacheTimestamp == 0 || Cache.currentTimestamp - Cache.cacheTimestamp > 86400000){
            Cache.cacheTimestamp = Cache.currentTimestamp;
            Cache.centresCache  = "";
            Cache.adressesCache = "";
            Cache.equipesParCentreDomaineThemeCache = "";
            Cache.nbPersonneCentreCache.clear();
            Cache.nbEquipeParCentreCache.clear();
            Cache.equipesParCentreCache.clear();
            // On réinitialise l'ensemble des variables du cache
            /*-----------------------------------------------CACHE CRSERVICE-------------------------------------------*/
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
            /*-----------------------------------------------CACHE STATSERVICE-------------------------------------------*/
            XQueryUtil queryUtil = new XQueryUtil();
            XQResultSequence res = null;
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
                    Cache.nbPersonneCentreCache.add(thisStat);
                }
            } catch (XQException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            res = null;
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

                    Cache.nbEquipeParCentreCache.add(thisStat);
                }
            } catch (XQException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            res = null;
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

                    Cache.equipesParCentreCache.add(thisStat);
                }
            } catch (XQException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            /*-----------------------------------------------CACHE EPSERVICE-------------------------------------------*/
            JAXBContext jcs = JAXBContext.newInstance("org.inria.fr.ns.sr");
            Unmarshaller unmarshallers = jcs.createUnmarshaller();
            StructuresInria equipes = (StructuresInria) unmarshallers.unmarshal(new File("src/main/resources/xml/bastri.xml"));

            for (StructureInria e : equipes.getStructureinria()) {
                Cache.equipesParCentreDomaineThemeCache += "<equipe>" ;
                Cache.equipesParCentreDomaineThemeCache += "<id>"+e.getSiidEquipeGroupe()+"</id>";
                Cache.equipesParCentreDomaineThemeCache += "<nomRes>"+e.getEntite().get(0).getPersonne().getNom()+"</nomRes>";
                Cache.equipesParCentreDomaineThemeCache += "<prenomRes>"+e.getEntite().get(0).getPersonne().getPrenom()+"</prenomRes>";
                Cache.equipesParCentreDomaineThemeCache += "<sigle>"+e.getSigle()+"</sigle>";
                Cache.equipesParCentreDomaineThemeCache += "<centre>"+e.getEntite().get(0).getAdressegeographique().getCri().getValue()+"</centre>";
                Cache.equipesParCentreDomaineThemeCache += "<domaine>"+e.getDomaine().get(0).getValue()+"</domaine>";
                Cache.equipesParCentreDomaineThemeCache += "<theme>"+e.getTheme().get(0).getValue()+"</theme>";
                Cache.equipesParCentreDomaineThemeCache += "</equipe>";
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

    public static List<Stat> getNbPersonneCentreCache() throws JAXBException, JSONException {
        Cache.loadCache();
        return Cache.nbPersonneCentreCache;
    }

    public static List<Stat> getNbEquipeCentreCache() throws JAXBException, JSONException {
        Cache.loadCache();
        return Cache.nbEquipeParCentreCache;
    }

    public static List<CentreInfo> getEquipesParCentreCache() throws JAXBException, JSONException {
        Cache.loadCache();
        return Cache.equipesParCentreCache;
    }

    public static String getEquipesParCentreDomaineThemeCache() throws JAXBException, JSONException {
        Cache.loadCache();
        return Cache.equipesParCentreDomaineThemeCache;
    }


    private static void setTimestamp(){
        Cache.currentTimestamp = System.currentTimeMillis();
    }
}
