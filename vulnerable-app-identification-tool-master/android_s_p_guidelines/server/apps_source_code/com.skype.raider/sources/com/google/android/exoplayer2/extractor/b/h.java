package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import com.google.android.exoplayer2.extractor.n;
import java.io.IOException;

abstract class h {
    private d a;
    private n b;
    private com.google.android.exoplayer2.extractor.h c;
    private f d;
    private long e;
    private long f;
    private long g;
    private int h;
    private int i;
    private a j;
    private long k;
    private boolean l;
    private boolean m;

    static class a {
        Format a;
        f b;

        a() {
        }
    }

    private static final class b implements f {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final long a(g input) throws IOException, InterruptedException {
            return -1;
        }

        public final long a_(long timeUs) {
            return 0;
        }

        public final m a() {
            return new com.google.android.exoplayer2.extractor.m.a(-9223372036854775807L);
        }
    }

    protected abstract long a(k kVar);

    protected abstract boolean a(k kVar, long j, a aVar) throws IOException, InterruptedException;

    h() {
    }

    final void a(com.google.android.exoplayer2.extractor.h output, n trackOutput) {
        this.c = output;
        this.b = trackOutput;
        this.a = new d();
        a(true);
    }

    protected void a(boolean headerData) {
        if (headerData) {
            this.j = new a();
            this.f = 0;
            this.h = 0;
        } else {
            this.h = 1;
        }
        this.e = -1;
        this.g = 0;
    }

    final void a(long position, long timeUs) {
        this.a.a();
        if (position == 0) {
            a(!this.l);
        } else if (this.h != 0) {
            this.e = this.d.a_(timeUs);
            this.h = 2;
        }
    }

    final int a(g input, l seekPosition) throws IOException, InterruptedException {
        switch (this.h) {
            case 0:
                boolean z = true;
                while (z) {
                    if (this.a.a(input)) {
                        this.k = input.c() - this.f;
                        z = a(this.a.c(), this.f, this.j);
                        if (z) {
                            this.f = input.c();
                        }
                    } else {
                        this.h = 3;
                        return -1;
                    }
                }
                this.i = this.j.a.s;
                if (!this.m) {
                    this.b.a(this.j.a);
                    this.m = true;
                }
                if (this.j.b != null) {
                    this.d = this.j.b;
                } else if (input.d() == -1) {
                    this.d = new b();
                } else {
                    e b = this.a.b();
                    this.d = new a(this.f, input.d(), this, b.i + b.h, b.c);
                }
                this.j = null;
                this.h = 2;
                this.a.d();
                return 0;
            case 1:
                input.b((int) this.f);
                this.h = 2;
                return 0;
            case 2:
                long a = this.d.a(input);
                if (a >= 0) {
                    seekPosition.a = a;
                    return 1;
                }
                if (a < -1) {
                    c(-(a + 2));
                }
                if (!this.l) {
                    this.c.a(this.d.a());
                    this.l = true;
                }
                if (this.k > 0 || this.a.a(input)) {
                    this.k = 0;
                    k c = this.a.c();
                    long a2 = a(c);
                    if (a2 >= 0 && this.g + a2 >= this.e) {
                        long a3 = a(this.g);
                        this.b.a(c, c.c());
                        this.b.a(a3, 1, c.c(), 0, null);
                        this.e = -1;
                    }
                    this.g += a2;
                    return 0;
                }
                this.h = 3;
                return -1;
            default:
                throw new IllegalStateException();
        }
    }

    protected final long a(long granule) {
        return (1000000 * granule) / ((long) this.i);
    }

    protected final long b(long timeUs) {
        return (((long) this.i) * timeUs) / 1000000;
    }

    protected void c(long currentGranule) {
        this.g = currentGranule;
    }
}
