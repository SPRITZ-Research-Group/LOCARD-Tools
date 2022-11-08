package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzak;
import com.google.android.gms.internal.measurement.zzav;
import com.google.android.gms.internal.measurement.zzaz;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzx;
import com.linecorp.yuki.effect.android.g;
import java.util.HashMap;
import java.util.Map;

final class zzp implements Runnable {
    private final /* synthetic */ Map zzte;
    private final /* synthetic */ boolean zztf;
    private final /* synthetic */ String zztg;
    private final /* synthetic */ long zzth;
    private final /* synthetic */ boolean zzti;
    private final /* synthetic */ boolean zztj;
    private final /* synthetic */ String zztk;
    private final /* synthetic */ Tracker zztl;

    zzp(Tracker tracker, Map map, boolean z, String str, long j, boolean z2, boolean z3, String str2) {
        this.zztl = tracker;
        this.zzte = map;
        this.zztf = z;
        this.zztg = str;
        this.zzth = j;
        this.zzti = z2;
        this.zztj = z3;
        this.zztk = str2;
    }

    public final void run() {
        if (this.zztl.zztb.zzah()) {
            r0.zzte.put("sc", "start");
        }
        zza zzcb = r0.zztl.zzcb();
        Preconditions.checkNotMainThread("getClientId can not be called from the main thread");
        zzdg.zzc(r0.zzte, "cid", zzcb.zzl().zzcr().zzdr());
        String str = (String) r0.zzte.get("sf");
        if (str != null) {
            double zza = zzdg.zza(str, 100.0d);
            if (zzdg.zza(zza, (String) r0.zzte.get("cid"))) {
                r0.zztl.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                return;
            }
        }
        zzak zzb = r0.zztl.zzch();
        if (r0.zztf) {
            zzdg.zzb(r0.zzte, "ate", zzb.zzbg());
            zzdg.zzb(r0.zzte, "adid", zzb.zzbn());
        } else {
            r0.zzte.remove("ate");
            r0.zzte.remove("adid");
        }
        zzx zzdf = r0.zztl.zzci().zzdf();
        zzdg.zzb(r0.zzte, "an", zzdf.zzaj());
        zzdg.zzb(r0.zzte, "av", zzdf.zzak());
        zzdg.zzb(r0.zzte, "aid", zzdf.zzal());
        zzdg.zzb(r0.zzte, "aiid", zzdf.zzam());
        r0.zzte.put("v", g.a);
        r0.zzte.put("_v", zzav.zzwa);
        zzdg.zzb(r0.zzte, "ul", r0.zztl.zzcj().zzek().getLanguage());
        zzdg.zzb(r0.zzte, "sr", r0.zztl.zzcj().zzel());
        Object obj = (r0.zztg.equals("transaction") || r0.zztg.equals("item")) ? 1 : null;
        if (obj != null || r0.zztl.zzta.zzew()) {
            long zzaf = zzdg.zzaf((String) r0.zzte.get("ht"));
            if (zzaf == 0) {
                zzaf = r0.zzth;
            }
            long j = zzaf;
            if (r0.zzti) {
                r0.zztl.zzby().zzc("Dry run enabled. Would have sent hit", new zzck(r0.zztl, r0.zzte, j, r0.zztj));
                return;
            }
            String str2 = (String) r0.zzte.get("cid");
            Map hashMap = new HashMap();
            zzdg.zza(hashMap, "uid", r0.zzte);
            zzdg.zza(hashMap, "an", r0.zzte);
            zzdg.zza(hashMap, "aid", r0.zzte);
            zzdg.zza(hashMap, "av", r0.zzte);
            zzdg.zza(hashMap, "aiid", r0.zzte);
            r0.zzte.put("_s", String.valueOf(r0.zztl.zzcc().zza(new zzaz(0, str2, r0.zztk, TextUtils.isEmpty((CharSequence) r0.zzte.get("adid")) ^ 1, 0, hashMap))));
            r0.zztl.zzcc().zza(new zzck(r0.zztl, r0.zzte, j, r0.zztj));
            return;
        }
        r0.zztl.zzby().zza(r0.zzte, "Too many hits sent too quickly, rate limiting invoked");
    }
}
