package com.facebook.imagepipeline.memory;

import com.facebook.common.e.h;
import com.facebook.common.f.a;
import com.facebook.common.internal.VisibleForTesting;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class u implements h {
    @GuardedBy("this")
    @VisibleForTesting
    a<r> a;
    private final int b;

    public u(a<r> bufRef, int size) {
        com.facebook.common.internal.h.a((Object) bufRef);
        boolean z = size >= 0 && size <= ((r) bufRef.a()).getSize();
        com.facebook.common.internal.h.a(z);
        this.a = bufRef.b();
        this.b = size;
    }

    public final synchronized int a() {
        e();
        return this.b;
    }

    public final synchronized byte a(int offset) {
        byte read;
        boolean z = true;
        synchronized (this) {
            boolean z2;
            e();
            if (offset >= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            com.facebook.common.internal.h.a(z2);
            if (offset >= this.b) {
                z = false;
            }
            com.facebook.common.internal.h.a(z);
            read = ((r) this.a.a()).read(offset);
        }
        return read;
    }

    public final synchronized int a(int offset, byte[] buffer, int bufferOffset, int length) {
        e();
        com.facebook.common.internal.h.a(offset + length <= this.b);
        return ((r) this.a.a()).read(offset, buffer, bufferOffset, length);
    }

    public final synchronized long b() throws UnsupportedOperationException {
        e();
        return ((r) this.a.a()).getNativePtr();
    }

    @Nullable
    public final synchronized ByteBuffer c() {
        return ((r) this.a.a()).getByteBuffer();
    }

    public final synchronized boolean d() {
        return !a.a(this.a);
    }

    public final synchronized void close() {
        a.c(this.a);
        this.a = null;
    }

    private synchronized void e() {
        if (d()) {
            throw new h.a();
        }
    }
}
