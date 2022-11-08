package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.c.g;
import com.google.android.gms.c.j;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.a;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseInstanceId {
    private static final long zzah = TimeUnit.HOURS.toSeconds(8);
    private static p zzai;
    @GuardedBy("FirebaseInstanceId.class")
    @VisibleForTesting
    private static ScheduledThreadPoolExecutor zzaj;
    private final a zzak;
    private final f zzal;
    private final ae zzam;
    private final i zzan;
    @GuardedBy("this")
    private boolean zzao;
    @GuardedBy("this")
    private boolean zzap;

    FirebaseInstanceId(a aVar) {
        this(aVar, new f(aVar.a()));
    }

    private FirebaseInstanceId(a aVar, f fVar) {
        this.zzan = new i();
        this.zzao = false;
        if (f.a(aVar) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (zzai == null) {
                zzai = new p(aVar.a());
            }
        }
        this.zzak = aVar;
        this.zzal = fVar;
        this.zzam = new ab(aVar, this, fVar);
        this.zzap = zzm();
        if (zzo()) {
            zzd();
        }
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(a.c());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull a aVar) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) aVar.a(FirebaseInstanceId.class);
        }
        return firebaseInstanceId;
    }

    private final synchronized void startSync() {
        if (!this.zzao) {
            zza(0);
        }
    }

    private static <T> T zza(g<T> gVar) throws IOException {
        try {
            return j.a(gVar);
        } catch (Throwable e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw new IOException(cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException e2) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    private final String zza(String str, String str2, Bundle bundle) throws IOException {
        return ((ab) this.zzam).a(str, str2, bundle);
    }

    static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzaj == null) {
                zzaj = new ScheduledThreadPoolExecutor(1);
            }
            zzaj.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    private static String zzd(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase("gcm")) ? "*" : str;
    }

    private final void zzd() {
        q zzg = zzg();
        if (zzg == null || zzg.b(this.zzal.b()) || zzai.a() != null) {
            startSync();
        }
    }

    static String zzf() {
        return f.a(zzai.c("").a());
    }

    static p zzi() {
        return zzai;
    }

    static boolean zzj() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }

    private final boolean zzm() {
        Context a = this.zzak.a();
        SharedPreferences sharedPreferences = a.getSharedPreferences("com.google.firebase.messaging", 0);
        if (sharedPreferences.contains("auto_init")) {
            return sharedPreferences.getBoolean("auto_init", true);
        }
        try {
            PackageManager packageManager = a.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(a.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled"))) {
                    return applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled");
                }
            }
        } catch (NameNotFoundException e) {
        }
        return zzn();
    }

    private final boolean zzn() {
        try {
            Class.forName("com.google.firebase.messaging.a");
            return true;
        } catch (ClassNotFoundException e) {
            Context a = this.zzak.a();
            Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
            intent.setPackage(a.getPackageName());
            ResolveInfo resolveService = a.getPackageManager().resolveService(intent, 0);
            return (resolveService == null || resolveService.serviceInfo == null) ? false : true;
        }
    }

    @WorkerThread
    public void deleteInstanceId() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Bundle bundle = new Bundle();
        bundle.putString("iid-operation", "delete");
        bundle.putString("delete", "1");
        zza("*", "*", bundle);
        zzk();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String zzd = zzd(str2);
        Bundle bundle = new Bundle();
        bundle.putString("delete", "1");
        zza(str, zzd, bundle);
        zzai.b("", str, zzd);
    }

    public long getCreationTime() {
        return zzai.c("").b();
    }

    @WorkerThread
    public String getId() {
        zzd();
        return zzf();
    }

    @Nullable
    public String getToken() {
        q zzg = zzg();
        if (zzg == null || zzg.b(this.zzal.b())) {
            startSync();
        }
        return zzg != null ? zzg.a : null;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        String zzd = zzd(str2);
        q a = zzai.a("", str, zzd);
        return (a == null || a.b(this.zzal.b())) ? this.zzan.a(str, zzd, new aa(this, str, zzd)) : a.a;
    }

    final /* synthetic */ String zza(String str, String str2) throws IOException {
        String str3 = (String) zza(this.zzam.a(str, str2));
        zzai.a("", str, str2, str3, this.zzal.b());
        return str3;
    }

    final synchronized void zza(long j) {
        zza(new r(this, this.zzal, Math.min(Math.max(30, j << 1), zzah)), j);
        this.zzao = true;
    }

    public final synchronized void zza(String str) {
        zzai.a(str);
        startSync();
    }

    final synchronized void zza(boolean z) {
        this.zzao = z;
    }

    final void zzb(String str) throws IOException {
        q zzg = zzg();
        if (zzg == null || zzg.b(this.zzal.b())) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String str3 = zzg.a;
        str2 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        zza(str3, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), bundle);
    }

    @VisibleForTesting
    public final synchronized void zzb(boolean z) {
        Editor edit = this.zzak.a().getSharedPreferences("com.google.firebase.messaging", 0).edit();
        edit.putBoolean("auto_init", z);
        edit.apply();
        if (!this.zzap && z) {
            zzd();
        }
        this.zzap = z;
    }

    final void zzc(String str) throws IOException {
        q zzg = zzg();
        if (zzg == null || zzg.b(this.zzal.b())) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        bundle.putString("delete", "1");
        String str3 = zzg.a;
        str2 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        zza(str3, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), bundle);
    }

    final a zze() {
        return this.zzak;
    }

    @Nullable
    final q zzg() {
        return zzai.a("", f.a(this.zzak), "*");
    }

    final String zzh() throws IOException {
        return getToken(f.a(this.zzak), "*");
    }

    final synchronized void zzk() {
        zzai.b();
        if (zzo()) {
            startSync();
        }
    }

    final void zzl() {
        zzai.d("");
        startSync();
    }

    @VisibleForTesting
    public final synchronized boolean zzo() {
        return this.zzap;
    }
}
