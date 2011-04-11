import junit.framework.TestCase;


public class HashBalancerTest extends TestCase {

	public void testGetIndexForKey() {
		String[] servers = {"0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "a", "b", "c", "d", "e", "f"
		};
		
		
		HashBalancer hb = new HashBalancer(servers);
		assertEquals("8", hb.getServerForKey("foo"));
		assertEquals("2", hb.getServerForKey("bar"));
		assertEquals("8", hb.getServerForKey("baz"));
		assertEquals("e", hb.getServerForKey("Roan"));
		assertEquals("f", hb.getServerForKey("Jan Paul"));
		assertEquals("8", hb.getServerForKey("Roy"));
	}

}
