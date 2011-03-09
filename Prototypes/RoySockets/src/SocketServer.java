import java.net.*;
import java.io.*;

//Simple class that reads a serializable object from a socket
//TODO: implement sending a response back.
public class SocketServer {
    public static void main(String args[]){
        int port = 2011;
        ServerSocket socket;
        try{
            socket = new ServerSocket(port);
            socket.setSoTimeout(0); //infinite timeout
            Socket connection = socket.accept();
            ObjectInputStream oIn = new ObjectInputStream(connection.getInputStream());
            
            ServerRequest request = (ServerRequest)oIn.readObject();
            if(request != null){
                System.out.println(request.getRequestKey());
            }

            oIn.close();
            socket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
