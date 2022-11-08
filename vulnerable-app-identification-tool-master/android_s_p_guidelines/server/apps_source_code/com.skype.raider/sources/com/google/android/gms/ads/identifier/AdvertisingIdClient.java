package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.h;
import com.google.android.gms.common.i;
import com.google.android.gms.common.internal.ab;
import com.google.android.gms.common.stats.b;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.a.e;
import com.google.android.gms.internal.a.f;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
@ParametersAreNonnullByDefault
public class AdvertisingIdClient {
    @Nullable
    @GuardedBy("this")
    private com.google.android.gms.common.a a;
    @Nullable
    @GuardedBy("this")
    private e b;
    @GuardedBy("this")
    private boolean c;
    private final Object d = new Object();
    @Nullable
    @GuardedBy("mAutoDisconnectTaskLock")
    private a e;
    @GuardedBy("this")
    private final Context f;
    private final boolean g;
    private final long h;

    @KeepForSdkWithMembers
    public static final class Info {
        private final String a;
        private final boolean b;

        public Info(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public final String getId() {
            return this.a;
        }

        public final boolean isLimitAdTrackingEnabled() {
            return this.b;
        }

        public final String toString() {
            String str = this.a;
            return new StringBuilder(String.valueOf(str).length() + 7).append("{").append(str).append("}").append(this.b).toString();
        }
    }

    @VisibleForTesting
    static class a extends Thread {
        CountDownLatch a = new CountDownLatch(1);
        boolean b = false;
        private WeakReference<AdvertisingIdClient> c;
        private long d;

        public a(AdvertisingIdClient advertisingIdClient, long j) {
            this.c = new WeakReference(advertisingIdClient);
            this.d = j;
            start();
        }

        private final void a() {
            AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.c.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.a();
                this.b = true;
            }
        }

        public final void run() {
            try {
                if (!this.a.await(this.d, TimeUnit.MILLISECONDS)) {
                    a();
                }
            } catch (InterruptedException e) {
                a();
            }
        }
    }

    @VisibleForTesting
    private AdvertisingIdClient(Context context, boolean z, boolean z2) {
        ab.a((Object) context);
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            this.f = context;
        } else {
            this.f = context;
        }
        this.c = false;
        this.h = -1;
        this.g = z2;
    }

    private static com.google.android.gms.common.a a(Context context, boolean z) throws IOException, h, i {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (com.google.android.gms.common.e.b().b(context, 12451000)) {
                case 0:
                case 2:
                    String str = z ? "com.google.android.gms.ads.identifier.service.PERSISTENT_START" : "com.google.android.gms.ads.identifier.service.START";
                    Object aVar = new com.google.android.gms.common.a();
                    Intent intent = new Intent(str);
                    intent.setPackage("com.google.android.gms");
                    try {
                        b.a();
                        if (b.b(context, intent, aVar, 1)) {
                            return aVar;
                        }
                        throw new IOException("Connection failure");
                    } catch (Throwable th) {
                        IOException iOException = new IOException(th);
                    }
                default:
                    throw new IOException("Google Play services not available");
            }
        } catch (NameNotFoundException e) {
            throw new h(9);
        }
    }

    @VisibleForTesting
    private static e a(com.google.android.gms.common.a aVar) throws IOException {
        try {
            return f.a(aVar.a(TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    @VisibleForTesting
    private static boolean a(Info info, boolean z, float f, long j, String str, Throwable th) {
        if (Math.random() > ((double) f)) {
            return false;
        }
        Map hashMap = new HashMap();
        hashMap.put("app_context", z ? "1" : "0");
        if (info != null) {
            hashMap.put("limit_ad_tracking", info.isLimitAdTrackingEnabled() ? "1" : "0");
        }
        if (!(info == null || info.getId() == null)) {
            hashMap.put("ad_id_size", Integer.toString(info.getId().length()));
        }
        if (th != null) {
            hashMap.put("error", th.getClass().getName());
        }
        if (!(str == null || str.isEmpty())) {
            hashMap.put("experiment_id", str);
        }
        hashMap.put("tag", "AdvertisingIdClient");
        hashMap.put("time_spent", Long.toString(j));
        new a(hashMap).start();
        return true;
    }

    @VisibleForTesting
    private final void b() throws IOException, IllegalStateException, h, i {
        ab.c("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.c) {
                a();
            }
            this.a = a(this.f, this.g);
            this.b = a(this.a);
            this.c = true;
        }
    }

    @KeepForSdk
    private Info c() throws IOException {
        Info info;
        ab.c("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.c) {
                synchronized (this.d) {
                    if (this.e == null || !this.e.b) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    b();
                    if (!this.c) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (RemoteException e) {
                    throw new IOException("Remote exception");
                } catch (Throwable e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            ab.a(this.a);
            ab.a(this.b);
            info = new Info(this.b.a(), this.b.b());
        }
        synchronized (this.d) {
            if (this.e != null) {
                this.e.a.countDown();
                try {
                    this.e.join();
                } catch (InterruptedException e3) {
                }
            }
            if (this.h > 0) {
                this.e = new a(this, this.h);
            }
        }
        return info;
    }

    @KeepForSdk
    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, h, i {
        b bVar = new b(context);
        boolean a = bVar.a("gads:ad_id_app_context:enabled");
        float b = bVar.b("gads:ad_id_app_context:ping_ratio");
        String a2 = bVar.a("gads:ad_id_use_shared_preference:experiment_id", "");
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, a, bVar.a("gads:ad_id_use_persistent_service:enabled"));
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            advertisingIdClient.b();
            Info c = advertisingIdClient.c();
            a(c, a, b, SystemClock.elapsedRealtime() - elapsedRealtime, a2, null);
            advertisingIdClient.a();
            return c;
        } catch (Throwable th) {
            advertisingIdClient.a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        ab.c("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.f == null || this.a == null) {
            } else {
                try {
                    if (this.c) {
                        b.a();
                        b.a(this.f, this.a);
                    }
                } catch (Throwable th) {
                }
                this.c = false;
                this.b = null;
                this.a = null;
            }
        }
    }

    protected void finalize() throws Throwable {
        a();
        super.finalize();
    }
}
