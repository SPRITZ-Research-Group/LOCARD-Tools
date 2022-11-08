package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.e;
import java.util.List;

public abstract class h extends e implements c {
    private c c;
    private long d;

    public abstract void f();

    public final void a(long timeUs, c subtitle, long subsampleOffsetUs) {
        this.a = timeUs;
        this.c = subtitle;
        if (subsampleOffsetUs == Long.MAX_VALUE) {
            subsampleOffsetUs = this.a;
        }
        this.d = subsampleOffsetUs;
    }

    public final int b() {
        return this.c.b();
    }

    public final long a(int index) {
        return this.c.a(index) + this.d;
    }

    public final int a(long timeUs) {
        return this.c.a(timeUs - this.d);
    }

    public final List<Cue> b(long timeUs) {
        return this.c.b(timeUs - this.d);
    }

    public final void a() {
        super.a();
        this.c = null;
    }
}
