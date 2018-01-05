package core.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class ThreeDESUtil {
	
	
	/* public static void main(String[] args) throws Exception {  
	        // TODO Auto-generated method stub  
	        String str = "userid";  
	        String key = "12345678";  
	        String encrytStr;  
	        byte[] encrytByte;  
	          
	        byte[] byteRe = enCrypt(str,key);  
	          
	        //加密过的二进制数组转化成16进制的字符串  
	        encrytStr = parseByte2HexStr(byteRe);         
	        System.out.println("加密后："+encrytStr);  
	          
	        //加密过的16进制的字符串转化成二进制数组  
	        encrytByte = parseHexStr2Byte(encrytStr);         
	        System.out.println("解密后："+deCrypt(encrytByte,key));  
	          
	          
	    } */ 
	      
	    /** 
	     * 加密函数 
	     * @param content   加密的内容 
	     * @param strKey    密钥 
	     * @return          返回二进制字符数组 
	     * @throws Exception 
	     */  
	    public static byte[] enCrypt(String content,String strKey) throws Exception{  
	        KeyGenerator keygen;          
	        SecretKey desKey;  
	        Cipher c;         
	        byte[] cByte;  
	        String str = content;  
	          
	        keygen = KeyGenerator.getInstance("AES");  
	        keygen.init(128, new SecureRandom(strKey.getBytes()));  
	          
	        desKey = keygen.generateKey();        
	        c = Cipher.getInstance("AES");  
	          
	        c.init(Cipher.ENCRYPT_MODE, desKey);  
	          
	        cByte = c.doFinal(str.getBytes("UTF-8"));         
	          
	        return cByte;  
	    }  
	      
	    /** 解密函数 
	     * @param src   加密过的二进制字符数组 
	     * @param strKey  密钥 
	     * @return 
	     * @throws Exception 
	     */  
	    public static String deCrypt (byte[] src,String strKey) throws Exception{  
	        KeyGenerator keygen;          
	        SecretKey desKey;  
	        Cipher c;         
	        byte[] cByte;     
	          
	        keygen = KeyGenerator.getInstance("AES");  
	        keygen.init(128, new SecureRandom(strKey.getBytes()));  
	          
	        desKey = keygen.generateKey();  
	        c = Cipher.getInstance("AES");  
	          
	        c.init(Cipher.DECRYPT_MODE, desKey);  
	          
	          
	        cByte = c.doFinal(src);   
	          
	        return new String(cByte,"UTF-8");  
	    }  
	      
	      
	    /**2进制转化成16进制 
	     * @param buf 
	     * @return 
	     */  
	    public static String parseByte2HexStr(byte buf[]) {  
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
	      
	      
	    /**将16进制转换为二进制 
	     * @param hexStr 
	     * @return 
	     */       
	    public static byte[] parseHexStr2Byte(String hexStr) {   
	            if (hexStr.length() < 1)   
	                    return null;   
	            byte[] result = new byte[hexStr.length()/2];   
	            for (int i = 0;i< hexStr.length()/2; i++) {   
	                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);   
	                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);   
	                    result[i] = (byte) (high * 16 + low);   
	            }   
	            return result;   
	    }   
	  
	
    /*// 算法名称 
    public static final String KEY_ALGORITHM = "desede";
    // 算法名称/加密模式/填充方式 
    public static final String CIPHER_ALGORITHM = "desede/CBC/NoPadding";

    *//** 
     * CBC加密原理：明文跟向量异或，再用KEY进行加密，结果作为下个BLOCK的初始化向量。
     * @param key 密钥 
     * @param keyiv IV 
     * @param data 明文 
     * @return Base64编码的密文 
     * @throws Exception 
     *//*
    public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
        Security.addProvider(new BouncyCastleProvider()); 
        Key deskey = keyGenerator(new String(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        for (int k = 0; k < bOut.length; k++) {
            System.out.print(bOut[k] + " ");
        }
        System.out.println("");
        return bOut;
    }

    *//** 
     *   
     * 生成密钥key对象 
     * @param KeyStr 密钥字符串 
     * @return 密钥对象 
     * @throws InvalidKeyException   
     * @throws NoSuchAlgorithmException   
     * @throws InvalidKeySpecException   
     * @throws Exception 
     *//*
    private static Key keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESedeKeySpec KeySpec = new DESedeKeySpec(input);
        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
    }

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }
 
    // 从十六进制字符串到字节数组转换 
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    *//** 
     * 解密原理：使用密钥先对密文解密，解密后再同初始向量异或得到明文。CBC需要对明文块大小进行Padding（补位），
     * 由于前后加密的相关性，只能实施串行化动作，无法并行运算。
     * 另外，CBC需要参量：密钥和初始化向量。
     * CBC解密 
     * @param key 密钥 
     * @param keyiv IV 
     * @param data Base64编码的密文 
     * @return 明文 
     * @throws Exception 
     *//*
    public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
        Key deskey = keyGenerator(new String(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    public static void main(String[] args) throws Exception {
        byte[] key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7".getBytes();
        byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
        byte[] data = "redisKey".getBytes("UTF-8");
        System.out.println("data.length=" + data.length);
        System.out.println("CBC加密解密");
        byte[] str5 = des3EncodeCBC(key, keyiv, data);
        System.out.println(new sun.misc.BASE64Encoder().encode(str5));

        byte[] str6 = des3DecodeCBC(key, keyiv, str5);
        System.out.println(new String(str6, "UTF-8"));
    }*/
}