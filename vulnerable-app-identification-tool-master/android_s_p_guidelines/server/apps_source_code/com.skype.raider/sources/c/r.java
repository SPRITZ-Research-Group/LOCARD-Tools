package c;

import java.util.Arrays;

final class r extends f {
    final transient byte[][] f;
    final transient int[] g;

    r(c buffer, int byteCount) {
        super(null);
        v.a(buffer.b, 0, (long) byteCount);
        int offset = 0;
        int segmentCount = 0;
        p s = buffer.a;
        while (offset < byteCount) {
            if (s.c == s.b) {
                throw new AssertionError("s.limit == s.pos");
            }
            offset += s.c - s.b;
            segmentCount++;
            s = s.f;
        }
        this.f = new byte[segmentCount][];
        this.g = new int[(segmentCount * 2)];
        offset = 0;
        segmentCount = 0;
        s = buffer.a;
        while (offset < byteCount) {
            this.f[segmentCount] = s.a;
            offset += s.c - s.b;
            if (offset > byteCount) {
                offset = byteCount;
            }
            this.g[segmentCount] = offset;
            this.g[this.f.length + segmentCount] = s.b;
            s.d = true;
            segmentCount++;
            s = s.f;
        }
    }

    public final String a() {
        return j().a();
    }

    public final String b() {
        return j().b();
    }

    public final String f() {
        return j().f();
    }

    public final f g() {
        return j().g();
    }

    public final f c() {
        return j().c();
    }

    public final f d() {
        return j().d();
    }

    public final f e() {
        return j().e();
    }

    public final f a(int beginIndex, int endIndex) {
        return j().a(beginIndex, endIndex);
    }

    public final byte a(int pos) {
        v.a((long) this.g[this.f.length - 1], (long) pos, 1);
        int segment = b(pos);
        return this.f[segment][(pos - (segment == 0 ? 0 : this.g[segment - 1])) + this.g[this.f.length + segment]];
    }

    private int b(int pos) {
        int i = Arrays.binarySearch(this.g, 0, this.f.length, pos + 1);
        return i >= 0 ? i : i ^ -1;
    }

    public final int h() {
        return this.g[this.f.length - 1];
    }

    public final byte[] i() {
        byte[] result = new byte[this.g[this.f.length - 1]];
        int segmentOffset = 0;
        int segmentCount = this.f.length;
        for (int s = 0; s < segmentCount; s++) {
            int segmentPos = this.g[segmentCount + s];
            int nextSegmentOffset = this.g[s];
            System.arraycopy(this.f[s], segmentPos, result, segmentOffset, nextSegmentOffset - segmentOffset);
            segmentOffset = nextSegmentOffset;
        }
        return result;
    }

    final void a(c buffer) {
        int segmentOffset = 0;
        int segmentCount = this.f.length;
        for (int s = 0; s < segmentCount; s++) {
            int segmentPos = this.g[segmentCount + s];
            int nextSegmentOffset = this.g[s];
            p segment = new p(this.f[s], segmentPos, (segmentPos + nextSegmentOffset) - segmentOffset, true, false);
            if (buffer.a == null) {
                segment.g = segment;
                segment.f = segment;
                buffer.a = segment;
            } else {
                buffer.a.g.a(segment);
            }
            segmentOffset = nextSegmentOffset;
        }
        buffer.b += (long) segmentOffset;
    }

    public final boolean a(int offset, f other, int otherOffset, int byteCount) {
        if (h() - byteCount < 0) {
            return false;
        }
        int s = b(0);
        while (byteCount > 0) {
            int segmentOffset = s == 0 ? 0 : this.g[s - 1];
            int stepSize = Math.min(byteCount, (segmentOffset + (this.g[s] - segmentOffset)) - offset);
            if (!other.a(otherOffset, this.f[s], (offset - segmentOffset) + this.g[this.f.length + s], stepSize)) {
                return false;
            }
            offset += stepSize;
            otherOffset += stepSize;
            byteCount -= stepSize;
            s++;
        }
        return true;
    }

    public final boolean a(int offset, byte[] other, int otherOffset, int byteCount) {
        if (offset < 0 || offset > h() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount) {
            return false;
        }
        int s = b(offset);
        while (byteCount > 0) {
            int segmentOffset = s == 0 ? 0 : this.g[s - 1];
            int stepSize = Math.min(byteCount, (segmentOffset + (this.g[s] - segmentOffset)) - offset);
            if (!v.a(this.f[s], (offset - segmentOffset) + this.g[this.f.length + s], other, otherOffset, stepSize)) {
                return false;
            }
            offset += stepSize;
            otherOffset += stepSize;
            byteCount -= stepSize;
            s++;
        }
        return true;
    }

    private f j() {
        return new f(i());
    }

    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof f) && ((f) o).h() == h() && a(0, (f) o, 0, h());
    }

    public final int hashCode() {
        int result = this.d;
        if (result != 0) {
            return result;
        }
        result = 1;
        int segmentOffset = 0;
        int segmentCount = this.f.length;
        for (int s = 0; s < segmentCount; s++) {
            byte[] segment = this.f[s];
            int segmentPos = this.g[segmentCount + s];
            int nextSegmentOffset = this.g[s];
            for (int i = segmentPos; i < segmentPos + (nextSegmentOffset - segmentOffset); i++) {
                result = (result * 31) + segment[i];
            }
            segmentOffset = nextSegmentOffset;
        }
        this.d = result;
        return result;
    }

    public final String toString() {
        return j().toString();
    }
}
