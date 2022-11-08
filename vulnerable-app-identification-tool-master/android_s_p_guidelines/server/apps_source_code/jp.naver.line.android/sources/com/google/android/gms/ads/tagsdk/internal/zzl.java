package com.google.android.gms.ads.tagsdk.internal;

import android.content.Context;
import java.util.concurrent.Callable;

final /* synthetic */ class zzl implements Callable {
    private final Context zzcq;
    private final zza zzcr;

    zzl(Context context, zza zza) {
        this.zzcq = context;
        this.zzcr = zza;
    }

    public final Object call() {
        return zzk.zza(this.zzcq, this.zzcr);
    }
}
