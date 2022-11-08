package c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class j implements t {
    private int a = 0;
    private final e b;
    private final Inflater c;
    private final k d;
    private final CRC32 e = new CRC32();

    public j(t source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.c = new Inflater(true);
        this.b = l.a(source);
        this.d = new k(this.b, this.c);
    }

    public final long read(c sink, long byteCount) throws IOException {
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (byteCount == 0) {
            return 0;
        } else {
            if (this.a == 0) {
                Object obj;
                long u;
                this.b.a(10);
                byte c = this.b.b().c(3);
                if (((c >> 1) & 1) == 1) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    a(this.b.b(), 0, 10);
                }
                a("ID1ID2", 8075, this.b.i());
                this.b.h(8);
                if (((c >> 2) & 1) == 1) {
                    this.b.a(2);
                    if (obj != null) {
                        a(this.b.b(), 0, 2);
                    }
                    short l = this.b.b().l();
                    this.b.a((long) l);
                    if (obj != null) {
                        a(this.b.b(), 0, (long) l);
                    }
                    this.b.h((long) l);
                }
                if (((c >> 3) & 1) == 1) {
                    u = this.b.u();
                    if (u == -1) {
                        throw new EOFException();
                    }
                    if (obj != null) {
                        a(this.b.b(), 0, u + 1);
                    }
                    this.b.h(u + 1);
                }
                if (((c >> 4) & 1) == 1) {
                    u = this.b.u();
                    if (u == -1) {
                        throw new EOFException();
                    }
                    if (obj != null) {
                        a(this.b.b(), 0, u + 1);
                    }
                    this.b.h(u + 1);
                }
                if (obj != null) {
                    a("FHCRC", this.b.l(), (short) ((int) this.e.getValue()));
                    this.e.reset();
                }
                this.a = 1;
            }
            if (this.a == 1) {
                long offset = sink.b;
                long result = this.d.read(sink, byteCount);
                if (result != -1) {
                    a(sink, offset, result);
                    return result;
                }
                this.a = 2;
            }
            if (this.a == 2) {
                a("CRC", this.b.m(), (int) this.e.getValue());
                a("ISIZE", this.b.m(), (int) this.c.getBytesWritten());
                this.a = 3;
                if (!this.b.e()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    public final u timeout() {
        return this.b.timeout();
    }

    public final void close() throws IOException {
        this.d.close();
    }

    private void a(c buffer, long offset, long byteCount) {
        p s = buffer.a;
        while (offset >= ((long) (s.c - s.b))) {
            offset -= (long) (s.c - s.b);
            s = s.f;
        }
        while (byteCount > 0) {
            int pos = (int) (((long) s.b) + offset);
            int toUpdate = (int) Math.min((long) (s.c - pos), byteCount);
            this.e.update(s.a, pos, toUpdate);
            byteCount -= (long) toUpdate;
            offset = 0;
            s = s.f;
        }
    }

    private static void a(String name, int expected, int actual) throws IOException {
        if (actual != expected) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{name, Integer.valueOf(actual), Integer.valueOf(expected)}));
        }
    }
}
