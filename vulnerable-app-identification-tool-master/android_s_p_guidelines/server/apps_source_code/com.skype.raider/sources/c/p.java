package c;

import javax.annotation.Nullable;

final class p {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    p f;
    p g;

    p() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    p(byte[] data, int pos, int limit, boolean shared, boolean owner) {
        this.a = data;
        this.b = pos;
        this.c = limit;
        this.d = shared;
        this.e = owner;
    }

    final p a() {
        this.d = true;
        return new p(this.a, this.b, this.c, true, false);
    }

    final p b() {
        return new p((byte[]) this.a.clone(), this.b, this.c, false, true);
    }

    @Nullable
    public final p c() {
        p result;
        if (this.f != this) {
            result = this.f;
        } else {
            result = null;
        }
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return result;
    }

    public final p a(p segment) {
        segment.g = this;
        segment.f = this.f;
        this.f.g = segment;
        this.f = segment;
        return segment;
    }

    public final void a(p sink, int byteCount) {
        if (sink.e) {
            if (sink.c + byteCount > 8192) {
                if (sink.d) {
                    throw new IllegalArgumentException();
                } else if ((sink.c + byteCount) - sink.b > 8192) {
                    throw new IllegalArgumentException();
                } else {
                    System.arraycopy(sink.a, sink.b, sink.a, 0, sink.c - sink.b);
                    sink.c -= sink.b;
                    sink.b = 0;
                }
            }
            System.arraycopy(this.a, this.b, sink.a, sink.c, byteCount);
            sink.c += byteCount;
            this.b += byteCount;
            return;
        }
        throw new IllegalArgumentException();
    }
}
