package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zal;
import defpackage.bz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    private static final Object zamg = new Object();
    private static HashSet<Uri> zamh = new HashSet();
    private static ImageManager zami;
    private final Context mContext;
    private final Handler mHandler = new zal(Looper.getMainLooper());
    private final ExecutorService zamj = Executors.newFixedThreadPool(4);
    private final zaa zamk = null;
    private final zak zaml = new zak();
    private final Map<zaa, ImageReceiver> zamm = new HashMap();
    private final Map<Uri, ImageReceiver> zamn = new HashMap();
    private final Map<Uri, Long> zamo = new HashMap();

    @KeepName
    final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        private final ArrayList<zaa> zamp = new ArrayList();
        private final /* synthetic */ ImageManager zamq;

        ImageReceiver(ImageManager imageManager, Uri uri) {
            this.zamq = imageManager;
            super(new zal(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public final void zab(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zamp.add(zaa);
        }

        public final void zac(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zamp.remove(zaa);
        }

        public final void zace() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.putExtra(Constants.EXTRA_URI, this.mUri);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            this.zamq.mContext.sendBroadcast(intent);
        }

        public final void onReceiveResult(int i, Bundle bundle) {
            this.zamq.zamj.execute(new zab(this.zamq, this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    final class zab implements Runnable {
        private final Uri mUri;
        private final /* synthetic */ ImageManager zamq;
        private final ParcelFileDescriptor zamr;

        public zab(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zamq = imageManager;
            this.mUri = uri;
            this.zamr = parcelFileDescriptor;
        }

        public final void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.images.ImageManager.zab.run():void. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
            /*
            r12 = this;
            r0 = "LoadBitmapFromDiskRunnable can't be executed in the main thread";
            com.google.android.gms.common.internal.Asserts.checkNotMainThread(r0);
            r0 = r12.zamr;
            r1 = 1;
            r2 = 0;
            r3 = 0;
            if (r0 == 0) goto L_0x0051;
        L_0x000c:
            r0 = r12.zamr;	 Catch:{ OutOfMemoryError -> 0x0018 }
            r0 = r0.getFileDescriptor();	 Catch:{ OutOfMemoryError -> 0x0018 }
            r0 = android.graphics.BitmapFactory.decodeFileDescriptor(r0);	 Catch:{ OutOfMemoryError -> 0x0018 }
            r3 = r0;
            goto L_0x0040;
        L_0x0018:
            r0 = move-exception;
            r2 = "ImageManager";
            r4 = r12.mUri;
            r4 = java.lang.String.valueOf(r4);
            r5 = java.lang.String.valueOf(r4);
            r5 = r5.length();
            r5 = r5 + 34;
            r6 = new java.lang.StringBuilder;
            r6.<init>(r5);
            r5 = "OOM while loading bitmap for uri: ";
            r6.append(r5);
            r6.append(r4);
            r4 = r6.toString();
            android.util.Log.e(r2, r4, r0);
            r2 = 1;
        L_0x0040:
            r0 = r12.zamr;	 Catch:{ IOException -> 0x0046 }
            r0.close();	 Catch:{ IOException -> 0x0046 }
            goto L_0x004e;
        L_0x0046:
            r0 = move-exception;
            r4 = "ImageManager";
            r5 = "closed failed";
            android.util.Log.e(r4, r5, r0);
        L_0x004e:
            r10 = r2;
            r9 = r3;
            goto L_0x0053;
        L_0x0051:
            r9 = r3;
            r10 = 0;
        L_0x0053:
            r0 = new java.util.concurrent.CountDownLatch;
            r0.<init>(r1);
            r1 = r12.zamq;
            r1 = r1.mHandler;
            r2 = new com.google.android.gms.common.images.ImageManager$zad;
            r7 = r12.zamq;
            r8 = r12.mUri;
            r6 = r2;
            r11 = r0;
            r6.<init>(r7, r8, r9, r10, r11);
            r1.post(r2);
            r0.await();	 Catch:{ InterruptedException -> 0x0070 }
            return;
        L_0x0070:
            r0 = "ImageManager";
            r1 = r12.mUri;
            r1 = java.lang.String.valueOf(r1);
            r2 = java.lang.String.valueOf(r1);
            r2 = r2.length();
            r2 = r2 + 32;
            r3 = new java.lang.StringBuilder;
            r3.<init>(r2);
            r2 = "Latch interrupted while posting ";
            r3.append(r2);
            r3.append(r1);
            r1 = r3.toString();
            android.util.Log.w(r0, r1);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.ImageManager.zab.run():void");
        }
    }

    final class zac implements Runnable {
        private final /* synthetic */ ImageManager zamq;
        private final zaa zams;

        public zac(ImageManager imageManager, zaa zaa) {
            this.zamq = imageManager;
            this.zams = zaa;
        }

        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) this.zamq.zamm.get(this.zams);
            if (imageReceiver != null) {
                this.zamq.zamm.remove(this.zams);
                imageReceiver.zac(this.zams);
            }
            zab zab = this.zams.zamu;
            if (zab.uri == null) {
                this.zams.zaa(this.zamq.mContext, this.zamq.zaml, true);
                return;
            }
            Bitmap zaa = this.zamq.zaa(zab);
            if (zaa != null) {
                this.zams.zaa(this.zamq.mContext, zaa, true);
                return;
            }
            Long l = (Long) this.zamq.zamo.get(zab.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zams.zaa(this.zamq.mContext, this.zamq.zaml, true);
                    return;
                }
                this.zamq.zamo.remove(zab.uri);
            }
            this.zams.zaa(this.zamq.mContext, this.zamq.zaml);
            ImageReceiver imageReceiver2 = (ImageReceiver) this.zamq.zamn.get(zab.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(this.zamq, zab.uri);
                this.zamq.zamn.put(zab.uri, imageReceiver2);
            }
            imageReceiver2.zab(this.zams);
            if (!(this.zams instanceof zad)) {
                this.zamq.zamm.put(this.zams, imageReceiver2);
            }
            synchronized (ImageManager.zamg) {
                if (!ImageManager.zamh.contains(zab.uri)) {
                    ImageManager.zamh.add(zab.uri);
                    imageReceiver2.zace();
                }
            }
        }
    }

    final class zad implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zadq;
        private final /* synthetic */ ImageManager zamq;
        private boolean zamt;

        public zad(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.zamq = imageManager;
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zamt = z;
            this.zadq = countDownLatch;
        }

        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            Object obj = this.mBitmap != null ? 1 : null;
            if (this.zamq.zamk != null) {
                if (this.zamt) {
                    this.zamq.zamk.evictAll();
                    System.gc();
                    this.zamt = false;
                    this.zamq.mHandler.post(this);
                    return;
                } else if (obj != null) {
                    this.zamq.zamk.put(new zab(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) this.zamq.zamn.remove(this.mUri);
            if (imageReceiver != null) {
                ArrayList zaa = imageReceiver.zamp;
                int size = zaa.size();
                for (int i = 0; i < size; i++) {
                    zaa zaa2 = (zaa) zaa.get(i);
                    if (obj != null) {
                        zaa2.zaa(this.zamq.mContext, this.mBitmap, false);
                    } else {
                        this.zamq.zamo.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                        zaa2.zaa(this.zamq.mContext, this.zamq.zaml, false);
                    }
                    if (!(zaa2 instanceof zad)) {
                        this.zamq.zamm.remove(zaa2);
                    }
                }
            }
            this.zadq.countDown();
            synchronized (ImageManager.zamg) {
                ImageManager.zamh.remove(this.mUri);
            }
        }
    }

    final class zaa extends bz<zab, Bitmap> {
        protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
            Bitmap bitmap = (Bitmap) obj2;
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        protected final /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
            super.entryRemoved(z, (zab) obj, (Bitmap) obj2, (Bitmap) obj3);
        }
    }

    public static ImageManager create(Context context) {
        if (zami == null) {
            zami = new ImageManager(context, false);
        }
        return zami;
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zaa(new zac(imageView, uri));
    }

    public final void loadImage(ImageView imageView, int i) {
        zaa(new zac(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        zaa zac = new zac(imageView, uri);
        zac.zamw = i;
        zaa(zac);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zaa(new zad(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zaa zad = new zad(onImageLoadedListener, uri);
        zad.zamw = i;
        zaa(zad);
    }

    private final void zaa(zaa zaa) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zac(this, zaa).run();
    }

    private final Bitmap zaa(zab zab) {
        if (this.zamk == null) {
            return null;
        }
        return (Bitmap) this.zamk.get(zab);
    }
}
