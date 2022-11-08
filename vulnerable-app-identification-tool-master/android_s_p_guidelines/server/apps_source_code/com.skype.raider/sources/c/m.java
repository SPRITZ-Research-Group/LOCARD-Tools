package c;

import java.io.IOException;

public final class m {
    final long a;
    final c b = new c();
    boolean c;
    boolean d;
    private final s e = new a(this);
    private final t f = new b(this);

    final class a implements s {
        final u a = new u();
        final /* synthetic */ m b;

        a(m this$0) {
            this.b = this$0;
        }

        public final void write(c source, long byteCount) throws IOException {
            synchronized (this.b.b) {
                if (this.b.c) {
                    throw new IllegalStateException("closed");
                }
                while (byteCount > 0) {
                    if (this.b.d) {
                        throw new IOException("source is closed");
                    }
                    long bufferSpaceAvailable = this.b.a - this.b.b.b;
                    if (bufferSpaceAvailable == 0) {
                        this.a.waitUntilNotified(this.b.b);
                    } else {
                        long bytesToWrite = Math.min(bufferSpaceAvailable, byteCount);
                        this.b.b.write(source, bytesToWrite);
                        byteCount -= bytesToWrite;
                        this.b.b.notifyAll();
                    }
                }
            }
        }

        public final void flush() throws IOException {
            synchronized (this.b.b) {
                if (this.b.c) {
                    throw new IllegalStateException("closed");
                } else if (!this.b.d || this.b.b.b <= 0) {
                } else {
                    throw new IOException("source is closed");
                }
            }
        }

        public final void close() throws IOException {
            synchronized (this.b.b) {
                if (this.b.c) {
                } else if (!this.b.d || this.b.b.b <= 0) {
                    this.b.c = true;
                    this.b.b.notifyAll();
                } else {
                    throw new IOException("source is closed");
                }
            }
        }

        public final u timeout() {
            return this.a;
        }
    }

    final class b implements t {
        final u a = new u();
        final /* synthetic */ m b;

        b(m this$0) {
            this.b = this$0;
        }

        public final long read(c sink, long byteCount) throws IOException {
            long result;
            synchronized (this.b.b) {
                if (this.b.d) {
                    throw new IllegalStateException("closed");
                }
                while (this.b.b.b == 0) {
                    if (this.b.c) {
                        result = -1;
                        break;
                    }
                    this.a.waitUntilNotified(this.b.b);
                }
                result = this.b.b.read(sink, byteCount);
                this.b.b.notifyAll();
            }
            return result;
        }

        public final void close() throws IOException {
            synchronized (this.b.b) {
                this.b.d = true;
                this.b.b.notifyAll();
            }
        }

        public final u timeout() {
            return this.a;
        }
    }

    public m() {
        if (8192 < 1) {
            throw new IllegalArgumentException("maxBufferSize < 1: 8192");
        }
        this.a = 8192;
    }

    public final t a() {
        return this.f;
    }

    public final s b() {
        return this.e;
    }
}
