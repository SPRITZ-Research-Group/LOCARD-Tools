package com.facebook.imagepipeline.d;

import com.facebook.cache.a.c;
import com.facebook.cache.disk.h;
import com.facebook.common.e.i;
import com.facebook.common.e.l;
import com.facebook.common.f.a;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.l.b;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class e {
    private static final Class<?> a = e.class;
    private final h b;
    private final i c;
    private final l d;
    private final Executor e;
    private final Executor f;
    private final u g = u.a();
    private final n h;

    public e(h fileCache, i pooledByteBufferFactory, l pooledByteStreams, Executor readExecutor, Executor writeExecutor, n imageCacheStatsTracker) {
        this.b = fileCache;
        this.c = pooledByteBufferFactory;
        this.d = pooledByteStreams;
        this.e = readExecutor;
        this.f = writeExecutor;
        this.h = imageCacheStatsTracker;
    }

    private boolean d(c key) {
        return this.g.c(key) || this.b.c(key);
    }

    public final b.h<Boolean> a(c key) {
        if (d(key)) {
            return b.h.a(Boolean.valueOf(true));
        }
        return e(key);
    }

    private b.h<Boolean> e(final c key) {
        try {
            return b.h.a(new Callable<Boolean>(this) {
                final /* synthetic */ e b;

                public final /* synthetic */ Object call() throws Exception {
                    return Boolean.valueOf(this.b.f(key));
                }
            }, this.e);
        } catch (Exception exception) {
            FLog.w(a, (Throwable) exception, "Failed to schedule disk-cache read for %s", key.a());
            return b.h.a(exception);
        }
    }

    public final boolean b(c key) {
        if (d(key)) {
            return true;
        }
        return f(key);
    }

    public final b.h<com.facebook.imagepipeline.image.e> a(c key, AtomicBoolean isCancelled) {
        try {
            b.h<com.facebook.imagepipeline.image.e> a;
            b.a();
            Object pinnedImage = this.g.b(key);
            if (pinnedImage != null) {
                FLog.v(a, "Found image for %s in staging area", key.a());
                a = b.h.a(pinnedImage);
            } else {
                a = b(key, isCancelled);
                b.a();
            }
            return a;
        } finally {
            b.a();
        }
    }

    private boolean f(c key) {
        com.facebook.imagepipeline.image.e result = this.g.b(key);
        if (result != null) {
            result.close();
            FLog.v(a, "Found image for %s in staging area", key.a());
            return true;
        }
        FLog.v(a, "Did not find image for %s in staging area", key.a());
        try {
            return this.b.d(key);
        } catch (Exception e) {
            return false;
        }
    }

    private b.h<com.facebook.imagepipeline.image.e> b(final c key, final AtomicBoolean isCancelled) {
        try {
            return b.h.a(new Callable<com.facebook.imagepipeline.image.e>(this) {
                final /* synthetic */ e c;

                public final /* synthetic */ Object call() throws Exception {
                    return a();
                }

                private com.facebook.imagepipeline.image.e a() throws Exception {
                    try {
                        b.a();
                        if (isCancelled.get()) {
                            throw new CancellationException();
                        }
                        com.facebook.imagepipeline.image.e result = this.c.g.b(key);
                        if (result != null) {
                            FLog.v(e.a, "Found image for %s in staging area", key.a());
                        } else {
                            FLog.v(e.a, "Did not find image for %s in staging area", key.a());
                            a ref;
                            try {
                                ref = a.a(this.c.g(key));
                                com.facebook.imagepipeline.image.e result2 = new com.facebook.imagepipeline.image.e(ref);
                                try {
                                    a.c(ref);
                                    result = result2;
                                } catch (Exception e) {
                                    result = result2;
                                    b.a();
                                    return null;
                                }
                            } catch (Exception e2) {
                            } catch (Throwable th) {
                                a.c(ref);
                            }
                        }
                        if (!Thread.interrupted()) {
                            return result;
                        }
                        FLog.v(e.a, "Host thread was interrupted, decreasing reference count");
                        if (result != null) {
                            result.close();
                        }
                        throw new InterruptedException();
                    } finally {
                        b.a();
                    }
                }
            }, this.e);
        } catch (Exception exception) {
            FLog.w(a, (Throwable) exception, "Failed to schedule disk-cache read for %s", key.a());
            return b.h.a(exception);
        }
    }

    public final void a(final c key, com.facebook.imagepipeline.image.e encodedImage) {
        final com.facebook.imagepipeline.image.e finalEncodedImage;
        try {
            b.a();
            com.facebook.common.internal.h.a((Object) key);
            com.facebook.common.internal.h.a(com.facebook.imagepipeline.image.e.e(encodedImage));
            this.g.a(key, encodedImage);
            finalEncodedImage = com.facebook.imagepipeline.image.e.a(encodedImage);
            this.f.execute(new Runnable(this) {
                final /* synthetic */ e c;

                public final void run() {
                    try {
                        b.a();
                        e.a(this.c, key, finalEncodedImage);
                    } finally {
                        this.c.g.b(key, finalEncodedImage);
                        com.facebook.imagepipeline.image.e.d(finalEncodedImage);
                        b.a();
                    }
                }
            });
        } catch (Throwable exception) {
            FLog.w(a, exception, "Failed to schedule disk-cache write for %s", key.a());
            this.g.b(key, encodedImage);
            com.facebook.imagepipeline.image.e.d(finalEncodedImage);
        } catch (Throwable th) {
            b.a();
        }
        b.a();
    }

    public final b.h<Void> c(final c key) {
        com.facebook.common.internal.h.a((Object) key);
        this.g.a(key);
        try {
            return b.h.a(new Callable<Void>(this) {
                final /* synthetic */ e b;

                public final /* synthetic */ Object call() throws Exception {
                    return a();
                }

                private Void a() throws Exception {
                    try {
                        b.a();
                        this.b.g.a(key);
                        this.b.b.b(key);
                        return null;
                    } finally {
                        b.a();
                    }
                }
            }, this.f);
        } catch (Exception exception) {
            FLog.w(a, (Throwable) exception, "Failed to schedule disk-cache remove for %s", key.a());
            return b.h.a(exception);
        }
    }

    public final b.h<Void> a() {
        this.g.b();
        try {
            return b.h.a(new Callable<Void>(this) {
                final /* synthetic */ e a;

                {
                    this.a = this$0;
                }

                public final /* synthetic */ Object call() throws Exception {
                    this.a.g.b();
                    this.a.b.a();
                    return null;
                }
            }, this.f);
        } catch (Exception exception) {
            FLog.w(a, (Throwable) exception, "Failed to schedule disk-cache clear", new Object[0]);
            return b.h.a(exception);
        }
    }

    private com.facebook.common.e.h g(c key) throws IOException {
        InputStream is;
        try {
            FLog.v(a, "Disk cache read for %s", key.a());
            com.facebook.binaryresource.a diskCacheResource = this.b.a(key);
            if (diskCacheResource == null) {
                FLog.v(a, "Disk cache miss for %s", key.a());
                return null;
            }
            FLog.v(a, "Found entry in disk cache for %s", key.a());
            is = diskCacheResource.a();
            com.facebook.common.e.h byteBuffer = this.c.a(is, (int) diskCacheResource.b());
            is.close();
            FLog.v(a, "Successful read from disk cache for %s", key.a());
            return byteBuffer;
        } catch (Throwable ioe) {
            FLog.w(a, ioe, "Exception reading from cache for %s", key.a());
            throw ioe;
        } catch (Throwable th) {
            is.close();
        }
    }

    static /* synthetic */ void a(e x0, c x1, final com.facebook.imagepipeline.image.e x2) {
        FLog.v(a, "About to write to disk-cache for key %s", x1.a());
        try {
            x0.b.a(x1, new com.facebook.cache.a.i(x0) {
                final /* synthetic */ e b;

                public final void a(OutputStream os) throws IOException {
                    this.b.d.a(x2.c(), os);
                }
            });
            FLog.v(a, "Successful disk-cache write for key %s", x1.a());
        } catch (Throwable e) {
            FLog.w(a, e, "Failed to write to disk-cache for key %s", x1.a());
        }
    }
}
