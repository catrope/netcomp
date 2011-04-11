import java.math.BigInteger;
import java.security.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class HashBalancer {
	protected Vector<String> servers;
	
	public HashBalancer(Vector<String> servers) {
		this.servers = new Vector<String>(servers);
	}
	
	public HashBalancer(String[] servers) {
		this.servers = new Vector<String>(Arrays.asList(servers));
	}
	
	public Vector<String> getServers() {
		return servers;
	}
	
	public int getNumberOfServers() {
		return servers.size();
	}
	
	public void addServer(String srv) {
		servers.add(srv);
	}
	
	public void removeServer(String srv) {
		servers.remove(srv);
	}
	
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
