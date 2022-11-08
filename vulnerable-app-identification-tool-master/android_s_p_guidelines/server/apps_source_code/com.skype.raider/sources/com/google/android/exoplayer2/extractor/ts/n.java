package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.d.j;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.q;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.ts.t.d;

public final class n implements t {
    private final g a;
    private final j b = new j(new byte[10]);
    private int c = 0;
    private int d;
    private q e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private long l;

    public n(g reader) {
        this.a = reader;
    }

    public final void a(q timestampAdjuster, h extractorOutput, d idGenerator) {
        this.e = timestampAdjuster;
        this.a.a(extractorOutput, idGenerator);
    }

    public final void a() {
        this.c = 0;
        this.d = 0;
        this.h = false;
        this.a.a();
    }

    public final void a(k data, boolean payloadUnitStartIndicator) {
        if (payloadUnitStartIndicator) {
            switch (this.c) {
                case 3:
                    if (this.j != -1) {
                        new StringBuilder("Unexpected start indicator: expected ").append(this.j).append(" more bytes");
                    }
                    this.a.b();
                    break;
            }
            a(1);
        }
        while (data.b() > 0) {
            switch (this.c) {
                case 0:
                    data.d(data.b());
                    break;
                case 1:
                    if (!a(data, this.b.a, 9)) {
                        break;
                    }
                    Object obj;
                    int c;
                    this.b.a(0);
                    if (this.b.c(24) != 1) {
                        this.j = -1;
                        obj = null;
                    } else {
                        this.b.b(8);
                        c = this.b.c(16);
                        this.b.b(5);
                        this.k = this.b.d();
                        this.b.b(2);
                        this.f = this.b.d();
                        this.g = this.b.d();
                        this.b.b(6);
                        this.i = this.b.c(8);
                        if (c == 0) {
                            this.j = -1;
                        } else {
                            this.j = ((c + 6) - 9) - this.i;
                        }
                        obj = 1;
                    }
                    if (obj != null) {
                        c = 2;
                    } else {
                        c = 0;
                    }
                    a(c);
                    break;
                case 2:
                    if (a(data, this.b.a, Math.min(10, this.i)) && a(data, null, this.i)) {
                        this.b.a(0);
                        this.l = -9223372036854775807L;
                        if (this.f) {
                            this.b.b(4);
                            long c2 = ((long) this.b.c(3)) << 30;
                            this.b.b(1);
                            c2 |= (long) (this.b.c(15) << 15);
                            this.b.b(1);
                            c2 |= (long) this.b.c(15);
                            this.b.b(1);
                            if (!this.h && this.g) {
                                this.b.b(4);
                                long c3 = ((long) this.b.c(3)) << 30;
                                this.b.b(1);
                                c3 |= (long) (this.b.c(15) << 15);
                                this.b.b(1);
                                c3 |= (long) this.b.c(15);
                                this.b.b(1);
                                this.e.a(c3);
                                this.h = true;
                            }
                            this.l = this.e.a(c2);
                        }
                        this.a.a(this.l, this.k);
                        a(3);
                        break;
                    }
                case 3:
                    int padding;
                    int readLength = data.b();
                    if (this.j == -1) {
                        padding = 0;
                    } else {
                        padding = readLength - this.j;
                    }
                    if (padding > 0) {
                        readLength -= padding;
                        data.b(data.d() + readLength);
                    }
                    this.a.a(data);
                    if (this.j == -1) {
                        break;
                    }
                    this.j -= readLength;
                    if (this.j != 0) {
                        break;
                    }
                    this.a.b();
                    a(1);
                    break;
                default:
                    break;
            }
        }
    }

    private void a(int state) {
        this.c = state;
        this.d = 0;
    }

    private boolean a(k source, byte[] target, int targetLength) {
        int bytesToRead = Math.min(source.b(), targetLength - this.d);
        if (bytesToRead <= 0) {
            return true;
        }
        if (target == null) {
            source.d(bytesToRead);
        } else {
            source.a(target, this.d, bytesToRead);
        }
        this.d += bytesToRead;
        if (this.d != targetLength) {
            return false;
        }
        return true;
    }
}
