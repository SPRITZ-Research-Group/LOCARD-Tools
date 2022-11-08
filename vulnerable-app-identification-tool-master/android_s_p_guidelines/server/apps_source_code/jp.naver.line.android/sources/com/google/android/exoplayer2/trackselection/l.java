package com.google.android.exoplayer2.trackselection;

import java.util.Arrays;

public final class l {
    public final int a;
    private final j[] b;
    private int c;

    public l(j... jVarArr) {
        this.b = jVarArr;
        this.a = jVarArr.length;
    }

    public final j a(int i) {
        return this.b[i];
    }

    public final j[] a() {
        return (j[]) this.b.clone();
    }

    public final int hashCode() {
        if (this.c == 0) {
            this.c = Arrays.hashCode(this.b) + 527;
        }
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.b, ((l) obj).b);
    }
}
