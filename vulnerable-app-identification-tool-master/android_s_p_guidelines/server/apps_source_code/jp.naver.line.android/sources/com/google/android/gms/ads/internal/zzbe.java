package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzaef;
import com.google.android.gms.internal.ads.zzaeg;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzpb;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

final class zzbe implements Callable<zzpb> {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ int zzaag;
    private final /* synthetic */ JSONArray zzaah;
    private final /* synthetic */ int zzaai;
    private final /* synthetic */ zzaji zzwg;

    zzbe(zzbc zzbc, int i, JSONArray jSONArray, int i2, zzaji zzaji) {
        this.zzaaf = zzbc;
        this.zzaag = i;
        this.zzaah = jSONArray;
        this.zzaai = i2;
        this.zzwg = zzaji;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.zzaag >= this.zzaah.length()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(r0.zzaah.get(r0.zzaag));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ads", jSONArray);
        zza zzbc = new zzbc(r0.zzaaf.zzvw.zzrt, r0.zzaaf.zzwc, r0.zzaaf.zzvw.zzacv, r0.zzaaf.zzvw.zzacp, r0.zzaaf.zzwh, r0.zzaaf.zzvw.zzacr, true);
        zzbc.zza(r0.zzaaf.zzvw, zzbc.zzvw);
        zzbc.zzdq();
        zzbc.zza(r0.zzaaf.zzvs);
        zznx zznx = zzbc.zzvr;
        int i = r0.zzaag;
        zznx.zze("num_ads_requested", String.valueOf(r0.zzaai));
        zznx.zze("ad_index", String.valueOf(i));
        zzaef zzaef = r0.zzwg.zzcgs;
        String jSONObject2 = jSONObject.toString();
        Bundle bundle = zzaef.zzccv.extras != null ? new Bundle(zzaef.zzccv.extras) : new Bundle();
        bundle.putString("_ad", jSONObject2);
        zzjj zzjj = r5;
        zzjj zzjj2 = new zzjj(zzaef.zzccv.versionCode, zzaef.zzccv.zzapw, bundle, zzaef.zzccv.zzapx, zzaef.zzccv.zzapy, zzaef.zzccv.zzapz, zzaef.zzccv.zzaqa, zzaef.zzccv.zzaqb, zzaef.zzccv.zzaqc, zzaef.zzccv.zzaqd, zzaef.zzccv.zzaqe, zzaef.zzccv.zzaqf, zzaef.zzccv.zzaqg, zzaef.zzccv.zzaqh, zzaef.zzccv.zzaqi, zzaef.zzccv.zzaqj, zzaef.zzccv.zzaqk, zzaef.zzccv.zzaql);
        zzbc.zza(new zzaeg(zzaef.zzccu, zzjj, zzaef.zzacv, zzaef.zzacp, zzaef.applicationInfo, zzaef.zzccw, zzaef.zzccy, zzaef.zzccz, zzaef.zzacr, zzaef.zzcda, zzaef.zzads, zzaef.zzcdk, zzaef.zzcdc, zzaef.zzcdd, zzaef.zzcde, zzaef.zzcdf, zzaef.zzagu, zzaef.zzcdg, zzaef.zzcdh, zzaef.zzcdi, zzaef.zzcdj, zzaef.zzaco, zzaef.zzadj, zzaef.zzcdm, zzaef.zzcdn, zzaef.zzcdt, zzaef.zzcdo, zzaef.zzcdp, zzaef.zzcdq, zzaef.zzcdr, zzano.zzi(zzaef.zzcds), zzaef.zzcdu, zzaef.zzbss, zzaef.zzcdv, zzaef.zzcdw, zzaef.zzcdx, zzaef.zzadl, zzaef.zzcdy, zzaef.zzcdz, zzaef.zzced, zzano.zzi(zzaef.zzccx), zzaef.zzadn, zzaef.zzcee, zzaef.zzcef, 1, zzaef.zzceh, zzaef.zzcei, zzaef.zzcej, zzaef.zzcek), zzbc.zzvr);
        return (zzpb) zzbc.zzds().get();
    }
}
