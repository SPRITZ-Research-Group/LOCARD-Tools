package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.ts.t.d;

public final class q implements t {
    private final p a;
    private final k b = new k(32);
    private int c;
    private int d;
    private boolean e;
    private boolean f;

    public q(p reader) {
        this.a = reader;
    }

    public final void a(com.google.android.exoplayer2.d.q timestampAdjuster, h extractorOutput, d idGenerator) {
        this.a.a(timestampAdjuster, extractorOutput, idGenerator);
        this.f = true;
    }

    public final void a() {
        this.f = true;
    }

    public final void a(k data, boolean payloadUnitStartIndicator) {
        int payloadStartPosition = -1;
        if (payloadUnitStartIndicator) {
            payloadStartPosition = data.d() + data.g();
        }
        if (this.f) {
            if (payloadUnitStartIndicator) {
                this.f = false;
                data.c(payloadStartPosition);
                this.d = 0;
            } else {
                return;
            }
        }
        while (data.b() > 0) {
            if (this.d < 3) {
                if (this.d == 0) {
                    int tableId = data.g();
                    data.c(data.d() - 1);
                    if (tableId == 255) {
                        this.f = true;
                        return;
                    }
                }
                int headerBytesToRead = Math.min(data.b(), 3 - this.d);
                data.a(this.b.a, this.d, headerBytesToRead);
                this.d += headerBytesToRead;
                if (this.d == 3) {
                    this.b.a(3);
                    this.b.d(1);
                    int secondHeaderByte = this.b.g();
                    int thirdHeaderByte = this.b.g();
                    this.e = (secondHeaderByte & 128) != 0;
                    this.c = (((secondHeaderByte & 15) << 8) | thirdHeaderByte) + 3;
                    if (this.b.e() < this.c) {
                        byte[] bytes = this.b.a;
                        this.b.a(Math.min(4098, Math.max(this.c, bytes.length * 2)));
                        System.arraycopy(bytes, 0, this.b.a, 0, 3);
                    }
                }
            } else {
                int bodyBytesToRead = Math.min(data.b(), this.c - this.d);
                data.a(this.b.a, this.d, bodyBytesToRead);
                this.d += bodyBytesToRead;
                if (this.d != this.c) {
                    continue;
                } else {
                    if (!this.e) {
                        this.b.a(this.c);
                    } else if (s.a(this.b.a, this.c, -1) != 0) {
                        this.f = true;
                        return;
                    } else {
                        this.b.a(this.c - 4);
                    }
                    this.a.a(this.b);
                    this.d = 0;
                }
            }
        }
    }
}
