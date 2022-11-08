package com.facebook.fresco.animation.bitmap.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.bitmap.b;
import com.facebook.imagepipeline.c.f;
import java.util.concurrent.ExecutorService;

public class c implements b {
    private static final Class<?> a = c.class;
    private final f b;
    private final b c;
    private final Config d;
    private final ExecutorService e;
    private final SparseArray<Runnable> f = new SparseArray();

    private class a implements Runnable {
        final /* synthetic */ c a;
        private final com.facebook.fresco.animation.bitmap.a b;
        private final com.facebook.fresco.animation.a.a c;
        private final int d;
        private final int e;

        public a(c cVar, com.facebook.fresco.animation.a.a animationBackend, com.facebook.fresco.animation.bitmap.a bitmapFrameCache, int frameNumber, int hashCode) {
            this.a = cVar;
            this.c = animationBackend;
            this.b = bitmapFrameCache;
            this.d = frameNumber;
            this.e = hashCode;
        }

        public final void run() {
            try {
                if (this.b.b(this.d)) {
                    FLog.v(c.a, "Frame %d is cached already.", Integer.valueOf(this.d));
                    synchronized (this.a.f) {
                        this.a.f.remove(this.e);
                    }
                    return;
                }
                if (a(this.d, 1)) {
                    FLog.v(c.a, "Prepared frame frame %d.", Integer.valueOf(this.d));
                } else {
                    FLog.e(c.a, "Could not prepare frame %d.", Integer.valueOf(this.d));
                }
                synchronized (this.a.f) {
                    this.a.f.remove(this.e);
                }
            } catch (Throwable th) {
                synchronized (this.a.f) {
                    this.a.f.remove(this.e);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean a(int frameNumber, int frameType) {
            while (true) {
                com.facebook.common.f.a<Bitmap> bitmapReference = null;
                int nextFrameType;
                switch (frameType) {
                    case 1:
                        bitmapReference = this.b.b();
                        nextFrameType = 2;
                    default:
                        try {
                            bitmapReference = this.a.b.a(this.c.a(), this.c.b(), this.a.d);
                            nextFrameType = -1;
                        } catch (Throwable e) {
                            FLog.w(c.a, "Failed to create frame bitmap", e);
                            return created;
                        } finally {
                            com.facebook.common.f.a.c(bitmapReference);
                        }
                }
                frameType = 2;
            }
            if (created || nextFrameType == -1) {
                return created;
            }
            frameType = 2;
        }

        private boolean a(int frameNumber, com.facebook.common.f.a<Bitmap> bitmapReference) {
            if (!com.facebook.common.f.a.a((com.facebook.common.f.a) bitmapReference)) {
                return false;
            }
            if (!this.a.c.a(frameNumber, (Bitmap) bitmapReference.a())) {
                return false;
            }
            FLog.v(c.a, "Frame %d ready.", Integer.valueOf(this.d));
            synchronized (this.a.f) {
                this.b.b(this.d, bitmapReference);
            }
            return true;
        }
    }

    public c(f platformBitmapFactory, b bitmapFrameRenderer, Config bitmapConfig, ExecutorService executorService) {
        this.b = platformBitmapFactory;
        this.c = bitmapFrameRenderer;
        this.d = bitmapConfig;
        this.e = executorService;
    }

    public final boolean a(com.facebook.fresco.animation.bitmap.a bitmapFrameCache, com.facebook.fresco.animation.a.a animationBackend, int frameNumber) {
        int frameId = (animationBackend.hashCode() * 31) + frameNumber;
        synchronized (this.f) {
            if (this.f.get(frameId) != null) {
                FLog.v(a, "Already scheduled decode job for frame %d", Integer.valueOf(frameNumber));
            } else if (bitmapFrameCache.b(frameNumber)) {
                FLog.v(a, "Frame %d is cached already.", Integer.valueOf(frameNumber));
            } else {
                Runnable frameDecodeRunnable = new a(this, animationBackend, bitmapFrameCache, frameNumber, frameId);
                this.f.put(frameId, frameDecodeRunnable);
                this.e.execute(frameDecodeRunnable);
            }
        }
        return true;
    }
}
