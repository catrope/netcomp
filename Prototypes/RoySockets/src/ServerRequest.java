import java.io.Serializable;

public class ServerRequest implements Serializable{
    public ServerRequest(String requestKey){
        this.requestKey = requestKey;
    }

    private String requestKey;
    public String getRequestKey() {
        return requestKey;
    }
}
