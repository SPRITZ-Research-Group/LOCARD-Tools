package com.google.android.gms.ads.tagsdk.internal;

import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.internal.biski.zza;
import com.google.android.gms.internal.biski.zza.zzd;
import com.google.android.gms.internal.biski.zzac.zzb;

final class zze {
    private final zzg zzbu;
    private final zzk zzbv;
    private final zza zzbw;

    zze(zzg zzg, zzk zzk, zza zza) {
        this.zzbu = zzg;
        this.zzbv = zzk;
        this.zzbw = zza;
    }

    final String zza(String str, String str2) {
        if (!this.zzbu.zza(str2, zzb.UNKNOWN)) {
            return null;
        }
        zza zzc = zzc(str);
        if (zzc == null) {
            return null;
        }
        switch (zzf.zzbx[zzc.zza().ordinal()]) {
            case 1:
                for (zzd zzd : zzc.zzb()) {
                    int i;
                    switch (zzf.zzby[zzd.zzf().ordinal()]) {
                        case 1:
                            i = 0;
                            break;
                        case 2:
                            i = 2;
                            break;
                        case 3:
                            i = 1;
                            break;
                        case 4:
                            i = 3;
                            break;
                        default:
                            i = -1;
                            break;
                    }
                    this.zzbv.zza(i, zzd.getX(), zzd.getY(), zzd.zzg(), zzd.zzh(), zzd.getDuration());
                }
                return null;
            case 2:
                return this.zzbv.zzr();
            default:
                this.zzbw.zza(106, zzb.UNKNOWN);
                return null;
        }
    }

    private final zza zzc(String str) {
        if (TextUtils.isEmpty(str)) {
            this.zzbw.zza(104, zzb.UNKNOWN);
            return null;
        }
        try {
            return zza.zza(Base64.decode(str, 0));
        } catch (Exception e) {
            this.zzbw.zza(103, zzb.UNKNOWN, e);
            return null;
        }
    }
}
