package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.upstream.f;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

public final class b implements g {
    private static final byte[] a = new byte[4096];
    private final f b;
    private final long c;
    private long d;
    private byte[] e = new byte[65536];
    private int f;
    private int g;

    public b(f dataSource, long position, long length) {
        this.b = dataSource;
        this.d = position;
        this.c = length;
    }

    public final int a(byte[] target, int offset, int length) throws IOException, InterruptedException {
        int bytesRead = d(target, offset, length);
        if (bytesRead == 0) {
            bytesRead = a(target, offset, length, 0, true);
        }
        f(bytesRead);
        return bytesRead;
    }

    public final boolean a(byte[] target, int offset, int length, boolean allowEndOfInput) throws IOException, InterruptedException {
        int bytesRead = d(target, offset, length);
        while (bytesRead < length && bytesRead != -1) {
            bytesRead = a(target, offset, length, bytesRead, allowEndOfInput);
        }
        f(bytesRead);
        return bytesRead != -1;
    }

    public final void b(byte[] target, int offset, int length) throws IOException, InterruptedException {
        a(target, offset, length, false);
    }

    public final int a(int length) throws IOException, InterruptedException {
        int bytesSkipped = d(length);
        if (bytesSkipped == 0) {
            bytesSkipped = a(a, 0, Math.min(length, a.length), 0, true);
        }
        f(bytesSkipped);
        return bytesSkipped;
    }

    public final boolean b(byte[] target, int offset, int length, boolean allowEndOfInput) throws IOException, InterruptedException {
        if (!a(length, allowEndOfInput)) {
            return false;
        }
        System.arraycopy(this.e, this.f - length, target, offset, length);
        return true;
    }

    public final void c(byte[] target, int offset, int length) throws IOException, InterruptedException {
        b(target, offset, length, false);
    }

    public final void c(int length) throws IOException, InterruptedException {
        a(length, false);
    }

    public final void a() {
        this.f = 0;
    }

    public final long b() {
        return this.d + ((long) this.f);
    }

    public final long c() {
        return this.d;
    }

    public final long d() {
        return this.c;
    }

    private int d(int length) {
        int bytesSkipped = Math.min(this.g, length);
        e(bytesSkipped);
        return bytesSkipped;
    }

    private int d(byte[] target, int offset, int length) {
        if (this.g == 0) {
            return 0;
        }
        int peekBytes = Math.min(this.g, length);
        System.arraycopy(this.e, 0, target, offset, peekBytes);
        e(peekBytes);
        return peekBytes;
    }

    private void e(int bytesConsumed) {
        this.g -= bytesConsumed;
        this.f = 0;
        byte[] newPeekBuffer = this.e;
        if (this.g < this.e.length - 524288) {
            newPeekBuffer = new byte[(this.g + 65536)];
        }
        System.arraycopy(this.e, bytesConsumed, newPeekBuffer, 0, this.g);
        this.e = newPeekBuffer;
    }

    private int a(byte[] target, int offset, int length, int bytesAlreadyRead, boolean allowEndOfInput) throws InterruptedException, IOException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int bytesRead = this.b.a(target, offset + bytesAlreadyRead, length - bytesAlreadyRead);
        if (bytesRead != -1) {
            return bytesAlreadyRead + bytesRead;
        }
        if (bytesAlreadyRead == 0 && allowEndOfInput) {
            return -1;
        }
        throw new EOFException();
    }

    private void f(int bytesRead) {
        if (bytesRead != -1) {
            this.d += (long) bytesRead;
        }
    }

    public final void b(int length) throws IOException, InterruptedException {
        int d = d(length);
        while (d < length && d != -1) {
            d = a(a, -d, Math.min(length, a.length + d), d, false);
        }
        f(d);
    }

    private boolean a(int length, boolean allowEndOfInput) throws IOException, InterruptedException {
        int i = this.f + length;
        if (i > this.e.length) {
            this.e = Arrays.copyOf(this.e, s.a(this.e.length * 2, 65536 + i, i + 524288));
        }
        int bytesPeeked = Math.min(this.g - this.f, length);
        while (bytesPeeked < length) {
            bytesPeeked = a(this.e, this.f, length, bytesPeeked, allowEndOfInput);
            if (bytesPeeked == -1) {
                return false;
            }
        }
        this.f += length;
        this.g = Math.max(this.g, this.f);
        return true;
    }
}
