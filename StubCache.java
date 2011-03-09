/**
 * Stub cache class that does not do anything.
 */
public class StubCache implements ICache
{
	public String get(String key) { return null; }
	public void set(String key, String val) {}
}