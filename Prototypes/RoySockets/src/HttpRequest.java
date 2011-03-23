import java.net.*;
import java.io.*;
import java.util.Vector;

public class HttpRequest {
     public static void main(String args[]){
         Vector<EncodedKeyValuePair> props = new Vector<EncodedKeyValuePair>();
         props.add(new EncodedKeyValuePair("MyKey", "MyValue"));
         Request("http://roy-t.nl/services/RESTEcho.php", props);
     }


    public static void Request(String address, Vector<EncodedKeyValuePair> props){
        StringBuilder sb;
        URL url = null;
        BufferedReader reader = null;
        try{
            sb = new StringBuilder(address);

            for(int i = 0; i < props.size(); i++){
                if(i == 0){
                    sb.append('?');
                }
                else
                {
                    sb.append('&');
                }
                sb.append(props.elementAt(i).key);
                sb.append("=");
                sb.append(props.elementAt(i).value);
            }

            url = new URL(sb.toString());

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", EncodedKeyValuePair.Charset);
            connection.setReadTimeout(15000);

            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

            
            reader.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
