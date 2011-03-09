import java.util.HashMap;
import java.util.LinkedList;

/**
 * Basic FIFO(-ish) caching class with a default size.
 */
public class BasicCache implements ICache
{
	LinkedList<String> list; // list to implement FIFO
	HashMap<String, String> map; // map to look up values
	int size; // size of the cache
	
	public BasicCache(int size)
	{
		// size must be sane
		if (size < 1) size = 1;
		
		// set the size of the cache
		this.size = size;
		
		// Make sure the hashing algorithm is fast enough (0.8 is quite low),
		// but make sure we will not reallocate (1.3*0.8 = 1.04)
		map = new HashMap<String, String>( (int)(size * 1.30) , 0.8f);
		
		// build a simple linked list
		list = new LinkedList<String>();
	}
	
	public BasicCache()
	{
		// default to size 50
		this(50);
	}
	
	public String get(String key)
	{
		// retrieve the value from the map
		return map.get(key);
	}
	
	public void set(String key, String val)
	{
		// if the key is not in the map yet, some extra work needs to be done
		if (!map.containsKey(key))
		{
			// if the cache is full, remove the last element from the map (FIFO)
			while (list.size() >= size) map.remove(list.removeLast());
			list.addFirst(key);
		}
		
		// store the value in the map
		map.put(key, val);
	}
}