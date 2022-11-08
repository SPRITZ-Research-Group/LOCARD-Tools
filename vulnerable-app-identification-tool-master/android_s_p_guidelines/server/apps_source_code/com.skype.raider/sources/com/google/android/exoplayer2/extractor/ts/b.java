package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.a.a.a;
import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.google.android.exoplayer2.extractor.ts.t.d;

public final class b implements g {
    private final j a;
    private final k b;
    private final String c;
    private String d;
    private n e;
    private int f;
    private int g;
    private boolean h;
    private long i;
    private Format j;
    private int k;
    private long l;

    public b() {
        this(null);
    }

    public b(String language) {
        this.a = new j(new byte[8]);
        this.b = new k(this.a.a);
        this.f = 0;
        this.c = language;
    }

    public final void a() {
        this.f = 0;
        this.g = 0;
        this.h = false;
    }

    public final void a(h extractorOutput, d generator) {
        generator.a();
        this.d = generator.c();
        this.e = extractorOutput.a(generator.b());
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.l = pesTimeUs;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(k data) {
        while (data.b() > 0) {
            Object obj;
            switch (this.f) {
                case 0:
                    while (data.b() > 0) {
                        if (this.h) {
                            int g = data.g();
                            if (g == 119) {
                                this.h = false;
                                obj = 1;
                                if (obj == null) {
                                    break;
                                }
                                this.f = 1;
                                this.b.a[0] = (byte) 11;
                                this.b.a[1] = (byte) 119;
                                this.g = 2;
                                break;
                            }
                            this.h = g == 11;
                        } else {
                            this.h = data.g() == 11;
                        }
                    }
                    break;
                case 1:
                    byte[] bArr = this.b.a;
                    int min = Math.min(data.b(), 8 - this.g);
                    data.a(bArr, this.g, min);
                    this.g += min;
                    if (this.g == 8) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        break;
                    }
                    this.a.a(0);
                    a a = com.google.android.exoplayer2.a.a.a(this.a);
                    if (!(this.j != null && a.c == this.j.r && a.b == this.j.s && a.a == this.j.f)) {
                        this.j = Format.a(this.d, a.a, -1, -1, a.c, a.b, null, null, this.c);
                        this.e.a(this.j);
                    }
                    this.k = a.d;
                    this.i = (1000000 * ((long) a.e)) / ((long) this.j.s);
                    this.b.c(0);
                    this.e.a(this.b, 8);
                    this.f = 2;
                    break;
                case 2:
                    int bytesToRead = Math.min(data.b(), this.k - this.g);
                    this.e.a(data, bytesToRead);
                    this.g += bytesToRead;
                    if (this.g != this.k) {
                        break;
                    }
                    this.e.a(this.l, 1, this.k, 0, null);
                    this.l += this.i;
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
