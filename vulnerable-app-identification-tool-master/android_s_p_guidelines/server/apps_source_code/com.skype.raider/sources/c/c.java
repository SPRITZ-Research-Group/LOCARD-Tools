package c;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

public final class c implements d, e, Cloneable, ByteChannel {
    private static final byte[] c = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102};
    @Nullable
    p a;
    long b;

    public static final class a implements Closeable {
        public c a;
        public boolean b;
        public long c = -1;
        public byte[] d;
        public int e = -1;
        public int f = -1;
        private p g;

        public final int a() {
            if (this.c == this.a.b) {
                throw new IllegalStateException();
            } else if (this.c == -1) {
                return a(0);
            } else {
                return a(this.c + ((long) (this.f - this.e)));
            }
        }

        public final int a(long offset) {
            if (offset < -1 || offset > this.a.b) {
                throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", new Object[]{Long.valueOf(offset), Long.valueOf(this.a.b)}));
            } else if (offset == -1 || offset == this.a.b) {
                this.g = null;
                this.c = offset;
                this.d = null;
                this.e = -1;
                this.f = -1;
                return -1;
            } else {
                p next;
                long nextOffset;
                long min = 0;
                long max = this.a.b;
                p head = this.a.a;
                p tail = this.a.a;
                if (this.g != null) {
                    long segmentOffset = this.c - ((long) (this.e - this.g.b));
                    if (segmentOffset > offset) {
                        max = segmentOffset;
                        tail = this.g;
                    } else {
                        min = segmentOffset;
                        head = this.g;
                    }
                }
                if (max - offset > offset - min) {
                    next = head;
                    nextOffset = min;
                    while (offset >= ((long) (next.c - next.b)) + nextOffset) {
                        nextOffset += (long) (next.c - next.b);
                        next = next.f;
                    }
                } else {
                    next = tail;
                    nextOffset = max;
                    while (nextOffset > offset) {
                        next = next.g;
                        nextOffset -= (long) (next.c - next.b);
                    }
                }
                if (this.b && next.d) {
                    p unsharedNext = next.b();
                    if (this.a.a == next) {
                        this.a.a = unsharedNext;
                    }
                    next = next.a(unsharedNext);
                    next.g.c();
                }
                this.g = next;
                this.c = offset;
                this.d = next.a;
                this.e = next.b + ((int) (offset - nextOffset));
                this.f = next.c;
                return this.f - this.e;
            }
        }

        public final void close() {
            if (this.a == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.a = null;
            this.g = null;
            this.c = -1;
            this.d = null;
            this.e = -1;
            this.f = -1;
        }
    }

    public final /* synthetic */ d b(String str) throws IOException {
        return a(str);
    }

    public final /* synthetic */ d c(f fVar) throws IOException {
        return a(fVar);
    }

    public final /* synthetic */ d c(byte[] bArr) throws IOException {
        return b(bArr);
    }

    public final /* synthetic */ d c(byte[] bArr, int i, int i2) throws IOException {
        return b(bArr, i, i2);
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return v();
    }

    public final /* synthetic */ d f(int i) throws IOException {
        return d(i);
    }

    public final /* synthetic */ d g(int i) throws IOException {
        return c(i);
    }

    public final /* synthetic */ d h(int i) throws IOException {
        return b(i);
    }

    public final /* synthetic */ d l(long j) throws IOException {
        return k(j);
    }

    public final /* synthetic */ d m(long j) throws IOException {
        return j(j);
    }

    public final /* bridge */ /* synthetic */ d w() throws IOException {
        return this;
    }

    public final long a() {
        return this.b;
    }

    public final c b() {
        return this;
    }

    public final OutputStream c() {
        return new OutputStream(this) {
            final /* synthetic */ c a;

            {
                this.a = this$0;
            }

            public final void write(int b) {
                this.a.b((byte) b);
            }

            public final void write(byte[] data, int offset, int byteCount) {
                this.a.b(data, offset, byteCount);
            }

            public final void flush() {
            }

            public final void close() {
            }

            public final String toString() {
                return this.a + ".outputStream()";
            }
        };
    }

    public final d d() {
        return this;
    }

    public final boolean e() {
        return this.b == 0;
    }

    public final void a(long byteCount) throws EOFException {
        if (this.b < byteCount) {
            throw new EOFException();
        }
    }

    public final boolean b(long byteCount) {
        return this.b >= byteCount;
    }

    public final InputStream f() {
        return new InputStream(this) {
            final /* synthetic */ c a;

            {
                this.a = this$0;
            }

            public final int read() {
                if (this.a.b > 0) {
                    return this.a.h() & 255;
                }
                return -1;
            }

            public final int read(byte[] sink, int offset, int byteCount) {
                return this.a.a(sink, offset, byteCount);
            }

            public final int available() {
                return (int) Math.min(this.a.b, 2147483647L);
            }

            public final void close() {
            }

            public final String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public final c a(c out, long offset, long byteCount) {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        v.a(this.b, offset, byteCount);
        if (byteCount != 0) {
            out.b += byteCount;
            p s = this.a;
            while (offset >= ((long) (s.c - s.b))) {
                offset -= (long) (s.c - s.b);
                s = s.f;
            }
            while (byteCount > 0) {
                p copy = s.a();
                copy.b = (int) (((long) copy.b) + offset);
                copy.c = Math.min(copy.b + ((int) byteCount), copy.c);
                if (out.a == null) {
                    copy.g = copy;
                    copy.f = copy;
                    out.a = copy;
                } else {
                    out.a.g.a(copy);
                }
                byteCount -= (long) (copy.c - copy.b);
                offset = 0;
                s = s.f;
            }
        }
        return this;
    }

    public final long g() {
        long result = this.b;
        if (result == 0) {
            return 0;
        }
        p tail = this.a.g;
        if (tail.c < 8192 && tail.e) {
            result -= (long) (tail.c - tail.b);
        }
        return result;
    }

    public final byte h() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        p segment = this.a;
        int pos = segment.b;
        int limit = segment.c;
        int pos2 = pos + 1;
        byte b = segment.a[pos];
        this.b--;
        if (pos2 == limit) {
            this.a = segment.c();
            q.a(segment);
        } else {
            segment.b = pos2;
        }
        return b;
    }

    public final byte c(long pos) {
        v.a(this.b, pos, 1);
        p s;
        if (this.b - pos > pos) {
            s = this.a;
            while (true) {
                int segmentByteCount = s.c - s.b;
                if (pos < ((long) segmentByteCount)) {
                    return s.a[s.b + ((int) pos)];
                }
                pos -= (long) segmentByteCount;
                s = s.f;
            }
        } else {
            pos -= this.b;
            s = this.a.g;
            while (true) {
                pos += (long) (s.c - s.b);
                if (pos >= 0) {
                    return s.a[s.b + ((int) pos)];
                }
                s = s.g;
            }
        }
    }

    public final short i() {
        if (this.b < 2) {
            throw new IllegalStateException("size < 2: " + this.b);
        }
        p segment = this.a;
        int pos = segment.b;
        int limit = segment.c;
        if (limit - pos < 2) {
            return (short) (((h() & 255) << 8) | (h() & 255));
        }
        byte[] data = segment.a;
        int pos2 = pos + 1;
        pos = pos2 + 1;
        int s = ((data[pos] & 255) << 8) | (data[pos2] & 255);
        this.b -= 2;
        if (pos == limit) {
            this.a = segment.c();
            q.a(segment);
        } else {
            segment.b = pos;
        }
        return (short) s;
    }

    public final int j() {
        if (this.b < 4) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        p segment = this.a;
        int pos = segment.b;
        int limit = segment.c;
        if (limit - pos < 4) {
            return ((((h() & 255) << 24) | ((h() & 255) << 16)) | ((h() & 255) << 8)) | (h() & 255);
        }
        byte[] data = segment.a;
        int pos2 = pos + 1;
        pos = pos2 + 1;
        pos2 = pos + 1;
        pos = pos2 + 1;
        int i = ((((data[pos] & 255) << 24) | ((data[pos2] & 255) << 16)) | ((data[pos] & 255) << 8)) | (data[pos2] & 255);
        this.b -= 4;
        if (pos == limit) {
            this.a = segment.c();
            q.a(segment);
            return i;
        }
        segment.b = pos;
        return i;
    }

    public final long k() {
        if (this.b < 8) {
            throw new IllegalStateException("size < 8: " + this.b);
        }
        p segment = this.a;
        int pos = segment.b;
        int limit = segment.c;
        if (limit - pos < 8) {
            return ((((long) j()) & 4294967295L) << 32) | (((long) j()) & 4294967295L);
        }
        byte[] data = segment.a;
        int pos2 = pos + 1;
        pos = pos2 + 1;
        pos2 = pos + 1;
        pos = pos2 + 1;
        pos2 = pos + 1;
        pos = pos2 + 1;
        pos2 = pos + 1;
        pos = pos2 + 1;
        long v = ((((((((((long) data[pos]) & 255) << 56) | ((((long) data[pos2]) & 255) << 48)) | ((((long) data[pos]) & 255) << 40)) | ((((long) data[pos2]) & 255) << 32)) | ((((long) data[pos]) & 255) << 24)) | ((((long) data[pos2]) & 255) << 16)) | ((((long) data[pos]) & 255) << 8)) | (((long) data[pos2]) & 255);
        this.b -= 8;
        if (pos == limit) {
            this.a = segment.c();
            q.a(segment);
            return v;
        }
        segment.b = pos;
        return v;
    }

    public final short l() {
        return v.a(i());
    }

    public final int m() {
        return v.a(j());
    }

    public final long n() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long value = 0;
        int seen = 0;
        boolean negative = false;
        boolean done = false;
        long overflowDigit = -7;
        do {
            p segment = this.a;
            byte[] data = segment.a;
            int pos = segment.b;
            int limit = segment.c;
            while (pos < limit) {
                int b = data[pos];
                if (b >= (byte) 48 && b <= (byte) 57) {
                    int digit = 48 - b;
                    if (value < -922337203685477580L || (value == -922337203685477580L && ((long) digit) < overflowDigit)) {
                        c buffer = new c().j(value).b(b);
                        if (!negative) {
                            buffer.h();
                        }
                        throw new NumberFormatException("Number too large: " + buffer.q());
                    }
                    value = (10 * value) + ((long) digit);
                } else if (b != 45 || seen != 0) {
                    if (seen != 0) {
                        done = true;
                        if (pos != limit) {
                            this.a = segment.c();
                            q.a(segment);
                        } else {
                            segment.b = pos;
                        }
                        if (!done) {
                            break;
                        }
                    } else {
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(b));
                    }
                } else {
                    negative = true;
                    overflowDigit--;
                }
                pos++;
                seen++;
            }
            if (pos != limit) {
                segment.b = pos;
            } else {
                this.a = segment.c();
                q.a(segment);
            }
            if (!done) {
                break;
            }
        } while (this.a != null);
        this.b -= (long) seen;
        return negative ? value : -value;
    }

    public final long o() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long value = 0;
        int seen = 0;
        boolean done = false;
        do {
            p segment = this.a;
            byte[] data = segment.a;
            int pos = segment.b;
            int limit = segment.c;
            while (pos < limit) {
                int digit;
                byte b = data[pos];
                if (b >= (byte) 48 && b <= (byte) 57) {
                    digit = b - 48;
                } else if (b >= (byte) 97 && b <= (byte) 102) {
                    digit = (b - 97) + 10;
                } else if (b < (byte) 65 || b > (byte) 70) {
                    if (seen != 0) {
                        done = true;
                        if (pos != limit) {
                            this.a = segment.c();
                            q.a(segment);
                        } else {
                            segment.b = pos;
                        }
                        if (!done) {
                            break;
                        }
                    } else {
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                    }
                } else {
                    digit = (b - 65) + 10;
                }
                if ((-1152921504606846976L & value) != 0) {
                    throw new NumberFormatException("Number too large: " + new c().k(value).b((int) b).q());
                }
                value = (value << 4) | ((long) digit);
                pos++;
                seen++;
            }
            if (pos != limit) {
                segment.b = pos;
            } else {
                this.a = segment.c();
                q.a(segment);
            }
            if (!done) {
                break;
            }
        } while (this.a != null);
        this.b -= (long) seen;
        return value;
    }

    public final f p() {
        return new f(s());
    }

    public final f d(long byteCount) throws EOFException {
        return new f(g(byteCount));
    }

    public final void a(c sink, long byteCount) throws EOFException {
        if (this.b < byteCount) {
            sink.write(this, this.b);
            throw new EOFException();
        } else {
            sink.write(this, byteCount);
        }
    }

    public final String q() {
        try {
            return a(this.b, v.a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    private String n(long byteCount) throws EOFException {
        return a(byteCount, v.a);
    }

    public final String a(Charset charset) {
        try {
            return a(this.b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    private String a(long byteCount, Charset charset) throws EOFException {
        v.a(this.b, 0, byteCount);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (byteCount > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        } else if (byteCount == 0) {
            return "";
        } else {
            p s = this.a;
            if (((long) s.b) + byteCount > ((long) s.c)) {
                return new String(g(byteCount), charset);
            }
            String result = new String(s.a, s.b, (int) byteCount, charset);
            s.b = (int) (((long) s.b) + byteCount);
            this.b -= byteCount;
            if (s.b != s.c) {
                return result;
            }
            this.a = s.c();
            q.a(s);
            return result;
        }
    }

    public final String r() throws EOFException {
        return e(Long.MAX_VALUE);
    }

    public final String e(long limit) throws EOFException {
        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0: " + limit);
        }
        long scanLength;
        if (limit == Long.MAX_VALUE) {
            scanLength = Long.MAX_VALUE;
        } else {
            scanLength = limit + 1;
        }
        long newline = a((byte) 10, 0, scanLength);
        if (newline != -1) {
            return f(newline);
        }
        if (scanLength < this.b && c(scanLength - 1) == (byte) 13 && c(scanLength) == (byte) 10) {
            return f(scanLength);
        }
        c data = new c();
        a(data, 0, Math.min(32, this.b));
        throw new EOFException("\\n not found: limit=" + Math.min(this.b, limit) + " content=" + data.p().f() + 8230);
    }

    final String f(long newline) throws EOFException {
        String result;
        if (newline <= 0 || c(newline - 1) != (byte) 13) {
            result = n(newline);
            h(1);
            return result;
        }
        result = n(newline - 1);
        h(2);
        return result;
    }

    public final byte[] s() {
        try {
            return g(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final byte[] g(long byteCount) throws EOFException {
        v.a(this.b, 0, byteCount);
        if (byteCount > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + byteCount);
        }
        byte[] result = new byte[((int) byteCount)];
        a(result);
        return result;
    }

    public final void a(byte[] sink) throws EOFException {
        int offset = 0;
        while (offset < sink.length) {
            int read = a(sink, offset, sink.length - offset);
            if (read == -1) {
                throw new EOFException();
            }
            offset += read;
        }
    }

    public final int a(byte[] sink, int offset, int byteCount) {
        v.a((long) sink.length, (long) offset, (long) byteCount);
        p s = this.a;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(byteCount, s.c - s.b);
        System.arraycopy(s.a, s.b, sink, offset, toCopy);
        s.b += toCopy;
        this.b -= (long) toCopy;
        if (s.b != s.c) {
            return toCopy;
        }
        this.a = s.c();
        q.a(s);
        return toCopy;
    }

    public final int read(ByteBuffer sink) throws IOException {
        p s = this.a;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(sink.remaining(), s.c - s.b);
        sink.put(s.a, s.b, toCopy);
        s.b += toCopy;
        this.b -= (long) toCopy;
        if (s.b != s.c) {
            return toCopy;
        }
        this.a = s.c();
        q.a(s);
        return toCopy;
    }

    public final void t() {
        try {
            h(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final void h(long byteCount) throws EOFException {
        while (byteCount > 0) {
            if (this.a == null) {
                throw new EOFException();
            }
            int toSkip = (int) Math.min(byteCount, (long) (this.a.c - this.a.b));
            this.b -= (long) toSkip;
            byteCount -= (long) toSkip;
            p pVar = this.a;
            pVar.b += toSkip;
            if (this.a.b == this.a.c) {
                p toRecycle = this.a;
                this.a = toRecycle.c();
                q.a(toRecycle);
            }
        }
    }

    public final c a(f byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.a(this);
        return this;
    }

    public final c a(String string) {
        return a(string, 0, string.length());
    }

    public final c a(String string, int beginIndex, int endIndex) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        } else if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + beginIndex);
        } else if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        } else if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        } else {
            int i = beginIndex;
            while (i < endIndex) {
                int c = string.charAt(i);
                if (c < 128) {
                    p tail = e(1);
                    byte[] data = tail.a;
                    int segmentOffset = tail.c - i;
                    int runLimit = Math.min(endIndex, 8192 - segmentOffset);
                    int i2 = i + 1;
                    data[segmentOffset + i] = (byte) c;
                    i = i2;
                    while (i < runLimit) {
                        c = string.charAt(i);
                        if (c >= 128) {
                            break;
                        }
                        i2 = i + 1;
                        data[segmentOffset + i] = (byte) c;
                        i = i2;
                    }
                    int runSize = (i + segmentOffset) - tail.c;
                    tail.c += runSize;
                    this.b += (long) runSize;
                } else if (c < 2048) {
                    b((c >> 6) | 192);
                    b((c & 63) | 128);
                    i++;
                } else if (c < 55296 || c > 57343) {
                    b((c >> 12) | 224);
                    b(((c >> 6) & 63) | 128);
                    b((c & 63) | 128);
                    i++;
                } else {
                    int low;
                    if (i + 1 < endIndex) {
                        low = string.charAt(i + 1);
                    } else {
                        low = 0;
                    }
                    if (c > 56319 || low < 56320 || low > 57343) {
                        b(63);
                        i++;
                    } else {
                        int codePoint = 65536 + (((-55297 & c) << 10) | (-56321 & low));
                        b((codePoint >> 18) | 240);
                        b(((codePoint >> 12) & 63) | 128);
                        b(((codePoint >> 6) & 63) | 128);
                        b((codePoint & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        }
    }

    public final c a(int codePoint) {
        if (codePoint < 128) {
            b(codePoint);
        } else if (codePoint < 2048) {
            b((codePoint >> 6) | 192);
            b((codePoint & 63) | 128);
        } else if (codePoint < 65536) {
            if (codePoint < 55296 || codePoint > 57343) {
                b((codePoint >> 12) | 224);
                b(((codePoint >> 6) & 63) | 128);
                b((codePoint & 63) | 128);
            } else {
                b(63);
            }
        } else if (codePoint <= 1114111) {
            b((codePoint >> 18) | 240);
            b(((codePoint >> 12) & 63) | 128);
            b(((codePoint >> 6) & 63) | 128);
            b((codePoint & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(codePoint));
        }
        return this;
    }

    public final c a(String string, int beginIndex, int endIndex, Charset charset) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        } else if (beginIndex < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + beginIndex);
        } else if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        } else if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(v.a)) {
            return a(string, beginIndex, endIndex);
        } else {
            byte[] data = string.substring(beginIndex, endIndex).getBytes(charset);
            return b(data, 0, data.length);
        }
    }

    public final c b(byte[] source) {
        if (source != null) {
            return b(source, 0, source.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    public final c b(byte[] source, int offset, int byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        v.a((long) source.length, (long) offset, (long) byteCount);
        int limit = offset + byteCount;
        while (offset < limit) {
            p tail = e(1);
            int toCopy = Math.min(limit - offset, 8192 - tail.c);
            System.arraycopy(source, offset, tail.a, tail.c, toCopy);
            offset += toCopy;
            tail.c += toCopy;
        }
        this.b += (long) byteCount;
        return this;
    }

    public final int write(ByteBuffer source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        int byteCount = source.remaining();
        int remaining = byteCount;
        while (remaining > 0) {
            p tail = e(1);
            int toCopy = Math.min(remaining, 8192 - tail.c);
            source.get(tail.a, tail.c, toCopy);
            remaining -= toCopy;
            tail.c += toCopy;
        }
        this.b += (long) byteCount;
        return byteCount;
    }

    public final long a(t source) throws IOException {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long totalBytesRead = 0;
        while (true) {
            long readCount = source.read(this, 8192);
            if (readCount == -1) {
                return totalBytesRead;
            }
            totalBytesRead += readCount;
        }
    }

    public final c b(int b) {
        p tail = e(1);
        byte[] bArr = tail.a;
        int i = tail.c;
        tail.c = i + 1;
        bArr[i] = (byte) b;
        this.b++;
        return this;
    }

    public final c c(int s) {
        p tail = e(2);
        byte[] data = tail.a;
        int i = tail.c;
        int i2 = i + 1;
        data[i] = (byte) ((s >>> 8) & 255);
        i = i2 + 1;
        data[i2] = (byte) (s & 255);
        tail.c = i;
        this.b += 2;
        return this;
    }

    public final c d(int i) {
        p tail = e(4);
        byte[] data = tail.a;
        int i2 = tail.c;
        int i3 = i2 + 1;
        data[i2] = (byte) ((i >>> 24) & 255);
        i2 = i3 + 1;
        data[i3] = (byte) ((i >>> 16) & 255);
        i3 = i2 + 1;
        data[i2] = (byte) ((i >>> 8) & 255);
        i2 = i3 + 1;
        data[i3] = (byte) (i & 255);
        tail.c = i2;
        this.b += 4;
        return this;
    }

    public final c i(long v) {
        p tail = e(8);
        byte[] data = tail.a;
        int i = tail.c;
        int i2 = i + 1;
        data[i] = (byte) ((int) ((v >>> 56) & 255));
        i = i2 + 1;
        data[i2] = (byte) ((int) ((v >>> 48) & 255));
        i2 = i + 1;
        data[i] = (byte) ((int) ((v >>> 40) & 255));
        i = i2 + 1;
        data[i2] = (byte) ((int) ((v >>> 32) & 255));
        i2 = i + 1;
        data[i] = (byte) ((int) ((v >>> 24) & 255));
        i = i2 + 1;
        data[i2] = (byte) ((int) ((v >>> 16) & 255));
        i2 = i + 1;
        data[i] = (byte) ((int) ((v >>> 8) & 255));
        i = i2 + 1;
        data[i2] = (byte) ((int) (v & 255));
        tail.c = i;
        this.b += 8;
        return this;
    }

    public final c j(long v) {
        if (v == 0) {
            return b(48);
        }
        boolean negative = false;
        if (v < 0) {
            v = -v;
            if (v < 0) {
                return a("-9223372036854775808");
            }
            negative = true;
        }
        int width = v < 100000000 ? v < 10000 ? v < 100 ? v < 10 ? 1 : 2 : v < 1000 ? 3 : 4 : v < 1000000 ? v < 100000 ? 5 : 6 : v < 10000000 ? 7 : 8 : v < 1000000000000L ? v < 10000000000L ? v < 1000000000 ? 9 : 10 : v < 100000000000L ? 11 : 12 : v < 1000000000000000L ? v < 10000000000000L ? 13 : v < 100000000000000L ? 14 : 15 : v < 100000000000000000L ? v < 10000000000000000L ? 16 : 17 : v < 1000000000000000000L ? 18 : 19;
        if (negative) {
            width++;
        }
        p tail = e(width);
        byte[] data = tail.a;
        int pos = tail.c + width;
        while (v != 0) {
            pos--;
            data[pos] = c[(int) (v % 10)];
            v /= 10;
        }
        if (negative) {
            data[pos - 1] = (byte) 45;
        }
        tail.c += width;
        this.b += (long) width;
        return this;
    }

    public final c k(long v) {
        if (v == 0) {
            return b(48);
        }
        int width = (Long.numberOfTrailingZeros(Long.highestOneBit(v)) / 4) + 1;
        p tail = e(width);
        byte[] data = tail.a;
        int start = tail.c;
        for (int pos = (tail.c + width) - 1; pos >= start; pos--) {
            data[pos] = c[(int) (15 & v)];
            v >>>= 4;
        }
        tail.c += width;
        this.b += (long) width;
        return this;
    }

    final p e(int minimumCapacity) {
        p pVar;
        if (minimumCapacity <= 0 || minimumCapacity > 8192) {
            throw new IllegalArgumentException();
        } else if (this.a == null) {
            this.a = q.a();
            p pVar2 = this.a;
            p pVar3 = this.a;
            pVar = this.a;
            pVar3.g = pVar;
            pVar2.f = pVar;
            return pVar;
        } else {
            pVar = this.a.g;
            if (pVar.c + minimumCapacity > 8192 || !pVar.e) {
                return pVar.a(q.a());
            }
            return pVar;
        }
    }

    public final void write(c source, long byteCount) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        } else if (source == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            v.a(source.b, 0, byteCount);
            while (byteCount > 0) {
                p pVar;
                int i;
                p a;
                if (byteCount < ((long) (source.a.c - source.a.b))) {
                    p tail = this.a != null ? this.a.g : null;
                    if (tail != null && tail.e) {
                        if ((byteCount + ((long) tail.c)) - ((long) (tail.d ? 0 : tail.b)) <= 8192) {
                            source.a.a(tail, (int) byteCount);
                            source.b -= byteCount;
                            this.b += byteCount;
                            return;
                        }
                    }
                    pVar = source.a;
                    i = (int) byteCount;
                    if (i <= 0 || i > pVar.c - pVar.b) {
                        throw new IllegalArgumentException();
                    }
                    if (i >= 1024) {
                        a = pVar.a();
                    } else {
                        a = q.a();
                        System.arraycopy(pVar.a, pVar.b, a.a, 0, i);
                    }
                    a.c = a.b + i;
                    pVar.b = i + pVar.b;
                    pVar.g.a(a);
                    source.a = a;
                }
                p segmentToMove = source.a;
                long movedByteCount = (long) (segmentToMove.c - segmentToMove.b);
                source.a = segmentToMove.c();
                if (this.a == null) {
                    this.a = segmentToMove;
                    a = this.a;
                    pVar = this.a;
                    p pVar2 = this.a;
                    pVar.g = pVar2;
                    a.f = pVar2;
                } else {
                    pVar = this.a.g.a(segmentToMove);
                    if (pVar.g == pVar) {
                        throw new IllegalStateException();
                    } else if (pVar.g.e) {
                        i = pVar.c - pVar.b;
                        if (i <= (pVar.g.d ? 0 : pVar.g.b) + (8192 - pVar.g.c)) {
                            pVar.a(pVar.g, i);
                            pVar.c();
                            q.a(pVar);
                        }
                    }
                }
                source.b -= movedByteCount;
                this.b += movedByteCount;
                byteCount -= movedByteCount;
            }
        }
    }

    public final long read(c sink, long byteCount) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else if (this.b == 0) {
            return -1;
        } else {
            if (byteCount > this.b) {
                byteCount = this.b;
            }
            sink.write(this, byteCount);
            return byteCount;
        }
    }

    public final long u() {
        return a((byte) 0, 0, Long.MAX_VALUE);
    }

    public final long a(byte b, long fromIndex, long toIndex) {
        if (fromIndex < 0 || toIndex < fromIndex) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", new Object[]{Long.valueOf(this.b), Long.valueOf(fromIndex), Long.valueOf(toIndex)}));
        }
        if (toIndex > this.b) {
            toIndex = this.b;
        }
        if (fromIndex == toIndex) {
            return -1;
        }
        p s = this.a;
        if (s == null) {
            return -1;
        }
        long offset;
        if (this.b - fromIndex >= fromIndex) {
            offset = 0;
            while (true) {
                long nextOffset = offset + ((long) (s.c - s.b));
                if (nextOffset >= fromIndex) {
                    break;
                }
                s = s.f;
                offset = nextOffset;
            }
        } else {
            offset = this.b;
            while (offset > fromIndex) {
                s = s.g;
                offset -= (long) (s.c - s.b);
            }
        }
        while (offset < toIndex) {
            byte[] data = s.a;
            int limit = (int) Math.min((long) s.c, (((long) s.b) + toIndex) - offset);
            for (int pos = (int) ((((long) s.b) + fromIndex) - offset); pos < limit; pos++) {
                if (data[pos] == b) {
                    return ((long) (pos - s.b)) + offset;
                }
            }
            offset += (long) (s.c - s.b);
            fromIndex = offset;
            s = s.f;
        }
        return -1;
    }

    public final boolean b(f bytes) {
        int h = bytes.h();
        if (0 < 0 || h < 0 || this.b - 0 < ((long) h) || bytes.h() + 0 < h) {
            return false;
        }
        for (int i = 0; i < h; i++) {
            if (c(((long) i) + 0) != bytes.a(i + 0)) {
                return false;
            }
        }
        return true;
    }

    public final void flush() {
    }

    public final boolean isOpen() {
        return true;
    }

    public final void close() {
    }

    public final u timeout() {
        return u.NONE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof c)) {
            return false;
        }
        c that = (c) o;
        if (this.b != that.b) {
            return false;
        }
        if (this.b == 0) {
            return true;
        }
        p sa = this.a;
        p sb = that.a;
        int posA = sa.b;
        int posB = sb.b;
        long pos = 0;
        while (pos < this.b) {
            long count = (long) Math.min(sa.c - posA, sb.c - posB);
            int i = 0;
            while (true) {
                int posB2 = posB;
                int posA2 = posA;
                if (((long) i) >= count) {
                    break;
                }
                posA = posA2 + 1;
                posB = posB2 + 1;
                if (sa.a[posA2] != sb.a[posB2]) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public final int hashCode() {
        p s = this.a;
        if (s == null) {
            return 0;
        }
        int result = 1;
        do {
            for (int pos = s.b; pos < s.c; pos++) {
                result = (result * 31) + s.a[pos];
            }
            s = s.f;
        } while (s != this.a);
        return result;
    }

    public final c v() {
        c result = new c();
        if (this.b != 0) {
            result.a = this.a.a();
            p pVar = result.a;
            p pVar2 = result.a;
            p pVar3 = result.a;
            pVar2.g = pVar3;
            pVar.f = pVar3;
            for (p s = this.a.f; s != this.a; s = s.f) {
                result.a.g.a(s.a());
            }
            result.b = this.b;
        }
        return result;
    }

    public final a a(a unsafeCursor) {
        if (unsafeCursor.a != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        unsafeCursor.a = this;
        unsafeCursor.b = true;
        return unsafeCursor;
    }

    public final String toString() {
        if (this.b > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
        }
        f fVar;
        int i = (int) this.b;
        if (i == 0) {
            fVar = f.b;
        } else {
            fVar = new r(this, i);
        }
        return fVar.toString();
    }
}
