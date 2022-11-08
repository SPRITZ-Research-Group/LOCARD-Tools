package com.microsoft.react.captiveportal;

import android.support.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.modules.network.e;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class CaptivePortal extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "CaptivePortal";
    private ag reactContext;

    public CaptivePortal(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void checkCaptivePortal(String checkUrl, int timeout, ae promise) {
        try {
            Builder clientBuilder = e.a().newBuilder();
            clientBuilder.connectTimeout((long) timeout, TimeUnit.MILLISECONDS).readTimeout((long) timeout, TimeUnit.MILLISECONDS).writeTimeout((long) timeout, TimeUnit.MILLISECONDS).followRedirects(false).followSslRedirects(false).cookieJar(CookieJar.NO_COOKIES);
            OkHttpClient client = clientBuilder.build();
            final ae innerPromise = promise;
            client.newCall(new Request.Builder().url(checkUrl).get().build()).enqueue(new Callback(this) {
                final /* synthetic */ CaptivePortal b;

                public final void onFailure(@NonNull Call request, @NonNull IOException e) {
                    innerPromise.a("CAPTIVE_PORTAL_CHECK_REQUEST_ERR", e.getMessage());
                }

                public final void onResponse(@NonNull Call call, @NonNull Response response) {
                    try {
                        response.code();
                        try {
                            String responseBody = response.body().string();
                            Object result = new WritableNativeMap();
                            result.putInt("statusCode", response.code());
                            result.putString("responseText", responseBody);
                            innerPromise.a(result);
                        } catch (IOException e) {
                            innerPromise.a("CAPTIVE_PORTAL_CHECK_PARSE_ERR", e.getMessage());
                        }
                    } catch (Exception e2) {
                        innerPromise.a("CAPTIVE_PORTAL_CHECK_RESPONSE_ERR", e2.getMessage());
                    }
                }
            });
        } catch (IllegalStateException e) {
            FLog.e(REACT_CLASS, e.toString());
            promise.a("CAPTIVE_PORTAL_CHECK_ERR", e.getMessage());
        }
    }
}
