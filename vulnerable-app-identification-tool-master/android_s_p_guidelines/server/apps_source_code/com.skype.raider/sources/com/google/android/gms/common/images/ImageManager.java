package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.g;
import com.google.android.gms.common.annotation.KeepName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public final class ImageManager {
    private static final Object a = new Object();
    private static HashSet<Uri> b = new HashSet();
    private final Context c;
    private final Handler d;
    private final ExecutorService e;
    private final b f;
    private final com.google.android.gms.common.images.internal.c g;
    private final Map<a, ImageReceiver> h;
    private final Map<Uri, ImageReceiver> i;
    private final Map<Uri, Long> j;

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final Uri a;
        private final ArrayList<a> b;
        private final /* synthetic */ ImageManager c;

        public final void onReceiveResult(int i, Bundle bundle) {
            this.c.e.execute(new c(this.c, this.a, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    public interface a {
    }

    private static final class b extends g<b, Bitmap> {
        protected final /* synthetic */ int b(Object obj) {
            Bitmap bitmap = (Bitmap) obj;
            return bitmap.getHeight() * bitmap.getRowBytes();
        }
    }

    private final class c implements Runnable {
        private final Uri a;
        private final ParcelFileDescriptor b;
        private final /* synthetic */ ImageManager c;

        public c(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.c = imageManager;
            this.a = uri;
            this.b = parcelFileDescriptor;
        }

        public final void run() {
            String str = "LoadBitmapFromDiskRunnable can't be executed in the main thread";
            String valueOf;
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                valueOf = String.valueOf(Thread.currentThread());
                String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
                new StringBuilder((String.valueOf(valueOf).length() + 56) + String.valueOf(valueOf2).length()).append("checkNotMainThread: current thread ").append(valueOf).append(" IS the main thread ").append(valueOf2).append("!");
                throw new IllegalStateException(str);
            }
            boolean z = false;
            Bitmap bitmap = null;
            if (this.b != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.b.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    str = String.valueOf(this.a);
                    new StringBuilder(String.valueOf(str).length() + 34).append("OOM while loading bitmap for uri: ").append(str);
                    z = true;
                }
                try {
                    this.b.close();
                } catch (IOException e2) {
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.c.d.post(new d(this.c, this.a, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                valueOf = String.valueOf(this.a);
                new StringBuilder(String.valueOf(valueOf).length() + 32).append("Latch interrupted while posting ").append(valueOf);
            }
        }
    }

    private final class d implements Runnable {
        private final Uri a;
        private final Bitmap b;
        private final CountDownLatch c;
        private boolean d;
        private final /* synthetic */ ImageManager e;

        public d(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.e = imageManager;
            this.a = uri;
            this.b = bitmap;
            this.d = z;
            this.c = countDownLatch;
        }

        public final void run() {
            int i = 0;
            String str = "OnBitmapLoadedRunnable must be executed in the main thread";
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                String valueOf = String.valueOf(Thread.currentThread());
                String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
                new StringBuilder((String.valueOf(valueOf).length() + 57) + String.valueOf(valueOf2).length()).append("checkMainThread: current thread ").append(valueOf).append(" IS NOT the main thread ").append(valueOf2).append("!");
                throw new IllegalStateException(str);
            }
            int i2 = this.b != null ? 1 : 0;
            if (this.e.f != null) {
                if (this.d) {
                    this.e.f.a();
                    System.gc();
                    this.d = false;
                    this.e.d.post(this);
                    return;
                } else if (i2 != 0) {
                    this.e.f.a(new b(this.a), this.b);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) this.e.i.remove(this.a);
            if (imageReceiver != null) {
                ArrayList a = imageReceiver.b;
                int size = a.size();
                while (i < size) {
                    a aVar = (a) a.get(i);
                    if (i2 != 0) {
                        aVar.a(this.e.c, this.b);
                    } else {
                        this.e.j.put(this.a, Long.valueOf(SystemClock.elapsedRealtime()));
                        aVar.a(this.e.c, this.e.g);
                    }
                    if (!(aVar instanceof com.google.android.gms.common.images.a.a)) {
                        this.e.h.remove(aVar);
                    }
                    i++;
                }
            }
            this.c.countDown();
            synchronized (ImageManager.a) {
                ImageManager.b.remove(this.a);
            }
        }
    }
}
