package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import android.util.SparseArray;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.skype.Defines;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public final class FragmentedMp4Extractor implements f {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new FragmentedMp4Extractor()};
        }
    };
    private static final int b = s.e("seig");
    private static final byte[] c = new byte[]{(byte) -94, (byte) 57, (byte) 79, (byte) 82, (byte) 90, (byte) -101, (byte) 79, (byte) 20, (byte) -94, (byte) 68, (byte) 108, (byte) 66, (byte) 124, (byte) 100, (byte) -115, (byte) -12};
    private int A;
    private int B;
    private boolean C;
    private h D;
    private n E;
    private n[] F;
    private boolean G;
    private final int d;
    private final Track e;
    private final SparseArray<b> f;
    private final k g;
    private final k h;
    private final k i;
    private final k j;
    private final q k;
    private final k l;
    private final byte[] m;
    private final Stack<a> n;
    private final LinkedList<a> o;
    private int p;
    private int q;
    private long r;
    private int s;
    private k t;
    private long u;
    private int v;
    private long w;
    private long x;
    private b y;
    private int z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private static final class a {
        public final long a;
        public final int b;

        public a(long presentationTimeDeltaUs, int size) {
            this.a = presentationTimeDeltaUs;
            this.b = size;
        }
    }

    private static final class b {
        public final j a = new j();
        public final n b;
        public Track c;
        public c d;
        public int e;
        public int f;
        public int g;

        public b(n output) {
            this.b = output;
        }

        public final void a(Track track, c defaultSampleValues) {
            this.c = (Track) com.google.android.exoplayer2.d.a.a((Object) track);
            this.d = (c) com.google.android.exoplayer2.d.a.a((Object) defaultSampleValues);
            this.b.a(track.f);
            a();
        }

        public final void a() {
            j jVar = this.a;
            jVar.e = 0;
            jVar.s = 0;
            jVar.m = false;
            jVar.r = false;
            jVar.o = null;
            this.e = 0;
            this.g = 0;
            this.f = 0;
        }
    }

    public FragmentedMp4Extractor() {
        this(0);
    }

    public FragmentedMp4Extractor(int flags) {
        this(flags, (byte) 0);
    }

    private FragmentedMp4Extractor(int flags, byte b) {
        this(flags, 0);
    }

    private FragmentedMp4Extractor(int flags, char c) {
        this.d = flags | 0;
        this.k = null;
        this.e = null;
        this.l = new k(16);
        this.g = new k(com.google.android.exoplayer2.d.i.a);
        this.h = new k(5);
        this.i = new k();
        this.j = new k(1);
        this.m = new byte[16];
        this.n = new Stack();
        this.o = new LinkedList();
        this.f = new SparseArray();
        this.w = -9223372036854775807L;
        this.x = -9223372036854775807L;
        a();
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        return h.a(input);
    }

    public final void a(h output) {
        this.D = output;
        if (this.e != null) {
            b bundle = new b(output.a(0));
            bundle.a(this.e, new c(0, 0, 0, 0));
            this.f.put(0, bundle);
            b();
            this.D.b();
        }
    }

    public final void a(long position, long timeUs) {
        int trackCount = this.f.size();
        for (int i = 0; i < trackCount; i++) {
            ((b) this.f.valueAt(i)).a();
        }
        this.o.clear();
        this.v = 0;
        this.n.clear();
        a();
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        while (true) {
            Object obj;
            long c;
            int size;
            int i;
            j jVar;
            int i2;
            long c2;
            long l;
            long a;
            int i3;
            int n;
            int b;
            b bVar;
            long j;
            b bVar2;
            switch (this.p) {
                case 0:
                    if (this.s == 0) {
                        if (!input.a(this.l.a, 0, 8, true)) {
                            obj = null;
                            if (obj != null) {
                                break;
                            }
                            return -1;
                        }
                        this.s = 8;
                        this.l.c(0);
                        this.r = this.l.l();
                        this.q = this.l.n();
                    }
                    if (this.r == 1) {
                        input.b(this.l.a, 8, 8);
                        this.s += 8;
                        this.r = this.l.v();
                    }
                    if (this.r < ((long) this.s)) {
                        throw new com.google.android.exoplayer2.k("Atom size less than header length (unsupported).");
                    }
                    c = input.c() - ((long) this.s);
                    if (this.q == a.K) {
                        size = this.f.size();
                        for (i = 0; i < size; i++) {
                            jVar = ((b) this.f.valueAt(i)).a;
                            jVar.b = c;
                            jVar.d = c;
                            jVar.c = c;
                        }
                    }
                    if (this.q == a.h) {
                        this.y = null;
                        this.u = this.r + c;
                        if (!this.G) {
                            this.D.a(new com.google.android.exoplayer2.extractor.m.a(this.w));
                            this.G = true;
                        }
                        this.p = 2;
                    } else {
                        i2 = this.q;
                        if (i2 == a.B || i2 == a.D || i2 == a.E || i2 == a.F || i2 == a.G || i2 == a.K || i2 == a.L || i2 == a.M || i2 == a.P) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            c2 = (input.c() + this.r) - 8;
                            this.n.add(new a(this.q, c2));
                            if (this.r == ((long) this.s)) {
                                a(c2);
                            } else {
                                a();
                            }
                        } else {
                            i2 = this.q;
                            if (i2 == a.S || i2 == a.R || i2 == a.C || i2 == a.A || i2 == a.T || i2 == a.w || i2 == a.x || i2 == a.O || i2 == a.y || i2 == a.z || i2 == a.U || i2 == a.ac || i2 == a.ad || i2 == a.ah || i2 == a.ag || i2 == a.ae || i2 == a.af || i2 == a.Q || i2 == a.N || i2 == a.aG) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            if (obj != null) {
                                if (this.s != 8) {
                                    throw new com.google.android.exoplayer2.k("Leaf atom defines extended atom size (unsupported).");
                                } else if (this.r > 2147483647L) {
                                    throw new com.google.android.exoplayer2.k("Leaf atom with length > 2147483647 (unsupported).");
                                } else {
                                    this.t = new k((int) this.r);
                                    System.arraycopy(this.l.a, 0, this.t.a, 0, 8);
                                    this.p = 1;
                                }
                            } else if (this.r > 2147483647L) {
                                throw new com.google.android.exoplayer2.k("Skipping atom with length > 2147483647 (unsupported).");
                            } else {
                                this.t = null;
                                this.p = 1;
                            }
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        return -1;
                    }
                    break;
                case 1:
                    i2 = ((int) this.r) - this.s;
                    if (this.t != null) {
                        input.b(this.t.a, 8, i2);
                        b bVar3 = new b(this.q, this.t);
                        long c3 = input.c();
                        if (!this.n.isEmpty()) {
                            ((a) this.n.peek()).a(bVar3);
                        } else if (bVar3.aP == a.A) {
                            k kVar = bVar3.aQ;
                            kVar.c(8);
                            i2 = a.a(kVar.n());
                            kVar.d(4);
                            l = kVar.l();
                            if (i2 == 0) {
                                c3 = kVar.l() + c3;
                                c2 = kVar.l();
                            } else {
                                c3 = kVar.v() + c3;
                                c2 = kVar.v();
                            }
                            a = s.a(c2, 1000000, l);
                            kVar.d(2);
                            int h = kVar.h();
                            int[] iArr = new int[h];
                            long[] jArr = new long[h];
                            long[] jArr2 = new long[h];
                            long[] jArr3 = new long[h];
                            long j2 = c3;
                            i3 = 0;
                            c = c2;
                            c2 = a;
                            while (i3 < h) {
                                n = kVar.n();
                                if ((Integer.MIN_VALUE & n) != 0) {
                                    throw new com.google.android.exoplayer2.k("Unhandled indirect reference");
                                }
                                long l2 = kVar.l();
                                iArr[i3] = n & Integer.MAX_VALUE;
                                jArr[i3] = j2;
                                jArr3[i3] = c2;
                                c2 = c + l2;
                                l2 = s.a(c2, 1000000, l);
                                jArr2[i3] = l2 - jArr3[i3];
                                kVar.d(4);
                                j2 += (long) iArr[i3];
                                i3++;
                                c = c2;
                                c2 = l2;
                            }
                            Pair create = Pair.create(Long.valueOf(a), new com.google.android.exoplayer2.extractor.a(iArr, jArr, jArr2, jArr3));
                            this.x = ((Long) create.first).longValue();
                            this.D.a((m) create.second);
                            this.G = true;
                        } else if (bVar3.aP == a.aG) {
                            k kVar2 = bVar3.aQ;
                            if (this.E != null) {
                                kVar2.c(12);
                                kVar2.w();
                                kVar2.w();
                                c = s.a(kVar2.l(), 1000000, kVar2.l());
                                kVar2.c(12);
                                b = kVar2.b();
                                this.E.a(kVar2, b);
                                if (this.x != -9223372036854775807L) {
                                    this.E.a(c + this.x, 1, b, 0, null);
                                } else {
                                    this.o.addLast(new a(c, b));
                                    this.v += b;
                                }
                            }
                        }
                    } else {
                        input.b(i2);
                    }
                    a(input.c());
                    break;
                case 2:
                    bVar = null;
                    c = Long.MAX_VALUE;
                    b = this.f.size();
                    size = 0;
                    while (size < b) {
                        jVar = ((b) this.f.valueAt(size)).a;
                        if (!jVar.r || jVar.d >= c) {
                            j = c;
                            bVar2 = bVar;
                        } else {
                            j = jVar.d;
                            bVar2 = (b) this.f.valueAt(size);
                        }
                        size++;
                        bVar = bVar2;
                        c = j;
                    }
                    if (bVar != null) {
                        i2 = (int) (c - input.c());
                        if (i2 >= 0) {
                            input.b(i2);
                            jVar = bVar.a;
                            input.b(jVar.q.a, 0, jVar.p);
                            jVar.q.c(0);
                            jVar.r = false;
                            break;
                        }
                        throw new com.google.android.exoplayer2.k("Offset to encryption data was negative.");
                    }
                    this.p = 3;
                    break;
                default:
                    int size2;
                    b bVar4;
                    if (this.p == 3) {
                        if (this.y == null) {
                            SparseArray sparseArray = this.f;
                            bVar = null;
                            l = Long.MAX_VALUE;
                            size2 = sparseArray.size();
                            i3 = 0;
                            while (i3 < size2) {
                                bVar4 = (b) sparseArray.valueAt(i3);
                                if (bVar4.g != bVar4.a.e) {
                                    c = bVar4.a.g[bVar4.g];
                                    if (c < l) {
                                        j = c;
                                        bVar2 = bVar4;
                                        c2 = j;
                                        i3++;
                                        l = c2;
                                        bVar = bVar2;
                                    }
                                }
                                bVar2 = bVar;
                                c2 = l;
                                i3++;
                                l = c2;
                                bVar = bVar2;
                            }
                            if (bVar == null) {
                                i2 = (int) (this.u - input.c());
                                if (i2 >= 0) {
                                    input.b(i2);
                                    a();
                                    obj = null;
                                    if (obj == null) {
                                        break;
                                    }
                                    return 0;
                                }
                                throw new com.google.android.exoplayer2.k("Offset to end of mdat was negative.");
                            }
                            i2 = (int) (bVar.a.g[bVar.g] - input.c());
                            if (i2 < 0) {
                                i2 = 0;
                            }
                            input.b(i2);
                            this.y = bVar;
                        }
                        this.z = this.y.a.i[this.y.e];
                        if (this.y.a.m) {
                            bVar = this.y;
                            j jVar2 = bVar.a;
                            k kVar3 = jVar2.q;
                            size = (jVar2.o != null ? jVar2.o : bVar.c.h[jVar2.a.a]).b;
                            boolean z = jVar2.n[bVar.e];
                            this.j.a[0] = (byte) ((z ? 128 : 0) | size);
                            this.j.c(0);
                            n nVar = bVar.b;
                            nVar.a(this.j, 1);
                            nVar.a(kVar3, size);
                            if (z) {
                                i = kVar3.h();
                                kVar3.d(-2);
                                i = (i * 6) + 2;
                                nVar.a(kVar3, i);
                                i2 = (size + 1) + i;
                            } else {
                                i2 = size + 1;
                            }
                            this.A = i2;
                            this.z += this.A;
                        } else {
                            this.A = 0;
                        }
                        if (this.y.c.g == 1) {
                            this.z -= 8;
                            input.b(8);
                        }
                        this.p = 4;
                        this.B = 0;
                    }
                    j jVar3 = this.y.a;
                    Track track = this.y.c;
                    n nVar2 = this.y.b;
                    size = this.y.e;
                    if (track.k != 0) {
                        byte[] bArr = this.h.a;
                        bArr[0] = (byte) 0;
                        bArr[1] = (byte) 0;
                        bArr[2] = (byte) 0;
                        i3 = track.k + 1;
                        n = 4 - track.k;
                        while (this.A < this.z) {
                            if (this.B == 0) {
                                input.b(bArr, n, i3);
                                this.h.c(0);
                                this.B = this.h.t() - 1;
                                this.g.c(0);
                                nVar2.a(this.g, 4);
                                nVar2.a(this.h, 1);
                                boolean z2 = this.F != null && com.google.android.exoplayer2.d.i.a(track.f.f, bArr[4]);
                                this.C = z2;
                                this.A += 5;
                                this.z += n;
                            } else {
                                if (this.C) {
                                    this.i.a(this.B);
                                    input.b(this.i.a, 0, this.B);
                                    nVar2.a(this.i, this.B);
                                    int i4 = this.B;
                                    size2 = com.google.android.exoplayer2.d.i.a(this.i.a, this.i.c());
                                    this.i.c("video/hevc".equals(track.f.f) ? 1 : 0);
                                    this.i.b(size2);
                                    com.google.android.exoplayer2.text.a.g.a(jVar3.b(size) * 1000, this.i, this.F);
                                    i2 = i4;
                                } else {
                                    i2 = nVar2.a(input, this.B, false);
                                }
                                this.A += i2;
                                this.B -= i2;
                            }
                        }
                    } else {
                        while (this.A < this.z) {
                            this.A = nVar2.a(input, this.z - this.A, false) + this.A;
                        }
                    }
                    a = 1000 * jVar3.b(size);
                    size = (jVar3.m ? ErrorDialogData.SUPPRESSED : 0) | (jVar3.l[size] ? 1 : 0);
                    i2 = jVar3.a.a;
                    byte[] bArr2 = null;
                    if (jVar3.m) {
                        byte[] bArr3;
                        if (jVar3.o != null) {
                            bArr3 = jVar3.o.c;
                        } else {
                            bArr3 = track.h[i2].c;
                        }
                        bArr2 = bArr3;
                    }
                    if (this.k != null) {
                        c = this.k.b(a);
                    } else {
                        c = a;
                    }
                    nVar2.a(c, size, this.z, 0, bArr2);
                    while (!this.o.isEmpty()) {
                        a aVar = (a) this.o.removeFirst();
                        this.v -= aVar.b;
                        this.E.a(aVar.a + c, 1, aVar.b, this.v, null);
                    }
                    bVar4 = this.y;
                    bVar4.e++;
                    bVar4 = this.y;
                    bVar4.f++;
                    if (this.y.f == jVar3.h[this.y.g]) {
                        bVar4 = this.y;
                        bVar4.g++;
                        this.y.f = 0;
                        this.y = null;
                    }
                    this.p = 3;
                    obj = 1;
                    if (obj == null) {
                        return 0;
                    }
                    break;
            }
        }
    }

    private void a() {
        this.p = 0;
        this.s = 0;
    }

    private void a(long atomEndPosition) throws com.google.android.exoplayer2.k {
        while (!this.n.isEmpty() && ((a) this.n.peek()).aQ == atomEndPosition) {
            a aVar = (a) this.n.pop();
            if (aVar.aP == a.B) {
                Track a;
                com.google.android.exoplayer2.d.a.b(this.e == null, "Unexpected moov box.");
                DrmInitData a2 = a(aVar.aR);
                a e = aVar.e(a.M);
                SparseArray sparseArray = new SparseArray();
                long j = -9223372036854775807L;
                int size = e.aR.size();
                for (int i = 0; i < size; i++) {
                    b bVar = (b) e.aR.get(i);
                    k kVar;
                    if (bVar.aP == a.y) {
                        kVar = bVar.aQ;
                        kVar.c(12);
                        Pair create = Pair.create(Integer.valueOf(kVar.n()), new c(kVar.t() - 1, kVar.t(), kVar.t(), kVar.n()));
                        sparseArray.put(((Integer) create.first).intValue(), create.second);
                    } else if (bVar.aP == a.N) {
                        long l;
                        kVar = bVar.aQ;
                        kVar.c(8);
                        if (a.a(kVar.n()) == 0) {
                            l = kVar.l();
                        } else {
                            l = kVar.v();
                        }
                        j = l;
                    }
                }
                SparseArray sparseArray2 = new SparseArray();
                int size2 = aVar.aS.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    a aVar2 = (a) aVar.aS.get(i2);
                    if (aVar2.aP == a.D) {
                        a = b.a(aVar2, aVar.d(a.C), j, a2, false);
                        if (a != null) {
                            sparseArray2.put(a.a, a);
                        }
                    }
                }
                int size3 = sparseArray2.size();
                if (this.f.size() == 0) {
                    for (int i3 = 0; i3 < size3; i3++) {
                        a = (Track) sparseArray2.valueAt(i3);
                        b bVar2 = new b(this.D.a(i3));
                        bVar2.a(a, (c) sparseArray.get(a.a));
                        this.f.put(a.a, bVar2);
                        this.w = Math.max(this.w, a.e);
                    }
                    b();
                    this.D.b();
                } else {
                    com.google.android.exoplayer2.d.a.b(this.f.size() == size3);
                    for (int i4 = 0; i4 < size3; i4++) {
                        a = (Track) sparseArray2.valueAt(i4);
                        ((b) this.f.get(a.a)).a(a, (c) sparseArray.get(a.a));
                    }
                }
            } else if (aVar.aP == a.K) {
                a(aVar);
            } else if (!this.n.isEmpty()) {
                ((a) this.n.peek()).a(aVar);
            }
        }
        a();
    }

    private void a(a moof) throws com.google.android.exoplayer2.k {
        SparseArray sparseArray = this.f;
        int i = this.d;
        byte[] bArr = this.m;
        int size = moof.aS.size();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            b bVar;
            if (i3 < size) {
                a aVar = (a) moof.aS.get(i3);
                if (aVar.aP == a.L) {
                    b bVar2;
                    k kVar = aVar.d(a.x).aQ;
                    kVar.c(8);
                    int b = a.b(kVar.n());
                    i2 = kVar.n();
                    if ((i & 16) != 0) {
                        i2 = 0;
                    }
                    bVar = (b) sparseArray.get(i2);
                    if (bVar == null) {
                        bVar2 = null;
                    } else {
                        if ((b & 1) != 0) {
                            long v = kVar.v();
                            bVar.a.c = v;
                            bVar.a.d = v;
                        }
                        c cVar = bVar.d;
                        bVar.a.a = new c((b & 2) != 0 ? kVar.t() - 1 : cVar.a, (b & 8) != 0 ? kVar.t() : cVar.b, (b & 16) != 0 ? kVar.t() : cVar.c, (b & 32) != 0 ? kVar.t() : cVar.d);
                        bVar2 = bVar;
                    }
                    if (bVar2 != null) {
                        long j;
                        k kVar2;
                        b bVar3;
                        j jVar = bVar2.a;
                        long j2 = jVar.s;
                        bVar2.a();
                        if (aVar.d(a.w) == null || (i & 2) != 0) {
                            j = j2;
                        } else {
                            kVar2 = aVar.d(a.w).aQ;
                            kVar2.c(8);
                            if (a.a(kVar2.n()) == 1) {
                                j2 = kVar2.v();
                            } else {
                                j2 = kVar2.l();
                            }
                            j = j2;
                        }
                        int i4 = 0;
                        int i5 = 0;
                        List list = aVar.aR;
                        int size2 = list.size();
                        i2 = 0;
                        while (true) {
                            int i6 = i2;
                            if (i6 >= size2) {
                                break;
                            }
                            bVar3 = (b) list.get(i6);
                            if (bVar3.aP == a.z) {
                                kVar2 = bVar3.aQ;
                                kVar2.c(12);
                                i2 = kVar2.t();
                                if (i2 > 0) {
                                    i2 += i5;
                                    i5 = i4 + 1;
                                    i6++;
                                    i4 = i5;
                                }
                            }
                            i2 = i5;
                            i5 = i4;
                            i6++;
                            i4 = i5;
                        }
                        bVar2.g = 0;
                        bVar2.f = 0;
                        bVar2.e = 0;
                        j jVar2 = bVar2.a;
                        jVar2.e = i4;
                        jVar2.f = i5;
                        if (jVar2.h == null || jVar2.h.length < i4) {
                            jVar2.g = new long[i4];
                            jVar2.h = new int[i4];
                        }
                        if (jVar2.i == null || jVar2.i.length < i5) {
                            i5 = (i5 * 125) / 100;
                            jVar2.i = new int[i5];
                            jVar2.j = new int[i5];
                            jVar2.k = new long[i5];
                            jVar2.l = new boolean[i5];
                            jVar2.n = new boolean[i5];
                        }
                        int i7 = 0;
                        int i8 = 0;
                        int i9 = 0;
                        while (i9 < size2) {
                            bVar3 = (b) list.get(i9);
                            if (bVar3.aP == a.z) {
                                Object obj;
                                long a;
                                int i10 = i7 + 1;
                                k kVar3 = bVar3.aQ;
                                kVar3.c(8);
                                i5 = a.b(kVar3.n());
                                Track track = bVar2.c;
                                j jVar3 = bVar2.a;
                                c cVar2 = jVar3.a;
                                jVar3.h[i7] = kVar3.t();
                                jVar3.g[i7] = jVar3.c;
                                if ((i5 & 1) != 0) {
                                    long[] jArr = jVar3.g;
                                    jArr[i7] = jArr[i7] + ((long) kVar3.n());
                                }
                                Object obj2 = (i5 & 4) != 0 ? 1 : null;
                                int i11 = cVar2.d;
                                if (obj2 != null) {
                                    i11 = kVar3.t();
                                }
                                Object obj3 = (i5 & Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) != 0 ? 1 : null;
                                Object obj4 = (i5 & 512) != 0 ? 1 : null;
                                Object obj5 = (i5 & 1024) != 0 ? 1 : null;
                                if ((i5 & 2048) != 0) {
                                    obj = 1;
                                } else {
                                    obj = null;
                                }
                                if (track.i != null && track.i.length == 1 && track.i[0] == 0) {
                                    a = s.a(track.j[0], 1000, track.c);
                                } else {
                                    a = 0;
                                }
                                int[] iArr = jVar3.i;
                                int[] iArr2 = jVar3.j;
                                long[] jArr2 = jVar3.k;
                                boolean[] zArr = jVar3.l;
                                Object obj6 = (track.b != 2 || (i & 1) == 0) ? null : 1;
                                int i12 = i8 + jVar3.h[i7];
                                long j3 = track.c;
                                if (i7 > 0) {
                                    j2 = jVar3.s;
                                } else {
                                    j2 = j;
                                }
                                while (true) {
                                    int i13 = i8;
                                    if (i13 >= i12) {
                                        break;
                                    }
                                    int t = obj3 != null ? kVar3.t() : cVar2.b;
                                    i7 = obj4 != null ? kVar3.t() : cVar2.c;
                                    i8 = (i13 != 0 || obj2 == null) ? obj5 != null ? kVar3.n() : cVar2.d : i11;
                                    if (obj != null) {
                                        iArr2[i13] = (int) (((long) (kVar3.n() * Constants.ONE_SECOND)) / j3);
                                    } else {
                                        iArr2[i13] = 0;
                                    }
                                    jArr2[i13] = s.a(j2, 1000, j3) - a;
                                    iArr[i13] = i7;
                                    boolean z = ((i8 >> 16) & 1) == 0 && (obj6 == null || i13 == 0);
                                    zArr[i13] = z;
                                    j2 += (long) t;
                                    i8 = i13 + 1;
                                }
                                jVar3.s = j2;
                                i2 = i12;
                                i5 = i10;
                            } else {
                                i2 = i8;
                                i5 = i7;
                            }
                            i9++;
                            i8 = i2;
                            i7 = i5;
                        }
                        bVar3 = aVar.d(a.ac);
                        if (bVar3 != null) {
                            i iVar = bVar2.c.h[jVar.a.a];
                            k kVar4 = bVar3.aQ;
                            int i14 = iVar.b;
                            kVar4.c(8);
                            if ((a.b(kVar4.n()) & 1) == 1) {
                                kVar4.d(8);
                            }
                            i4 = kVar4.g();
                            int t2 = kVar4.t();
                            if (t2 != jVar.f) {
                                throw new com.google.android.exoplayer2.k("Length mismatch: " + t2 + ", " + jVar.f);
                            }
                            if (i4 == 0) {
                                boolean[] zArr2 = jVar.n;
                                i2 = 0;
                                i5 = 0;
                                while (i5 < t2) {
                                    int g = kVar4.g();
                                    i4 = i2 + g;
                                    zArr2[i5] = g > i14;
                                    i5++;
                                    i2 = i4;
                                }
                            } else {
                                i5 = (i4 * t2) + 0;
                                Arrays.fill(jVar.n, 0, t2, i4 > i14);
                                i2 = i5;
                            }
                            jVar.a(i2);
                        }
                        bVar3 = aVar.d(a.ad);
                        if (bVar3 != null) {
                            kVar2 = bVar3.aQ;
                            kVar2.c(8);
                            i5 = kVar2.n();
                            if ((a.b(i5) & 1) == 1) {
                                kVar2.d(8);
                            }
                            i4 = kVar2.t();
                            if (i4 != 1) {
                                throw new com.google.android.exoplayer2.k("Unexpected saio entry count: " + i4);
                            }
                            jVar.d = (a.a(i5) == 0 ? kVar2.l() : kVar2.v()) + jVar.d;
                        }
                        bVar3 = aVar.d(a.ah);
                        if (bVar3 != null) {
                            a(bVar3.aQ, 0, jVar);
                        }
                        bVar3 = aVar.d(a.ae);
                        b d = aVar.d(a.af);
                        if (!(bVar3 == null || d == null)) {
                            kVar2 = bVar3.aQ;
                            k kVar5 = d.aQ;
                            kVar2.c(8);
                            i4 = kVar2.n();
                            if (kVar2.n() == b) {
                                if (a.a(i4) == 1) {
                                    kVar2.d(4);
                                }
                                if (kVar2.n() != 1) {
                                    throw new com.google.android.exoplayer2.k("Entry count in sbgp != 1 (unsupported).");
                                }
                                kVar5.c(8);
                                i2 = kVar5.n();
                                if (kVar5.n() == b) {
                                    i2 = a.a(i2);
                                    if (i2 == 1) {
                                        if (kVar5.l() == 0) {
                                            throw new com.google.android.exoplayer2.k("Variable length decription in sgpd found (unsupported)");
                                        }
                                    } else if (i2 >= 2) {
                                        kVar5.d(4);
                                    }
                                    if (kVar5.l() != 1) {
                                        throw new com.google.android.exoplayer2.k("Entry count in sgpd != 1 (unsupported).");
                                    }
                                    kVar5.d(2);
                                    if ((kVar5.g() == 1 ? 1 : null) != null) {
                                        i2 = kVar5.g();
                                        byte[] bArr2 = new byte[16];
                                        kVar5.a(bArr2, 0, 16);
                                        jVar.m = true;
                                        jVar.o = new i(true, i2, bArr2);
                                    }
                                }
                            }
                        }
                        i4 = aVar.aR.size();
                        for (i5 = 0; i5 < i4; i5++) {
                            bVar3 = (b) aVar.aR.get(i5);
                            if (bVar3.aP == a.ag) {
                                kVar2 = bVar3.aQ;
                                kVar2.c(8);
                                kVar2.a(bArr, 0, 16);
                                if (Arrays.equals(bArr, c)) {
                                    a(kVar2, 16, jVar);
                                }
                            }
                        }
                    } else {
                        continue;
                    }
                }
                i2 = i3 + 1;
            } else {
                DrmInitData drmInitData = a(moof.aR);
                if (drmInitData != null) {
                    int trackCount = this.f.size();
                    for (int i15 = 0; i15 < trackCount; i15++) {
                        bVar = (b) this.f.valueAt(i15);
                        bVar.b.a(bVar.c.f.a(drmInitData));
                    }
                    return;
                }
                return;
            }
        }
    }

    private void b() {
        if ((this.d & 4) != 0 && this.E == null) {
            this.E = this.D.a(this.f.size());
            this.E.a(Format.a("application/x-emsg", Long.MAX_VALUE));
        }
        if ((this.d & 8) != 0 && this.F == null) {
            this.D.a(this.f.size() + 1).a(Format.a(null, "application/cea-608", 0, null, null));
            this.F = new n[]{cea608TrackOutput};
        }
    }

    private static void a(k senc, int offset, j out) throws com.google.android.exoplayer2.k {
        senc.c(offset + 8);
        int flags = a.b(senc.n());
        if ((flags & 1) != 0) {
            throw new com.google.android.exoplayer2.k("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean subsampleEncryption;
        if ((flags & 2) != 0) {
            subsampleEncryption = true;
        } else {
            subsampleEncryption = false;
        }
        int sampleCount = senc.t();
        if (sampleCount != out.f) {
            throw new com.google.android.exoplayer2.k("Length mismatch: " + sampleCount + ", " + out.f);
        }
        Arrays.fill(out.n, 0, sampleCount, subsampleEncryption);
        out.a(senc.b());
        senc.a(out.q.a, 0, out.p);
        out.q.c(0);
        out.r = false;
    }

    private static DrmInitData a(List<b> leafChildren) {
        List schemeDatas = null;
        int leafChildrenSize = leafChildren.size();
        for (int i = 0; i < leafChildrenSize; i++) {
            b child = (b) leafChildren.get(i);
            if (child.aP == a.U) {
                if (schemeDatas == null) {
                    schemeDatas = new ArrayList();
                }
                byte[] psshData = child.aQ.a;
                UUID uuid = g.a(psshData);
                if (uuid != null) {
                    schemeDatas.add(new SchemeData(uuid, "video/mp4", psshData));
                }
            }
        }
        return schemeDatas == null ? null : new DrmInitData(schemeDatas);
    }
}
