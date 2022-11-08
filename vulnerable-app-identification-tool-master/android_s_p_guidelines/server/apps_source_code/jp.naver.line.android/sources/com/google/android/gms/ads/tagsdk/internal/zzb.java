package com.google.android.gms.ads.tagsdk.internal;

final /* synthetic */ class zzb implements Runnable {
    private final zza zzbo;
    private final int zzbp;
    private final com.google.android.gms.internal.biski.zzac.zzb zzbq;
    private final Exception zzbr;

    zzb(zza zza, int i, com.google.android.gms.internal.biski.zzac.zzb zzb, Exception exception) {
        this.zzbo = zza;
        this.zzbp = i;
        this.zzbq = zzb;
        this.zzbr = exception;
    }

    public final void run() {
        this.zzbo.zzb(this.zzbp, this.zzbq, this.zzbr);
    }
}
