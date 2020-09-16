package cn.framework.util.encrypt.ras;

import cn.framework.util.sys.SysUtil;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * @ClassName RsaUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-30 17:20
 * @Version 1.0
 */
public class RsaUtil {

    /**
     * RSA算法定义
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 生成密钥对
     *
     * @return 长度为2的String数组，分别为BASE64编码的公钥和密钥
     * @throws Exception
     */
    public static String[] generateKeyPair(int KEY_SIZE) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(KEY_SIZE);

        KeyPair keyPair = keyPairGen.generateKeyPair();
        String publicKey = SysUtil
                .encrypt(keyPair.getPublic().getEncoded());
        String privateKey = SysUtil.encrypt(keyPair.getPrivate()
                .getEncoded());

        return new String[]{publicKey, privateKey};
    }

    public static void main(String[] args) throws Exception {
        String[] keyPair = generateKeyPair(1024);
        System.out.println("公钥=" + keyPair[0]);
        System.out.println("私钥=" + keyPair[1]);
    }
}