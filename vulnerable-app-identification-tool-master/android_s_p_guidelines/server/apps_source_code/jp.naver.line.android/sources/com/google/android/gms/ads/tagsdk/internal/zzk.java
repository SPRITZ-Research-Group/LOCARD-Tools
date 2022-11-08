package com.google.android.gms.ads.tagsdk.internal;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.view.MotionEvent;
import com.google.android.gms.internal.biski.zzac.zzb;
import com.google.android.gms.internal.biski.zzbx;
import com.google.android.gms.internal.biski.zze.zza;
import com.google.android.gms.internal.biski.zze.zzc;
import com.google.android.gms.internal.biski.zzt;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

final class zzk {
    private static final String[] zzcg = new String[]{"/aclk", "/pcs/click"};
    private static final String[] zzch = new String[]{"googleads.g.doubleclick.net", "adclick.g.doubleclick.net", "www.googleadservices.com", "adclick.googleadservices.com"};
    private final Executor zzbn;
    private final zza zzbw;
    private final zzi zzcb;
    private final Context zzci;
    private final Object zzcj = new Object();
    private final byte[] zzck;
    private final Task<zzt> zzcl;
    private volatile zzt zzcm;
    private volatile Task<String> zzcn;
    private long zzco = -1;
    private volatile boolean zzcp = true;

    static zzk zza(Context context, Executor executor, zza zza) {
        return new zzk(context, Tasks.call(executor, new zzl(context, zza)), executor, zza, new zzi(UUID.randomUUID().toString()));
    }

    private zzk(Context context, Task<zzt> task, Executor executor, zza zza, zzi zzi) {
        this.zzci = context;
        this.zzcl = task;
        this.zzbn = executor;
        this.zzbw = zza;
        this.zzcb = zzi;
        this.zzck = ((zza) ((zzbx) zza.zzk().zzb((zzc) ((zzbx) zzc.zzo().zzb(zzi.zzq()).zzbt())).zzbt())).toByteArray();
        task.addOnCompleteListener(new zzm(this, zza));
    }

    final void zza(int i, int i2, int i3, int i4, int i5, int i6) {
        zzt zzt = this.zzcm;
        if (zzt == null) {
            this.zzbw.zza(13, zzb.TOUCH);
        } else if (i == 1) {
            try {
                zzt.zza(MotionEvent.obtain(0, (long) i6, i, (float) i2, (float) i3, 0));
            } catch (Exception e) {
                this.zzbw.zza(12, zzb.TOUCH, e);
            }
        }
    }

    final String zzr() {
        zzb zzb = this.zzcp ? zzb.FIRST_QUERY : zzb.QUERY;
        this.zzcp = false;
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            String str = (String) Tasks.await(zzu(), 70, TimeUnit.MILLISECONDS);
            zzs();
            this.zzbw.zza(11, zzb, SystemClock.elapsedRealtime() - elapsedRealtime);
            return str;
        } catch (Exception e) {
            this.zzbw.zza(12, zzb, e);
            return "19";
        } catch (Exception e2) {
            this.zzbw.zza(12, zzb, e2);
            return "3";
        }
    }

    final void zzs() {
        synchronized (this.zzcj) {
            if (this.zzcn == null || !zzt()) {
                this.zzcn = zzv();
                this.zzco = SystemClock.elapsedRealtime();
            }
        }
    }

    private final boolean zzt() {
        return SystemClock.elapsedRealtime() - this.zzco <= 900000;
    }

    private final Task<String> zzu() {
        if (this.zzcn != null) {
            synchronized (this.zzcj) {
                if (this.zzcn == null || !zzt()) {
                } else {
                    Task<String> task = this.zzcn;
                    this.zzcn = null;
                    return task;
                }
            }
        }
        return zzv();
    }

    private final Task<String> zzv() {
        return this.zzcl.continueWith(this.zzbn, new zzn(this));
    }

    final Uri zzb(Uri uri) {
        if (!isGoogleAdClickUrl(uri)) {
            return uri;
        }
        zzt zzt = this.zzcm;
        if (zzt == null) {
            this.zzbw.zza(13, zzb.CLICK);
            return uri;
        }
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Uri zza = zzt.zza(uri, this.zzci);
            this.zzbw.zza(11, zzb.CLICK, SystemClock.elapsedRealtime() - elapsedRealtime);
            return zza;
        } catch (Exception e) {
            this.zzbw.zza(12, zzb.CLICK, e);
            return uri;
        }
    }

    static boolean isGoogleAdClickUrl(Uri uri) {
        String host = uri.getHost();
        String path = uri.getPath();
        if (host == null || path == null) {
            return false;
        }
        for (CharSequence contains : zzcg) {
            if (path.contains(contains)) {
                for (String equals : zzch) {
                    if (equals.equals(host)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    final zzi zzw() {
        return this.zzcb;
    }

    final /* synthetic */ String zza(Task task) throws Exception {
        if (task.isSuccessful()) {
            return ((zzt) task.getResult()).zza(this.zzci, this.zzck);
        }
        return "17";
    }

    final /* synthetic */ void zza(zza zza, Task task) {
        if (task.isSuccessful()) {
            this.zzcm = (zzt) task.getResult();
            return;
        }
        if (task.getException() != null) {
            zza.zza(12, zzb.INIT, task.getException());
        }
    }

    static final /* synthetic */ zzt zza(Context context, zza zza) throws Exception {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        zzt zzt = new zzt("afma-tag-a-v12451009.12451000", context);
        zza.zza(11, zzb.INIT, SystemClock.elapsedRealtime() - elapsedRealtime);
        return zzt;
    }
}
