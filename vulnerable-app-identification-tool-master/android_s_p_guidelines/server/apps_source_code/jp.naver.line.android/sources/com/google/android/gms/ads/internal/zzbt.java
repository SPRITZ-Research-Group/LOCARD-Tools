package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.concurrent.TimeUnit;

final class zzbt extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzbp zzaba;

    private zzbt(zzbp zzbp) {
        this.zzaba = zzbp;
    }

    /* synthetic */ zzbt(zzbp zzbp, zzbq zzbq) {
        this(zzbp);
    }

    private final String zza(Void... voidArr) {
        try {
            this.zzaba.zzaay = (zzci) this.zzaba.zzaav.get(((Long) zzkb.zzik().zzd(zznk.zzbdb)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            zzane.zzc("", e);
        }
        return this.zzaba.zzea();
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return zza((Void[]) objArr);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.zzaba.zzaax != null && str != null) {
            this.zzaba.zzaax.loadUrl(str);
        }
    }
}
