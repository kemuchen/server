package cn.framework.util.encrypt.ras;

import cn.framework.util.sys.SysUtil;

import javax.crypto.Cipher;

/**
 * @ClassName RsaSignatureString
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-30 17:25
 * @Version 1.0
 */
public class RsaSignatureString {

    /**
     * @Description: 对信息进行签名
     * @Params: [content, privateKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-30 17:26
     */
    public static String sign(String content, String privateKey) throws Exception {
        return RsaSignatureByte.sign(SysUtil.decrypt(content), privateKey);
    }

    /**
     * @Description: 验证信息签名正确性
     * @Params: [content, publicKey, sign]
     * @return: boolean
     * @Author: 柯雷
     * @Date: 2020-04-30 17:26
     */
    public static boolean verify(String content, String publicKey, String sign) throws Exception {
        return RsaSignatureByte.verify(SysUtil.decrypt(content), publicKey, sign);
    }

    /**
     * @Description: 使用私钥解密信息
     * @Params: [content, privateKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-30 17:26
     */
    public static String decryptByPrivateKey(String content, String privateKey) {
        //rsaSignatureByte = new RsaSignatureByte();
        String a = "";
        try {
            Cipher cipher = RsaSignatureByte.getDecryptCipherByPrivateKey(privateKey);
            a = new String(RsaSignatureByte.decrypt(SysUtil.decrypt(content), cipher), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * @Description: 使用公钥解密信息
     * @Params: [content, publicKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-30 17:26
     */
    public static String decryptByPublicKey(String content, String publicKey) throws Exception {
        Cipher cipher = RsaSignatureByte.getDecryptCipherByPublicKey(publicKey);

        return new String(RsaSignatureByte.decrypt(SysUtil.decrypt(content), cipher), "UTF-8");
    }

    /**
     * @Description: 使用私钥加密信息
     * @Params: [content, privateKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-30 17:26
     */
    public static String encryptByPrivateKey(String content, String privateKey) throws Exception {
        Cipher cipher = RsaSignatureByte.getEncryptCipherByPrivateKey(privateKey);

        return SysUtil.encrypt(RsaSignatureByte.encrypt(content.getBytes("UTF-8"), cipher));
    }

    /**
     * @Description: 使用公钥加密信息
     * @Params: [content, publicKey]
     * @return: java.lang.String
     * @Author: 柯雷
     * @Date: 2020-04-30 17:25
     */
    public static String encryptByPublicKey(String content, String publicKey) throws Exception {
        Cipher cipher = RsaSignatureByte.getEncryptCipherByPublicKey(publicKey);
        return SysUtil.encrypt(RsaSignatureByte.encrypt(content.getBytes("UTF-8"), cipher));
    }

    public static void main(String[] args) throws Exception {
        String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrdeaJCguaiGDCkr8e7Ip7Iir2MvCl4sBN4WRuRdXcLWS_JGKofy8YXXD62Yu90swqLHamSsFD4gKzH3Qf_MgQsToqxIzY5vlg7kKnu2Vs1FTKro-KMX5jYb2-TEJFTBmMRvBoTnJgPAxlXtiXKCWrR1C4ZypvkF6AVbZo_Z0G2QIDAQAB";
        String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKt15okKC5qIYMKSvx7sinsiKvYy8KXiwE3hZG5F1dwtZL8kYqh_LxhdcPrZi73SzCosdqZKwUPiArMfdB_8yBCxOirEjNjm-WDuQqe7ZWzUVMquj4oxfmNhvb5MQkVMGYxG8GhOcmA8DGVe2JcoJatHULhnKm-QXoBVtmj9nQbZAgMBAAECgYEAopoZ-aYMIQ3qbTuLgkEihul9QqRGcZrbckAo5SbXTInD_Dz-TvrmZ73dshbzHh61vd0S3FsSc1T36HUQxHEOLK-sdwZSgi01BWZlR-vFGN_7hY63BfxxjkwF-zYzch-EkvAJ_kDR_LKMOwBk6SW6eL5uOvlEsiAkaKwYxOc1LW0CQQDjEXAXgKY2NNltJW8BL3dvc17DMIDPYknE9IZgRzCUycC67zAh-JS8YMakHooTiufQaInG4eFWnp4Dka-i2In3AkEAwU6kSCaJdmisUGuu4XXtmLIpe6t3R8ZW43Al8Q18xkb7kVsKkJdYu6B50x-8ELrqHQwFF5iDEWCEgjCVKClBrwJAJ0XR1E7M8J0taeJKk7Lo_l6-6hWOgc_nIGiGgUpuzZ0jd3F9KrL6zqYSVTpu-ThSJcqe4cYZmkiu3cEp1omLOwJAY1_lTn4ZLrkS1Gj3R0qTsWrtrnjwYgKRimyN5MT1Pw0gqJHv9XMIl7QgzKtEyCj-z53tRDsQLi-7Dx6gnzvOWwJAdNpBzEUgmnfAi-r1A9ZdSIsaGtjywduy7fSqE5VJdKx5_8wakz3VTFh6H6WLsgziGPIBCCCQVbMvzG_RbUGbrA";

        String encrypt = encryptByPublicKey("123456", public_key);
        System.err.println(encrypt);

        String decrypt = decryptByPrivateKey(encrypt, private_key);
        System.err.println(decrypt);
    }
}
