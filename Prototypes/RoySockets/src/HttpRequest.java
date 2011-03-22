import org.omg.CORBA.Request;

import java.net.*;
import java.io.*;
import java.util.Vector;

public class HttpRequest {
     public static void main(String args[]){
         Vector<RequestProperty> props = new Vector<RequestProperty>();
         props.add(new RequestProperty("Accept", "text/html"));
         Request("http://www.google.nl", props);
     }


    public static void Request(String address, Vector<RequestProperty> props){
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
        try{
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            //Add request headers
            for(RequestProperty prop : props){
                connection.addRequestProperty(prop.key,  prop.value);
            }

            connection.setReadTimeout(15000);
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }        
    }
}
