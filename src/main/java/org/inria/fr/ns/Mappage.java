package org.inria.fr.ns;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



@Path("/")
public class Mappage {



/*
    @GET
    @Produces("text/xml")
    public String getWelcome() throws XQException {
        String result = "";
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");

        XQConnection conn = xqs.getConnection();

        XQPreparedExpression xqpe
                = conn.prepareExpression("declare variable $x as xs:string external; $x");

        xqpe.bindString(new QName("x"), "Hello World!", null);

        XQResultSequence rs = xqpe.executeQuery();

        while (rs.next()) {
            //System.out.println(rs.getItemAsString(null));
            result += rs.getItemAsString(null);
        }

        conn.close();
        return result;
    }*/

/*

    @GET
    @Produces("text/xml")
    public String getWelcome() throws IOException {
        String chaine="";

        try {

            URL url =new URL("/home/thiaw/MIAGE_TP_XML_2017-master/tp7/projetmgag/src/main/resources/bastriCris.xml");
            URLConnection connecttion=url.openConnection();
            BufferedReader buff= new  BufferedReader(new InputStreamReader(connecttion.getInputStream()));
            String ligne;
            while ((ligne=buff.readLine())!=null){
                System.out.println(ligne);
                chaine+=ligne+"\n";
            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
      return chaine;
    }

*/
    /*
    @GET
    @Path("/centresderecherche")
    @Produces("text/plain")
    public List<String>  getCentre() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("org.inria.fr.ns.cr");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Crs centres = (Crs) unmarshaller.unmarshal(new File("src/main/resources/xml/bastriCris.xml"));

        String result ="";
        for (Cr c : centres.getCr()) {
            listCr.add(c.getLibelle());
            result +=c.getLibelle()+"\n" ;
        }
        return  listCr;
    }
    final List<String> listCr=new ArrayList<String>();
    */

}
