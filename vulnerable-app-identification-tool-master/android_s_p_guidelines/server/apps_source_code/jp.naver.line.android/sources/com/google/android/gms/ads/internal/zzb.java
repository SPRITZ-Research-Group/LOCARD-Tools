package com.google.android.gms.ads.internal;

import android.os.Debug;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

final class zzb extends TimerTask {
    private final /* synthetic */ CountDownLatch zzwd;
    private final /* synthetic */ Timer zzwe;
    private final /* synthetic */ zza zzwf;

    zzb(zza zza, CountDownLatch countDownLatch, Timer timer) {
        this.zzwf = zza;
        this.zzwd = countDownLatch;
        this.zzwe = timer;
    }

    public final void run() {
        if (((long) ((Integer) zzkb.zzik().zzd(zznk.zzbck)).intValue()) != this.zzwd.getCount()) {
            zzane.zzck("Stopping method tracing");
            Debug.stopMethodTracing();
            if (this.zzwd.getCount() == 0) {
                this.zzwe.cancel();
                return;
            }
        }
        String concat = String.valueOf(this.zzwf.zzvw.zzrt.getPackageName()).concat("_adsTrace_");
        try {
            zzane.zzck("Starting method tracing");
            this.zzwd.countDown();
            long currentTimeMillis = zzbv.zzer().currentTimeMillis();
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(concat).length() + 20);
            stringBuilder.append(concat);
            stringBuilder.append(currentTimeMillis);
            Debug.startMethodTracing(stringBuilder.toString(), ((Integer) zzkb.zzik().zzd(zznk.zzbcl)).intValue());
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
