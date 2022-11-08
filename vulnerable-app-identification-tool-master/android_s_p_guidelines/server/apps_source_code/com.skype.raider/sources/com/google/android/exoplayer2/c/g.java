package com.google.android.exoplayer2.c;

import java.util.Arrays;

public final class g {
    public final int a;
    private final f[] b;
    private int c;

    public g(f... trackSelections) {
        this.b = trackSelections;
        this.a = trackSelections.length;
    }

    public final f a(int index) {
        return this.b[index];
    }

    public final f[] a() {
        return (f[]) this.b.clone();
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
        return Arrays.equals(this.b, ((g) obj).b);
    }
}
