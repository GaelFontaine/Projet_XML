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

    @GET
    @Produces("text/xml")
    public String getWelcome() throws IOException {
        String chaine="";

        try{

            URL url =new URL("http://localhost:8080/exist/rest/raweb/liste-projets");
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
}
