package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.g;
import java.io.IOException;

final class d {
    private final k a = new k(8);
    private int b;

    public final boolean a(g input) throws IOException, InterruptedException {
        long j;
        long inputLength = input.d();
        if (inputLength == -1 || inputLength > 1024) {
            j = 1024;
        } else {
            j = inputLength;
        }
        int bytesToSearch = (int) j;
        input.c(this.a.a, 0, 4);
        long tag = this.a.l();
        this.b = 4;
        while (tag != 440786851) {
            int i = this.b + 1;
            this.b = i;
            if (i == bytesToSearch) {
                return false;
            }
            input.c(this.a.a, 0, 1);
            tag = ((tag << 8) & -256) | ((long) (this.a.a[0] & 255));
        }
        long headerSize = b(input);
        long headerStart = (long) this.b;
        if (headerSize == Long.MIN_VALUE || (inputLength != -1 && headerStart + headerSize >= inputLength)) {
            return false;
        }
        while (((long) this.b) < headerStart + headerSize) {
            if (b(input) == Long.MIN_VALUE) {
                return false;
            }
            long size = b(input);
            if (size < 0 || size > 2147483647L) {
                return false;
            }
            if (size != 0) {
                input.c((int) size);
                this.b = (int) (((long) this.b) + size);
            }
        }
        return ((long) this.b) == headerStart + headerSize;
    }

    private long b(g input) throws IOException, InterruptedException {
        input.c(this.a.a, 0, 1);
        int value = this.a.a[0] & 255;
        if (value == 0) {
            return Long.MIN_VALUE;
        }
        int mask = 128;
        int length = 0;
        while ((value & mask) == 0) {
            mask >>= 1;
            length++;
        }
        value &= mask ^ -1;
        input.c(this.a.a, 1, length);
        for (int i = 0; i < length; i++) {
            value = (value << 8) + (this.a.a[i + 1] & 255);
        }
        this.b += length + 1;
        return (long) value;
    }
}
