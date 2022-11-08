package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.g;
import java.io.EOFException;
import java.io.IOException;

final class e {
    private static final int k = s.e("OggS");
    public int a;
    public int b;
    public long c;
    public long d;
    public long e;
    public long f;
    public int g;
    public int h;
    public int i;
    public final int[] j = new int[255];
    private final k l = new k(255);

    e() {
    }

    public final void a() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }

    public final boolean a(g input, boolean quiet) throws IOException, InterruptedException {
        this.l.a();
        a();
        boolean z = input.d() == -1 || input.d() - input.b() >= 27;
        if (z && input.b(this.l.a, 0, 27, true)) {
            if (this.l.l() == ((long) k)) {
                this.a = this.l.g();
                if (this.a == 0) {
                    this.b = this.l.g();
                    this.c = this.l.q();
                    this.d = this.l.m();
                    this.e = this.l.m();
                    this.f = this.l.m();
                    this.g = this.l.g();
                    this.h = this.g + 27;
                    this.l.a();
                    input.c(this.l.a, 0, this.g);
                    for (int i = 0; i < this.g; i++) {
                        this.j[i] = this.l.g();
                        this.i += this.j[i];
                    }
                    return true;
                } else if (quiet) {
                    return false;
                } else {
                    throw new com.google.android.exoplayer2.k("unsupported bit stream revision");
                }
            } else if (quiet) {
                return false;
            } else {
                throw new com.google.android.exoplayer2.k("expected OggS capture pattern at begin of page");
            }
        } else if (quiet) {
            return false;
        } else {
            throw new EOFException();
        }
    }
}
