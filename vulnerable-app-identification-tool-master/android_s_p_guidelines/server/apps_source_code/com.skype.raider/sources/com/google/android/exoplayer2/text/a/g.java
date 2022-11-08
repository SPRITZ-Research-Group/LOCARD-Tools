package com.google.android.exoplayer2.text.a;

import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.extractor.n;

public final class g {
    public static void a(long presentationTimeUs, k seiBuffer, n[] outputs) {
        while (seiBuffer.b() > 1) {
            int payloadType = a(seiBuffer);
            int payloadSize = a(seiBuffer);
            if (payloadSize == -1 || payloadSize > seiBuffer.b()) {
                seiBuffer.c(seiBuffer.c());
            } else {
                Object obj;
                if (payloadType != 4 || payloadSize < 8) {
                    obj = null;
                } else {
                    int d = seiBuffer.d();
                    int g = seiBuffer.g();
                    int h = seiBuffer.h();
                    int n = seiBuffer.n();
                    int g2 = seiBuffer.g();
                    seiBuffer.c(d);
                    obj = (g == 181 && h == 49 && n == 1195456820 && g2 == 3) ? 1 : null;
                }
                if (obj != null) {
                    seiBuffer.d(8);
                    int ccCount = seiBuffer.g() & 31;
                    seiBuffer.d(1);
                    int sampleLength = ccCount * 3;
                    int sampleStartPosition = seiBuffer.d();
                    for (n output : outputs) {
                        seiBuffer.c(sampleStartPosition);
                        output.a(seiBuffer, sampleLength);
                        output.a(presentationTimeUs, 1, sampleLength, 0, null);
                    }
                    seiBuffer.d(payloadSize - ((ccCount * 3) + 10));
                } else {
                    seiBuffer.d(payloadSize);
                }
            }
        }
    }

    private static int a(k buffer) {
        int value = 0;
        while (buffer.b() != 0) {
            int b = buffer.g();
            value += b;
            if (b != 255) {
                return value;
            }
        }
        return -1;
    }
}
