package cn.framework.util.encrypt.ras;

import cn.framework.util.sys.SysUtil;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @ClassName RsaSignatureByte
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-30 17:23
 * @Version 1.0
 */
public class RsaSignatureByte {

    /**
     * RSA算法定义
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 1024;
    /**
     * MD5withRSA算法定义
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * @Description: 对信息进行签名
     * @Params: [data, privateKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-30 17:28
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] privateKeyBytes = SysUtil.decrypt(privateKey);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(
                privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return SysUtil.encrypt(signature.sign());
    }

    /**
     * @Description: 验证信息签名正确性
     * @Params: [data, publicKey, sign]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020-04-30 17:26
     */
    public static boolean verify(byte[] data,
                                 String publicKey, String sign) throws Exception {
        byte[] keyBytes = SysUtil.decrypt(publicKey);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);

        return signature.verify(SysUtil.decrypt(sign));
    }

    /**
     * @Description: 根据私钥获得解密用Cipher
     * @Params: [privateKey]
     * @return: javax.crypto.Cipher
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static Cipher getDecryptCipherByPrivateKey(String privateKey)
            throws Exception {
        byte[] keyBytes = SysUtil.decrypt(privateKey);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return cipher;
    }

    /**
     * @Description: 根据公钥获得解密用Cipher
     * @Params: [publicKey]
     * @return: javax.crypto.Cipher
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static Cipher getDecryptCipherByPublicKey(String publicKey)
            throws Exception {
        byte[] keyBytes = SysUtil.decrypt(publicKey);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher;
    }

    /**
     * @Description: 根据私钥获得加密用Cipher
     * @Params: [privateKey]
     * @return: javax.crypto.Cipher
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static Cipher getEncryptCipherByPrivateKey(String privateKey)
            throws Exception {
        byte[] keyBytes = SysUtil.decrypt(privateKey);

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        return cipher;
    }

    /**
     * @Description: 根据公钥获得加密用Cipher
     * @Params: [publicKey]
     * @return: javax.crypto.Cipher
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static Cipher getEncryptCipherByPublicKey(String publicKey)
            throws Exception {
        byte[] keyBytes = SysUtil.decrypt(publicKey);

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(x509KeySpec);

        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher;
    }

    /**
     * @Description: 使用Cipher解密信息
     * @Params: [data, cipher]
     * @return: byte[]
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static byte[] decrypt(byte[] data, Cipher cipher)
            throws Exception {
        int dataLimit = getMaxDataBytesLimit();
        int encryptLimit = getMaxEncryptBytesLimit();
        int groupNum = (int) Math.ceil(data.length / (double) encryptLimit);
        byte[] decryptT = new byte[groupNum * dataLimit];

        int decryptOffset = 0;
        for (int group = 0; group < groupNum; group++) {
            byte[] dataTemp = new byte[encryptLimit];
            System.arraycopy(data, group * encryptLimit, dataTemp, 0,
                    encryptLimit);
            byte[] decryptTemp = cipher.doFinal(dataTemp);
            System.arraycopy(decryptTemp, 0, decryptT, decryptOffset,
                    decryptTemp.length);
            decryptOffset += decryptTemp.length;
        }

        byte[] decrypt = new byte[decryptOffset];
        System.arraycopy(decryptT, 0, decrypt, 0, decrypt.length);

        return decrypt;
    }

    /**
     * @Description: 使用Cipher加密信息
     * @Params: [data, cipher]
     * @return: byte[]
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static byte[] encrypt(byte[] data, Cipher cipher)
            throws Exception {
        int dataLimit = getMaxDataBytesLimit();
        int encryptLimit = getMaxEncryptBytesLimit();
        int groupNum = (int) Math.ceil(data.length / (double) dataLimit);
        byte[] encrypt = new byte[groupNum * encryptLimit];

        int encryptOffset = 0;
        for (int group = 0; group < groupNum; group++) {
            int dataLimitT = dataLimit;
            if ((group + 1) * dataLimit > data.length) {
                dataLimitT = data.length - group * dataLimit;
            }
            byte[] dataTemp = new byte[dataLimitT];
            System.arraycopy(data, group * dataLimit, dataTemp, 0, dataLimitT);
            byte[] encryptTemp = cipher.doFinal(dataTemp);
            System.arraycopy(encryptTemp, 0, encrypt, encryptOffset,
                    encryptTemp.length);
            encryptOffset += encryptTemp.length;
        }

        return encrypt;
    }

    /**
     * @Description: 使用私钥解密信息
     * @Params: [data, privateKey]
     * @return: byte[]
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static byte[] decryptByPrivateKey(byte[] data,
                                             String privateKey) throws Exception {
        Cipher cipher = getDecryptCipherByPrivateKey(privateKey);

        return decrypt(data, cipher);
    }

    /**
     * @Description: 使用公钥解密信息
     * @Params: [data, publicKey]
     * @return: byte[]
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static byte[] decryptByPublicKey(byte[] data,
                                            String publicKey) throws Exception {
        Cipher cipher = getDecryptCipherByPublicKey(publicKey);

        return decrypt(data, cipher);
    }

    /**
     * @Description: 使用私钥加密信息
     * @Params: [data, privateKey]
     * @return: byte[]
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static byte[] encryptByPrivateKey(byte[] data,
                                             String privateKey) throws Exception {
        Cipher cipher = getEncryptCipherByPrivateKey(privateKey);

        return encrypt(data, cipher);
    }

    /**
     * @Description: 使用公钥加密信息
     * @Params: [data, publicKey]
     * @return: byte[]
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    public static byte[] encryptByPublicKey(byte[] data,
                                            String publicKey) throws Exception {
        Cipher cipher = getEncryptCipherByPublicKey(publicKey);

        return encrypt(data, cipher);
    }

    /**
     * @Description: 获取最大待加密字节限制
     * @Params: []
     * @return: int
     * @Author: 柯雷
     * @Date: 2020-04-30 17:27
     */
    private static int getMaxDataBytesLimit() {
        return getMaxEncryptBytesLimit() - 11;
    }

    /**
     * @Description: 获取最大密文字节限制
     * @Params: []
     * @return: int
     * @Author: 柯雷
     * @Date: 2020-04-30 17:28
     */
    private static int getMaxEncryptBytesLimit() {
        return (int) Math.ceil(KEY_SIZE / 8.0);
    }
}
