package com.google.android.exoplayer2;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.bcz;

public final class aa {
    public static final aa a = new aa();
    public final float b;
    public final float c;
    public final boolean d;
    private final int e;

    private aa() {
        this(1.0f, 1.0f, false);
    }

    public aa(float f, float f2, boolean z) {
        boolean z2 = false;
        bcz.a(f > BitmapDescriptorFactory.HUE_RED);
        if (f2 > BitmapDescriptorFactory.HUE_RED) {
            z2 = true;
        }
        bcz.a(z2);
        this.b = f;
        this.c = f2;
        this.d = z;
        this.e = Math.round(f * 1000.0f);
    }

    public final long a(long j) {
        return j * ((long) this.e);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        aa aaVar = (aa) obj;
        return this.b == aaVar.b && this.c == aaVar.c && this.d == aaVar.d;
    }

    public final int hashCode() {
        return ((((Float.floatToRawIntBits(this.b) + 527) * 31) + Float.floatToRawIntBits(this.c)) * 31) + this.d;
    }
}
