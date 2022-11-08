package com.microsoft.a.b;

import java.io.IOException;

public final class e extends b {
    private byte[] a;
    private int b;

    public e() {
        this((byte) 0);
    }

    private e(byte b) {
        this.a = new byte[1024];
        this.b = 0;
    }

    public final void close() throws IOException {
        this.a = null;
        this.b = -1;
    }

    public final void a(byte value) {
        b(1);
        this.a[this.b] = value;
        this.b++;
    }

    public final void a(byte[] buffer, int length) {
        b(length);
        System.arraycopy(buffer, 0, this.a, this.b, length);
        this.b += length;
    }

    private void b(int extraBytes) {
        if (this.a.length < this.b + extraBytes) {
            int newSize = this.a.length + (this.a.length >> 1);
            if (newSize < this.b + extraBytes) {
                newSize = this.b + extraBytes;
            }
            byte[] newBuffer = new byte[newSize];
            System.arraycopy(this.a, 0, newBuffer, 0, this.b);
            this.a = newBuffer;
        }
    }

    public final void a(byte[] buffer) {
        a(buffer, buffer.length);
    }

    public final byte[] a() {
        byte[] bufferToReturn = new byte[this.b];
        System.arraycopy(this.a, 0, bufferToReturn, 0, bufferToReturn.length);
        return bufferToReturn;
    }

    public final int b() throws IOException {
        return this.b;
    }

    public final int a(int offset) throws IOException {
        int i = this.b + offset;
        if (i < 0 || i >= this.a.length) {
            throw new IllegalArgumentException(String.format("Cannot jump to position [%d]. Valid positions are from [%d] to [%d] inclusive.", new Object[]{Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.a.length - 1)}));
        }
        this.b = i;
        return this.b;
    }
}
