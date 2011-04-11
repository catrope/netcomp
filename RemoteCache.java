import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Vector;

public class RemoteCache implements ICache
{
	ICache cache;
	HashBalancer hb;
	
	public RemoteCache(Vector<String> serverNames, int localId, ICache localCache)
	{
		String serverName = serverNames.get(localId);
		
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		
		try {
			URL url = new URL("http:" + serverName);
			int port = 1099;
			
			if (url.getPort() > 0)
				port = url.getPort();
			
			LocateRegistry.createRegistry(port);
			System.err.println("RMI on port " + port + "...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			cache = localCache;
			Naming.rebind(serverName, cache);
			hb = new HashBalancer(serverNames);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void clear()
	{
		for (String server : hb.getServers())
		{
			try {
				((ICache)Naming.lookup(server)).clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String get(String key) throws RemoteException
	{
		return getCacheServer(key).get(key);
	}
	
	public void set(String key, String val) throws RemoteException
	{
		getCacheServer(key).set(key, val);
	}
	
	private ICache getCacheServer(String key)
	{
		try {
			return (ICache)Naming.lookup(hb.getServerForKey(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cache;
	}
}
