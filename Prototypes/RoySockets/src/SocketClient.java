import java.net.*;
import java.io.*;

//Simple class that writes a serializable object to a socket
//TODO: receive a response
public class SocketClient {
    public static void main(String args[]){
        try{

            Socket s = new Socket("localhost", 2011);
            ObjectOutputStream oOut = new ObjectOutputStream(s.getOutputStream());

            ClientRequest request = new ClientRequest("my request is 1");
            oOut.writeObject(request);

            oOut.close();
            s.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
