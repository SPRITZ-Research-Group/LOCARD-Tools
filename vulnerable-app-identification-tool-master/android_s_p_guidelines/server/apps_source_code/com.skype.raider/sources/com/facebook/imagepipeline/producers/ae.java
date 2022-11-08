package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.e.a;
import com.facebook.common.e.i;
import com.facebook.common.e.k;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.l.b;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public final class ae implements aj<e> {
    private final i a;
    private final a b;
    private final af c;

    public ae(i pooledByteBufferFactory, a byteArrayPool, af networkFetcher) {
        this.a = pooledByteBufferFactory;
        this.b = byteArrayPool;
        this.c = networkFetcher;
    }

    public final void a(Consumer<e> consumer, ak context) {
        context.c().a(context.b(), "NetworkFetchProducer");
        final s fetchState = this.c.a((Consumer) consumer, context);
        this.c.a(fetchState, new af.a(this) {
            final /* synthetic */ ae b;

            public final void a(InputStream response, int responseLength) throws IOException {
                b.a();
                this.b.a(fetchState, response, responseLength);
                b.a();
            }

            public final void a(Throwable throwable) {
                s sVar = fetchState;
                sVar.d().a(sVar.c(), "NetworkFetchProducer", throwable, null);
                sVar.d().a(sVar.c(), "NetworkFetchProducer", false);
                sVar.a().b(throwable);
            }

            public final void a() {
                s sVar = fetchState;
                sVar.d().b(sVar.c(), "NetworkFetchProducer", null);
                sVar.a().b();
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final void a(s fetchState, InputStream responseData, int responseContentLength) throws IOException {
        k pooledOutputStream;
        if (responseContentLength > 0) {
            pooledOutputStream = this.a.a(responseContentLength);
        } else {
            pooledOutputStream = this.a.a();
        }
        byte[] ioArray = (byte[]) this.b.a(16384);
        loop0:
        while (true) {
            try {
                int length = responseData.read(ioArray);
                if (length < 0) {
                    break loop0;
                } else if (length > 0) {
                    Object obj;
                    float progress;
                    pooledOutputStream.write(ioArray, 0, length);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    if (fetchState.b().h()) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null && uptimeMillis - fetchState.f() >= 100) {
                        fetchState.a(uptimeMillis);
                        fetchState.d().a(fetchState.c(), "NetworkFetchProducer", "intermediate_result");
                        a(pooledOutputStream, fetchState.g(), fetchState.i(), fetchState.a());
                    }
                    int b = pooledOutputStream.b();
                    if (responseContentLength > 0) {
                        progress = ((float) b) / ((float) responseContentLength);
                    } else {
                        progress = 1.0f - ((float) Math.exp(((double) (-b)) / 50000.0d));
                    }
                    fetchState.a().b(progress);
                }
            } finally {
                this.b.a((Object) ioArray);
                pooledOutputStream.close();
            }
        }
    }

    private static void a(k pooledOutputStream, int status, @Nullable com.facebook.imagepipeline.common.a responseBytesRange, Consumer<e> consumer) {
        Throwable th;
        com.facebook.common.f.a result = com.facebook.common.f.a.a(pooledOutputStream.a());
        e encodedImage = null;
        try {
            e encodedImage2 = new e(result);
            try {
                encodedImage2.a(responseBytesRange);
                encodedImage2.n();
                consumer.b(encodedImage2, status);
                e.d(encodedImage2);
                com.facebook.common.f.a.c(result);
            } catch (Throwable th2) {
                th = th2;
                encodedImage = encodedImage2;
                e.d(encodedImage);
                com.facebook.common.f.a.c(result);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            e.d(encodedImage);
            com.facebook.common.f.a.c(result);
            throw th;
        }
    }
}
