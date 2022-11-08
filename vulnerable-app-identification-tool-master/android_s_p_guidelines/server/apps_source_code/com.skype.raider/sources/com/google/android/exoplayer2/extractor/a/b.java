package com.google.android.exoplayer2.extractor.a;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import java.io.IOException;

public final class b implements f, m {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new b()};
        }
    };
    private static final int e = s.e("FLV");
    public int b;
    public int c;
    public long d;
    private final k f = new k(4);
    private final k g = new k(9);
    private final k h = new k(11);
    private final k i = new k();
    private h j;
    private int k = 1;
    private int l;
    private a m;
    private e n;
    private c o;

    public final boolean a(g input) throws IOException, InterruptedException {
        input.c(this.f.a, 0, 3);
        this.f.c(0);
        if (this.f.k() != e) {
            return false;
        }
        input.c(this.f.a, 0, 2);
        this.f.c(0);
        if ((this.f.h() & 250) != 0) {
            return false;
        }
        input.c(this.f.a, 0, 4);
        this.f.c(0);
        int dataOffset = this.f.n();
        input.a();
        input.c(dataOffset);
        input.c(this.f.a, 0, 4);
        this.f.c(0);
        if (this.f.n() == 0) {
            return true;
        }
        return false;
    }

    public final void a(h output) {
        this.j = output;
    }

    public final void a(long position, long timeUs) {
        this.k = 1;
        this.l = 0;
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        while (true) {
            int g;
            boolean z;
            switch (this.k) {
                case 1:
                    if (input.a(this.g.a, 0, 9, true)) {
                        this.g.c(0);
                        this.g.d(4);
                        g = this.g.g();
                        boolean z2 = (g & 4) != 0;
                        if ((g & 1) != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z2 && this.m == null) {
                            this.m = new a(this.j.a(8));
                        }
                        if (z && this.n == null) {
                            this.n = new e(this.j.a(9));
                        }
                        if (this.o == null) {
                            this.o = new c();
                        }
                        this.j.b();
                        this.j.a((m) this);
                        this.l = (this.g.n() - 9) + 4;
                        this.k = 2;
                        z = true;
                    } else {
                        g = 0;
                    }
                    if (g != 0) {
                        break;
                    }
                    return -1;
                case 2:
                    input.b(this.l);
                    this.l = 0;
                    this.k = 3;
                    break;
                case 3:
                    if (input.a(this.h.a, 0, 11, true)) {
                        this.h.c(0);
                        this.b = this.h.g();
                        this.c = this.h.k();
                        this.d = (long) this.h.k();
                        this.d = (((long) (this.h.g() << 24)) | this.d) * 1000;
                        this.h.d(3);
                        this.k = 4;
                        z = true;
                    } else {
                        g = 0;
                    }
                    if (g != 0) {
                        break;
                    }
                    return -1;
                case 4:
                    if (this.b == 8 && this.m != null) {
                        this.m.b(b(input), this.d);
                        z = true;
                    } else if (this.b == 9 && this.n != null) {
                        this.n.b(b(input), this.d);
                        z = true;
                    } else if (this.b != 18 || this.o == null) {
                        input.b(this.c);
                        z = false;
                    } else {
                        this.o.b(b(input), this.d);
                        z = true;
                    }
                    this.l = 4;
                    this.k = 2;
                    if (!z) {
                        break;
                    }
                    return 0;
                    break;
                default:
                    break;
            }
        }
    }

    private k b(g input) throws IOException, InterruptedException {
        if (this.c > this.i.e()) {
            this.i.a(new byte[Math.max(this.i.e() * 2, this.c)], 0);
        } else {
            this.i.c(0);
        }
        this.i.b(this.c);
        input.b(this.i.a, 0, this.c);
        return this.i;
    }

    public final boolean i_() {
        return false;
    }

    public final long b() {
        return this.o.a();
    }

    public final long a(long timeUs) {
        return 0;
    }
}
