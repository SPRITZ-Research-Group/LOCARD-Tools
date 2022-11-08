package com.facebook.imagepipeline.producers;

import b.h;
import com.facebook.cache.a.c;
import com.facebook.common.e.i;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.image.e;
import com.facebook.imagepipeline.k.b;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public final class ag implements aj<e> {
    private final com.facebook.imagepipeline.d.e a;
    private final f b;
    private final i c;
    private final com.facebook.common.e.a d;
    private final aj<e> e;

    private static class a extends m<e, e> {
        private final com.facebook.imagepipeline.d.e a;
        private final c b;
        private final i c;
        private final com.facebook.common.e.a d;
        @Nullable
        private final e e;

        /* synthetic */ a(Consumer x0, com.facebook.imagepipeline.d.e x1, c x2, i x3, com.facebook.common.e.a x4, e x5, byte b) {
            this(x0, x1, x2, x3, x4, x5);
        }

        public final /* synthetic */ void a(Object obj, int i) {
            Throwable th;
            e eVar = (e) obj;
            if (!b.b(i)) {
                if (this.e == null || eVar.k() == null) {
                    if (b.a(i, 8) && b.a(i) && eVar.e() != com.facebook.imageformat.c.a) {
                        this.a.a(this.b, eVar);
                    }
                    d().b(eVar, i);
                    return;
                }
                try {
                    e eVar2 = this.e;
                    OutputStream a = this.c.a(eVar.l() + eVar.k().a);
                    a(eVar2.c(), a, eVar.k().a);
                    a(eVar.c(), a, eVar.l());
                    com.facebook.common.f.a a2 = com.facebook.common.f.a.a(a.a());
                    e eVar3;
                    try {
                        eVar3 = new e(a2);
                        try {
                            eVar3.n();
                            d().b(eVar3, 1);
                            e.d(eVar3);
                            com.facebook.common.f.a.c(a2);
                        } catch (Throwable th2) {
                            th = th2;
                            e.d(eVar3);
                            com.facebook.common.f.a.c(a2);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        eVar3 = null;
                        e.d(eVar3);
                        com.facebook.common.f.a.c(a2);
                        throw th;
                    }
                } catch (Throwable th4) {
                    FLog.e("PartialDiskCacheProducer", "Error while merging image data", th4);
                    d().b(th4);
                } finally {
                    eVar.close();
                    this.e.close();
                }
                this.a.c(this.b);
            }
        }

        private a(Consumer<e> consumer, com.facebook.imagepipeline.d.e defaultBufferedDiskCache, c partialImageCacheKey, i pooledByteBufferFactory, com.facebook.common.e.a byteArrayPool, @Nullable e partialEncodedImageFromCache) {
            super(consumer);
            this.a = defaultBufferedDiskCache;
            this.b = partialImageCacheKey;
            this.c = pooledByteBufferFactory;
            this.d = byteArrayPool;
            this.e = partialEncodedImageFromCache;
        }

        private void a(InputStream from, OutputStream to, int length) throws IOException {
            int bytesStillToRead = length;
            byte[] ioArray = (byte[]) this.d.a(16384);
            while (bytesStillToRead > 0) {
                try {
                    int bufferLength = from.read(ioArray, 0, Math.min(16384, bytesStillToRead));
                    if (bufferLength < 0) {
                        break;
                    } else if (bufferLength > 0) {
                        to.write(ioArray, 0, bufferLength);
                        bytesStillToRead -= bufferLength;
                    }
                } catch (Throwable th) {
                    this.d.a((Object) ioArray);
                }
            }
            this.d.a((Object) ioArray);
            if (bytesStillToRead > 0) {
                throw new IOException(String.format(null, "Failed to read %d bytes - finished %d short", new Object[]{Integer.valueOf(length), Integer.valueOf(bytesStillToRead)}));
            }
        }
    }

    public ag(com.facebook.imagepipeline.d.e defaultBufferedDiskCache, f cacheKeyFactory, i pooledByteBufferFactory, com.facebook.common.e.a byteArrayPool, aj<e> inputProducer) {
        this.a = defaultBufferedDiskCache;
        this.b = cacheKeyFactory;
        this.c = pooledByteBufferFactory;
        this.d = byteArrayPool;
        this.e = inputProducer;
    }

    public final void a(Consumer<e> consumer, ak producerContext) {
        b imageRequest = producerContext.a();
        if (imageRequest.n()) {
            producerContext.c().a(producerContext.b(), "PartialDiskCacheProducer");
            final c partialImageCacheKey = this.b.a(imageRequest.b().buildUpon().appendQueryParameter("fresco_partial", "true").build());
            final AtomicBoolean isCancelled = new AtomicBoolean(false);
            h<e> diskLookupTask = this.a.a(partialImageCacheKey, isCancelled);
            final String b = producerContext.b();
            final am c = producerContext.c();
            final Consumer<e> consumer2 = consumer;
            final ak akVar = producerContext;
            diskLookupTask.a(new b.f<e, Void>(this) {
                final /* synthetic */ ag f;

                public final /* synthetic */ Object a(h hVar) throws Exception {
                    boolean z;
                    boolean z2 = true;
                    if (hVar.b() || (hVar.c() && (hVar.e() instanceof CancellationException))) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        c.b(b, "PartialDiskCacheProducer", null);
                        consumer2.b();
                    } else if (hVar.c()) {
                        c.a(b, "PartialDiskCacheProducer", hVar.e(), null);
                        this.f.e.a(new a(consumer2, this.f.a, partialImageCacheKey, this.f.c, this.f.d, null, (byte) 0), akVar);
                    } else {
                        e eVar = (e) hVar.d();
                        if (eVar != null) {
                            boolean z3;
                            c.a(b, "PartialDiskCacheProducer", ag.a(c, b, true, eVar.l()));
                            int l = eVar.l() - 1;
                            com.facebook.common.internal.h.a(l > 0);
                            com.facebook.imagepipeline.common.a aVar = new com.facebook.imagepipeline.common.a(0, l);
                            eVar.a(aVar);
                            l = eVar.l();
                            b a = akVar.a();
                            com.facebook.imagepipeline.common.a h = a.h();
                            if (h == null || aVar.a > h.a || aVar.b < h.b) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            if (z3) {
                                c.a(b, "PartialDiskCacheProducer", true);
                                consumer2.b(eVar, 9);
                            } else {
                                consumer2.b(eVar, 8);
                                com.facebook.imagepipeline.k.c a2 = com.facebook.imagepipeline.k.c.a(a);
                                l--;
                                if (l < 0) {
                                    z2 = false;
                                }
                                com.facebook.common.internal.h.a(z2);
                                this.f.e.a(new a(consumer2, this.f.a, partialImageCacheKey, this.f.c, this.f.d, eVar, (byte) 0), new aq(a2.a(new com.facebook.imagepipeline.common.a(l, Integer.MAX_VALUE)).q(), akVar));
                            }
                        } else {
                            c.a(b, "PartialDiskCacheProducer", ag.a(c, b, false, 0));
                            this.f.e.a(new a(consumer2, this.f.a, partialImageCacheKey, this.f.c, this.f.d, eVar, (byte) 0), akVar);
                        }
                    }
                    return null;
                }
            });
            producerContext.a(new e(this) {
                final /* synthetic */ ag b;

                public final void a() {
                    isCancelled.set(true);
                }
            });
            return;
        }
        this.e.a(consumer, producerContext);
    }

    @VisibleForTesting
    @Nullable
    static Map<String, String> a(am listener, String requestId, boolean valueFound, int sizeInBytes) {
        if (!listener.b(requestId)) {
            return null;
        }
        if (valueFound) {
            return com.facebook.common.internal.e.a("cached_value_found", String.valueOf(valueFound), "encodedImageSize", String.valueOf(sizeInBytes));
        }
        return com.facebook.common.internal.e.a("cached_value_found", String.valueOf(valueFound));
    }
}
