/**
 * Basic cache functions. Caching does not guarantee the data is actually
 * stored.
 */
public interface ICache
{
	/**
	 * Get a string from the cache based on the key.
	 * @return Requested string, or null when not found
	 */
	public String get(String key);
	
	/**
	 * Store a string in the cache.
	 */
	public void set(String key, String val);
	
	/**
	 * Clear the cache.
	 */
	public void clear();
}