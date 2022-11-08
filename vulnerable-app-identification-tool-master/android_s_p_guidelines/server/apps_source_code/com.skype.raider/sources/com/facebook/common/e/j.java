package com.facebook.common.e;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class j extends InputStream {
    @VisibleForTesting
    final h a;
    @VisibleForTesting
    int b;
    @VisibleForTesting
    int c;

    public j(h pooledByteBuffer) {
        boolean z;
        if (pooledByteBuffer.d()) {
            z = false;
        } else {
            z = true;
        }
        h.a(z);
        this.a = (h) h.a((Object) pooledByteBuffer);
        this.b = 0;
        this.c = 0;
    }

    public final int available() {
        return this.a.a() - this.b;
    }

    public final void mark(int readlimit) {
        this.c = this.b;
    }

    public final boolean markSupported() {
        return true;
    }

    public final int read() {
        if (available() <= 0) {
            return -1;
        }
        h hVar = this.a;
        int i = this.b;
        this.b = i + 1;
        return hVar.a(i) & 255;
    }

    public final int read(byte[] buffer) {
        return read(buffer, 0, buffer.length);
    }

    public final int read(byte[] buffer, int offset, int length) {
        if (offset < 0 || length < 0 || offset + length > buffer.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + buffer.length + "; regionStart=" + offset + "; regionLength=" + length);
        }
        int available = available();
        if (available <= 0) {
            return -1;
        }
        if (length <= 0) {
            return 0;
        }
        int numToRead = Math.min(available, length);
        this.a.a(this.b, buffer, offset, numToRead);
        this.b += numToRead;
        return numToRead;
    }

    public final void reset() {
        this.b = this.c;
    }

    public final long skip(long byteCount) {
        h.a(byteCount >= 0);
        int skipped = Math.min((int) byteCount, available());
        this.b += skipped;
        return (long) skipped;
    }
}
