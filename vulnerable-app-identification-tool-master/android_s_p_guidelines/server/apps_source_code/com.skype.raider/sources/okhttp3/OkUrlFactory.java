package okhttp3;

import com.adjust.sdk.Constants;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import okhttp3.internal.URLFilter;
import okhttp3.internal.huc.OkHttpURLConnection;
import okhttp3.internal.huc.OkHttpsURLConnection;

public final class OkUrlFactory implements Cloneable, URLStreamHandlerFactory {
    private OkHttpClient client;
    private URLFilter urlFilter;

    public OkUrlFactory(OkHttpClient client) {
        this.client = client;
    }

    public final OkHttpClient client() {
        return this.client;
    }

    public final OkUrlFactory setClient(OkHttpClient client) {
        this.client = client;
        return this;
    }

    final void setUrlFilter(URLFilter filter) {
        this.urlFilter = filter;
    }

    public final OkUrlFactory clone() {
        return new OkUrlFactory(this.client);
    }

    public final HttpURLConnection open(URL url) {
        return open(url, this.client.proxy());
    }

    final HttpURLConnection open(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient copy = this.client.newBuilder().proxy(proxy).build();
        if (protocol.equals("http")) {
            return new OkHttpURLConnection(url, copy, this.urlFilter);
        }
        if (protocol.equals(Constants.SCHEME)) {
            return new OkHttpsURLConnection(url, copy, this.urlFilter);
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

    public final URLStreamHandler createURLStreamHandler(final String protocol) {
        if (protocol.equals("http") || protocol.equals(Constants.SCHEME)) {
            return new URLStreamHandler() {
                protected URLConnection openConnection(URL url) {
                    return OkUrlFactory.this.open(url);
                }

                protected URLConnection openConnection(URL url, Proxy proxy) {
                    return OkUrlFactory.this.open(url, proxy);
                }

                protected int getDefaultPort() {
                    if (protocol.equals("http")) {
                        return 80;
                    }
                    if (protocol.equals(Constants.SCHEME)) {
                        return 443;
                    }
                    throw new AssertionError();
                }
            };
        }
        return null;
    }
}
