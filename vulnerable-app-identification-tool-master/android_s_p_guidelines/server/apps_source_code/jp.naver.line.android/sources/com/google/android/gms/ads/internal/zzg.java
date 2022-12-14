package com.google.android.gms.ads.internal;

import android.webkit.CookieManager;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.concurrent.Callable;

final class zzg implements Callable<String> {
    private final /* synthetic */ zzd zzwk;

    zzg(zzd zzd) {
        this.zzwk = zzd;
    }

    public final /* synthetic */ Object call() throws Exception {
        String str = "";
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbdj)).booleanValue()) {
            return str;
        }
        CookieManager zzax = zzbv.zzem().zzax(this.zzwk.zzvw.zzrt);
        return zzax != null ? zzax.getCookie("googleads.g.doubleclick.net") : str;
    }
}
