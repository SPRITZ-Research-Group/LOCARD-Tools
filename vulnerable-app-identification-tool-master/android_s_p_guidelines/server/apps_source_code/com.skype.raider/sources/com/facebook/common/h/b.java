package com.facebook.common.h;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class b extends FilterInputStream {
    private final byte[] a;
    private int b;
    private int c;

    public b(InputStream inputStream, byte[] tail) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (tail == null) {
            throw new NullPointerException();
        } else {
            this.a = tail;
        }
    }

    public final int read() throws IOException {
        int readResult = this.in.read();
        return readResult != -1 ? readResult : a();
    }

    public final int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    public final int read(byte[] buffer, int offset, int count) throws IOException {
        int readResult = this.in.read(buffer, offset, count);
        if (readResult != -1) {
            return readResult;
        }
        if (count == 0) {
            return 0;
        }
        int bytesRead = 0;
        while (bytesRead < count) {
            int nextByte = a();
            if (nextByte == -1) {
                break;
            }
            buffer[offset + bytesRead] = (byte) nextByte;
            bytesRead++;
        }
        return bytesRead > 0 ? bytesRead : -1;
    }

    public final void reset() throws IOException {
        if (this.in.markSupported()) {
            this.in.reset();
            this.b = this.c;
            return;
        }
        throw new IOException("mark is not supported");
    }

    public final void mark(int readLimit) {
        if (this.in.markSupported()) {
            super.mark(readLimit);
            this.c = this.b;
        }
    }

    private int a() {
        if (this.b >= this.a.length) {
            return -1;
        }
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }
}
