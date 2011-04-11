
public class HTTPServerTester {
	public static void main(String args[]){
		try{
			HTTPServer server = new HTTPServer(8081);
			server.Accept();
		}catch(Exception e){
			
		}
	}
}
