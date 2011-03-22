import java.io.Serializable;

//Class that encapsulates the response a server gives to a client
public class ServerResponse implements  Serializable{

    //Construct an invalid server response
    //(for when the server has no value given the key)
    public ServerResponse(){
        this.isValid = false;
        this.response = "";
    }

    //Construct a valid server response
    public ServerResponse(String response){
        this.isValid = true;
        this.response = response;
    }

    //True if the response has meaning/value
    private boolean isValid;
    public boolean GetValidity(){
        return isValid;
    }

    //The server response
    private String response;
    public String getResponse(){
        return response;
    }
}
