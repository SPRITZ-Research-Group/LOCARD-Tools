package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.i;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.l;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;
import java.util.Collections;

public final class j implements g {
    private final r a;
    private String b;
    private n c;
    private a d;
    private boolean e;
    private final boolean[] f = new boolean[3];
    private final m g = new m(32);
    private final m h = new m(33);
    private final m i = new m(34);
    private final m j = new m(39);
    private final m k = new m(40);
    private long l;
    private long m;
    private final k n = new k();

    private static final class a {
        private final n a;
        private long b;
        private boolean c;
        private int d;
        private long e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private long k;
        private long l;
        private boolean m;

        public a(n output) {
            this.a = output;
        }

        public final void a() {
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public final void a(long position, int offset, int nalUnitType, long pesTimeUs) {
            boolean z;
            boolean z2 = false;
            this.g = false;
            this.h = false;
            this.e = pesTimeUs;
            this.d = 0;
            this.b = position;
            if (nalUnitType >= 32) {
                if (!this.j && this.i) {
                    a(offset);
                    this.i = false;
                }
                if (nalUnitType <= 34) {
                    this.h = !this.j;
                    this.j = true;
                }
            }
            if (nalUnitType < 16 || nalUnitType > 21) {
                z = false;
            } else {
                z = true;
            }
            this.c = z;
            if (this.c || nalUnitType <= 9) {
                z2 = true;
            }
            this.f = z2;
        }

        public final void a(byte[] data, int offset, int limit) {
            if (this.f) {
                int headerOffset = (offset + 2) - this.d;
                if (headerOffset < limit) {
                    boolean z;
                    if ((data[headerOffset] & 128) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.g = z;
                    this.f = false;
                    return;
                }
                this.d += limit - offset;
            }
        }

        public final void a(long position, int offset) {
            if (this.j && this.g) {
                this.m = this.c;
                this.j = false;
            } else if (this.h || this.g) {
                if (this.i) {
                    a(offset + ((int) (position - this.b)));
                }
                this.k = this.b;
                this.l = this.e;
                this.i = true;
                this.m = this.c;
            }
        }

        private void a(int offset) {
            this.a.a(this.l, this.m ? 1 : 0, (int) (this.b - this.k), offset, null);
        }
    }

    public j(r seiReader) {
        this.a = seiReader;
    }

    public final void a() {
        i.a(this.f);
        this.g.a();
        this.h.a();
        this.i.a();
        this.j.a();
        this.k.a();
        this.d.a();
        this.l = 0;
    }

    public final void a(h extractorOutput, d idGenerator) {
        idGenerator.a();
        this.b = idGenerator.c();
        this.c = extractorOutput.a(idGenerator.b());
        this.d = new a(this.c);
        this.a.a(extractorOutput, idGenerator);
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.m = pesTimeUs;
    }

    public final void a(k data) {
        while (data.b() > 0) {
            int offset = data.d();
            int limit = data.c();
            byte[] dataArray = data.a;
            this.l += (long) data.b();
            this.c.a(data, data.b());
            while (offset < limit) {
                int nalUnitOffset = i.a(dataArray, offset, limit, this.f);
                if (nalUnitOffset == limit) {
                    a(dataArray, offset, limit);
                    return;
                }
                int i;
                int nalUnitType = i.c(dataArray, nalUnitOffset);
                int lengthToNalUnit = nalUnitOffset - offset;
                if (lengthToNalUnit > 0) {
                    a(dataArray, offset, nalUnitOffset);
                }
                int bytesWrittenPastPosition = limit - nalUnitOffset;
                long absolutePosition = this.l - ((long) bytesWrittenPastPosition);
                if (lengthToNalUnit < 0) {
                    i = -lengthToNalUnit;
                } else {
                    i = 0;
                }
                long j = this.m;
                if (this.e) {
                    this.d.a(absolutePosition, bytesWrittenPastPosition);
                } else {
                    this.g.b(i);
                    this.h.b(i);
                    this.i.b(i);
                    if (this.g.b() && this.h.b() && this.i.b()) {
                        int i2;
                        int c;
                        int c2;
                        int c3;
                        int c4;
                        int i3;
                        float f;
                        n nVar = this.c;
                        String str = this.b;
                        m mVar = this.g;
                        m mVar2 = this.h;
                        m mVar3 = this.i;
                        Object obj = new byte[((mVar.b + mVar2.b) + mVar3.b)];
                        System.arraycopy(mVar.a, 0, obj, 0, mVar.b);
                        System.arraycopy(mVar2.a, 0, obj, mVar.b, mVar2.b);
                        System.arraycopy(mVar3.a, 0, obj, mVar.b + mVar2.b, mVar3.b);
                        l lVar = new l(mVar2.a, 0, mVar2.b);
                        lVar.a(44);
                        int c5 = lVar.c(3);
                        lVar.a(1);
                        lVar.a(88);
                        lVar.a(8);
                        int i4 = 0;
                        for (i2 = 0; i2 < c5; i2++) {
                            if (lVar.a()) {
                                i4 += 89;
                            }
                            if (lVar.a()) {
                                i4 += 8;
                            }
                        }
                        lVar.a(i4);
                        if (c5 > 0) {
                            lVar.a((8 - c5) * 2);
                        }
                        lVar.c();
                        int c6 = lVar.c();
                        if (c6 == 3) {
                            lVar.a(1);
                        }
                        i2 = lVar.c();
                        int c7 = lVar.c();
                        if (lVar.a()) {
                            c = lVar.c();
                            c2 = lVar.c();
                            c3 = lVar.c();
                            c4 = lVar.c();
                            i3 = (c6 == 1 || c6 == 2) ? 2 : 1;
                            i2 -= i3 * (c + c2);
                            c7 -= (c6 == 1 ? 2 : 1) * (c3 + c4);
                        }
                        lVar.c();
                        lVar.c();
                        c = lVar.c();
                        i4 = lVar.a() ? 0 : c5;
                        while (i4 <= c5) {
                            lVar.c();
                            lVar.c();
                            lVar.c();
                            i4++;
                        }
                        lVar.c();
                        lVar.c();
                        lVar.c();
                        lVar.c();
                        lVar.c();
                        lVar.c();
                        if (lVar.a() && lVar.a()) {
                            i4 = 0;
                            while (true) {
                                i3 = i4;
                                if (i3 >= 4) {
                                    break;
                                }
                                i4 = 0;
                                while (true) {
                                    c5 = i4;
                                    if (c5 >= 6) {
                                        break;
                                    }
                                    if (lVar.a()) {
                                        c6 = Math.min(64, 1 << ((i3 << 1) + 4));
                                        if (i3 > 1) {
                                            lVar.d();
                                        }
                                        for (i4 = 0; i4 < c6; i4++) {
                                            lVar.d();
                                        }
                                    } else {
                                        lVar.c();
                                    }
                                    i4 = (i3 == 3 ? 3 : 1) + c5;
                                }
                                i4 = i3 + 1;
                            }
                        }
                        lVar.a(2);
                        if (lVar.a()) {
                            lVar.a(8);
                            lVar.c();
                            lVar.c();
                            lVar.a(1);
                        }
                        c2 = lVar.c();
                        boolean z = false;
                        c5 = 0;
                        c6 = 0;
                        while (true) {
                            i4 = c5;
                            boolean z2 = z;
                            if (c6 >= c2) {
                                break;
                            }
                            if (c6 != 0) {
                                z = lVar.a();
                            } else {
                                z = z2;
                            }
                            if (z) {
                                lVar.a(1);
                                lVar.c();
                                for (c5 = 0; c5 <= i4; c5++) {
                                    if (lVar.a()) {
                                        lVar.a(1);
                                    }
                                }
                            } else {
                                c3 = lVar.c();
                                c4 = lVar.c();
                                i4 = c3 + c4;
                                for (c5 = 0; c5 < c3; c5++) {
                                    lVar.c();
                                    lVar.a(1);
                                }
                                for (c5 = 0; c5 < c4; c5++) {
                                    lVar.c();
                                    lVar.a(1);
                                }
                            }
                            c5 = c6 + 1;
                        }
                        if (lVar.a()) {
                            for (i4 = 0; i4 < lVar.c(); i4++) {
                                lVar.a((c + 4) + 1);
                            }
                        }
                        lVar.a(2);
                        float f2 = 1.0f;
                        if (lVar.a() && lVar.a()) {
                            c5 = lVar.c(8);
                            if (c5 == 255) {
                                c5 = lVar.c(16);
                                i3 = lVar.c(16);
                                if (!(c5 == 0 || i3 == 0)) {
                                    f2 = ((float) c5) / ((float) i3);
                                }
                                f = f2;
                            } else if (c5 < i.b.length) {
                                f = i.b[c5];
                            }
                            nVar.a(Format.a(str, "video/hevc", i2, c7, Collections.singletonList(obj), f));
                            this.e = true;
                        }
                        f = 1.0f;
                        nVar.a(Format.a(str, "video/hevc", i2, c7, Collections.singletonList(obj), f));
                        this.e = true;
                    }
                }
                if (this.j.b(i)) {
                    this.n.a(this.j.a, i.a(this.j.a, this.j.b));
                    this.n.d(5);
                    this.a.a(j, this.n);
                }
                if (this.k.b(i)) {
                    this.n.a(this.k.a, i.a(this.k.a, this.k.b));
                    this.n.d(5);
                    this.a.a(j, this.n);
                }
                long j2 = this.m;
                if (this.e) {
                    this.d.a(absolutePosition, bytesWrittenPastPosition, nalUnitType, j2);
                } else {
                    this.g.a(nalUnitType);
                    this.h.a(nalUnitType);
                    this.i.a(nalUnitType);
                }
                this.j.a(nalUnitType);
                this.k.a(nalUnitType);
                offset = nalUnitOffset + 3;
            }
        }
    }

    public final void b() {
    }

    private void a(byte[] dataArray, int offset, int limit) {
        if (this.e) {
            this.d.a(dataArray, offset, limit);
        } else {
            this.g.a(dataArray, offset, limit);
            this.h.a(dataArray, offset, limit);
            this.i.a(dataArray, offset, limit);
        }
        this.j.a(dataArray, offset, limit);
        this.k.a(dataArray, offset, limit);
    }
}
