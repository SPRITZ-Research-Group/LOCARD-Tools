package com.facebook.imagepipeline.b.a;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ak;
import com.facebook.imagepipeline.producers.c;
import com.facebook.imagepipeline.producers.s;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.CacheControl.Builder;
import okhttp3.Call;
import okhttp3.Call.Factory;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class a extends c<a> {
    private final Factory a;
    @Nullable
    private final CacheControl b;
    private Executor c;

    public static class a extends s {
        public long a;
        public long b;
        public long c;

        public a(Consumer<e> consumer, ak producerContext) {
            super(consumer, producerContext);
        }
    }

    public final /* synthetic */ Map a(s sVar, int i) {
        a aVar = (a) sVar;
        Map hashMap = new HashMap(4);
        hashMap.put("queue_time", Long.toString(aVar.b - aVar.a));
        hashMap.put("fetch_time", Long.toString(aVar.c - aVar.b));
        hashMap.put("total_time", Long.toString(aVar.c - aVar.a));
        hashMap.put("image_size", Integer.toString(i));
        return hashMap;
    }

    public final /* synthetic */ void a(s sVar) {
        ((a) sVar).c = SystemClock.elapsedRealtime();
    }

    public a(OkHttpClient okHttpClient) {
        this(okHttpClient, okHttpClient.dispatcher().executorService());
    }

    private a(Factory callFactory, Executor cancellationExecutor) {
        this(callFactory, cancellationExecutor, (byte) 0);
    }

    private a(Factory callFactory, Executor cancellationExecutor, byte b) {
        this.a = callFactory;
        this.c = cancellationExecutor;
        this.b = new Builder().noStore().build();
    }

    public void a(a fetchState, com.facebook.imagepipeline.producers.af.a callback) {
        fetchState.a = SystemClock.elapsedRealtime();
        try {
            Request.Builder requestBuilder = new Request.Builder().url(fetchState.e().toString()).get();
            if (this.b != null) {
                requestBuilder.cacheControl(this.b);
            }
            com.facebook.imagepipeline.common.a bytesRange = fetchState.b().a().h();
            if (bytesRange != null) {
                requestBuilder.addHeader("Range", bytesRange.a());
            }
            a(fetchState, callback, requestBuilder.build());
        } catch (Exception e) {
            callback.a(e);
        }
    }

    protected final void a(final a fetchState, final com.facebook.imagepipeline.producers.af.a callback, Request request) {
        final Call call = this.a.newCall(request);
        fetchState.b().a(new com.facebook.imagepipeline.producers.e(this) {
            final /* synthetic */ a b;

            public final void a() {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    call.cancel();
                } else {
                    this.b.c.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = this$1;
                        }

                        public final void run() {
                            call.cancel();
                        }
                    });
                }
            }
        });
        call.enqueue(new Callback(this) {
            final /* synthetic */ a c;

            public final void onResponse(Call call, Response response) throws IOException {
                fetchState.b = SystemClock.elapsedRealtime();
                ResponseBody body = response.body();
                try {
                    if (response.isSuccessful()) {
                        com.facebook.imagepipeline.common.a responseRange = com.facebook.imagepipeline.common.a.a(response.header("Content-Range"));
                        if (!(responseRange == null || (responseRange.a == 0 && responseRange.b == Integer.MAX_VALUE))) {
                            fetchState.a(responseRange);
                            fetchState.h();
                        }
                        long contentLength = body.contentLength();
                        if (contentLength < 0) {
                            contentLength = 0;
                        }
                        callback.a(body.byteStream(), (int) contentLength);
                        body.close();
                        return;
                    }
                    a.a(call, new IOException("Unexpected HTTP code " + response), callback);
                } catch (Exception e) {
                    a.a(call, e, callback);
                } finally {
                    body.close();
                }
            }

            public final void onFailure(Call call, IOException e) {
                a.a(call, (Exception) e, callback);
            }
        });
    }

    public final /* synthetic */ s a(Consumer consumer, ak akVar) {
        return new a(consumer, akVar);
    }

    static /* synthetic */ void a(Call x1, Exception x2, com.facebook.imagepipeline.producers.af.a x3) {
        if (x1.isCanceled()) {
            x3.a();
        } else {
            x3.a(x2);
        }
    }
}
