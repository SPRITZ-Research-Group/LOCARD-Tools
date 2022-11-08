package com.facebook.common.i;

import com.facebook.common.internal.h;
import java.io.IOException;
import java.io.InputStream;

public final class d {
    public static long a(InputStream inputStream, long bytesCount) throws IOException {
        h.a((Object) inputStream);
        h.a(bytesCount >= 0);
        long toSkip = bytesCount;
        while (toSkip > 0) {
            long skipped = inputStream.skip(toSkip);
            if (skipped > 0) {
                toSkip -= skipped;
            } else if (inputStream.read() == -1) {
                return bytesCount - toSkip;
            } else {
                toSkip--;
            }
        }
        return bytesCount;
    }
}
