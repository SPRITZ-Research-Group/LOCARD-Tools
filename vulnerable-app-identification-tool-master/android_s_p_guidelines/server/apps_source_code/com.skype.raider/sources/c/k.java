package c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class k implements t {
    private final e a;
    private final Inflater b;
    private int c;
    private boolean d;

    k(e source, Inflater inflater) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = source;
            this.b = inflater;
        }
    }

    public final long read(c sink, long byteCount) throws IOException {
        if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (this.d) {
            throw new IllegalStateException("closed");
        } else if (byteCount == 0) {
            return 0;
        } else {
            boolean sourceExhausted;
            do {
                if (this.b.needsInput()) {
                    a();
                    if (this.b.getRemaining() != 0) {
                        throw new IllegalStateException("?");
                    } else if (this.a.e()) {
                        sourceExhausted = true;
                    } else {
                        p pVar = this.a.b().a;
                        this.c = pVar.c - pVar.b;
                        this.b.setInput(pVar.a, pVar.b, this.c);
                        sourceExhausted = false;
                    }
                } else {
                    sourceExhausted = false;
                }
                p tail = sink.e(1);
                int bytesInflated = this.b.inflate(tail.a, tail.c, (int) Math.min(byteCount, (long) (8192 - tail.c)));
                if (bytesInflated > 0) {
                    tail.c += bytesInflated;
                    sink.b += (long) bytesInflated;
                    return (long) bytesInflated;
                }
                try {
                    if (this.b.finished() || this.b.needsDictionary()) {
                        a();
                        if (tail.b == tail.c) {
                            sink.a = tail.c();
                            q.a(tail);
                        }
                        return -1;
                    }
                } catch (DataFormatException e) {
                    throw new IOException(e);
                }
            } while (!sourceExhausted);
            throw new EOFException("source exhausted prematurely");
        }
    }

    private void a() throws IOException {
        if (this.c != 0) {
            int toRelease = this.c - this.b.getRemaining();
            this.c -= toRelease;
            this.a.h((long) toRelease);
        }
    }

    public final u timeout() {
        return this.a.timeout();
    }

    public final void close() throws IOException {
        if (!this.d) {
            this.b.end();
            this.d = true;
            this.a.close();
        }
    }
}
