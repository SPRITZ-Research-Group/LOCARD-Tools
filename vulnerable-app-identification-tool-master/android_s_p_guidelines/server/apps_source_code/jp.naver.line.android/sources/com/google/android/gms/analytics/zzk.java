package com.google.android.gms.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Process;
import android.util.DisplayMetrics;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzac;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzx;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint({"StaticFieldLeak"})
@VisibleForTesting
public final class zzk {
    private static volatile zzk zzsm;
    private final Context zzri;
    private final List<zzn> zzsn = new CopyOnWriteArrayList();
    private final zze zzso = new zze();
    private final zza zzsp = new zza(this);
    private volatile zzx zzsq;
    private UncaughtExceptionHandler zzsr;

    final class zza extends ThreadPoolExecutor {
        final /* synthetic */ zzk zzst;

        public zza(zzk zzk) {
            this.zzst = zzk;
            super(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb());
            allowCoreThreadTimeOut(true);
        }

        protected final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new zzm(this, runnable, t);
        }
    }

    final class zzb implements ThreadFactory {
        private static final AtomicInteger zzsv = new AtomicInteger();

        private zzb() {
        }

        public final Thread newThread(Runnable runnable) {
            int incrementAndGet = zzsv.incrementAndGet();
            StringBuilder stringBuilder = new StringBuilder(23);
            stringBuilder.append("measurement-");
            stringBuilder.append(incrementAndGet);
            return new zzc(runnable, stringBuilder.toString());
        }

        /* synthetic */ zzb(zzl zzl) {
            this();
        }
    }

    final class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public final void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    @VisibleForTesting
    private zzk(Context context) {
        context = context.getApplicationContext();
        Preconditions.checkNotNull(context);
        this.zzri = context;
    }

    public static zzk zzb(Context context) {
        Preconditions.checkNotNull(context);
        if (zzsm == null) {
            synchronized (zzk.class) {
                if (zzsm == null) {
                    zzsm = new zzk(context);
                }
            }
        }
        return zzsm;
    }

    public final com.google.android.gms.internal.measurement.zzx zzad() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.analytics.zzk.zzad():com.google.android.gms.internal.measurement.zzx. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r7 = this;
        r0 = r7.zzsq;
        if (r0 != 0) goto L_0x0070;
    L_0x0004:
        monitor-enter(r7);
        r0 = r7.zzsq;	 Catch:{ all -> 0x006d }
        if (r0 != 0) goto L_0x006b;	 Catch:{ all -> 0x006d }
    L_0x0009:
        r0 = new com.google.android.gms.internal.measurement.zzx;	 Catch:{ all -> 0x006d }
        r0.<init>();	 Catch:{ all -> 0x006d }
        r1 = r7.zzri;	 Catch:{ all -> 0x006d }
        r1 = r1.getPackageManager();	 Catch:{ all -> 0x006d }
        r2 = r7.zzri;	 Catch:{ all -> 0x006d }
        r2 = r2.getPackageName();	 Catch:{ all -> 0x006d }
        r0.setAppId(r2);	 Catch:{ all -> 0x006d }
        r3 = r1.getInstallerPackageName(r2);	 Catch:{ all -> 0x006d }
        r0.setAppInstallerId(r3);	 Catch:{ all -> 0x006d }
        r3 = 0;
        r4 = r7.zzri;	 Catch:{ NameNotFoundException -> 0x0047 }
        r4 = r4.getPackageName();	 Catch:{ NameNotFoundException -> 0x0047 }
        r5 = 0;	 Catch:{ NameNotFoundException -> 0x0047 }
        r4 = r1.getPackageInfo(r4, r5);	 Catch:{ NameNotFoundException -> 0x0047 }
        if (r4 == 0) goto L_0x0063;	 Catch:{ NameNotFoundException -> 0x0047 }
    L_0x0032:
        r5 = r4.applicationInfo;	 Catch:{ NameNotFoundException -> 0x0047 }
        r1 = r1.getApplicationLabel(r5);	 Catch:{ NameNotFoundException -> 0x0047 }
        r5 = android.text.TextUtils.isEmpty(r1);	 Catch:{ NameNotFoundException -> 0x0047 }
        if (r5 != 0) goto L_0x0043;	 Catch:{ NameNotFoundException -> 0x0047 }
    L_0x003e:
        r1 = r1.toString();	 Catch:{ NameNotFoundException -> 0x0047 }
        r2 = r1;	 Catch:{ NameNotFoundException -> 0x0047 }
    L_0x0043:
        r1 = r4.versionName;	 Catch:{ NameNotFoundException -> 0x0047 }
        r3 = r1;
        goto L_0x0063;
    L_0x0047:
        r1 = "GAv4";	 Catch:{ all -> 0x006d }
        r4 = "Error retrieving package info: appName set to ";	 Catch:{ all -> 0x006d }
        r5 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x006d }
        r6 = r5.length();	 Catch:{ all -> 0x006d }
        if (r6 == 0) goto L_0x005a;	 Catch:{ all -> 0x006d }
    L_0x0055:
        r4 = r4.concat(r5);	 Catch:{ all -> 0x006d }
        goto L_0x0060;	 Catch:{ all -> 0x006d }
    L_0x005a:
        r5 = new java.lang.String;	 Catch:{ all -> 0x006d }
        r5.<init>(r4);	 Catch:{ all -> 0x006d }
        r4 = r5;	 Catch:{ all -> 0x006d }
    L_0x0060:
        android.util.Log.e(r1, r4);	 Catch:{ all -> 0x006d }
    L_0x0063:
        r0.setAppName(r2);	 Catch:{ all -> 0x006d }
        r0.setAppVersion(r3);	 Catch:{ all -> 0x006d }
        r7.zzsq = r0;	 Catch:{ all -> 0x006d }
    L_0x006b:
        monitor-exit(r7);	 Catch:{ all -> 0x006d }
        goto L_0x0070;	 Catch:{ all -> 0x006d }
    L_0x006d:
        r0 = move-exception;	 Catch:{ all -> 0x006d }
        monitor-exit(r7);	 Catch:{ all -> 0x006d }
        throw r0;
    L_0x0070:
        r0 = r7.zzsq;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.zzk.zzad():com.google.android.gms.internal.measurement.zzx");
    }

    public final zzac zzae() {
        DisplayMetrics displayMetrics = this.zzri.getResources().getDisplayMetrics();
        zzac zzac = new zzac();
        zzac.setLanguage(zzdg.zza(Locale.getDefault()));
        zzac.zzuh = displayMetrics.widthPixels;
        zzac.zzui = displayMetrics.heightPixels;
        return zzac;
    }

    final void zze(zzg zzg) {
        if (zzg.zzaa()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (zzg.zzx()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        } else {
            zzg = zzg.zzs();
            zzg.zzy();
            this.zzsp.execute(new zzl(this, zzg));
        }
    }

    public final Context getContext() {
        return this.zzri;
    }

    public static void zzaf() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final void zza(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzsr = uncaughtExceptionHandler;
    }

    public final <V> Future<V> zza(Callable<V> callable) {
        Preconditions.checkNotNull(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzsp.submit(callable);
        }
        Future futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    public final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        this.zzsp.submit(runnable);
    }

    private static void zzb(zzg zzg) {
        Preconditions.checkNotMainThread("deliver should be called from worker thread");
        Preconditions.checkArgument(zzg.zzx(), "Measurement must be submitted");
        List<zzo> zzu = zzg.zzu();
        if (!zzu.isEmpty()) {
            Set hashSet = new HashSet();
            for (zzo zzo : zzu) {
                Uri zzo2 = zzo.zzo();
                if (!hashSet.contains(zzo2)) {
                    hashSet.add(zzo2);
                    zzo.zzb(zzg);
                }
            }
        }
    }
}
