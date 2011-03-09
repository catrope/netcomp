import java.util.HashMap;

/**
 * Basic FIFO caching class with a default size.
 */
public class BasicCache implements ICache
{
	BasicCacheNode first, last;
	HashMap<String, String> map;
	int size;
	
	/**
	 * Inits the cache with a certain size.
	 */
	public BasicCache(int size)
	{
		this.size = size;
		
		// Make sure the hashing algorithm is fast enough (0.8 is quite low),
		// but make sure we will not reallocate (1.3*0.8 = 1.04)
		map = new HashMap<String, String>( (int)(size * 1.30) , 0.8f);
	}
	
	/**
	 * Default constructor, makes a cache with size 50.
	 */
	public BasicCache()
	{
		this(50);
	}
	
	public String get(String key)
	{
		return map.get(key);
	}
	
	public void set(String key, String val)
	{
		// if the key is not in the map yet, some extra work needs to be done
		if (!map.containsKey(key))
		{
			// if the cache is full, remove the last element from the map (FIFO)
			while (map.size() >= size - 1 && last.prev != null)
			{
				map.remove(last.key);
				last = last.prev;
			}
			
			if (first == null)
			{
				// if this is the first time a value is set, init first and last
				first = new BasicCacheNode(key);
				last = first;
			}
			else
			{
				// normally, store first, set the new one, and have the old one point
				// to the new one
				BasicCacheNode oldFirst = first;
				first = new BasicCacheNode(key);
				oldFirst.prev = first;
			}
		}
		map.put(key, val);
	}
	
	/**
	 * Simple linked list node class
	 */
	private class BasicCacheNode
	{
		public String key;
		public BasicCacheNode prev;
		
		BasicCacheNode(String key)
		{
			this.key = key;
			prev = null;
		}
	}
}