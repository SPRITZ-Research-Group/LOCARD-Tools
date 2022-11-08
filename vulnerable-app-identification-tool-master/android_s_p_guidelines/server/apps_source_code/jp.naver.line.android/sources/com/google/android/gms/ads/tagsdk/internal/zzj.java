package com.google.android.gms.ads.tagsdk.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.WebView;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.internal.biski.zzac.zzb;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class zzj {
    private final zzk zzbv;
    private final zza zzbw;
    private final WeakHashMap<WebView, zzg> zzcf = new WeakHashMap();

    public static zzj zza(Context context) {
        context = context.getApplicationContext();
        Executor newCachedThreadPool = Executors.newCachedThreadPool();
        zza zza = new zza(new ClearcutLogger(context, "BISKI_CLIENT", null), context.getPackageName(), newCachedThreadPool);
        return new zzj(zzk.zza(context, newCachedThreadPool, zza), zza);
    }

    private zzj(zzk zzk, zza zza) {
        this.zzbv = zzk;
        this.zzbw = zza;
    }

    public final void zza(WebView webView, List<String> list) {
        if (!this.zzcf.containsKey(webView)) {
            WeakHashMap weakHashMap = this.zzcf;
            zzk zzk = this.zzbv;
            zza zza = this.zzbw;
            zzk.zzs();
            zzg zzg = new zzg(webView, list, zza, zzk.zzw());
            zze zze = new zze(zzg, zzk, zza);
            if (VERSION.SDK_INT >= 21) {
                webView.addJavascriptInterface(new JSInterface(zze), "_gmptnl");
            }
            weakHashMap.put(webView, zzg);
        }
    }

    public final Uri zza(Uri uri, WebView webView) {
        zzg zzg = (zzg) this.zzcf.get(webView);
        return (zzg == null || !zzg.zza(zzb.CLICK)) ? uri : this.zzbv.zzb(uri);
    }

    public static boolean zza(Uri uri) {
        return zzk.isGoogleAdClickUrl(uri);
    }
}
