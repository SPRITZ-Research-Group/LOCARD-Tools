package com.google.android.exoplayer2.extractor.b;

import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.g;
import java.io.IOException;
import java.util.Arrays;

final class d {
    private final e a = new e();
    private final k b = new k(new byte[65025], 0);
    private int c = -1;
    private int d;
    private boolean e;

    d() {
    }

    public final void a() {
        this.a.a();
        this.b.a();
        this.c = -1;
        this.e = false;
    }

    public final boolean a(g input) throws IOException, InterruptedException {
        a.b(input != null);
        if (this.e) {
            this.e = false;
            this.b.a();
        }
        while (!this.e) {
            int segmentIndex;
            if (this.c < 0) {
                if (!this.a.a(input, true)) {
                    return false;
                }
                segmentIndex = 0;
                int bytesToSkip = this.a.h;
                if ((this.a.b & 1) == 1 && this.b.c() == 0) {
                    bytesToSkip += a(0);
                    segmentIndex = this.d + 0;
                }
                input.b(bytesToSkip);
                this.c = segmentIndex;
            }
            int size = a(this.c);
            segmentIndex = this.c + this.d;
            if (size > 0) {
                boolean z;
                if (this.b.e() < this.b.c() + size) {
                    this.b.a = Arrays.copyOf(this.b.a, this.b.c() + size);
                }
                input.b(this.b.a, this.b.c(), size);
                this.b.b(this.b.c() + size);
                if (this.a.j[segmentIndex - 1] != 255) {
                    z = true;
                } else {
                    z = false;
                }
                this.e = z;
            }
            if (segmentIndex == this.a.g) {
                segmentIndex = -1;
            }
            this.c = segmentIndex;
        }
        return true;
    }

    public final e b() {
        return this.a;
    }

    public final k c() {
        return this.b;
    }

    public final void d() {
        if (this.b.a.length != 65025) {
            this.b.a = Arrays.copyOf(this.b.a, Math.max(65025, this.b.c()));
        }
    }

    private int a(int startSegmentIndex) {
        this.d = 0;
        int size = 0;
        while (this.d + startSegmentIndex < this.a.g) {
            int[] iArr = this.a.j;
            int i = this.d;
            this.d = i + 1;
            int segmentLength = iArr[i + startSegmentIndex];
            size += segmentLength;
            if (segmentLength != 255) {
                break;
            }
        }
        return size;
    }
}
