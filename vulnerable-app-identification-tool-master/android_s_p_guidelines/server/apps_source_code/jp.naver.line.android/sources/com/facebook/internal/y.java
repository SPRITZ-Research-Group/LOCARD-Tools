package com.facebook.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class y extends InputStream {
    final InputStream a;
    final OutputStream b;

    public final boolean markSupported() {
        return false;
    }

    y(InputStream inputStream, OutputStream outputStream) {
        this.a = inputStream;
        this.b = outputStream;
    }

    public final int available() throws IOException {
        return this.a.available();
    }

    public final void close() throws IOException {
        try {
            this.a.close();
        } finally {
            this.b.close();
        }
    }

    public final void mark(int i) {
        throw new UnsupportedOperationException();
    }

    public final int read(byte[] bArr) throws IOException {
        int read = this.a.read(bArr);
        if (read > 0) {
            this.b.write(bArr, 0, read);
        }
        return read;
    }

    public final int read() throws IOException {
        int read = this.a.read();
        if (read >= 0) {
            this.b.write(read);
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        i2 = this.a.read(bArr, i, i2);
        if (i2 > 0) {
            this.b.write(bArr, i, i2);
        }
        return i2;
    }

    public final synchronized void reset() {
        throw new UnsupportedOperationException();
    }

    public final long skip(long j) throws IOException {
        byte[] bArr = new byte[1024];
        long j2 = 0;
        while (j2 < j) {
            int read = read(bArr, 0, (int) Math.min(j - j2, 1024));
            if (read < 0) {
                return j2;
            }
            j2 += (long) read;
        }
        return j2;
    }
}
