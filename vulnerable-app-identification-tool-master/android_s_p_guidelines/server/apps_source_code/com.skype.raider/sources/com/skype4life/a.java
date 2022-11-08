package com.skype4life;

import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

final class a implements Interceptor {
    a() {
    }

    public final Response intercept(Chain chain) throws IOException {
        long startNs = System.nanoTime();
        Request request = chain.request();
        try {
            Response response = chain.proceed(request);
            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
            RequestBody requestBody = request.body();
            ResponseBody responseBody = response.body();
            String url = request.url().host();
            StringBuilder builder = new StringBuilder();
            builder.append("Http succeeded, request: ").append(request.method()).append(' ').append(url).append(requestBody != null ? " (" + requestBody.contentLength() + " body)" : "").append(". Response: ").append(response.code()).append(' ').append(response.message()).append(" (").append(tookMs).append(" ms").append(responseBody != null ? ", " + responseBody.contentLength() + " body" : "").append(")");
            FLog.i("HttpLoggingInterceptor", builder.toString());
            return response;
        } catch (Throwable e) {
            FLog.w("HttpLoggingInterceptor", e, "Http failed: ", new Object[0]);
            throw e;
        }
    }
}
