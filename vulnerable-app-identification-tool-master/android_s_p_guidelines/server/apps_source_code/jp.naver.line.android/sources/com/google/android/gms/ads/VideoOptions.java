package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzmu;

@zzadh
public final class VideoOptions {
    private final boolean zzuz;
    private final boolean zzva;
    private final boolean zzvb;

    public final class Builder {
        private boolean zzuz = true;
        private boolean zzva = false;
        private boolean zzvb = false;

        public final VideoOptions build() {
            return new VideoOptions();
        }

        public final Builder setClickToExpandRequested(boolean z) {
            this.zzvb = z;
            return this;
        }

        public final Builder setCustomControlsRequested(boolean z) {
            this.zzva = z;
            return this;
        }

        public final Builder setStartMuted(boolean z) {
            this.zzuz = z;
            return this;
        }
    }

    private VideoOptions(Builder builder) {
        this.zzuz = builder.zzuz;
        this.zzva = builder.zzva;
        this.zzvb = builder.zzvb;
    }

    /* synthetic */ VideoOptions(Builder builder, zzc zzc) {
        this(builder);
    }

    public VideoOptions(zzmu zzmu) {
        this.zzuz = zzmu.zzato;
        this.zzva = zzmu.zzatp;
        this.zzvb = zzmu.zzatq;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzvb;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzva;
    }

    public final boolean getStartMuted() {
        return this.zzuz;
    }
}
