package com.google.android.exoplayer2.source;

import java.util.Arrays;

public final class k {
    public static final k a = new k(new j[0]);
    public final int b;
    private final j[] c;
    private int d;

    public k(j... trackGroups) {
        this.c = trackGroups;
        this.b = trackGroups.length;
    }

    public final j a(int index) {
        return this.c[index];
    }

    public final int a(j group) {
        for (int i = 0; i < this.b; i++) {
            if (this.c[i] == group) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.d == 0) {
            this.d = Arrays.hashCode(this.c);
        }
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        k other = (k) obj;
        if (this.b == other.b && Arrays.equals(this.c, other.c)) {
            return true;
        }
        return false;
    }
}
