package com.cqx.web;

import org.junit.Test;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by BG307435 on 2018/2/24.
 */
public class HttpsTest {

    private static TrustManager tm = new TrustManagerM();

    private static HostnameVerifier hv = new HostnameVerifierM();

    /**
     * javax.net.ssl.SSLHandshakeException:
     * sun.security.validator.ValidatorException:
     * PKIX path building failed:
     * sun.security.provider.certpath.SunCertPathBuilderException:
     * unable to find valid certification path to requested target
     *
     * @throws IOException
     */
    @Test
    public void withoutCer() throws IOException {
        URL url = new URL("https://www.v2ex.com/?tab=all");
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.connect();
        System.out.println(httpsConn.getCipherSuite());
        httpsConn.disconnect();
    }


    @Test
    public void withCer() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("TLS");

        ctx.init(null, new TrustManager[]{tm}, null);

        SSLSocketFactory ssf = ctx.getSocketFactory();

        URL url = new URL("https://testopen.95155.com");
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        httpsConn.setHostnameVerifier(hv);
        httpsConn.connect();
        httpsConn.disconnect();
    }


    @Test
    public void encodeAndDecode() throws UnsupportedEncodingException {
        String str = "C9730A8D90D5A0B856E2622EBC9051CB11603C6739F76EB5E2828C3DE72B20E0";
        String result = URLDecoder.decode(str, "UTF-8");
        System.out.println(result);
    }

    private static class HostnameVerifierM implements HostnameVerifier {

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

    private static class TrustManagerM implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
