import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Main
{
	ICache cache;
	
	/**
	 * Init using "java Main configfile.txt serverId cacheSize"
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
		try {
			Scanner scanner = new Scanner(new FileInputStream(args[1]));
			Vector<String> serverNames = new Vector<String>();
			
			while (scanner.hasNextLine()) {
				serverNames.add(scanner.nextLine());
			}
			
			cache = new RemoteCache((String[])serverNames.toArray(), Integer.parseInt(args[2]), new BasicCache(Integer.parseInt(args[3])));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Using basic cache...");
			cache = new BasicCache();
		}
		
		// TODO: instantiate HTTP server
	}
	
	public static void main(String[] args)
	{
		new Main(args);
	}
}
