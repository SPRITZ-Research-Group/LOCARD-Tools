package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.j;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.metadata.Metadata;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class f implements com.google.android.exoplayer2.extractor.f, m {
    public static final i a = new i() {
        public final com.google.android.exoplayer2.extractor.f[] a() {
            return new com.google.android.exoplayer2.extractor.f[]{new f()};
        }
    };
    private static final int b = s.e("qt  ");
    private final k c = new k(com.google.android.exoplayer2.d.i.a);
    private final k d = new k(4);
    private final k e = new k(16);
    private final Stack<a> f = new Stack();
    private int g;
    private int h;
    private long i;
    private int j;
    private k k;
    private int l;
    private int m;
    private h n;
    private a[] o;
    private long p;
    private boolean q;

    private static final class a {
        public final Track a;
        public final k b;
        public final n c;
        public int d;

        public a(Track track, k sampleTable, n trackOutput) {
            this.a = track;
            this.b = sampleTable;
            this.c = trackOutput;
        }
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        return h.b(input);
    }

    public final void a(h output) {
        this.n = output;
    }

    public final void a(long position, long timeUs) {
        this.f.clear();
        this.j = 0;
        this.l = 0;
        this.m = 0;
        if (position == 0) {
            c();
        } else if (this.o != null) {
            for (a aVar : this.o) {
                k kVar = aVar.b;
                int a = kVar.a(timeUs);
                if (a == -1) {
                    a = kVar.b(timeUs);
                }
                aVar.d = a;
            }
        }
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        while (true) {
            Object obj;
            int i;
            long j;
            long c;
            switch (this.g) {
                case 0:
                    if (this.j == 0) {
                        if (!input.a(this.e.a, 0, 8, true)) {
                            obj = null;
                            if (obj != null) {
                                break;
                            }
                            return -1;
                        }
                        this.j = 8;
                        this.e.c(0);
                        this.i = this.e.l();
                        this.h = this.e.n();
                    }
                    if (this.i == 1) {
                        input.b(this.e.a, 8, 8);
                        this.j += 8;
                        this.i = this.e.v();
                    }
                    i = this.h;
                    if (i == a.B || i == a.D || i == a.E || i == a.F || i == a.G || i == a.P) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        long c2 = (input.c() + this.i) - ((long) this.j);
                        this.f.add(new a(this.h, c2));
                        if (this.i == ((long) this.j)) {
                            b(c2);
                        } else {
                            c();
                        }
                    } else {
                        i = this.h;
                        if (i == a.R || i == a.C || i == a.S || i == a.T || i == a.am || i == a.an || i == a.ao || i == a.Q || i == a.ap || i == a.aq || i == a.ar || i == a.as || i == a.at || i == a.O || i == a.a || i == a.aA) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            com.google.android.exoplayer2.d.a.b(this.j == 8);
                            com.google.android.exoplayer2.d.a.b(this.i <= 2147483647L);
                            this.k = new k((int) this.i);
                            System.arraycopy(this.e.a, 0, this.k.a, 0, 8);
                            this.g = 1;
                        } else {
                            this.k = null;
                            this.g = 1;
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        return -1;
                    }
                    break;
                case 1:
                    j = this.i - ((long) this.j);
                    c = input.c() + j;
                    if (this.k != null) {
                        input.b(this.k.a, this.j, (int) j);
                        if (this.h == a.a) {
                            boolean z;
                            k kVar = this.k;
                            kVar.c(8);
                            if (kVar.n() == b) {
                                z = true;
                            } else {
                                kVar.d(4);
                                while (kVar.b() > 0) {
                                    if (kVar.n() == b) {
                                        z = true;
                                    }
                                }
                                z = false;
                            }
                            this.q = z;
                            obj = null;
                        } else if (this.f.isEmpty()) {
                            obj = null;
                        } else {
                            ((a) this.f.peek()).a(new b(this.h, this.k));
                            obj = null;
                        }
                    } else if (j < 262144) {
                        input.b((int) j);
                        obj = null;
                    } else {
                        seekPosition.a = input.c() + j;
                        obj = 1;
                    }
                    b(c);
                    if (obj == null || this.g == 2) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj == null) {
                        break;
                    }
                    return 1;
                case 2:
                    int i2;
                    int i3 = -1;
                    j = Long.MAX_VALUE;
                    for (i = 0; i < this.o.length; i++) {
                        a aVar = this.o[i];
                        i2 = aVar.d;
                        if (i2 != aVar.b.a) {
                            c = aVar.b.b[i2];
                            if (c < j) {
                                j = c;
                                i3 = i;
                            }
                        }
                    }
                    if (i3 == -1) {
                        return -1;
                    }
                    a aVar2 = this.o[i3];
                    n nVar = aVar2.c;
                    int i4 = aVar2.d;
                    j = aVar2.b.b[i4];
                    i = aVar2.b.c[i4];
                    if (aVar2.a.g == 1) {
                        j += 8;
                        i -= 8;
                    }
                    long c3 = (j - input.c()) + ((long) this.l);
                    if (c3 < 0 || c3 >= 262144) {
                        seekPosition.a = j;
                        return 1;
                    }
                    input.b((int) c3);
                    int i5;
                    if (aVar2.a.k != 0) {
                        byte[] bArr = this.d.a;
                        bArr[0] = (byte) 0;
                        bArr[1] = (byte) 0;
                        bArr[2] = (byte) 0;
                        i5 = aVar2.a.k;
                        int i6 = 4 - aVar2.a.k;
                        while (this.l < i) {
                            if (this.m == 0) {
                                input.b(this.d.a, i6, i5);
                                this.d.c(0);
                                this.m = this.d.t();
                                this.c.c(0);
                                nVar.a(this.c, 4);
                                this.l += 4;
                                i += i6;
                            } else {
                                i2 = nVar.a(input, this.m, false);
                                this.l += i2;
                                this.m -= i2;
                            }
                        }
                        i2 = i;
                    } else {
                        while (this.l < i) {
                            i5 = nVar.a(input, i - this.l, false);
                            this.l += i5;
                            this.m -= i5;
                        }
                        i2 = i;
                    }
                    nVar.a(aVar2.b.e[i4], aVar2.b.f[i4], i2, 0, null);
                    aVar2.d++;
                    this.l = 0;
                    this.m = 0;
                    return 0;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public final boolean i_() {
        return true;
    }

    public final long b() {
        return this.p;
    }

    public final long a(long timeUs) {
        long earliestSamplePosition = Long.MAX_VALUE;
        for (a aVar : this.o) {
            k sampleTable = aVar.b;
            int sampleIndex = sampleTable.a(timeUs);
            if (sampleIndex == -1) {
                sampleIndex = sampleTable.b(timeUs);
            }
            long offset = sampleTable.b[sampleIndex];
            if (offset < earliestSamplePosition) {
                earliestSamplePosition = offset;
            }
        }
        return earliestSamplePosition;
    }

    private void c() {
        this.g = 0;
        this.j = 0;
    }

    private void b(long atomEndPosition) throws com.google.android.exoplayer2.k {
        while (!this.f.isEmpty() && ((a) this.f.peek()).aQ == atomEndPosition) {
            a containerAtom = (a) this.f.pop();
            if (containerAtom.aP == a.B) {
                Metadata metadata;
                long j;
                long j2 = -9223372036854775807L;
                List arrayList = new ArrayList();
                j jVar = new j();
                b d = containerAtom.d(a.aA);
                if (d != null) {
                    Metadata a = b.a(d, this.q);
                    if (a != null) {
                        jVar.a(a);
                    }
                    metadata = a;
                } else {
                    metadata = null;
                }
                int i = 0;
                while (true) {
                    int i2 = i;
                    j = j2;
                    if (i2 >= containerAtom.aS.size()) {
                        break;
                    }
                    a aVar = (a) containerAtom.aS.get(i2);
                    if (aVar.aP == a.D) {
                        Track a2 = b.a(aVar, containerAtom.d(a.C), -9223372036854775807L, null, this.q);
                        if (a2 != null) {
                            k a3 = b.a(a2, aVar.e(a.E).e(a.F).e(a.G), jVar);
                            if (a3.a != 0) {
                                a aVar2 = new a(a2, a3, this.n.a(i2));
                                Format a4 = a2.f.a(a3.d + 30);
                                if (a2.b == 1) {
                                    if (jVar.a()) {
                                        a4 = a4.a(jVar.b, jVar.c);
                                    }
                                    if (metadata != null) {
                                        a4 = a4.a(metadata);
                                    }
                                }
                                aVar2.c.a(a4);
                                j = Math.max(j, a2.e);
                                arrayList.add(aVar2);
                            }
                        }
                    }
                    j2 = j;
                    i = i2 + 1;
                }
                this.p = j;
                this.o = (a[]) arrayList.toArray(new a[arrayList.size()]);
                this.n.b();
                this.n.a((m) this);
                this.f.clear();
                this.g = 2;
            } else if (!this.f.isEmpty()) {
                ((a) this.f.peek()).a(containerAtom);
            }
        }
        if (this.g != 2) {
            c();
        }
    }
}
