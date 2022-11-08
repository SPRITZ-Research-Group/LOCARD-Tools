package com.microsoft.a.b;

import com.microsoft.a.b;
import java.io.EOFException;
import java.io.IOException;

public final class d extends a {
    private final byte[] a;
    private final int b = 0;
    private final int c;
    private int d;

    public d(byte[] buffer, int length) {
        this.a = buffer;
        this.c = length;
        this.d = 0;
    }

    public final void close() throws IOException {
    }

    public final int a(int offset) {
        int newPosition = this.d + offset;
        if (newPosition < 0) {
            throw new b(String.format("Invalid stream position [%s].", new Object[]{Integer.valueOf(newPosition)}));
        } else if (newPosition > this.c) {
            throw new b(String.format("Position [%s] is past the end of the buffer.", new Object[]{Integer.valueOf(newPosition)}));
        } else {
            this.d = newPosition;
            return this.d;
        }
    }

    public final byte a() throws EOFException {
        b(1);
        this.d++;
        return this.a[(this.b + this.d) - 1];
    }

    private void b(int bytesToBeRead) throws EOFException {
        if (this.d + bytesToBeRead > this.c) {
            throw new EOFException(String.format("EOF reached. Trying to read [%d] bytes", new Object[]{Integer.valueOf(bytesToBeRead)}));
        }
    }

    public final int a(byte[] buffer, int length) throws EOFException {
        b(length);
        System.arraycopy(this.a, this.b + this.d, buffer, 0, length);
        this.d += length;
        return length;
    }
}
