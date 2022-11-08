package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class b {
    private final ByteBuffer a;

    private b(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
        this.a.order(ByteOrder.LITTLE_ENDIAN);
    }

    private b(byte[] bArr, int i) {
        this(ByteBuffer.wrap(bArr, 0, i));
    }

    public static int a(int i) {
        return i >= 0 ? c(i) : 10;
    }

    public static int a(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    private static int a(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + i2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i2 = i3 + i;
                if (i2 < length) {
                    return i2;
                }
                throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
            }
        }
        i2 = i3;
        if (i2 < length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
    }

    public static int a(String str) {
        int a = a((CharSequence) str);
        return a + c(a);
    }

    public static b a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static b a(byte[] bArr, int i) {
        return new b(bArr, i);
    }

    private static void a(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i = 0;
        int arrayOffset;
        int remaining;
        char charAt;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                remaining = byteBuffer.remaining();
                int length = charSequence.length();
                int i2 = arrayOffset + remaining;
                while (i < length && i + arrayOffset < i2) {
                    charAt = charSequence.charAt(i);
                    if (charAt >= 128) {
                        break;
                    }
                    array[arrayOffset + i] = (byte) charAt;
                    i++;
                }
                if (i == length) {
                    i = arrayOffset + length;
                } else {
                    remaining = arrayOffset + i;
                    while (i < length) {
                        char charAt2 = charSequence.charAt(i);
                        int i3;
                        if (charAt2 < 128 && remaining < i2) {
                            arrayOffset = remaining + 1;
                            array[remaining] = (byte) charAt2;
                        } else if (charAt2 < 2048 && remaining <= i2 - 2) {
                            i3 = remaining + 1;
                            array[remaining] = (byte) ((charAt2 >>> 6) | 960);
                            arrayOffset = i3 + 1;
                            array[i3] = (byte) ((charAt2 & 63) | 128);
                        } else if ((charAt2 < 55296 || 57343 < charAt2) && remaining <= i2 - 3) {
                            arrayOffset = remaining + 1;
                            array[remaining] = (byte) ((charAt2 >>> 12) | 480);
                            remaining = arrayOffset + 1;
                            array[arrayOffset] = (byte) (((charAt2 >>> 6) & 63) | 128);
                            arrayOffset = remaining + 1;
                            array[remaining] = (byte) ((charAt2 & 63) | 128);
                        } else if (remaining <= i2 - 4) {
                            if (i + 1 != charSequence.length()) {
                                i++;
                                char charAt3 = charSequence.charAt(i);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int toCodePoint = Character.toCodePoint(charAt2, charAt3);
                                    arrayOffset = remaining + 1;
                                    array[remaining] = (byte) ((toCodePoint >>> 18) | 240);
                                    remaining = arrayOffset + 1;
                                    array[arrayOffset] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                                    i3 = remaining + 1;
                                    array[remaining] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                                    arrayOffset = i3 + 1;
                                    array[i3] = (byte) ((toCodePoint & 63) | 128);
                                }
                            }
                            throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                        } else {
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + remaining);
                        }
                        i++;
                        remaining = arrayOffset;
                    }
                    i = remaining;
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
            } catch (Throwable e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            arrayOffset = charSequence.length();
            while (i < arrayOffset) {
                charAt = charSequence.charAt(i);
                if (charAt < 128) {
                    byteBuffer.put((byte) charAt);
                } else if (charAt < 2048) {
                    byteBuffer.put((byte) ((charAt >>> 6) | 960));
                    byteBuffer.put((byte) ((charAt & 63) | 128));
                } else if (charAt < 55296 || 57343 < charAt) {
                    byteBuffer.put((byte) ((charAt >>> 12) | 480));
                    byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((charAt & 63) | 128));
                } else {
                    if (i + 1 != charSequence.length()) {
                        i++;
                        char charAt4 = charSequence.charAt(i);
                        if (Character.isSurrogatePair(charAt, charAt4)) {
                            remaining = Character.toCodePoint(charAt, charAt4);
                            byteBuffer.put((byte) ((remaining >>> 18) | 240));
                            byteBuffer.put((byte) (((remaining >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((remaining >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((remaining & 63) | 128));
                        }
                    }
                    throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                }
                i++;
            }
        }
    }

    private final void b(long j) throws IOException {
        while ((-128 & j) != 0) {
            d((((int) j) & 127) | 128);
            j >>>= 7;
        }
        d((int) j);
    }

    public static int c(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    private final void d(int i) throws IOException {
        byte b = (byte) i;
        if (this.a.hasRemaining()) {
            this.a.put(b);
            return;
        }
        throw new c(this.a.position(), this.a.limit());
    }

    public final void a() {
        if (this.a.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.a.remaining())}));
        }
    }

    public final void a(int i, double d) throws IOException {
        c(i, 1);
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (this.a.remaining() < 8) {
            throw new c(this.a.position(), this.a.limit());
        }
        this.a.putLong(doubleToLongBits);
    }

    public final void a(int i, float f) throws IOException {
        c(i, 5);
        int floatToIntBits = Float.floatToIntBits(f);
        if (this.a.remaining() < 4) {
            throw new c(this.a.position(), this.a.limit());
        }
        this.a.putInt(floatToIntBits);
    }

    public final void a(int i, int i2) throws IOException {
        c(i, 0);
        if (i2 >= 0) {
            b(i2);
        } else {
            b((long) i2);
        }
    }

    public final void a(int i, long j) throws IOException {
        c(i, 0);
        b(j);
    }

    public final void a(int i, j jVar) throws IOException {
        c(i, 2);
        a(jVar);
    }

    public final void a(int i, String str) throws IOException {
        c(i, 2);
        try {
            int c = c(str.length());
            if (c == c(str.length() * 3)) {
                int position = this.a.position();
                if (this.a.remaining() < c) {
                    throw new c(c + position, this.a.limit());
                }
                this.a.position(position + c);
                a((CharSequence) str, this.a);
                int position2 = this.a.position();
                this.a.position(position);
                b((position2 - position) - c);
                this.a.position(position2);
                return;
            }
            b(a((CharSequence) str));
            a((CharSequence) str, this.a);
        } catch (Throwable e) {
            c cVar = new c(this.a.position(), this.a.limit());
            cVar.initCause(e);
            throw cVar;
        }
    }

    public final void a(int i, boolean z) throws IOException {
        int i2 = 0;
        c(i, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (this.a.hasRemaining()) {
            this.a.put(b);
            return;
        }
        throw new c(this.a.position(), this.a.limit());
    }

    public final void a(j jVar) throws IOException {
        b(jVar.c());
        jVar.a(this);
    }

    public final void b(int i) throws IOException {
        while ((i & -128) != 0) {
            d((i & 127) | 128);
            i >>>= 7;
        }
        d(i);
    }

    public final void b(int i, long j) throws IOException {
        c(i, 0);
        b(j);
    }

    public final void b(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.a.remaining() >= length) {
            this.a.put(bArr, 0, length);
            return;
        }
        throw new c(this.a.position(), this.a.limit());
    }

    public final void c(int i, int i2) throws IOException {
        b((i << 3) | i2);
    }

    public static int c(int i, long j) {
        return c(i << 3) + a(j);
    }

    public static int b(int i, int i2) {
        return c(i << 3) + a(i2);
    }

    public static int b(int i, String str) {
        return c(i << 3) + a(str);
    }

    public static int b(int i, j jVar) {
        int c = c(i << 3);
        int d = jVar.d();
        return c + (d + c(d));
    }
}
