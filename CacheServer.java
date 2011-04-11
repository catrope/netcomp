import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI wrapper for a cache server
 */
public class CacheServer extends UnicastRemoteObject implements ICache
{
	private static final long serialVersionUID = 1L;
	
	final ICache localCache;
	
	public CacheServer(ICache localCache) throws RemoteException
	{
		this.localCache = localCache;
	}
	
	public void clear()
	{
		localCache.clear();
	}
	
	public String get(String key)
	{
		return localCache.get(key);
	}

	public void set(String key, String val)
	{
		localCache.set(key, val);
	}
}
