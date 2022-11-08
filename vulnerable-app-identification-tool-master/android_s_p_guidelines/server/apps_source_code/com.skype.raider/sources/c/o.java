package c;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

final class o implements e {
    public final c a = new c();
    public final t b;
    boolean c;

    o(t source) {
        if (source == null) {
            throw new NullPointerException("source == null");
        }
        this.b = source;
    }

    public final c b() {
        return this.a;
    }

    public final long read(c sink, long byteCount) throws IOException {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else if (this.a.b == 0 && this.b.read(this.a, 8192) == -1) {
            return -1;
        } else {
            return this.a.read(sink, Math.min(byteCount, this.a.b));
        }
    }

    public final boolean e() throws IOException {
        if (!this.c) {
            return this.a.e() && this.b.read(this.a, 8192) == -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public final void a(long byteCount) throws IOException {
        if (!b(byteCount)) {
            throw new EOFException();
        }
    }

    public final boolean b(long byteCount) throws IOException {
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else {
            while (this.a.b < byteCount) {
                if (this.b.read(this.a, 8192) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    public final byte h() throws IOException {
        a(1);
        return this.a.h();
    }

    public final f d(long byteCount) throws IOException {
        a(byteCount);
        return this.a.d(byteCount);
    }

    public final byte[] s() throws IOException {
        this.a.a(this.b);
        return this.a.s();
    }

    public final byte[] g(long byteCount) throws IOException {
        a(byteCount);
        return this.a.g(byteCount);
    }

    public final void a(byte[] sink) throws IOException {
        try {
            a((long) sink.length);
            this.a.a(sink);
        } catch (EOFException e) {
            int offset = 0;
            while (this.a.b > 0) {
                int read = this.a.a(sink, offset, (int) this.a.b);
                if (read == -1) {
                    throw new AssertionError();
                }
                offset += read;
            }
            throw e;
        }
    }

    public final int read(ByteBuffer sink) throws IOException {
        if (this.a.b == 0 && this.b.read(this.a, 8192) == -1) {
            return -1;
        }
        return this.a.read(sink);
    }

    public final void a(c sink, long byteCount) throws IOException {
        try {
            a(byteCount);
            this.a.a(sink, byteCount);
        } catch (EOFException e) {
            sink.a(this.a);
            throw e;
        }
    }

    public final String a(Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.a.a(this.b);
        return this.a.a(charset);
    }

    public final String r() throws IOException {
        return e(Long.MAX_VALUE);
    }

    public final String e(long limit) throws IOException {
        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0: " + limit);
        }
        long scanLength = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1;
        long newline = a((byte) 10, 0, scanLength);
        if (newline != -1) {
            return this.a.f(newline);
        }
        if (scanLength < Long.MAX_VALUE && b(scanLength) && this.a.c(scanLength - 1) == (byte) 13 && b(1 + scanLength) && this.a.c(scanLength) == (byte) 10) {
            return this.a.f(scanLength);
        }
        c data = new c();
        this.a.a(data, 0, Math.min(32, this.a.b));
        throw new EOFException("\\n not found: limit=" + Math.min(this.a.b, limit) + " content=" + data.p().f() + 8230);
    }

    public final short i() throws IOException {
        a(2);
        return this.a.i();
    }

    public final short l() throws IOException {
        a(2);
        return v.a(this.a.i());
    }

    public final int j() throws IOException {
        a(4);
        return this.a.j();
    }

    public final int m() throws IOException {
        a(4);
        return v.a(this.a.j());
    }

    public final long k() throws IOException {
        a(8);
        return this.a.k();
    }

    public final long n() throws IOException {
        a(1);
        int pos = 0;
        while (b((long) (pos + 1))) {
            byte b = this.a.c((long) pos);
            if ((b < (byte) 48 || b > (byte) 57) && !(pos == 0 && b == (byte) 45)) {
                if (pos == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", new Object[]{Byte.valueOf(b)}));
                }
                return this.a.n();
            }
            pos++;
        }
        return this.a.n();
    }

    public final long o() throws IOException {
        a(1);
        for (int pos = 0; b((long) (pos + 1)); pos++) {
            byte b = this.a.c((long) pos);
            if ((b < (byte) 48 || b > (byte) 57) && ((b < (byte) 97 || b > (byte) 102) && (b < (byte) 65 || b > (byte) 70))) {
                if (pos == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[]{Byte.valueOf(b)}));
                }
                return this.a.o();
            }
        }
        return this.a.o();
    }

    public final void h(long byteCount) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (byteCount > 0) {
            if (this.a.b == 0 && this.b.read(this.a, 8192) == -1) {
                throw new EOFException();
            }
            long toSkip = Math.min(byteCount, this.a.b);
            this.a.h(toSkip);
            byteCount -= toSkip;
        }
    }

    public final long u() throws IOException {
        return a((byte) 0, 0, Long.MAX_VALUE);
    }

    private long a(byte b, long fromIndex, long toIndex) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        } else if (0 < 0 || toIndex < 0) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", new Object[]{Long.valueOf(0), Long.valueOf(toIndex)}));
        } else {
            while (fromIndex < toIndex) {
                long result = this.a.a(b, fromIndex, toIndex);
                if (result != -1) {
                    return result;
                }
                long lastBufferSize = this.a.b;
                if (lastBufferSize >= toIndex || this.b.read(this.a, 8192) == -1) {
                    return -1;
                }
                fromIndex = Math.max(fromIndex, lastBufferSize);
            }
            return -1;
        }
    }

    public final boolean b(f bytes) throws IOException {
        int h = bytes.h();
        if (this.c) {
            throw new IllegalStateException("closed");
        } else if (0 < 0 || h < 0 || bytes.h() + 0 < h) {
            return false;
        } else {
            int i = 0;
            while (i < h) {
                long j = ((long) i) + 0;
                if (!b(1 + j) || this.a.c(j) != bytes.a(i + 0)) {
                    return false;
                }
                i++;
            }
            return true;
        }
    }

    public final InputStream f() {
        return new InputStream(this) {
            final /* synthetic */ o a;

            {
                this.a = this$0;
            }

            public final int read() throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                } else if (this.a.a.b == 0 && this.a.b.read(this.a.a, 8192) == -1) {
                    return -1;
                } else {
                    return this.a.a.h() & 255;
                }
            }

            public final int read(byte[] data, int offset, int byteCount) throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
                v.a((long) data.length, (long) offset, (long) byteCount);
                if (this.a.a.b == 0 && this.a.b.read(this.a.a, 8192) == -1) {
                    return -1;
                }
                return this.a.a.a(data, offset, byteCount);
            }

            public final int available() throws IOException {
                if (!this.a.c) {
                    return (int) Math.min(this.a.a.b, 2147483647L);
                }
                throw new IOException("closed");
            }

            public final void close() throws IOException {
                this.a.close();
            }

            public final String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public final boolean isOpen() {
        return !this.c;
    }

    public final void close() throws IOException {
        if (!this.c) {
            this.c = true;
            this.b.close();
            this.a.t();
        }
    }

    public final u timeout() {
        return this.b.timeout();
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
