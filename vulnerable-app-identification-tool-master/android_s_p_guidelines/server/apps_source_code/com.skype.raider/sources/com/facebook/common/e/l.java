package com.facebook.common.e;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class l {
    private final int a;
    private final a b;

    public l(a byteArrayPool) {
        this(byteArrayPool, (byte) 0);
    }

    @VisibleForTesting
    private l(a byteArrayPool, byte b) {
        h.a(true);
        this.a = 16384;
        this.b = byteArrayPool;
    }

    public final long a(InputStream from, OutputStream to) throws IOException {
        long count = 0;
        byte[] tmp = (byte[]) this.b.a(this.a);
        while (true) {
            int read = from.read(tmp, 0, this.a);
            if (read == -1) {
                break;
            }
            try {
                to.write(tmp, 0, read);
                count += (long) read;
            } finally {
                this.b.a((Object) tmp);
            }
        }
        return count;
    }
}
