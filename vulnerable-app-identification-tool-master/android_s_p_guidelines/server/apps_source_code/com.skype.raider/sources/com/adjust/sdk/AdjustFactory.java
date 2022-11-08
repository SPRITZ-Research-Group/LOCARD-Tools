package com.adjust.sdk;

import android.content.Context;
import com.adjust.sdk.UtilNetworking.IConnectionOptions;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class AdjustFactory {
    private static IActivityHandler activityHandler = null;
    private static IAttributionHandler attributionHandler = null;
    private static String baseUrl = Constants.BASE_URL;
    private static IConnectionOptions connectionOptions = null;
    private static HttpsURLConnection httpsURLConnection = null;
    private static ILogger logger = null;
    private static long maxDelayStart = -1;
    private static IPackageHandler packageHandler = null;
    private static BackoffStrategy packageHandlerBackoffStrategy = null;
    private static IRequestHandler requestHandler = null;
    private static BackoffStrategy sdkClickBackoffStrategy = null;
    private static ISdkClickHandler sdkClickHandler = null;
    private static long sessionInterval = -1;
    private static long subsessionInterval = -1;
    private static long timerInterval = -1;
    private static long timerStart = -1;
    private static boolean tryInstallReferrer = true;

    public static class URLGetConnection {
        HttpsURLConnection httpsURLConnection;
        URL url;

        URLGetConnection(HttpsURLConnection httpsURLConnection, URL url) {
            this.httpsURLConnection = httpsURLConnection;
            this.url = url;
        }
    }

    public static IPackageHandler getPackageHandler(IActivityHandler activityHandler, Context context, boolean startsSending) {
        if (packageHandler == null) {
            return new PackageHandler(activityHandler, context, startsSending);
        }
        packageHandler.init(activityHandler, context, startsSending);
        return packageHandler;
    }

    public static IRequestHandler getRequestHandler(IPackageHandler packageHandler) {
        if (requestHandler == null) {
            return new RequestHandler(packageHandler);
        }
        requestHandler.init(packageHandler);
        return requestHandler;
    }

    public static ILogger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public static long getTimerInterval() {
        if (timerInterval == -1) {
            return 60000;
        }
        return timerInterval;
    }

    public static long getTimerStart() {
        if (timerStart == -1) {
            return 60000;
        }
        return timerStart;
    }

    public static long getSessionInterval() {
        if (sessionInterval == -1) {
            return 1800000;
        }
        return sessionInterval;
    }

    public static long getSubsessionInterval() {
        if (subsessionInterval == -1) {
            return 1000;
        }
        return subsessionInterval;
    }

    public static BackoffStrategy getSdkClickBackoffStrategy() {
        if (sdkClickBackoffStrategy == null) {
            return BackoffStrategy.SHORT_WAIT;
        }
        return sdkClickBackoffStrategy;
    }

    public static BackoffStrategy getPackageHandlerBackoffStrategy() {
        if (packageHandlerBackoffStrategy == null) {
            return BackoffStrategy.LONG_WAIT;
        }
        return packageHandlerBackoffStrategy;
    }

    public static IActivityHandler getActivityHandler(AdjustConfig config) {
        if (activityHandler == null) {
            return ActivityHandler.getInstance(config);
        }
        activityHandler.init(config);
        return activityHandler;
    }

    public static IAttributionHandler getAttributionHandler(IActivityHandler activityHandler, ActivityPackage attributionPackage, boolean startsSending) {
        if (attributionHandler == null) {
            return new AttributionHandler(activityHandler, attributionPackage, startsSending);
        }
        attributionHandler.init(activityHandler, attributionPackage, startsSending);
        return attributionHandler;
    }

    public static HttpsURLConnection getHttpsURLConnection(URL url) throws IOException {
        if (httpsURLConnection == null) {
            return (HttpsURLConnection) url.openConnection();
        }
        return httpsURLConnection;
    }

    public static ISdkClickHandler getSdkClickHandler(IActivityHandler activityHandler, boolean startsSending) {
        if (sdkClickHandler == null) {
            return new SdkClickHandler(activityHandler, startsSending);
        }
        sdkClickHandler.init(activityHandler, startsSending);
        return sdkClickHandler;
    }

    public static long getMaxDelayStart() {
        if (maxDelayStart == -1) {
            return 10000;
        }
        return maxDelayStart;
    }

    public static String getBaseUrl() {
        if (baseUrl == null) {
            return Constants.BASE_URL;
        }
        return baseUrl;
    }

    public static IConnectionOptions getConnectionOptions() {
        if (connectionOptions == null) {
            return new a();
        }
        return connectionOptions;
    }

    public static boolean getTryInstallReferrer() {
        return tryInstallReferrer;
    }

    public static void setPackageHandler(IPackageHandler packageHandler) {
        packageHandler = packageHandler;
    }

    public static void setRequestHandler(IRequestHandler requestHandler) {
        requestHandler = requestHandler;
    }

    public static void setLogger(ILogger logger) {
        logger = logger;
    }

    public static void setTimerInterval(long timerInterval) {
        timerInterval = timerInterval;
    }

    public static void setTimerStart(long timerStart) {
        timerStart = timerStart;
    }

    public static void setSessionInterval(long sessionInterval) {
        sessionInterval = sessionInterval;
    }

    public static void setSubsessionInterval(long subsessionInterval) {
        subsessionInterval = subsessionInterval;
    }

    public static void setSdkClickBackoffStrategy(BackoffStrategy sdkClickBackoffStrategy) {
        sdkClickBackoffStrategy = sdkClickBackoffStrategy;
    }

    public static void setPackageHandlerBackoffStrategy(BackoffStrategy packageHandlerBackoffStrategy) {
        packageHandlerBackoffStrategy = packageHandlerBackoffStrategy;
    }

    public static void setActivityHandler(IActivityHandler activityHandler) {
        activityHandler = activityHandler;
    }

    public static void setAttributionHandler(IAttributionHandler attributionHandler) {
        attributionHandler = attributionHandler;
    }

    public static void setHttpsURLConnection(HttpsURLConnection httpsURLConnection) {
        httpsURLConnection = httpsURLConnection;
    }

    public static void setSdkClickHandler(ISdkClickHandler sdkClickHandler) {
        sdkClickHandler = sdkClickHandler;
    }

    public static void setBaseUrl(String baseUrl) {
        baseUrl = baseUrl;
    }

    public static void useTestConnectionOptions() {
        connectionOptions = new IConnectionOptions() {
            public final void applyConnectionOptions(HttpsURLConnection connection, String clientSdk) {
                new a().applyConnectionOptions(connection, clientSdk);
                try {
                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, new TrustManager[]{new X509TrustManager(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = this$0;
                        }

                        public final X509Certificate[] getAcceptedIssuers() {
                            AdjustFactory.getLogger().verbose("getAcceptedIssuers", new Object[0]);
                            return null;
                        }

                        public final void checkClientTrusted(X509Certificate[] certs, String authType) {
                            AdjustFactory.getLogger().verbose("checkClientTrusted ", new Object[0]);
                        }

                        public final void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                            AdjustFactory.getLogger().verbose("checkServerTrusted ", new Object[0]);
                            try {
                                if (!AdjustFactory.byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(certs[0].getEncoded())).equalsIgnoreCase("7BCFF44099A35BC093BB48C5A6B9A516CDFDA0D1")) {
                                    throw new CertificateException();
                                }
                            } catch (NoSuchAlgorithmException e) {
                                AdjustFactory.getLogger().error("testingMode error %s", e.getMessage());
                            } catch (CertificateEncodingException e2) {
                                AdjustFactory.getLogger().error("testingMode error %s", e2.getMessage());
                            }
                        }
                    }}, new SecureRandom());
                    connection.setSSLSocketFactory(sc.getSocketFactory());
                    connection.setHostnameVerifier(new HostnameVerifier(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = this$0;
                        }

                        public final boolean verify(String hostname, SSLSession session) {
                            AdjustFactory.getLogger().verbose("verify hostname ", new Object[0]);
                            return true;
                        }
                    });
                } catch (Exception e) {
                    AdjustFactory.getLogger().error("testingMode error %s", e.getMessage());
                }
            }
        };
    }

    public static void setTryInstallReferrer(boolean tryInstallReferrer) {
        tryInstallReferrer = tryInstallReferrer;
    }

    private static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (byte toHexString : arr) {
            String h = Integer.toHexString(toHexString);
            int l = h.length();
            if (l == 1) {
                h = "0" + h;
            }
            if (l > 2) {
                h = h.substring(l - 2, l);
            }
            str.append(h.toUpperCase());
        }
        return str.toString();
    }

    public static void teardown(Context context) {
        if (context != null) {
            ActivityHandler.deleteState(context);
            PackageHandler.deleteState(context);
        }
        packageHandler = null;
        requestHandler = null;
        attributionHandler = null;
        activityHandler = null;
        logger = null;
        httpsURLConnection = null;
        sdkClickHandler = null;
        timerInterval = -1;
        timerStart = -1;
        sessionInterval = -1;
        subsessionInterval = -1;
        sdkClickBackoffStrategy = null;
        packageHandlerBackoffStrategy = null;
        maxDelayStart = -1;
        baseUrl = Constants.BASE_URL;
        connectionOptions = null;
        tryInstallReferrer = true;
    }
}
