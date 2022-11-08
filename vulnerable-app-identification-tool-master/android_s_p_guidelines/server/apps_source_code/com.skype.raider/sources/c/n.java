package c;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

final class n implements d {
    public final c a = new c();
    public final s b;
    boolean c;

    n(s sink) {
        if (sink == null) {
            throw new NullPointerException("sink == null");
        }
        this.b = sink;
    }

    public final c b() {
        return this.a;
    }

    public final void write(c source, long byteCount) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.write(source, byteCount);
        w();
    }

    public final d c(f byteString) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(byteString);
        return w();
    }

    public final d b(String string) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(string);
        return w();
    }

    public final d c(byte[] source) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(source);
        return w();
    }

    public final d c(byte[] source, int offset, int byteCount) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(source, offset, byteCount);
        return w();
    }

    public final int write(ByteBuffer source) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        int result = this.a.write(source);
        w();
        return result;
    }

    public final long a(t source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read(this.a, 8192);
            if (readCount == -1) {
                return totalBytesRead;
            }
            totalBytesRead += readCount;
            w();
        }
    }

    public final d h(int b) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(b);
        return w();
    }

    public final d g(int s) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(s);
        return w();
    }

    public final d f(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.d(i);
        return w();
    }

    public final d m(long v) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.j(v);
        return w();
    }

    public final d l(long v) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.k(v);
        return w();
    }

    public final d w() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long byteCount = this.a.g();
        if (byteCount > 0) {
            this.b.write(this.a, byteCount);
        }
        return this;
    }

    public final d d() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long byteCount = this.a.b;
        if (byteCount > 0) {
            this.b.write(this.a, byteCount);
        }
        return this;
    }

    public final OutputStream c() {
        return new OutputStream(this) {
            final /* synthetic */ n a;

            {
                this.a = this$0;
            }

            public final void write(int b) throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
                this.a.a.b((byte) b);
                this.a.w();
            }

            public final void write(byte[] data, int offset, int byteCount) throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
                this.a.a.b(data, offset, byteCount);
                this.a.w();
            }

            public final void flush() throws IOException {
                if (!this.a.c) {
                    this.a.flush();
                }
            }

            public final void close() throws IOException {
                this.a.close();
            }

            public final String toString() {
                return this.a + ".outputStream()";
            }
        };
    }

    public final void flush() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0) {
            this.b.write(this.a, this.a.b);
        }
        this.b.flush();
    }

    public final boolean isOpen() {
        return !this.c;
    }

    public final void close() throws IOException {
        if (!this.c) {
            Throwable thrown = null;
            try {
                if (this.a.b > 0) {
                    this.b.write(this.a, this.a.b);
                }
            } catch (Throwable th) {
                thrown = th;
            }
            try {
                this.b.close();
            } catch (Throwable e) {
                if (thrown == null) {
                    thrown = e;
                }
            }
            this.c = true;
            if (thrown != null) {
                v.a(thrown);
            }
        }
    }

    public final u timeout() {
        return this.b.timeout();
    }

    public final String toString() {
        return "buffer(" + this.b + ")";
    }
}
