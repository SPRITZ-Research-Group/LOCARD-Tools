package com.google.android.gms.ads.tagsdk.internal;

import com.google.android.gms.internal.biski.zzac.zzb;

final /* synthetic */ class zzd implements Runnable {
    private final zza zzbo;
    private final int zzbp;
    private final zzb zzbq;

    zzd(zza zza, int i, zzb zzb) {
        this.zzbo = zza;
        this.zzbp = i;
        this.zzbq = zzb;
    }

    public final void run() {
        this.zzbo.zzb(this.zzbp, this.zzbq);
    }
}
