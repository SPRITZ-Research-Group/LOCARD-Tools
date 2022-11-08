package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;

public final class l implements g {
    private final k a;
    private final com.google.android.exoplayer2.extractor.k b;
    private final String c;
    private String d;
    private n e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private long j;
    private int k;
    private long l;

    public l() {
        this(null);
    }

    public l(String language) {
        this.f = 0;
        this.a = new k(4);
        this.a.a[0] = (byte) -1;
        this.b = new com.google.android.exoplayer2.extractor.k();
        this.c = language;
    }

    public final void a() {
        this.f = 0;
        this.g = 0;
        this.i = false;
    }

    public final void a(h extractorOutput, d idGenerator) {
        idGenerator.a();
        this.d = idGenerator.c();
        this.e = extractorOutput.a(idGenerator.b());
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.l = pesTimeUs;
    }

    public final void a(k data) {
        while (data.b() > 0) {
            int d;
            switch (this.f) {
                case 0:
                    byte[] bArr = data.a;
                    d = data.d();
                    int c = data.c();
                    int i = d;
                    while (i < c) {
                        boolean z;
                        boolean z2 = (bArr[i] & 255) == 255;
                        if (this.i && (bArr[i] & 224) == 224) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.i = z2;
                        if (z) {
                            data.c(i + 1);
                            this.i = false;
                            this.a.a[1] = bArr[i];
                            this.g = 2;
                            this.f = 1;
                            break;
                        }
                        i++;
                    }
                    data.c(c);
                    break;
                case 1:
                    d = Math.min(data.b(), 4 - this.g);
                    data.a(this.a.a, this.g, d);
                    this.g = d + this.g;
                    if (this.g < 4) {
                        break;
                    }
                    this.a.c(0);
                    if (!com.google.android.exoplayer2.extractor.k.a(this.a.n(), this.b)) {
                        this.g = 0;
                        this.f = 1;
                        break;
                    }
                    this.k = this.b.c;
                    if (!this.h) {
                        this.j = (1000000 * ((long) this.b.g)) / ((long) this.b.d);
                        this.e.a(Format.a(this.d, this.b.b, -1, 4096, this.b.e, this.b.d, null, null, this.c));
                        this.h = true;
                    }
                    this.a.c(0);
                    this.e.a(this.a, 4);
                    this.f = 2;
                    break;
                case 2:
                    d = Math.min(data.b(), this.k - this.g);
                    this.e.a(data, d);
                    this.g = d + this.g;
                    if (this.g < this.k) {
                        break;
                    }
                    this.e.a(this.l, 1, this.k, 0, null);
                    this.l += this.j;
                    this.g = 0;
                    this.f = 0;
                    break;
                default:
                    break;
            }
        }
    }

    public final void b() {
    }
}
