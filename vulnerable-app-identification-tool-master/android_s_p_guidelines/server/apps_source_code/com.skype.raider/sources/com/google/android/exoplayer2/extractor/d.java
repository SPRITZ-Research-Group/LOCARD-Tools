package com.google.android.exoplayer2.extractor;

import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.k;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.h;
import com.skype.android.video.hw.extension.SliqConstants;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public final class d implements n {
    private final com.google.android.exoplayer2.upstream.b a;
    private final int b;
    private final b c = new b();
    private final LinkedBlockingDeque<com.google.android.exoplayer2.upstream.a> d = new LinkedBlockingDeque();
    private final a e = new a();
    private final k f = new k(32);
    private final AtomicInteger g = new AtomicInteger();
    private long h;
    private Format i;
    private boolean j;
    private Format k;
    private long l;
    private long m;
    private com.google.android.exoplayer2.upstream.a n;
    private int o = this.b;
    private boolean p;
    private c q;

    private static final class a {
        public int a;
        public long b;
        public long c;
        public byte[] d;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    private static final class b {
        private int a = Constants.ONE_SECOND;
        private int[] b = new int[this.a];
        private long[] c = new long[this.a];
        private int[] d = new int[this.a];
        private int[] e = new int[this.a];
        private long[] f = new long[this.a];
        private byte[][] g = new byte[this.a][];
        private Format[] h = new Format[this.a];
        private int i;
        private int j;
        private int k;
        private int l;
        private long m = Long.MIN_VALUE;
        private long n = Long.MIN_VALUE;
        private boolean o = true;
        private boolean p = true;
        private Format q;
        private int r;

        public final void a() {
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.i = 0;
            this.o = true;
        }

        public final void b() {
            this.m = Long.MIN_VALUE;
            this.n = Long.MIN_VALUE;
        }

        public final int c() {
            return this.j + this.i;
        }

        public final synchronized boolean d() {
            return this.i == 0;
        }

        public final synchronized Format e() {
            return this.p ? null : this.q;
        }

        public final synchronized long f() {
            return Math.max(this.m, this.n);
        }

        public final synchronized int a(h formatHolder, DecoderInputBuffer buffer, boolean formatRequired, boolean loadingFinished, Format downstreamFormat, a extrasHolder) {
            int i = -4;
            synchronized (this) {
                if (this.i == 0) {
                    if (loadingFinished) {
                        buffer.a_(4);
                    } else if (this.q == null || (!formatRequired && this.q == downstreamFormat)) {
                        i = -3;
                    } else {
                        formatHolder.a = this.q;
                        i = -5;
                    }
                } else if (formatRequired || this.h[this.k] != downstreamFormat) {
                    formatHolder.a = this.h[this.k];
                    i = -5;
                } else if (buffer.f()) {
                    i = -3;
                } else {
                    long j;
                    buffer.c = this.f[this.k];
                    buffer.a_(this.e[this.k]);
                    extrasHolder.a = this.d[this.k];
                    extrasHolder.b = this.c[this.k];
                    extrasHolder.d = this.g[this.k];
                    this.m = Math.max(this.m, buffer.c);
                    this.i--;
                    this.k++;
                    this.j++;
                    if (this.k == this.a) {
                        this.k = 0;
                    }
                    if (this.i > 0) {
                        j = this.c[this.k];
                    } else {
                        j = extrasHolder.b + ((long) extrasHolder.a);
                    }
                    extrasHolder.c = j;
                }
            }
            return i;
        }

        public final synchronized long g() {
            long j;
            if (this.i == 0) {
                j = -1;
            } else {
                int lastSampleIndex = ((this.k + this.i) - 1) % this.a;
                this.k = (this.k + this.i) % this.a;
                this.j += this.i;
                this.i = 0;
                j = this.c[lastSampleIndex] + ((long) this.d[lastSampleIndex]);
            }
            return j;
        }

        public final synchronized long a(long timeUs, boolean allowTimeBeyondBuffer) {
            long j = -1;
            synchronized (this) {
                if (this.i != 0 && timeUs >= this.f[this.k]) {
                    if (timeUs <= this.n || allowTimeBeyondBuffer) {
                        int sampleCount = 0;
                        int sampleCountToKeyframe = -1;
                        int searchIndex = this.k;
                        while (searchIndex != this.l && this.f[searchIndex] <= timeUs) {
                            if ((this.e[searchIndex] & 1) != 0) {
                                sampleCountToKeyframe = sampleCount;
                            }
                            searchIndex = (searchIndex + 1) % this.a;
                            sampleCount++;
                        }
                        if (sampleCountToKeyframe != -1) {
                            this.k = (this.k + sampleCountToKeyframe) % this.a;
                            this.j += sampleCountToKeyframe;
                            this.i -= sampleCountToKeyframe;
                            j = this.c[this.k];
                        }
                    }
                }
            }
            return j;
        }

        public final synchronized boolean a(Format format) {
            boolean z = false;
            synchronized (this) {
                if (format == null) {
                    this.p = true;
                } else {
                    this.p = false;
                    if (!s.a((Object) format, this.q)) {
                        this.q = format;
                        z = true;
                    }
                }
            }
            return z;
        }

        public final synchronized void a(long timeUs, int sampleFlags, long offset, int size, byte[] encryptionKey) {
            if (this.o) {
                if ((sampleFlags & 1) != 0) {
                    this.o = false;
                }
            }
            com.google.android.exoplayer2.d.a.b(!this.p);
            a(timeUs);
            this.f[this.l] = timeUs;
            this.c[this.l] = offset;
            this.d[this.l] = size;
            this.e[this.l] = sampleFlags;
            this.g[this.l] = encryptionKey;
            this.h[this.l] = this.q;
            this.b[this.l] = this.r;
            this.i++;
            if (this.i == this.a) {
                int newCapacity = this.a + Constants.ONE_SECOND;
                int[] newSourceIds = new int[newCapacity];
                long[] newOffsets = new long[newCapacity];
                long[] newTimesUs = new long[newCapacity];
                int[] newFlags = new int[newCapacity];
                int[] newSizes = new int[newCapacity];
                byte[][] newEncryptionKeys = new byte[newCapacity][];
                Format[] newFormats = new Format[newCapacity];
                int beforeWrap = this.a - this.k;
                System.arraycopy(this.c, this.k, newOffsets, 0, beforeWrap);
                System.arraycopy(this.f, this.k, newTimesUs, 0, beforeWrap);
                System.arraycopy(this.e, this.k, newFlags, 0, beforeWrap);
                System.arraycopy(this.d, this.k, newSizes, 0, beforeWrap);
                System.arraycopy(this.g, this.k, newEncryptionKeys, 0, beforeWrap);
                System.arraycopy(this.h, this.k, newFormats, 0, beforeWrap);
                System.arraycopy(this.b, this.k, newSourceIds, 0, beforeWrap);
                int afterWrap = this.k;
                System.arraycopy(this.c, 0, newOffsets, beforeWrap, afterWrap);
                System.arraycopy(this.f, 0, newTimesUs, beforeWrap, afterWrap);
                System.arraycopy(this.e, 0, newFlags, beforeWrap, afterWrap);
                System.arraycopy(this.d, 0, newSizes, beforeWrap, afterWrap);
                System.arraycopy(this.g, 0, newEncryptionKeys, beforeWrap, afterWrap);
                System.arraycopy(this.h, 0, newFormats, beforeWrap, afterWrap);
                System.arraycopy(this.b, 0, newSourceIds, beforeWrap, afterWrap);
                this.c = newOffsets;
                this.f = newTimesUs;
                this.e = newFlags;
                this.d = newSizes;
                this.g = newEncryptionKeys;
                this.h = newFormats;
                this.b = newSourceIds;
                this.k = 0;
                this.l = this.a;
                this.i = this.a;
                this.a = newCapacity;
            } else {
                this.l++;
                if (this.l == this.a) {
                    this.l = 0;
                }
            }
        }

        public final synchronized void a(long timeUs) {
            this.n = Math.max(this.n, timeUs);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized boolean b(long timeUs) {
            boolean z = false;
            synchronized (this) {
                if (this.m < timeUs) {
                    int retainCount = this.i;
                    while (retainCount > 0 && this.f[((this.k + retainCount) - 1) % this.a] >= timeUs) {
                        retainCount--;
                    }
                    int c = c() - (this.j + retainCount);
                    if (c >= 0 && c <= this.i) {
                        z = true;
                    }
                    com.google.android.exoplayer2.d.a.a(z);
                    if (c != 0) {
                        this.i -= c;
                        this.l = ((this.l + this.a) - c) % this.a;
                        this.n = Long.MIN_VALUE;
                        for (int i = this.i - 1; i >= 0; i--) {
                            c = (this.k + i) % this.a;
                            this.n = Math.max(this.n, this.f[c]);
                            if ((this.e[c] & 1) != 0) {
                                break;
                            }
                        }
                    }
                    z = true;
                }
            }
            return z;
        }
    }

    public interface c {
        void j_();
    }

    public d(com.google.android.exoplayer2.upstream.b allocator) {
        this.a = allocator;
        this.b = allocator.c();
    }

    public final void a(boolean enable) {
        int i;
        AtomicInteger atomicInteger = this.g;
        if (enable) {
            i = 0;
        } else {
            i = 2;
        }
        int previousState = atomicInteger.getAndSet(i);
        i();
        this.c.b();
        if (previousState == 2) {
            this.i = null;
        }
    }

    public final int a() {
        return this.c.c();
    }

    public final void b() {
        if (this.g.getAndSet(2) == 0) {
            i();
        }
    }

    public final boolean c() {
        return this.c.d();
    }

    public final Format d() {
        return this.c.e();
    }

    public final long e() {
        return this.c.f();
    }

    public final void f() {
        long nextOffset = this.c.g();
        if (nextOffset != -1) {
            a(nextOffset);
        }
    }

    public final boolean a(long timeUs, boolean allowTimeBeyondBuffer) {
        long nextOffset = this.c.a(timeUs, allowTimeBeyondBuffer);
        if (nextOffset == -1) {
            return false;
        }
        a(nextOffset);
        return true;
    }

    public final int a(h formatHolder, DecoderInputBuffer buffer, boolean formatRequired, boolean loadingFinished, long decodeOnlyUntilUs) {
        switch (this.c.a(formatHolder, buffer, formatRequired, loadingFinished, this.i, this.e)) {
            case SliqConstants.SLIQ_ERROR_ACCELERATION_NOT_SUPPORTED /*-5*/:
                this.i = formatHolder.a;
                return -5;
            case SliqConstants.SLIQ_ERROR_INVALID_STREAM /*-4*/:
                if (!buffer.c()) {
                    long j;
                    int i;
                    if (buffer.c < decodeOnlyUntilUs) {
                        buffer.b(Integer.MIN_VALUE);
                    }
                    if (buffer.g()) {
                        long j2;
                        int i2;
                        long j3;
                        a aVar = this.e;
                        long j4 = aVar.b;
                        this.f.a(1);
                        a(j4, this.f.a, 1);
                        j = 1 + j4;
                        byte b = this.f.a[0];
                        Object obj = (b & 128) != 0 ? 1 : null;
                        i = b & 127;
                        if (buffer.a.a == null) {
                            buffer.a.a = new byte[16];
                        }
                        a(j, buffer.a.a, i);
                        j += (long) i;
                        if (obj != null) {
                            this.f.a(2);
                            a(j, this.f.a, 2);
                            j += 2;
                            i = this.f.h();
                            j2 = j;
                        } else {
                            i = 1;
                            j2 = j;
                        }
                        int[] iArr = buffer.a.d;
                        if (iArr == null || iArr.length < i) {
                            iArr = new int[i];
                        }
                        int[] iArr2 = buffer.a.e;
                        if (iArr2 == null || iArr2.length < i) {
                            iArr2 = new int[i];
                        }
                        if (obj != null) {
                            i2 = i * 6;
                            this.f.a(i2);
                            a(j2, this.f.a, i2);
                            j2 += (long) i2;
                            this.f.c(0);
                            for (i2 = 0; i2 < i; i2++) {
                                iArr[i2] = this.f.h();
                                iArr2[i2] = this.f.t();
                            }
                            j3 = j2;
                        } else {
                            iArr[0] = 0;
                            iArr2[0] = aVar.a - ((int) (j2 - aVar.b));
                            j3 = j2;
                        }
                        buffer.a.a(i, iArr, iArr2, aVar.d, buffer.a.a);
                        i2 = (int) (j3 - aVar.b);
                        aVar.b += (long) i2;
                        aVar.a -= i2;
                    }
                    buffer.d(this.e.a);
                    j = this.e.b;
                    ByteBuffer byteBuffer = buffer.b;
                    i = this.e.a;
                    while (i > 0) {
                        a(j);
                        int i3 = (int) (j - this.h);
                        int min = Math.min(i, this.b - i3);
                        com.google.android.exoplayer2.upstream.a aVar2 = (com.google.android.exoplayer2.upstream.a) this.d.peek();
                        byteBuffer.put(aVar2.a, aVar2.a(i3), min);
                        j += (long) min;
                        i -= min;
                    }
                    a(this.e.c);
                }
                return -4;
            case SliqConstants.SLIQ_ERROR_INVALID_MEMORY /*-3*/:
                return -3;
            default:
                throw new IllegalStateException();
        }
    }

    private void a(long absolutePosition, byte[] target, int length) {
        int bytesRead = 0;
        while (bytesRead < length) {
            a(absolutePosition);
            int positionInAllocation = (int) (absolutePosition - this.h);
            int toCopy = Math.min(length - bytesRead, this.b - positionInAllocation);
            com.google.android.exoplayer2.upstream.a allocation = (com.google.android.exoplayer2.upstream.a) this.d.peek();
            System.arraycopy(allocation.a, allocation.a(positionInAllocation), target, bytesRead, toCopy);
            absolutePosition += (long) toCopy;
            bytesRead += toCopy;
        }
    }

    private void a(long absolutePosition) {
        int allocationIndex = ((int) (absolutePosition - this.h)) / this.b;
        for (int i = 0; i < allocationIndex; i++) {
            this.a.a((com.google.android.exoplayer2.upstream.a) this.d.remove());
            this.h += (long) this.b;
        }
    }

    public final void a(c listener) {
        this.q = listener;
    }

    public final void a(Format format) {
        Format adjustedFormat;
        long j = this.l;
        if (format == null) {
            adjustedFormat = null;
        } else {
            Format format2;
            if (j == 0 || format.w == Long.MAX_VALUE) {
                format2 = format;
            } else {
                format2 = format.a(j + format.w);
            }
            adjustedFormat = format2;
        }
        boolean formatChanged = this.c.a(adjustedFormat);
        this.k = format;
        this.j = false;
        if (this.q != null && formatChanged) {
            this.q.j_();
        }
    }

    public final int a(g input, int length, boolean allowEndOfInput) throws IOException, InterruptedException {
        if (g()) {
            try {
                int bytesAppended = input.a(this.n.a, this.n.a(this.o), a(length));
                if (bytesAppended != -1) {
                    this.o += bytesAppended;
                    this.m += (long) bytesAppended;
                    h();
                    return bytesAppended;
                } else if (allowEndOfInput) {
                    return -1;
                } else {
                    throw new EOFException();
                }
            } finally {
                h();
            }
        } else {
            int bytesSkipped = input.a(length);
            if (bytesSkipped != -1) {
                return bytesSkipped;
            }
            if (allowEndOfInput) {
                return -1;
            }
            throw new EOFException();
        }
    }

    public final void a(k buffer, int length) {
        if (g()) {
            while (length > 0) {
                int thisAppendLength = a(length);
                buffer.a(this.n.a, this.n.a(this.o), thisAppendLength);
                this.o += thisAppendLength;
                this.m += (long) thisAppendLength;
                length -= thisAppendLength;
            }
            h();
            return;
        }
        buffer.d(length);
    }

    public final void a(long timeUs, int flags, int size, int offset, byte[] encryptionKey) {
        if (this.j) {
            a(this.k);
        }
        if (g()) {
            try {
                if (this.p) {
                    if ((flags & 1) == 0 || !this.c.b(timeUs)) {
                        h();
                        return;
                    }
                    this.p = false;
                }
                this.c.a(timeUs + this.l, flags, (this.m - ((long) size)) - ((long) offset), size, encryptionKey);
                h();
            } catch (Throwable th) {
                h();
            }
        } else {
            this.c.a(timeUs);
        }
    }

    private boolean g() {
        return this.g.compareAndSet(0, 1);
    }

    private void h() {
        if (!this.g.compareAndSet(1, 0)) {
            i();
        }
    }

    private void i() {
        this.c.a();
        this.a.a((com.google.android.exoplayer2.upstream.a[]) this.d.toArray(new com.google.android.exoplayer2.upstream.a[this.d.size()]));
        this.d.clear();
        this.a.b();
        this.h = 0;
        this.m = 0;
        this.n = null;
        this.o = this.b;
    }

    private int a(int length) {
        if (this.o == this.b) {
            this.o = 0;
            this.n = this.a.a();
            this.d.add(this.n);
        }
        return Math.min(length, this.b - this.o);
    }
}
