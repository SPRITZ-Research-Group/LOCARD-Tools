package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.h;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public final class i implements r, Closeable {
    private ByteBuffer a;
    private final int b;
    private final long c = ((long) System.identityHashCode(this));

    public i(int size) {
        this.a = ByteBuffer.allocateDirect(size);
        this.b = size;
    }

    public final synchronized void close() {
        this.a = null;
    }

    public final synchronized boolean isClosed() {
        return this.a == null;
    }

    public final int getSize() {
        return this.b;
    }

    public final synchronized int write(int memoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        h.a((Object) byteArray);
        h.b(!isClosed());
        actualCount = t.a(memoryOffset, count, this.b);
        t.a(memoryOffset, byteArray.length, byteArrayOffset, actualCount, this.b);
        this.a.position(memoryOffset);
        this.a.put(byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    public final synchronized int read(int memoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        h.a((Object) byteArray);
        h.b(!isClosed());
        actualCount = t.a(memoryOffset, count, this.b);
        t.a(memoryOffset, byteArray.length, byteArrayOffset, actualCount, this.b);
        this.a.position(memoryOffset);
        this.a.get(byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    public final synchronized byte read(int offset) {
        byte b;
        boolean z = true;
        synchronized (this) {
            boolean z2;
            if (isClosed()) {
                z2 = false;
            } else {
                z2 = true;
            }
            h.b(z2);
            if (offset >= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            h.a(z2);
            if (offset >= this.b) {
                z = false;
            }
            h.a(z);
            b = this.a.get(offset);
        }
        return b;
    }

    public final void copy(int offset, r other, int otherOffset, int count) {
        h.a((Object) other);
        if (other.getUniqueId() == getUniqueId()) {
            new StringBuilder("Copying from BufferMemoryChunk ").append(Long.toHexString(getUniqueId())).append(" to BufferMemoryChunk ").append(Long.toHexString(other.getUniqueId())).append(" which are the same ");
            h.a(false);
        }
        if (other.getUniqueId() < getUniqueId()) {
            synchronized (other) {
                synchronized (this) {
                    a(offset, other, otherOffset, count);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (other) {
                a(offset, other, otherOffset, count);
            }
        }
    }

    public final long getNativePtr() {
        throw new UnsupportedOperationException("Cannot get the pointer of a BufferMemoryChunk");
    }

    @Nullable
    public final synchronized ByteBuffer getByteBuffer() {
        return this.a;
    }

    public final long getUniqueId() {
        return this.c;
    }

    private void a(int offset, r other, int otherOffset, int count) {
        boolean z = true;
        if (other instanceof i) {
            boolean z2;
            if (isClosed()) {
                z2 = false;
            } else {
                z2 = true;
            }
            h.b(z2);
            if (other.isClosed()) {
                z = false;
            }
            h.b(z);
            t.a(offset, other.getSize(), otherOffset, count, this.b);
            this.a.position(offset);
            other.getByteBuffer().position(otherOffset);
            byte[] b = new byte[count];
            this.a.get(b, 0, count);
            other.getByteBuffer().put(b, 0, count);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }
}
