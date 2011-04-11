import java.rmi.RemoteException;

public class Tests
{
	public static void main(String[] args) { new Tests(); }
	
	public Tests()
	{
		try {
			testStubCache();
			testBasicCache();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void testStubCache() throws RemoteException
	{
		System.out.println("creating new StubCache");
		StubCache cache = new StubCache();
		
		System.out.println("setting 1:A");
		cache.set("1", "A");
		System.out.println("getting 1, result: " + cache.get("1"));
		
		System.out.println();
	}
	
	private void testBasicCache() throws RemoteException
	{
		System.out.println("creating new BasicCache, size 2");
		BasicCache cache = new BasicCache(2);
		
		System.out.println("setting 1:A");
		cache.set("1", "A");
		System.out.println("getting 1, result: " + cache.get("1"));
		
		System.out.println("setting 2:B");
		cache.set("2", "B");
		System.out.println("getting 1, result: " + cache.get("1"));
		System.out.println("getting 2, result: " + cache.get("2"));
		
		System.out.println("setting 3:C");
		cache.set("3", "C");
		System.out.println("getting 1, result: " + cache.get("1"));
		System.out.println("getting 2, result: " + cache.get("2"));
		System.out.println("getting 3, result: " + cache.get("3"));
		
		System.out.println();
	}
}