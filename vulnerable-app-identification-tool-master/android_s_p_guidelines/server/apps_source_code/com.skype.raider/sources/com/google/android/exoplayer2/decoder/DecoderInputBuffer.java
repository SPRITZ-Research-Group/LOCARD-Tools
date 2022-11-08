package com.google.android.exoplayer2.decoder;

import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

public class DecoderInputBuffer extends a {
    public final b a = new b();
    public ByteBuffer b;
    public long c;
    private final int d;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BufferReplacementMode {
    }

    public DecoderInputBuffer(int bufferReplacementMode) {
        this.d = bufferReplacementMode;
    }

    public final void d(int length) throws IllegalStateException {
        if (this.b == null) {
            this.b = e(length);
            return;
        }
        int capacity = this.b.capacity();
        int position = this.b.position();
        int requiredCapacity = position + length;
        if (capacity < requiredCapacity) {
            ByteBuffer newData = e(requiredCapacity);
            if (position > 0) {
                this.b.position(0);
                this.b.limit(position);
                newData.put(this.b);
            }
            this.b = newData;
        }
    }

    public final boolean f() {
        return this.b == null && this.d == 0;
    }

    public final boolean g() {
        return c(ErrorDialogData.SUPPRESSED);
    }

    public final void h() {
        this.b.flip();
    }

    public final void a() {
        super.a();
        if (this.b != null) {
            this.b.clear();
        }
    }

    private ByteBuffer e(int requiredCapacity) {
        if (this.d == 1) {
            return ByteBuffer.allocate(requiredCapacity);
        }
        if (this.d == 2) {
            return ByteBuffer.allocateDirect(requiredCapacity);
        }
        throw new IllegalStateException("Buffer too small (" + (this.b == null ? 0 : this.b.capacity()) + " < " + requiredCapacity + ")");
    }
}
