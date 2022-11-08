package com.facebook.react.modules.fresco;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.b.a.a;
import com.facebook.imagepipeline.producers.af;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.am;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

final class b extends a {
    private final OkHttpClient a;
    private final Executor b;

    public b(OkHttpClient okHttpClient) {
        super(okHttpClient);
        this.a = okHttpClient;
        this.b = okHttpClient.dispatcher().executorService();
    }

    public final void a(a.a fetchState, af.a callback) {
        fetchState.a = SystemClock.elapsedRealtime();
        Uri uri = fetchState.e();
        Map requestHeaders = null;
        if (fetchState.b().a() instanceof a) {
            am s = ((a) fetchState.b().a()).s();
            if (s == null) {
                requestHeaders = null;
            } else {
                ReadableMapKeySetIterator keySetIterator = s.keySetIterator();
                requestHeaders = new HashMap();
                while (keySetIterator.hasNextKey()) {
                    String nextKey = keySetIterator.nextKey();
                    requestHeaders.put(nextKey, s.getString(nextKey));
                }
            }
        }
        if (requestHeaders == null) {
            requestHeaders = Collections.emptyMap();
        }
        a(fetchState, callback, new Builder().cacheControl(new CacheControl.Builder().noStore().build()).url(uri.toString()).headers(Headers.of(requestHeaders)).get().build());
    }
}
