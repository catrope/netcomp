import java.util.HashMap;

/**
 * Basic FIFO caching class with a default size.
 */
public class BasicCache implements ICache
{
	BasicCachePointer first, last;
	HashMap<String, String> map;
	
	private static final int CACHE_SIZE = 50;
	
	public void init()
	{
		map = new HashMap<String, String>( (int)(CACHE_SIZE * 1.20) , 0.8f);
	}
	
	public String get(String key)
	{
		return map.get(key);
	}
	
	public void set(String key, String val)
	{
		if (!map.containsKey(key))
		{		
			while(map.size() >= CACHE_SIZE - 1 && last.prev != null)
			{
				map.remove(last.key);
				last = last.prev;
			}
			BasicCachePointer oldFirst = first;
			first = new BasicCachePointer(key);
			oldFirst.prev = first;
		}
		map.put(key, val);
	}
	
	private class BasicCachePointer
	{
		public String key;
		public BasicCachePointer prev;
		
		BasicCachePointer(String key)
		{
			this.key = key;
			prev = null;
		}
	}
}