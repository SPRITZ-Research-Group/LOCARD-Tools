package com.google.android.exoplayer2;

import com.google.android.exoplayer2.c.g;
import com.google.android.exoplayer2.d.n;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.upstream.h;

public final class b implements j {
    private final h a;
    private final long b;
    private final long c;
    private final long d;
    private final long e;
    private final n f;
    private int g;
    private boolean h;

    public b() {
        this(new h());
    }

    private b(h allocator) {
        this(allocator, (byte) 0);
    }

    private b(h allocator, byte b) {
        this(allocator, 0);
    }

    private b(h allocator, char c) {
        this.a = allocator;
        this.b = 15000000;
        this.c = 30000000;
        this.d = 2500000;
        this.e = 5000000;
        this.f = null;
    }

    public final void a() {
        a(false);
    }

    public final void a(m[] renderers, g trackSelections) {
        this.g = 0;
        for (int i = 0; i < renderers.length; i++) {
            if (trackSelections.a(i) != null) {
                this.g += s.c(renderers[i].a());
            }
        }
        this.a.a(this.g);
    }

    public final void b() {
        a(true);
    }

    public final void c() {
        a(true);
    }

    public final com.google.android.exoplayer2.upstream.b d() {
        return this.a;
    }

    public final boolean a(long bufferedDurationUs, boolean rebuffering) {
        long minBufferDurationUs = rebuffering ? this.e : this.d;
        return minBufferDurationUs <= 0 || bufferedDurationUs >= minBufferDurationUs;
    }

    public final boolean a(long bufferedDurationUs) {
        boolean z = false;
        int bufferTimeState = bufferedDurationUs > this.c ? 0 : bufferedDurationUs < this.b ? 2 : 1;
        boolean targetBufferSizeReached;
        if (this.a.e() >= this.g) {
            targetBufferSizeReached = true;
        } else {
            targetBufferSizeReached = false;
        }
        boolean wasBuffering = this.h;
        if (bufferTimeState == 2 || (bufferTimeState == 1 && this.h && !targetBufferSizeReached)) {
            z = true;
        }
        this.h = z;
        if (!(this.f == null || this.h == wasBuffering)) {
            if (this.h) {
                this.f.a();
            } else {
                this.f.b();
            }
        }
        return this.h;
    }

    private void a(boolean resetAllocator) {
        this.g = 0;
        if (this.f != null && this.h) {
            this.f.b();
        }
        this.h = false;
        if (resetAllocator) {
            this.a.d();
        }
    }
}
