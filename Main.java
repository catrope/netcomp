import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Vector;

public class Main
{
	/**
	 * Init using "java -Djava.security.policy=server.policy Main port configfile.txt serverId cacheSize"
	 * where configfile.txt is a newline separated list of RMI
	 * paths, serverId is an integer that denotes the current
	 * instance (by referring to a line in configfile.txt) and
	 * cacheSize is the size of the local cache.
	 * 
	 * If anything fails, a basic cache is used that doesn't use
	 * RMI.
	 */
	public Main(String[] args)
	{
		ICache cache;
		
		try {
			if (args.length < 4) throw new Exception("Usage: java -Djava.security.policy=server.policy Main port configfile.txt serverId cacheSize");
			
			Scanner scanner = new Scanner(new FileInputStream(args[1]));
			Vector<String> serverNames = new Vector<String>();
			
			while (scanner.hasNextLine()) {
				serverNames.add(scanner.nextLine());
			}
			
			cache = new RemoteCache(serverNames, Integer.parseInt(args[2]), new BasicCache(Integer.parseInt(args[3])));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Using basic cache...");
			try {
				cache = new BasicCache();
			} catch (RemoteException e1) {
				cache = null;
				e1.printStackTrace();
			}
		}
		
		int port;
		
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		else
			port = 8081;
		
		System.err.println("Using port " + port + "...");
		
		try {
			HTTPServer server = new HTTPServer(port, cache);
			server.Accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new Main(args);
	}
}
