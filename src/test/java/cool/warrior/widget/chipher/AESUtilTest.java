package cool.warrior.widget.chipher;

import cool.warrior.widget.cipher.AESUtil;
import junit.framework.TestCase;

public class AESUtilTest extends TestCase {
	public AESUtilTest(String testName) {
		super(testName);
	}

	public void testEncode() {
		String res = "";
    	String key = "我是一个key";
    	byte[] seed = "201710101636wyrwyr".getBytes();
    	AESUtil userCipherUtil = new AESUtil(seed);
    	
    	
    	String encodeStr = userCipherUtil.encode(res, key);
    	assertEquals("C578E0EDA3F99A719593420AE6600F83", encodeStr);
    	
    	encodeStr = userCipherUtil.encode(res, key);
    	assertEquals("85F873376267E4914706F5EB37F90C2B", encodeStr);
    	
    	encodeStr = userCipherUtil.encode(res, key);
    	assertEquals("980399B11ACF0BFB151FBEA4CD8A086C", encodeStr);
	}
	
}
