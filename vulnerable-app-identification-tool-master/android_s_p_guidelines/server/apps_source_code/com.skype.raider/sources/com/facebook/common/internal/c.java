package com.facebook.common.internal;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class c extends FilterOutputStream {
    private long a = 0;

    public c(OutputStream out) {
        super(out);
    }

    public final long a() {
        return this.a;
    }

    public final void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        this.a += (long) len;
    }

    public final void write(int b) throws IOException {
        this.out.write(b);
        this.a++;
    }

    public final void close() throws IOException {
        this.out.close();
    }
}
