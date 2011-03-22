import java.io.Serializable;

//Class that encapsulate the request a client makes to a server
public class ClientRequest implements Serializable{
    public ClientRequest(String requestKey){
        this.requestKey = requestKey;
    }

    //The key to the requested information
    private String requestKey;
    public String getRequestKey() {
        return requestKey;
    }
}
