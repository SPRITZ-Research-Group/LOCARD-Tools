package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.a.g;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;

public final class e implements g {
    private final k a = new k(new byte[15]);
    private final String b;
    private String c;
    private n d;
    private int e;
    private int f;
    private int g;
    private long h;
    private Format i;
    private int j;
    private long k;

    public e(String language) {
        this.a.a[0] = Byte.MAX_VALUE;
        this.a.a[1] = (byte) -2;
        this.a.a[2] = Byte.MIN_VALUE;
        this.a.a[3] = (byte) 1;
        this.e = 0;
        this.b = language;
    }

    public final void a() {
        this.e = 0;
        this.f = 0;
        this.g = 0;
    }

    public final void a(h extractorOutput, d idGenerator) {
        idGenerator.a();
        this.c = idGenerator.c();
        this.d = extractorOutput.a(idGenerator.b());
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.k = pesTimeUs;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(k data) {
        while (data.b() > 0) {
            int i;
            switch (this.e) {
                case 0:
                    while (data.b() > 0) {
                        this.g <<= 8;
                        this.g |= data.g();
                        if (this.g == 2147385345) {
                            this.g = 0;
                            i = 1;
                            if (i == 0) {
                                break;
                            }
                            this.f = 4;
                            this.e = 1;
                            break;
                        }
                    }
                    break;
                case 1:
                    byte[] bArr = this.a.a;
                    int min = Math.min(data.b(), 15 - this.f);
                    data.a(bArr, this.f, min);
                    this.f += min;
                    if (this.f == 15) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (i == 0) {
                        break;
                    }
                    bArr = this.a.a;
                    if (this.i == null) {
                        this.i = g.a(bArr, this.c, this.b);
                        this.d.a(this.i);
                    }
                    this.j = g.b(bArr);
                    this.h = (long) ((int) ((1000000 * ((long) g.a(bArr))) / ((long) this.i.s)));
                    this.a.c(0);
                    this.d.a(this.a, 15);
                    this.e = 2;
                    break;
                case 2:
                    int bytesToRead = Math.min(data.b(), this.j - this.f);
                    this.d.a(data, bytesToRead);
                    this.f += bytesToRead;
                    if (this.f != this.j) {
                        break;
                    }
                    this.d.a(this.k, 1, this.j, 0, null);
                    this.k += this.h;
                    this.e = 0;
                    break;
                default:
                    break;
            }
        }
    }

    public final void b() {
    }
}
