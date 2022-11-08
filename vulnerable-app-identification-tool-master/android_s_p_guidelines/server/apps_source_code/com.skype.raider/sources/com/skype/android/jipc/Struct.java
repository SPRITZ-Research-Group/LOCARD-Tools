package com.skype.android.jipc;

import android.os.Parcel;
import com.skype.android.jipc.Transactor.In;
import com.skype.android.jipc.Transactor.Out;
import com.skype.android.jipc.inout.OutInt32;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class Struct implements SizeOf, In, Out<Void> {
    private static final ByteOrder d = ByteOrder.LITTLE_ENDIAN;
    protected final ByteBuffer a;
    protected final ShortBuffer b;
    protected final IntBuffer c;
    private final int e;
    private final int f;

    public static class IntField {
        private final Struct a;
        private final int b;

        public IntField(Struct struct, int position) {
            this.b = position;
            this.a = struct;
        }

        public final int a() {
            return this.a.c.get(this.b);
        }

        public final void a(int value) {
            this.a.c.put(this.b, value);
        }

        public final void a(boolean value) {
            a(value ? 1 : 0);
        }

        public final void a(Enumerable enumerable) {
            a(enumerable.a());
        }

        public String toString() {
            return OutInt32.b(a());
        }
    }

    public static class LongField {
        private final Struct a;
        private final int b;

        public LongField(Struct struct, int int32Pos) {
            this.b = int32Pos;
            this.a = struct;
        }

        public final long a() {
            return (((long) this.a.c.get(this.b + 1)) << 32) | ((long) this.a.c.get(this.b));
        }

        public String toString() {
            long value = a();
            return String.format("%d 0x%016x", new Object[]{Long.valueOf(value), Long.valueOf(value)});
        }
    }

    public static class ShortField {
        private final Struct a;
        private final int b = 6;

        public ShortField(Struct struct) {
            this.a = struct;
        }

        public final void a(short value) {
            this.a.b.put(this.b, value);
        }

        public String toString() {
            short value = this.a.b.get(this.b);
            return String.format("%d 0x%04x", new Object[]{Short.valueOf(value), Short.valueOf(value)});
        }
    }

    public final /* synthetic */ Object c(Parcel parcel) {
        return b(parcel);
    }

    public Struct(boolean direct, int wordCount) {
        this.e = wordCount;
        this.f = wordCount * 4;
        this.a = (direct ? ByteBuffer.allocateDirect(this.f) : ByteBuffer.allocate(this.f)).order(d);
        this.b = this.a.asShortBuffer();
        this.c = this.a.asIntBuffer();
        e();
    }

    public Struct(int wordCount) {
        this(false, wordCount);
    }

    public int a() {
        return this.f;
    }

    public void a(Parcel in) {
        for (int i = 0; i < this.e; i++) {
            in.writeInt(this.c.get(i));
        }
    }

    public final Void b(Parcel reply) {
        for (int i = 0; i < this.e; i++) {
            this.c.put(i, reply.readInt());
        }
        return null;
    }

    public void b() {
        e();
    }

    private void e() {
        for (int i = 0; i < this.a.capacity(); i++) {
            this.a.put(i, (byte) 0);
        }
        this.a.clear();
        this.c.clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Struct)) {
            return false;
        }
        ByteBuffer warBuffer = ((Struct) obj).a;
        int aCityPac = warBuffer.capacity();
        if (aCityPac != this.a.capacity()) {
            return false;
        }
        if (warBuffer.order() != this.a.order()) {
            return false;
        }
        for (int i = 0; i < aCityPac; i++) {
            if (warBuffer.get(i) != this.a.get(i)) {
                return false;
            }
        }
        return true;
    }

    public final Buffer c() {
        if (this.a.isDirect()) {
            return this.a;
        }
        throw new UnsupportedOperationException("Underlying storage is not direct");
    }
}
