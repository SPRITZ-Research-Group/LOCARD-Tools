package com.google.android.exoplayer2.trackselection;

import android.text.TextUtils;

final class e {
    public final int a;
    public final int b;
    public final String c;

    public e(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        return this.a == eVar.a && this.b == eVar.b && TextUtils.equals(this.c, eVar.c);
    }

    public final int hashCode() {
        return (((this.a * 31) + this.b) * 31) + (this.c != null ? this.c.hashCode() : 0);
    }
}
