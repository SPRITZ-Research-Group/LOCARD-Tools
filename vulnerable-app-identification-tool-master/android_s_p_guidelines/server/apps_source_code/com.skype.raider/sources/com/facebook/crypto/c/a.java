package com.facebook.crypto.c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class a extends ByteArrayOutputStream {
    public a(int size) {
        super(size);
    }

    public final byte[] a() throws IOException {
        if (this.buf.length == this.count) {
            return this.buf;
        }
        throw new IOException("Size supplied is too small");
    }
}
