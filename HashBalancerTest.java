import junit.framework.TestCase;


public class HashBalancerTest extends TestCase {

	public void testGetIndexForKey() {
		HashBalancer hb = new HashBalancer(16);
		assertEquals(8, hb.getIndexForKey("foo"));
		assertEquals(2, hb.getIndexForKey("bar"));
		assertEquals(8, hb.getIndexForKey("baz"));
	}

}
