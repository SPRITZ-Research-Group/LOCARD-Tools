package com.google.android.gms.ads.tagsdk.internal;

import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.biski.zzac.zzb;
import com.google.android.gms.internal.biski.zzbx;
import com.google.android.gms.internal.biski.zzw;
import java.util.concurrent.Executor;

final class zza {
    @VisibleForTesting
    private final ClearcutLogger zzbl;
    private final String zzbm;
    private final Executor zzbn;

    zza(ClearcutLogger clearcutLogger, String str, Executor executor) {
        this.zzbl = clearcutLogger;
        this.zzbm = str;
        this.zzbn = executor;
    }

    private final void zza(int i, com.google.android.gms.internal.biski.zzw.zzb.zza zza) {
        this.zzbl.newEvent(((zzw) ((zzbx) zzw.zzx().zzf(this.zzbm).zzb(zza).zzbt())).toByteArray()).setEventCode(i).log();
    }

    final void zza(int i, zzb zzb, Exception exception) {
        this.zzbn.execute(new zzb(this, i, zzb, exception));
    }

    final void zza(int i, zzb zzb, long j) {
        this.zzbn.execute(new zzc(this, 11, zzb, j));
    }

    final void zza(int i, zzb zzb) {
        this.zzbn.execute(new zzd(this, i, zzb));
    }

    final /* synthetic */ void zzb(int i, zzb zzb) {
        zza(i, zzw.zzb.zzz().zzc(zzb));
    }

    final /* synthetic */ void zzb(int i, zzb zzb, long j) {
        zza(i, zzw.zzb.zzz().zzc(zzb).zzb(j));
    }

    final /* synthetic */ void zzb(int i, zzb zzb, Exception exception) {
        zza(i, zzw.zzb.zzz().zzc(zzb).zzh(exception.getClass().getName()));
    }
}
