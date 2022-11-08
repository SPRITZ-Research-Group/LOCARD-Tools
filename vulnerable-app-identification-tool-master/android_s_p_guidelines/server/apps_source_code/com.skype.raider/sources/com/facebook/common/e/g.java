package com.facebook.common.e;

import com.facebook.common.f.c;
import com.facebook.common.internal.h;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class g extends InputStream {
    private final InputStream a;
    private final byte[] b;
    private final c<byte[]> c;
    private int d = 0;
    private int e = 0;
    private boolean f = false;

    public g(InputStream inputStream, byte[] byteArray, c<byte[]> resourceReleaser) {
        this.a = (InputStream) h.a((Object) inputStream);
        this.b = (byte[]) h.a((Object) byteArray);
        this.c = (c) h.a((Object) resourceReleaser);
    }

    public final int read() throws IOException {
        h.b(this.e <= this.d);
        b();
        if (!a()) {
            return -1;
        }
        byte[] bArr = this.b;
        int i = this.e;
        this.e = i + 1;
        return bArr[i] & 255;
    }

    public final int read(byte[] buffer, int offset, int length) throws IOException {
        h.b(this.e <= this.d);
        b();
        if (!a()) {
            return -1;
        }
        int bytesToRead = Math.min(this.d - this.e, length);
        System.arraycopy(this.b, this.e, buffer, offset, bytesToRead);
        this.e += bytesToRead;
        return bytesToRead;
    }

    public final int available() throws IOException {
        h.b(this.e <= this.d);
        b();
        return (this.d - this.e) + this.a.available();
    }

    public final void close() throws IOException {
        if (!this.f) {
            this.f = true;
            this.c.a(this.b);
            super.close();
        }
    }

    public final long skip(long byteCount) throws IOException {
        h.b(this.e <= this.d);
        b();
        int bytesLeftInBuffer = this.d - this.e;
        if (((long) bytesLeftInBuffer) >= byteCount) {
            this.e = (int) (((long) this.e) + byteCount);
            return byteCount;
        }
        this.e = this.d;
        return ((long) bytesLeftInBuffer) + this.a.skip(byteCount - ((long) bytesLeftInBuffer));
    }

    private boolean a() throws IOException {
        if (this.e < this.d) {
            return true;
        }
        int readData = this.a.read(this.b);
        if (readData <= 0) {
            return false;
        }
        this.d = readData;
        this.e = 0;
        return true;
    }

    private void b() throws IOException {
        if (this.f) {
            throw new IOException("stream already closed");
        }
    }

    protected final void finalize() throws Throwable {
        if (!this.f) {
            FLog.e("PooledByteInputStream", "Finalized without closing");
            close();
        }
        super.finalize();
    }
}
