import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RemoteCache implements ICache
{
	CacheServer cache;
	
	public RemoteCache(String[] serverNames, int localId, ICache localCache)
	{
		try {
			cache = new CacheServer(localCache);
			Naming.rebind(serverNames[localId], cache);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void clear()
	{
		// TODO: request the list of server names from the hash
		// generator and call them all
	}
	
	public String get(String key)
	{
		return getCacheServer(key).get(key);
	}
	
	public void set(String key, String val)
	{
		getCacheServer(key).set(key, val);
	}
	
	private ICache getCacheServer(String key)
	{
		// TODO: use hash generator
		return cache;
	}
}
