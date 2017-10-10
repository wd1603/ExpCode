package cool.warrior.widget.cipher;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	
	public static final String AES = "AES";
	
	public static final String charset = null;
	
	public static final int keysizeAES = 128;
	
	private SecureRandom secureRandom;

	private AESUtil(byte[] seed) {
		secureRandom = new SecureRandom(seed);
	}


    /**
     * 使用 AES 进行加密
     */
    public String encode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, true);
    }
    
    /**
     * 使用 AES 进行解密
     */
    public String decode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, false);
    }
	
    // 使用KeyGenerator双向加密，DES/AES，注意这里转化为字符串的时候是将2进制转为16进制格式的字符串，不是直接转，因为会出错
    private String keyGeneratorES(String res, String algorithm, String key, int keysize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                kg.init(secureRandom);
            } else if (key == null) {
                kg.init(keysize);
            } else {
                kg.init(keysize, secureRandom);
            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(res)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 将二进制转换成16进制
    private String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    
    // 将16进制转换为二进制
    private byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    public static void main(String[] args) {
    	String res = "";
    	String key = "我是一个key";
    	byte[] seed = "201710101636wyrwyr".getBytes();
    	AESUtil userCipherUtil = new AESUtil(seed);
    	String encodeStr = userCipherUtil.encode(res, key);
    	System.out.println(encodeStr);//C578E0EDA3F99A719593420AE6600F83
    	encodeStr = userCipherUtil.encode(res, key);
    	System.out.println(encodeStr);//85F873376267E4914706F5EB37F90C2B
    	encodeStr = userCipherUtil.encode(res, key);
    	System.out.println(encodeStr);//980399B11ACF0BFB151FBEA4CD8A086C
    }
}
