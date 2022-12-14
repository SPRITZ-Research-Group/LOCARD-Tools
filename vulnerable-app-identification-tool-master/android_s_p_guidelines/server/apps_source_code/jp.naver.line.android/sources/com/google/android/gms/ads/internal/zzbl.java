package com.google.android.gms.ads.internal;

import com.google.android.exoplayer.hls.HlsChunkSource;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzjj;
import java.lang.ref.WeakReference;

@zzadh
public final class zzbl {
    private final zzbn zzaan;
    private zzjj zzaao;
    private boolean zzaap;
    private boolean zzaaq;
    private long zzaar;
    private final Runnable zzy;

    public zzbl(zza zza) {
        this(zza, new zzbn(zzakk.zzcrm));
    }

    @VisibleForTesting
    private zzbl(zza zza, zzbn zzbn) {
        this.zzaap = false;
        this.zzaaq = false;
        this.zzaar = 0;
        this.zzaan = zzbn;
        this.zzy = new zzbm(this, new WeakReference(zza));
    }

    public final void cancel() {
        this.zzaap = false;
        this.zzaan.removeCallbacks(this.zzy);
    }

    public final void pause() {
        this.zzaaq = true;
        if (this.zzaap) {
            this.zzaan.removeCallbacks(this.zzy);
        }
    }

    public final void resume() {
        this.zzaaq = false;
        if (this.zzaap) {
            this.zzaap = false;
            zza(this.zzaao, this.zzaar);
        }
    }

    public final void zza(zzjj zzjj, long j) {
        if (this.zzaap) {
            zzane.zzdk("An ad refresh is already scheduled.");
            return;
        }
        this.zzaao = zzjj;
        this.zzaap = true;
        this.zzaar = j;
        if (!this.zzaaq) {
            StringBuilder stringBuilder = new StringBuilder(65);
            stringBuilder.append("Scheduling ad refresh ");
            stringBuilder.append(j);
            stringBuilder.append(" milliseconds from now.");
            zzane.zzdj(stringBuilder.toString());
            this.zzaan.postDelayed(this.zzy, j);
        }
    }

    public final void zzdy() {
        this.zzaaq = false;
        this.zzaap = false;
        if (!(this.zzaao == null || this.zzaao.extras == null)) {
            this.zzaao.extras.remove("_ad");
        }
        zza(this.zzaao, 0);
    }

    public final boolean zzdz() {
        return this.zzaap;
    }

    public final void zzf(zzjj zzjj) {
        this.zzaao = zzjj;
    }

    public final void zzg(zzjj zzjj) {
        zza(zzjj, (long) HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS);
    }
}
