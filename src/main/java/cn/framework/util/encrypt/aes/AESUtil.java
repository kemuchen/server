package cn.framework.util.encrypt.aes;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @ClassName AESUtil
 * @Desc
 * @Author 柯雷
 * @Date 2019/9/12 8:44
 * @Version 1.0
 */
public class AESUtil {
    /**
     * 获得一个 密钥长度为 256 位的 AES 密钥，
     *
     * @return 返回经 BASE64 处理之后的密钥字符串
     */
    public static String getStrKeyAES() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes("utf-8"));
        keyGen.init(128, secureRandom);   // 这里可以是 128、192、256、越大越安全
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 将使用 Base64 加密后的字符串类型的 secretKey 转为 SecretKey
     *
     * @param strKey
     * @return SecretKey
     */
    public static SecretKey strKey2SecretKey(String strKey) {
        byte[] bytes = Base64.getDecoder().decode(strKey);
        SecretKeySpec secretKey = new SecretKeySpec(bytes, "AES");
        return secretKey;
    }

    /**
     * 加密
     *
     * @param content   待加密内容
     * @param secretKey 加密使用的 AES 密钥
     * @return 加密后的密文 byte[]
     */
    public static byte[] encryptAES(byte[] content, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(String.format("%s/%s/%s", "AES", "ECB", "PKCS5Padding"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(content);
    }

    /**
     * 解密
     *
     * @param content   待解密内容
     * @param secretKey 解密使用的 AES 密钥
     * @return 解密后的明文 byte[]
     */
    public static byte[] decryptAES(byte[] content, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(String.format("%s/%s/%s", "AES", "ECB", "PKCS5Padding"));
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(content);
    }

    /**
     * @Description: string转byte[]
     * @Params: [string]
     * @return: byte[]
     * @Author: 柯雷
     * @Date: 2019/9/12 8:50
     */
    public static byte[] stringToByte(String string) throws Exception {
        return string.getBytes(Charset.forName("utf-8"));
    }

    /**
     * @Description: byte[]转string
     * @Params: [bytes]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2019/9/12 8:50
     */
    public static String byteToString(byte[] bytes) {
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }

    /**
     * @Description: 加密
     * @Params: [content, secretKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2019/9/12 8:52
     */
    public static String encryptAES(String content, String secretKey) throws Exception {
        byte[] bytes = encryptAES(stringToByte(content), new SecretKeySpec(stringToByte(secretKey), "AES"));
        return byteToString(bytes);
    }

    /**
     * @Description: 解密
     * @Params: [content, secretKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2019/9/12 8:52
     */
    public static String decryptAES(String content, String secretKey) throws Exception {
        byte[] bytes = decryptAES(stringToByte(content), new SecretKeySpec(stringToByte(secretKey), "AES"));
        return byteToString(bytes);
    }
}
