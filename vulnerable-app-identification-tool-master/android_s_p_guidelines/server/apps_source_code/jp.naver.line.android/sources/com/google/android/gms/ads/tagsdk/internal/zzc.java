package com.google.android.gms.ads.tagsdk.internal;

import com.google.android.gms.internal.biski.zzac.zzb;

final /* synthetic */ class zzc implements Runnable {
    private final zza zzbo;
    private final int zzbp = 11;
    private final zzb zzbq;
    private final long zzbs;

    zzc(zza zza, int i, zzb zzb, long j) {
        this.zzbo = zza;
        this.zzbq = zzb;
        this.zzbs = j;
    }

    public final void run() {
        this.zzbo.zzb(this.zzbp, this.zzbq, this.zzbs);
    }
}
