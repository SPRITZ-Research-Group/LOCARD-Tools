package com.facebook.crypto.c;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class d extends FilterInputStream {
    private final byte[] a;
    private final int b;
    private int c;
    private boolean d;

    protected d(InputStream in, int tailLength) {
        super(in);
        this.a = new byte[tailLength];
        this.b = tailLength;
    }

    public final int read() throws IOException {
        byte[] buffer = new byte[1];
        int read = read(buffer, 0, 1);
        while (read == 0) {
            read = read(buffer, 0, 1);
        }
        if (read == -1) {
            return -1;
        }
        return buffer[0] & 255;
    }

    public final int read(byte[] buffer, int offset, int count) throws IOException {
        if (this.d) {
            return -1;
        }
        if (count == 0) {
            return 0;
        }
        int read = 0;
        while (read == 0) {
            int read2;
            int i;
            if (count >= this.c) {
                read2 = this.in.read(buffer, this.c + offset, count - this.c);
                if (read2 == -1) {
                    this.d = true;
                    read = -1;
                } else {
                    if (this.c > 0) {
                        System.arraycopy(this.a, 0, buffer, offset, this.c);
                    }
                    i = this.c + read2;
                    read2 = this.in.read(this.a, 0, this.b);
                    if (read2 == -1) {
                        this.d = true;
                        read2 = 0;
                    }
                    read = a(buffer, i, read2, offset);
                }
            } else {
                read2 = this.c - count;
                System.arraycopy(this.a, 0, buffer, offset, count);
                System.arraycopy(this.a, count, this.a, 0, read2);
                i = this.in.read(this.a, read2, this.b - read2);
                if (i == -1) {
                    System.arraycopy(this.a, 0, this.a, count, read2);
                    System.arraycopy(buffer, offset, this.a, 0, count);
                    this.d = true;
                    read = -1;
                } else {
                    read = a(buffer, count, read2 + i, offset);
                }
            }
        }
        return read;
    }

    private int a(byte[] readBuffer, int bytesInBuffer, int tailBytes, int bufferOffset) {
        int toFill = this.b - tailBytes;
        int tailOffsetInBuffer = Math.max(0, bytesInBuffer - toFill) + bufferOffset;
        int bytesToCopy = Math.min(toFill, bytesInBuffer);
        if (bytesToCopy > 0) {
            if (tailBytes > 0) {
                System.arraycopy(this.a, 0, this.a, bytesToCopy, tailBytes);
            }
            System.arraycopy(readBuffer, tailOffsetInBuffer, this.a, 0, bytesToCopy);
        }
        this.c = bytesToCopy + tailBytes;
        return tailOffsetInBuffer - bufferOffset;
    }

    public final boolean markSupported() {
        return false;
    }

    public final byte[] a() throws IOException {
        if (this.c == this.b) {
            return this.a;
        }
        throw new IOException("Not enough tail data");
    }
}
