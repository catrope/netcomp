import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HTTPServer {
	final ICache cache;
	
	public HTTPServer(int port) {
		this(port, new BasicCache());
	}
	
	public HTTPServer(int port, ICache cache){
		this.port = port;
		this.cache = cache;
	}
	
	public void Accept() throws IOException{
		ServerSocket socket = null;
		try{
			socket = new ServerSocket(port);
			socket.setSoTimeout(0); //infinite timeout
			while(true){				
				Socket connection = socket.accept();
				
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				DataOutputStream output = new DataOutputStream(connection.getOutputStream());
				
				HandleRequest(input, output);
				
				connection.close();
			}			
		}catch(Exception e){
			if(socket != null){ socket.close(); }
		}
	}
	
	private void HandleRequest(BufferedReader input, DataOutputStream output) throws IOException {

		String methodString = input.readLine().toUpperCase();
		if(methodString.startsWith("GET")){
			HandleGET(methodString.substring(4),input, output);
		}else if(methodString.startsWith("POST")){
			HandlePOST(methodString.substring(5),input, output);
		}
	}
	
	private void HandleGET(String properties, BufferedReader input, DataOutputStream output) throws IOException {
		/*
			GET /path/file.html HTTP/1.0
			From: someuser@jmarshall.com
			User-Agent: HTTPTool/1.0			
		 */
		String[] parts = properties.split(" ");
		String data = cache.get(parts[0]);
		System.err.println("Retrieving '" + parts[0] + "'...");
		WriteOKResponseHeader(data, output);
	}
	
	private void HandlePOST(String properties, BufferedReader input, DataOutputStream output) throws IOException {
		/*
			POST /path/script.cgi HTTP/1.0
			From: frog@jmarshall.com
			User-Agent: HTTPTool/1.0
			Content-Type: application/x-www-form-urlencoded
			Content-Length: 32
			
			home=Cosby&favorite+flavor=flies
		 */	
		String[] parts = properties.split(" ");
		
		String content = "";
		String line;		
		
		int contentLength = 0;
		while((line = input.readLine()) != null){
			if(line.startsWith("Content-Length: ")){
				contentLength = Integer.parseInt(line.substring(16));
			}
			
			if(line.equals("")){				
				content = ReadContent(contentLength, input);
				break;
			}
		}
		
		System.err.println("Setting '" + parts[0] + "' to '" + content + "'...");
		cache.set(parts[0], content);
		
		WriteOKResponseHeader("", output);
	}
	
	private String ReadContent(int contentLength, BufferedReader input) throws IOException{
		String content = "";
		String line;
		while((line = input.readLine()) != null){
			content += line + "\n";
			if(content.length() >= contentLength){
				break;
			}
		}
		return content.substring(0, contentLength);
	}

	private void WriteOKResponseHeader(String data, DataOutputStream output) throws IOException{
		StringBuffer response = new StringBuffer();
		response.append("HTTP/1.0 200 OK \n"); //Append HTTP/1.0 200 OK
		
		int length = 0;
		if(data != null && !data.equals("")){
			length = data.length();
		}
		
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy HH:mm:ss z");
		Date date = new Date();
		
		response.append("Date: ");
		response.append(dateFormat.format(date)); //Append Date: Fri, 31 Dec 1999 23:59:59 GMT
		response.append("\n");
		response.append("Content-Type: text/plain; charset=utf-8\n"); //Append Content-Type: text/plain; charset=utf-8
		response.append("Content-Length: " + length + "\n"); //Append Content-Length: 1354
		response.append("Connection: close\n\n"); //Append Connection: close
		response.append(data);
		response.append("\n");
		output.writeUTF(response.toString());
	}
	
	private int port;
}
