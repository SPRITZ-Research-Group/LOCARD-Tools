package com.google.android.exoplayer2.c;

import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.o;
import com.google.android.exoplayer2.source.k;

public final class i {
    public final k a;
    public final g b;
    public final Object c;
    public final o[] d;

    public i(k groups, g selections, Object info, o[] rendererConfigurations) {
        this.a = groups;
        this.b = selections;
        this.c = info;
        this.d = rendererConfigurations;
    }

    public final boolean a(i other, int index) {
        if (other != null && s.a(this.b.a(index), other.b.a(index)) && s.a(this.d[index], other.d[index])) {
            return true;
        }
        return false;
    }
}
