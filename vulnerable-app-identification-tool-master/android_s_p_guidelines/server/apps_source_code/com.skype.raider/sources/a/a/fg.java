package a.a;

import com.adjust.sdk.Constants;
import com.appboy.f.c;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class fg {
    private static final String a = c.a(fg.class);
    private static eb b;

    static {
        try {
            b = new eb();
        } catch (Throwable e) {
            c.d(a, "Exception initializing static TLS socket factory.", e);
        }
    }

    public static URLConnection a(URL url) {
        URLConnection openConnection = url.openConnection();
        if (url.getProtocol().equals(Constants.SCHEME)) {
            try {
                ((HttpsURLConnection) openConnection).setSSLSocketFactory(b);
            } catch (Throwable e) {
                c.d(a, "Exception setting TLS socket factory on url connection.", e);
            }
        }
        return openConnection;
    }
}
