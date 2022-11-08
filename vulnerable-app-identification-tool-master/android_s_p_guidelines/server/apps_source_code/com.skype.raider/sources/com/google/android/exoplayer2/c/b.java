package com.google.android.exoplayer2.c;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.j;
import java.util.Arrays;
import java.util.Comparator;

public abstract class b implements f {
    protected final j a;
    protected final int b;
    protected final int[] c;
    private final Format[] d;
    private final long[] e;
    private int f;

    private static final class a implements Comparator<Format> {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return ((Format) obj2).b - ((Format) obj).b;
        }
    }

    public b(j group, int... tracks) {
        boolean z;
        int i;
        if (tracks.length > 0) {
            z = true;
        } else {
            z = false;
        }
        com.google.android.exoplayer2.d.a.b(z);
        this.a = (j) com.google.android.exoplayer2.d.a.a((Object) group);
        this.b = tracks.length;
        this.d = new Format[this.b];
        for (i = 0; i < tracks.length; i++) {
            this.d[i] = group.a(tracks[i]);
        }
        Arrays.sort(this.d, new a());
        this.c = new int[this.b];
        for (i = 0; i < this.b; i++) {
            this.c[i] = group.a(this.d[i]);
        }
        this.e = new long[this.b];
    }

    public final j a() {
        return this.a;
    }

    public final int b() {
        return this.c.length;
    }

    public final Format a(int index) {
        return this.d[index];
    }

    public final int b(int index) {
        return this.c[index];
    }

    protected final boolean c(int index) {
        return this.e[index] > Long.MIN_VALUE;
    }

    public int hashCode() {
        if (this.f == 0) {
            this.f = (System.identityHashCode(this.a) * 31) + Arrays.hashCode(this.c);
        }
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b other = (b) obj;
        if (this.a == other.a && Arrays.equals(this.c, other.c)) {
            return true;
        }
        return false;
    }
}
