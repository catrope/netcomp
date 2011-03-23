//Class that encapsulates request headers
import java.io.UnsupportedEncodingException;
import java.net.*;

public class EncodedKeyValuePair {
    public static final String Charset = "UTF-8";

    public EncodedKeyValuePair(String key, String value){
        try{
        this.key = URLEncoder.encode(key, Charset);
        this.value = URLEncoder.encode(value, Charset);
        }
        catch(UnsupportedEncodingException ex){
            this.key = key;
            this.value = value;
        }
    }
    public String key;
    public String value;
}