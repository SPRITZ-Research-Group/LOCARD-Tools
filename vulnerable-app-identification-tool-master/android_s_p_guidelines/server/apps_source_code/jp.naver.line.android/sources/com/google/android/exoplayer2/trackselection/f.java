package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters;

public final class f implements Comparable<f> {
    private final Parameters a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public final /* synthetic */ int compareTo(Object obj) {
        return a((f) obj);
    }

    public f(Format format, Parameters parameters, int i) {
        this.a = parameters;
        this.b = DefaultTrackSelector.a(i, false);
        this.c = DefaultTrackSelector.a(format, parameters.b);
        i = 1;
        if ((format.y & 1) == 0) {
            i = 0;
        }
        this.d = i;
        this.e = format.t;
        this.f = format.u;
        this.g = format.c;
    }

    public final int a(f fVar) {
        if (this.b != fVar.b) {
            return DefaultTrackSelector.a(this.b, fVar.b);
        }
        if (this.c != fVar.c) {
            return DefaultTrackSelector.a(this.c, fVar.c);
        }
        if (this.d != fVar.d) {
            return DefaultTrackSelector.a(this.d, fVar.d);
        }
        if (this.a.n) {
            return DefaultTrackSelector.a(fVar.g, this.g);
        }
        int i = 1;
        if (this.b != 1) {
            i = -1;
        }
        if (this.e != fVar.e) {
            return i * DefaultTrackSelector.a(this.e, fVar.e);
        }
        if (this.f != fVar.f) {
            return i * DefaultTrackSelector.a(this.f, fVar.f);
        }
        return i * DefaultTrackSelector.a(this.g, fVar.g);
    }
}
