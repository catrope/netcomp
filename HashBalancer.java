import java.math.BigInteger;
import java.security.*;
import java.util.Random;

public class HashBalancer {
	protected int n;
	
	public HashBalancer(int n) {
		this.n = n;
	}
	
	public int getIndexForKey(String key) {
		try {
			byte[] byteArr = key.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(byteArr);
			BigInteger h = new BigInteger(1, hash);
			return h.mod(BigInteger.valueOf(n)).intValue();
		} catch(Exception e) {
			// Java throws some stupid exceptions in case UTF-8 or MD5
			// aren't supported. This is never realistically gonna happen,
			// but just in case let's use a randomized balancer in that case.
			Random generator = new Random();
			return generator.nextInt(n);
		}
	}
}
