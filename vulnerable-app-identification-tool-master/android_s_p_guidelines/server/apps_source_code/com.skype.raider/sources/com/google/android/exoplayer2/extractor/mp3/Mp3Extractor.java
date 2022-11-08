package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.j;
import com.google.android.exoplayer2.extractor.l;
import com.google.android.exoplayer2.extractor.m;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.metadata.Metadata;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Mp3Extractor implements f {
    public static final i a = new i() {
        public final f[] a() {
            return new f[]{new Mp3Extractor()};
        }
    };
    private static final int b = s.e("Xing");
    private static final int c = s.e("Info");
    private static final int d = s.e("VBRI");
    private final int e;
    private final long f;
    private final k g;
    private final com.google.android.exoplayer2.extractor.k h;
    private final j i;
    private h j;
    private n k;
    private int l;
    private Metadata m;
    private a n;
    private long o;
    private long p;
    private int q;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    interface a extends m {
        long b(long j);
    }

    public Mp3Extractor() {
        this(0);
    }

    public Mp3Extractor(int flags) {
        this(flags, (byte) 0);
    }

    private Mp3Extractor(int flags, byte b) {
        this.e = flags;
        this.f = -9223372036854775807L;
        this.g = new k(10);
        this.h = new com.google.android.exoplayer2.extractor.k();
        this.i = new j();
        this.o = -9223372036854775807L;
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        return a(input, true);
    }

    public final void a(h output) {
        this.j = output;
        this.k = this.j.a(0);
        this.j.b();
    }

    public final void a(long position, long timeUs) {
        this.l = 0;
        this.o = -9223372036854775807L;
        this.p = 0;
        this.q = 0;
    }

    public final int a(g input, l seekPosition) throws IOException, InterruptedException {
        int n;
        if (this.l == 0) {
            try {
                a(input, false);
            } catch (EOFException e) {
                return -1;
            }
        }
        if (this.n == null) {
            int i;
            a a;
            j jVar;
            int k;
            int i2;
            n nVar;
            String str;
            int i3;
            int i4;
            int i5;
            Metadata metadata;
            k kVar = new k(this.h.c);
            input.c(kVar.a, 0, this.h.c);
            int i6 = (this.h.a & 1) != 0 ? this.h.e != 1 ? 36 : 21 : this.h.e != 1 ? 21 : 13;
            if (kVar.c() >= i6 + 4) {
                kVar.c(i6);
                n = kVar.n();
                if (n == b || n == c) {
                    i = n;
                    if (i != b || i == c) {
                        a = c.a(this.h, kVar, input.c(), input.d());
                        if (!(a == null || this.i.a())) {
                            input.a();
                            input.c(i6 + 141);
                            input.c(this.g.a, 0, 3);
                            this.g.c(0);
                            jVar = this.i;
                            k = this.g.k();
                            i2 = k >> 12;
                            k &= 4095;
                            if (i2 > 0 || k > 0) {
                                jVar.b = i2;
                                jVar.c = k;
                            }
                        }
                        input.b(this.h.c);
                        if (!(a == null || a.i_() || i != c)) {
                            a = b(input);
                        }
                    } else if (i == d) {
                        a = b.a(this.h, kVar, input.c(), input.d());
                        input.b(this.h.c);
                    } else {
                        a = null;
                        input.a();
                    }
                    this.n = a;
                    if (this.n == null || !(this.n.i_() || (this.e & 1) == 0)) {
                        this.n = b(input);
                    }
                    this.j.a(this.n);
                    nVar = this.k;
                    str = this.h.b;
                    i3 = this.h.e;
                    i4 = this.h.d;
                    i = this.i.b;
                    i5 = this.i.c;
                    if ((this.e & 2) != 0) {
                        metadata = null;
                    } else {
                        metadata = this.m;
                    }
                    nVar.a(Format.a(null, str, -1, 4096, i3, i4, -1, i, i5, null, null, 0, null, metadata));
                }
            }
            if (kVar.c() >= 40) {
                kVar.c(36);
                if (kVar.n() == d) {
                    i = d;
                    if (i != b) {
                    }
                    a = c.a(this.h, kVar, input.c(), input.d());
                    input.a();
                    input.c(i6 + 141);
                    input.c(this.g.a, 0, 3);
                    this.g.c(0);
                    jVar = this.i;
                    k = this.g.k();
                    i2 = k >> 12;
                    k &= 4095;
                    jVar.b = i2;
                    jVar.c = k;
                    input.b(this.h.c);
                    a = b(input);
                    this.n = a;
                    this.n = b(input);
                    this.j.a(this.n);
                    nVar = this.k;
                    str = this.h.b;
                    i3 = this.h.e;
                    i4 = this.h.d;
                    i = this.i.b;
                    i5 = this.i.c;
                    if ((this.e & 2) != 0) {
                        metadata = this.m;
                    } else {
                        metadata = null;
                    }
                    nVar.a(Format.a(null, str, -1, 4096, i3, i4, -1, i, i5, null, null, 0, null, metadata));
                }
            }
            i = 0;
            if (i != b) {
            }
            a = c.a(this.h, kVar, input.c(), input.d());
            input.a();
            input.c(i6 + 141);
            input.c(this.g.a, 0, 3);
            this.g.c(0);
            jVar = this.i;
            k = this.g.k();
            i2 = k >> 12;
            k &= 4095;
            jVar.b = i2;
            jVar.c = k;
            input.b(this.h.c);
            a = b(input);
            this.n = a;
            this.n = b(input);
            this.j.a(this.n);
            nVar = this.k;
            str = this.h.b;
            i3 = this.h.e;
            i4 = this.h.d;
            i = this.i.b;
            i5 = this.i.c;
            if ((this.e & 2) != 0) {
                metadata = null;
            } else {
                metadata = this.m;
            }
            nVar.a(Format.a(null, str, -1, 4096, i3, i4, -1, i, i5, null, null, 0, null, metadata));
        }
        if (this.q == 0) {
            input.a();
            if (!input.b(this.g.a, 0, 4, true)) {
                return -1;
            }
            this.g.c(0);
            n = this.g.n();
            if (!a(n, (long) this.l) || com.google.android.exoplayer2.extractor.k.a(n) == -1) {
                input.b(1);
                this.l = 0;
                return 0;
            }
            com.google.android.exoplayer2.extractor.k.a(n, this.h);
            if (this.o == -9223372036854775807L) {
                this.o = this.n.b(input.c());
                if (this.f != -9223372036854775807L) {
                    this.o = (this.f - this.n.b(0)) + this.o;
                }
            }
            this.q = this.h.c;
        }
        n = this.k.a(input, this.q, true);
        if (n == -1) {
            return -1;
        }
        this.q -= n;
        if (this.q > 0) {
            return 0;
        }
        this.k.a(((this.p * 1000000) / ((long) this.h.d)) + this.o, 1, this.h.c, 0, null);
        this.p += (long) this.h.g;
        this.q = 0;
        return 0;
    }

    private boolean a(g input, boolean sniffing) throws IOException, InterruptedException {
        int validFrameCount = 0;
        int candidateSynchronizedHeaderData = 0;
        int peekedId3Bytes = 0;
        int searchedBytes = 0;
        int searchLimitBytes = sniffing ? 16384 : 131072;
        input.a();
        if (input.c() == 0) {
            int i = 0;
            while (true) {
                input.c(this.g.a, 0, 10);
                this.g.c(0);
                if (this.g.k() != com.google.android.exoplayer2.metadata.id3.a.a) {
                    break;
                }
                this.g.d(3);
                int s = this.g.s();
                int i2 = s + 10;
                if (this.m == null) {
                    byte[] bArr = new byte[i2];
                    System.arraycopy(this.g.a, 0, bArr, 0, 10);
                    input.c(bArr, 10, s);
                    this.m = new com.google.android.exoplayer2.metadata.id3.a((this.e & 2) != 0 ? j.a : null).a(bArr, i2);
                    if (this.m != null) {
                        this.i.a(this.m);
                    }
                } else {
                    input.c(s);
                }
                i += i2;
            }
            input.a();
            input.c(i);
            peekedId3Bytes = (int) input.b();
            if (!sniffing) {
                input.b(peekedId3Bytes);
            }
        }
        while (true) {
            if (!input.b(this.g.a, 0, 4, validFrameCount > 0)) {
                break;
            }
            this.g.c(0);
            int headerData = this.g.n();
            if (candidateSynchronizedHeaderData == 0 || a(headerData, (long) candidateSynchronizedHeaderData)) {
                int frameSize = com.google.android.exoplayer2.extractor.k.a(headerData);
                if (frameSize != -1) {
                    validFrameCount++;
                    if (validFrameCount != 1) {
                        if (validFrameCount == 4) {
                            break;
                        }
                    }
                    com.google.android.exoplayer2.extractor.k.a(headerData, this.h);
                    candidateSynchronizedHeaderData = headerData;
                    input.c(frameSize - 4);
                }
            }
            int searchedBytes2 = searchedBytes + 1;
            if (searchedBytes != searchLimitBytes) {
                validFrameCount = 0;
                candidateSynchronizedHeaderData = 0;
                if (sniffing) {
                    input.a();
                    input.c(peekedId3Bytes + searchedBytes2);
                    searchedBytes = searchedBytes2;
                } else {
                    input.b(1);
                    searchedBytes = searchedBytes2;
                }
            } else if (sniffing) {
                searchedBytes = searchedBytes2;
                return false;
            } else {
                throw new com.google.android.exoplayer2.k("Searched too many bytes.");
            }
        }
        if (sniffing) {
            input.b(peekedId3Bytes + searchedBytes);
        } else {
            input.a();
        }
        this.l = candidateSynchronizedHeaderData;
        return true;
    }

    private a b(g input) throws IOException, InterruptedException {
        input.c(this.g.a, 0, 4);
        this.g.c(0);
        com.google.android.exoplayer2.extractor.k.a(this.g.n(), this.h);
        return new a(input.c(), this.h.f, input.d());
    }

    private static boolean a(int headerA, long headerB) {
        return ((long) (-128000 & headerA)) == (-128000 & headerB);
    }
}
