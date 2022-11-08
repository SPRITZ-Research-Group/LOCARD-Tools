package com.google.android.exoplayer2;

import defpackage.bcz;

public final class ak {
    public static final ak a = new ak(0, 0);
    public static final ak b = new ak(Long.MAX_VALUE, Long.MAX_VALUE);
    public static final ak c = new ak(Long.MAX_VALUE, 0);
    public static final ak d = new ak(0, Long.MAX_VALUE);
    public static final ak e = a;
    public final long f;
    public final long g;

    public ak(long j, long j2) {
        boolean z = false;
        bcz.a(j >= 0);
        if (j2 >= 0) {
            z = true;
        }
        bcz.a(z);
        this.f = j;
        this.g = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ak akVar = (ak) obj;
        return this.f == akVar.f && this.g == akVar.g;
    }

    public final int hashCode() {
        return (((int) this.f) * 31) + ((int) this.g);
    }
}
