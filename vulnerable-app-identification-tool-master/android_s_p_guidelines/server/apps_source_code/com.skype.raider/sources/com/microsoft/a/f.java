package com.microsoft.a;

import com.microsoft.a.a.a;
import com.microsoft.a.a.d;
import com.microsoft.a.b.b;
import java.io.IOException;

public final class f extends n {
    public static final short a = ((short) l.c.a());
    private final b b;
    private final m c;
    private final byte[] d = new byte[10];

    public static f a(b stream) {
        return new f(m.ONE, stream);
    }

    private f(m version, b stream) {
        this.c = version;
        this.b = stream;
    }

    public final void a(boolean isBase) throws IOException {
        a((byte) (isBase ? a.BT_STOP_BASE.a() : a.BT_STOP.a()));
    }

    public final void a(a type, int id) throws IOException {
        byte fieldType = (byte) type.a();
        if (id <= 5) {
            this.b.a((byte) ((id << 5) | fieldType));
        } else if (id <= 255) {
            this.b.a((byte) (fieldType | 192));
            this.b.a((byte) id);
        } else {
            this.b.a((byte) (fieldType | 224));
            this.b.a((byte) id);
            this.b.a((byte) (id >>> 8));
        }
    }

    public final void a(int size, a elementType) throws IOException {
        a((byte) elementType.a());
        b(size);
    }

    public final void a(int size, a keyType, a valueType) throws IOException {
        a((byte) keyType.a());
        a((byte) valueType.a());
        b(size);
    }

    public final void b(boolean value) throws IOException {
        a((byte) (value ? 1 : 0));
    }

    public final void a(String value) throws IOException {
        if (value.isEmpty()) {
            b(0);
            return;
        }
        byte[] buffer = d.a(value);
        b(buffer.length);
        this.b.a(buffer);
    }

    public final void b(String value) throws IOException {
        if (value.isEmpty()) {
            b(0);
            return;
        }
        b(value.length());
        byte[] buffer = d.b(value);
        this.b.a(buffer, buffer.length);
    }

    public final void a(double value) throws IOException {
        byte[] bArr = this.d;
        long doubleToRawLongBits = Double.doubleToRawLongBits(value);
        bArr[0] = (byte) ((int) doubleToRawLongBits);
        bArr[1] = (byte) ((int) (doubleToRawLongBits >> 8));
        bArr[2] = (byte) ((int) (doubleToRawLongBits >> 16));
        bArr[3] = (byte) ((int) (doubleToRawLongBits >> 24));
        bArr[4] = (byte) ((int) (doubleToRawLongBits >> 32));
        bArr[5] = (byte) ((int) (doubleToRawLongBits >> 40));
        bArr[6] = (byte) ((int) (doubleToRawLongBits >> 48));
        bArr[7] = (byte) ((int) (doubleToRawLongBits >> 56));
        this.b.a(this.d, 8);
    }

    public final void a(byte value) throws IOException {
        this.b.a(value);
    }

    public final void a(short value) throws IOException {
        int value2;
        int i = 0;
        byte[] bArr = this.d;
        if ((65408 & value2) != 0) {
            bArr[0] = (byte) ((value2 & 127) | 128);
            value2 = (short) (value2 >>> 7);
            if ((65408 & value2) != 0) {
                i = 2;
                bArr[1] = (byte) ((value2 & 127) | 128);
                value2 = (short) (value2 >>> 7);
            } else {
                i = 1;
            }
        }
        int bytesWritten = i + 1;
        bArr[i] = (byte) (value2 & 127);
        this.b.a(this.d, bytesWritten);
    }

    private void b(int value) throws IOException {
        this.b.a(this.d, a.a(value, this.d, 0));
    }

    public final void a(long value) throws IOException {
        this.b.a(this.d, a.a(value, this.d, 0));
    }

    public final boolean a(j capability) {
        switch (capability) {
            case CAN_OMIT_FIELDS:
            case TAGGED:
                return true;
            default:
                return super.a(capability);
        }
    }

    public final String toString() {
        return String.format("[%s version=%d]", new Object[]{getClass().getName(), Short.valueOf(this.c.a())});
    }

    public final void a(int value) throws IOException {
        this.b.a(this.d, a.a((value << 1) ^ (value >> 31), this.d, 0));
    }

    public final void b(long value) throws IOException {
        this.b.a(this.d, a.a((value << 1) ^ (value >> 63), this.d, 0));
    }
}
