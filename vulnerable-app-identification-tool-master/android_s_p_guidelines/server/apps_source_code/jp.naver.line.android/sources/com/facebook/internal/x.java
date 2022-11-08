package com.facebook.internal;

import java.io.IOException;
import java.io.OutputStream;

final class x extends OutputStream {
    final OutputStream a;
    final ab b;

    x(OutputStream outputStream, ab abVar) {
        this.a = outputStream;
        this.b = abVar;
    }

    public final void close() throws IOException {
        try {
            this.a.close();
        } finally {
            this.b.a();
        }
    }

    public final void flush() throws IOException {
        this.a.flush();
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.a.write(bArr, i, i2);
    }

    public final void write(byte[] bArr) throws IOException {
        this.a.write(bArr);
    }

    public final void write(int i) throws IOException {
        this.a.write(i);
    }
}
