package cn.framework.util.http;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * @ClassName MyX509TrustManager
 * @Desc
 * @Author kelei
 * @Date 2019/7/9 15:02
 * @Version 1.0
 */
public class MyX509TrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] var1, String var2) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] var1, String var2) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
