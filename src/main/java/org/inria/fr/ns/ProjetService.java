package org.inria.fr.ns;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.inria.fr.ns.sr.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

@Path("projet")
public class ProjetService {
	@GET
    @Path("/theme")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response returnThemes() throws JAXBException, JSONException {
        JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.sr");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StructureInrias structureInrias = (StructureInrias) unmarshaller.unmarshal(new File("src/main/resources/xml/bastri.xml"));
        String result ="";
        List<Structureinria> santeBio = new ArrayList<Structureinria>();
        List<Structureinria> mathApp = new ArrayList<Structureinria>();
        List<Structureinria> reseSys = new ArrayList<Structureinria>();
        List<Structureinria> pic = new ArrayList<Structureinria>();
        List<Structureinria> apla = new ArrayList<Structureinria>();
        List<Structureinria> others = new ArrayList<Structureinria>();
        for (Structureinria s : structureInrias.getStructureinria()) {
        		for(Domaine domaine : s.getDomaine()) {
        			if(domaine.getLang().equals("fr")) {
        				switch (domaine.getValue()) {
						case "Santé, biologie et planète numériques":
							santeBio.add(s);
							break;
						case "Mathématiques appliquées, calcul et simulation":
							mathApp.add(s);
							break;
						case "Réseaux, systèmes et services, calcul distribué":
							reseSys.add(s);
							break;
						case "Perception, Cognition, Interaction":
							pic.add(s);
							break;
						case "Algorithmique, programmation, logiciels et architectures":
							apla.add(s);
							break;
						default:
							others.add(s);
							break;
						}
        			}
        		}
        }
        
        result += construireLibelleJson(santeBio,"santeBio");
        result += construireLibelleJson(mathApp,"mathApp");
        result += construireLibelleJson(reseSys,"reseSys");
        result += construireLibelleJson(pic,"pic");
        result += construireLibelleJson(apla,"apla");
        result += construireLibelleJson(others,"others");
        
        System.out.println(result);
        JSONObject obj= XML.toJSONObject(result);
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }
	private String construireLibelleJson(List<Structureinria> listS,String listName) {
		String resultat ="";
		//replace "&" to "and", in order to make XML.toJSONObject() work
		for(Structureinria structureinria : listS) {
			StringBuilder sb = new StringBuilder(structureinria.getLibellefr());
			int index;
			if((index = sb.indexOf("&"))!=-1) {
				sb.replace(index, index+1, "and");
			}
			resultat += "<"+listName+">"+sb.toString()+"</"+listName+">\n";
		}
		return resultat;
	}
	public static List<String> getDomaine() {
		List<String> listNomDomaine = null;
		try {
			JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.sr");
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        StructureInrias structureInrias = (StructureInrias) unmarshaller.unmarshal(new File("src/main/resources/xml/bastri.xml"));
	        List<Domaine> listDomaine = new ArrayList<Domaine>();
	        listNomDomaine = new ArrayList<String>();
	        for (Structureinria s : structureInrias.getStructureinria()) {
	        		for(Domaine domaine : s.getDomaine()) {
	        			if(domaine.getLang().equals("fr") && listNomDomaine.indexOf(domaine.getValue())==-1) {
	        				listDomaine.add(domaine);
	        				listNomDomaine.add(domaine.getValue());
	        			}	
	        		}           
	        }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listNomDomaine;
	}

	
	@GET
    @Path("/domaine")
    @Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
    public Response returnDomaine() throws JAXBException, JSONException {
        JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.sr");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StructureInrias structureInrias = (StructureInrias) unmarshaller.unmarshal(new File("src/main/resources/xml/bastri.xml"));
        List<Domaine> listDomaine = new ArrayList<Domaine>();
        List<String> listNomDomaine = new ArrayList<String>();
        String result ="";
        for (Structureinria s : structureInrias.getStructureinria()) {
        		for(Domaine domaine : s.getDomaine()) {
        			if(domaine.getLang().equals("fr") && listNomDomaine.indexOf(domaine.getValue())==-1) {
        				listDomaine.add(domaine);
        				listNomDomaine.add(domaine.getValue());
        			}	
        		}           
        }
        for(int i = 0;i<listDomaine.size();i++) {
        		result += "<domaine>"+listDomaine.get(i).getValue() +"</domaine>\n" ;
        }
        JSONObject obj= XML.toJSONObject(result);
        return Response.status(Response.Status.OK).entity(obj.toString()).build() ;
    }
	public boolean hasDuplicate(List<Domaine> listDomaine,Domaine domaine) {
		
		return false;
	}
	
}
