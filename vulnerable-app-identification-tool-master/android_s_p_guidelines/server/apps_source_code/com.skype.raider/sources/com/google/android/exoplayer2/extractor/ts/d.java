package com.google.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.b;
import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.e;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.n;
import com.skype.Defines;
import java.util.Arrays;
import java.util.Collections;

public final class d implements g {
    private static final byte[] a = new byte[]{(byte) 73, (byte) 68, (byte) 51};
    private final boolean b;
    private final j c;
    private final k d;
    private final String e;
    private String f;
    private n g;
    private n h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private long n;
    private int o;
    private long p;
    private n q;
    private long r;

    public d() {
        this(true, null);
    }

    public d(boolean exposeId3, String language) {
        this.c = new j(new byte[7]);
        this.d = new k(Arrays.copyOf(a, 10));
        c();
        this.b = exposeId3;
        this.e = language;
    }

    public final void a() {
        c();
    }

    public final void a(h extractorOutput, com.google.android.exoplayer2.extractor.ts.t.d idGenerator) {
        idGenerator.a();
        this.f = idGenerator.c();
        this.g = extractorOutput.a(idGenerator.b());
        if (this.b) {
            idGenerator.a();
            this.h = extractorOutput.a(idGenerator.b());
            this.h.a(Format.a(idGenerator.c(), "application/id3", null));
            return;
        }
        this.h = new e();
    }

    public final void a(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.p = pesTimeUs;
    }

    public final void a(k data) {
        while (data.b() > 0) {
            int d;
            int i;
            switch (this.i) {
                case 0:
                    byte[] bArr = data.a;
                    d = data.d();
                    int c = data.c();
                    i = d;
                    while (i < c) {
                        d = i + 1;
                        i = bArr[i] & 255;
                        if (this.k == 512 && i >= 240 && i != 255) {
                            boolean z;
                            if ((i & 1) == 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            this.l = z;
                            this.i = 2;
                            this.j = 0;
                            data.c(d);
                            break;
                        }
                        switch (i | this.k) {
                            case 329:
                                this.k = 768;
                                i = d;
                                break;
                            case 511:
                                this.k = 512;
                                i = d;
                                break;
                            case 836:
                                this.k = 1024;
                                i = d;
                                break;
                            case 1075:
                                this.i = 1;
                                this.j = a.length;
                                this.o = 0;
                                this.d.c(0);
                                break;
                            default:
                                if (this.k == Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) {
                                    i = d;
                                    break;
                                }
                                this.k = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
                                i = d - 1;
                                break;
                        }
                    }
                    d = i;
                    data.c(d);
                    break;
                case 1:
                    if (!a(data, this.d.a, 10)) {
                        break;
                    }
                    this.h.a(this.d, 10);
                    this.d.c(6);
                    a(this.h, 0, 10, this.d.s() + 10);
                    break;
                case 2:
                    if (!a(data, this.c.a, this.l ? 7 : 5)) {
                        break;
                    }
                    this.c.a(0);
                    if (this.m) {
                        this.c.b(10);
                    } else {
                        i = this.c.c(2) + 1;
                        if (i != 2) {
                            new StringBuilder("Detected audio object type: ").append(i).append(", but assuming AAC LC.");
                            i = 2;
                        }
                        d = this.c.c(4);
                        this.c.b(1);
                        byte[] a = b.a(i, d, this.c.c(3));
                        Pair a2 = b.a(a);
                        Format a3 = Format.a(this.f, "audio/mp4a-latm", -1, -1, ((Integer) a2.second).intValue(), ((Integer) a2.first).intValue(), Collections.singletonList(a), null, this.e);
                        this.n = 1024000000 / ((long) a3.s);
                        this.g.a(a3);
                        this.m = true;
                    }
                    this.c.b(4);
                    int c2 = (this.c.c(13) - 2) - 5;
                    if (this.l) {
                        c2 -= 2;
                    }
                    a(this.g, this.n, 0, c2);
                    break;
                case 3:
                    i = Math.min(data.b(), this.o - this.j);
                    this.q.a(data, i);
                    this.j = i + this.j;
                    if (this.j != this.o) {
                        break;
                    }
                    this.q.a(this.p, 1, this.o, 0, null);
                    this.p += this.r;
                    c();
                    break;
                default:
                    break;
            }
        }
    }

    public final void b() {
    }

    private boolean a(k source, byte[] target, int targetLength) {
        int bytesToRead = Math.min(source.b(), targetLength - this.j);
        source.a(target, this.j, bytesToRead);
        this.j += bytesToRead;
        return this.j == targetLength;
    }

    private void c() {
        this.i = 0;
        this.j = 0;
        this.k = Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE;
    }

    private void a(n outputToUse, long currentSampleDuration, int priorReadBytes, int sampleSize) {
        this.i = 3;
        this.j = priorReadBytes;
        this.q = outputToUse;
        this.r = currentSampleDuration;
        this.o = sampleSize;
    }
}
