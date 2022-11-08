package com.microsoft.a;

import com.microsoft.a.a.c;
import com.microsoft.a.b.a;
import com.microsoft.a.b.d;
import com.microsoft.a.k.b;
import java.io.IOException;

public final class e extends k {
    protected final a a;
    private final m b;
    private byte[] c = new byte[64];

    public static e a(byte[] buffer) {
        return new e(m.ONE, new d(buffer, buffer.length));
    }

    private e(m version, a stream) {
        this.b = version;
        this.a = stream;
    }

    private void a(int size) {
        if (this.c.length < size) {
            this.c = new byte[size];
        }
    }

    public final k.a a() throws IOException {
        a aVar = a.BT_STOP;
        byte raw = this.a.a();
        a type = a.a(raw & 31);
        int id = raw & 224;
        if (id == 224) {
            id = (this.a.a() & 255) | ((this.a.a() & 255) << 8);
        } else if (id == 192) {
            id = this.a.a();
        } else {
            id >>= 5;
        }
        return new k.a(type, id);
    }

    public final String e() throws IOException {
        int size = (int) com.microsoft.a.a.a.a(this.a);
        if (size == 0) {
            return "";
        }
        a(size);
        this.a.a(this.c, size);
        return com.microsoft.a.a.d.a(this.c, size);
    }

    public final String f() throws IOException {
        int size = ((int) com.microsoft.a.a.a.a(this.a)) << 1;
        if (size == 0) {
            return "";
        }
        a(size);
        this.a.a(this.c, size);
        return com.microsoft.a.a.d.b(this.c, size);
    }

    public final float g() throws IOException {
        a(4);
        this.a.a(this.c, 4);
        byte[] bArr = this.c;
        return Float.intBitsToFloat(((bArr[3] & 255) << 24) | (((bArr[0] & 255) | ((bArr[1] & 255) << 8)) | ((bArr[2] & 255) << 16)));
    }

    public final double h() throws IOException {
        a(8);
        this.a.a(this.c, 8);
        byte[] bArr = this.c;
        return Double.longBitsToDouble(((((long) bArr[7]) & 255) << 56) | (((((((((long) bArr[0]) & 255) | ((((long) bArr[1]) & 255) << 8)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[6]) & 255) << 48)));
    }

    public final byte i() throws IOException {
        return this.a.a();
    }

    public final short j() throws IOException {
        return (short) ((int) com.microsoft.a.a.a.a(this.a));
    }

    public final int k() throws IOException {
        return (int) com.microsoft.a.a.a.a(this.a);
    }

    public final long l() throws IOException {
        return com.microsoft.a.a.a.a(this.a);
    }

    public final byte m() throws IOException {
        return this.a.a();
    }

    public final short n() throws IOException {
        short a = (short) ((int) com.microsoft.a.a.a.a(this.a));
        return (short) ((-(a & 1)) ^ ((65535 & a) >>> 1));
    }

    public final int o() throws IOException {
        int a = (int) com.microsoft.a.a.a.a(this.a);
        return (-(a & 1)) ^ (a >>> 1);
    }

    public final long p() throws IOException {
        long a = com.microsoft.a.a.a.a(this.a);
        return (-(a & 1)) ^ (a >>> 1);
    }

    public final void a(a type) throws IOException {
        switch (type) {
            case BT_STRING:
                this.a.a(k());
                return;
            case BT_WSTRING:
                this.a.a(k() << 1);
                return;
            case BT_LIST:
            case BT_SET:
                b b = b();
                if (b.b == a.BT_UINT8 || b.b == a.BT_INT8) {
                    this.a.a(b.a);
                    return;
                }
                for (int i = 0; i < b.a; i++) {
                    a(b.b);
                }
                return;
            case BT_STRUCT:
                break;
            default:
                c.a(this, type);
                return;
        }
        while (true) {
            k.a tag = a();
            if (tag.b != a.BT_STOP && tag.b != a.BT_STOP_BASE) {
                a(tag.b);
            } else if (tag.b == a.BT_STOP) {
                return;
            }
        }
    }

    public final void close() throws IOException {
        this.a.close();
    }

    public final boolean a(j capability) {
        switch (capability) {
            case CLONEABLE:
            case CAN_OMIT_FIELDS:
            case TAGGED:
            case CAN_SEEK:
                return true;
            default:
                return super.a(capability);
        }
    }

    public final String toString() {
        return String.format("[%s version=%d]", new Object[]{getClass().getName(), Short.valueOf(this.b.a())});
    }

    public final b b() throws IOException {
        return new b((int) com.microsoft.a.a.a.a(this.a), a.a(this.a.a()));
    }

    public final k.c c() throws IOException {
        return new k.c((int) com.microsoft.a.a.a.a(this.a), a.a(this.a.a()), a.a(this.a.a()));
    }

    public final boolean d() throws IOException {
        return this.a.a() != (byte) 0;
    }
}
