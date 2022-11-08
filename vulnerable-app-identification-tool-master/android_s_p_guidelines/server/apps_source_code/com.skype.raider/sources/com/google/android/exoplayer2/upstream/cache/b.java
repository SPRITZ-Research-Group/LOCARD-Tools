package com.google.android.exoplayer2.upstream.cache;

import android.support.annotation.NonNull;
import java.io.File;

public final class b implements Comparable<b> {
    public final String a;
    public final long b;
    public final long c;
    public final boolean d;
    public final File e;

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        b bVar = (b) obj;
        if (!this.a.equals(bVar.a)) {
            return this.a.compareTo(bVar.a);
        }
        long j = this.b - bVar.b;
        if (j == 0) {
            return 0;
        }
        return j < 0 ? -1 : 1;
    }
}
