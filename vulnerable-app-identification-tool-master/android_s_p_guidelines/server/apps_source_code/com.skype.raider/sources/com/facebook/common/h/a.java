package com.facebook.common.h;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class a extends FilterInputStream {
    private int a;
    private int b;

    public a(InputStream inputStream, int limit) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        } else {
            this.a = limit;
            this.b = -1;
        }
    }

    public final int read() throws IOException {
        if (this.a == 0) {
            return -1;
        }
        int readByte = this.in.read();
        if (readByte == -1) {
            return readByte;
        }
        this.a--;
        return readByte;
    }

    public final int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        if (this.a == 0) {
            return -1;
        }
        int bytesRead = this.in.read(buffer, byteOffset, Math.min(byteCount, this.a));
        if (bytesRead <= 0) {
            return bytesRead;
        }
        this.a -= bytesRead;
        return bytesRead;
    }

    public final long skip(long byteCount) throws IOException {
        long bytesSkipped = this.in.skip(Math.min(byteCount, (long) this.a));
        this.a = (int) (((long) this.a) - bytesSkipped);
        return bytesSkipped;
    }

    public final int available() throws IOException {
        return Math.min(this.in.available(), this.a);
    }

    public final void mark(int readLimit) {
        if (this.in.markSupported()) {
            this.in.mark(readLimit);
            this.b = this.a;
        }
    }

    public final void reset() throws IOException {
        if (!this.in.markSupported()) {
            throw new IOException("mark is not supported");
        } else if (this.b == -1) {
            throw new IOException("mark not set");
        } else {
            this.in.reset();
            this.a = this.b;
        }
    }
}
