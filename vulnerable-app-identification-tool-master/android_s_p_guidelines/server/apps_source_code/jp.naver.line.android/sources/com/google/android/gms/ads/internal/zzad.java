package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajl;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzanm;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzanz;
import com.google.android.gms.internal.ads.zzaoe;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwk;
import org.json.JSONObject;

@zzadh
public final class zzad {
    private Context mContext;
    private final Object mLock = new Object();
    private long zzxm = 0;

    public final void zza(Context context, zzang zzang, String str, Runnable runnable) {
        zza(context, zzang, true, null, str, null, runnable);
    }

    @VisibleForTesting
    final void zza(Context context, zzang zzang, boolean z, zzajl zzajl, String str, String str2, Runnable runnable) {
        if (zzbv.zzer().elapsedRealtime() - this.zzxm < HlsChunkSource.DEFAULT_MIN_BUFFER_TO_SWITCH_UP_MS) {
            zzane.zzdk("Not retrying to fetch app settings");
            return;
        }
        this.zzxm = zzbv.zzer().elapsedRealtime();
        Object obj = 1;
        if (zzajl != null) {
            if ((zzbv.zzer().currentTimeMillis() - zzajl.zzps() > ((Long) zzkb.zzik().zzd(zznk.zzbcu)).longValue() ? 1 : null) == null && zzajl.zzpt()) {
                obj = null;
            }
        }
        if (obj != null) {
            if (context == null) {
                zzane.zzdk("Context not provided to fetch application settings");
            } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                zzane.zzdk("App settings could not be fetched. Required parameters missing");
            } else {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = context;
                }
                this.mContext = applicationContext;
                Object obj2 = zzwk.zzbrc;
                zzwf zza = zzbv.zzey().zzb(this.mContext, zzang).zza("google.afma.config.fetchAppSettings", obj2, obj2);
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str)) {
                        jSONObject.put("app_id", str);
                    } else if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("ad_unit_id", str2);
                    }
                    jSONObject.put("is_init", z);
                    jSONObject.put("pn", context.getPackageName());
                    zzanz zzf = zza.zzf(jSONObject);
                    zzanz zza2 = zzano.zza(zzf, zzae.zzxn, zzaoe.zzcvz);
                    if (runnable != null) {
                        zzf.zza(runnable, zzaoe.zzcvz);
                    }
                    zzanm.zza(zza2, "ConfigLoader.maybeFetchNewAppSettings");
                } catch (Throwable e) {
                    zzane.zzb("Error requesting application settings", e);
                }
            }
        }
    }
}
