package com.facebook.react.modules.network;

import android.os.Build.VERSION;
import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.TlsVersion;

public final class e {
    @Nullable
    private static OkHttpClient a;

    public static OkHttpClient a() {
        if (a == null) {
            a = b();
        }
        return a;
    }

    public static void a(OkHttpClient client) {
        a = client;
    }

    public static OkHttpClient b() {
        Builder client;
        if (a != null) {
            client = a.newBuilder();
        } else {
            client = new Builder();
        }
        client.connectTimeout(0, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).cookieJar(new j());
        return a(client).build();
    }

    private static Builder a(Builder client) {
        if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19) {
            try {
                client.sslSocketFactory(new m());
                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                List<ConnectionSpec> specs = new ArrayList();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);
                client.connectionSpecs(specs);
            } catch (Throwable exc) {
                FLog.e("OkHttpClientProvider", "Error while enabling TLS 1.2", exc);
            }
        }
        return client;
    }
}
