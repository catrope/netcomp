import java.math.BigInteger;
import java.security.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

/**
 * Manages a set of caching servers and load balances them using key hashing
 * @author Roan Kattouw
 */
public class HashBalancer {
	protected Vector<String> servers;
	
	/**
	 * Constructor
	 * @param servers Vector of server names
	 */
	public HashBalancer(Vector<String> servers) {
		this.servers = new Vector<String>(servers);
	}
	
	/**
	 * Constructor
	 * @param servers Array of server names
	 */
	public HashBalancer(String[] servers) {
		this.servers = new Vector<String>(Arrays.asList(servers));
	}
	
	/**
	 * Get a list of all servers managed by this object
	 * @return Vector of server names
	 */
	public Vector<String> getServers() {
		return servers;
	}
	
	/**
	 * Get the number of servers managed by this object.
	 */
	public int getNumberOfServers() {
		return servers.size();
	}
	
	/**
	 * Add a server to be managed by this object
	 * @param srv Server name
	 */
	public void addServer(String srv) {
		servers.add(srv);
	}
	
	/**
	 * Remove a server from this object
	 * @param srv Server name
	 */
	public void removeServer(String srv) {
		servers.remove(srv);
	}
	
	/**
	 * Find out which server should have a given key. This is the
	 * hash-based load balancing part
	 * @param key Cache key
	 * @return Server name
	 */
	public String getServerForKey(String key) {
		return servers.get(getIndexForKey(key));
	}
	
	protected int getIndexForKey(String key) {
		try {
			byte[] byteArr = key.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(byteArr);
			BigInteger h = new BigInteger(1, hash);
			return h.mod(BigInteger.valueOf(servers.size())).intValue();
		} catch(Exception e) {
			// Java throws some stupid exceptions in case UTF-8 or MD5
			// aren't supported. This is never realistically gonna happen,
			// but just in case let's use a randomized balancer in that case.
			Random generator = new Random();
			return generator.nextInt(servers.size());
		}
	}
}
