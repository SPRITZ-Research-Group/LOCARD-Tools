package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.nativecode.a;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@DoNotStrip
public class NativeMemoryChunk implements r, Closeable {
    private static final String TAG = "NativeMemoryChunk";
    private boolean mIsClosed;
    private final long mNativePtr;
    private final int mSize;

    @DoNotStrip
    private static native long nativeAllocate(int i);

    @DoNotStrip
    private static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeFree(long j);

    @DoNotStrip
    private static native void nativeMemcpy(long j, long j2, int i);

    @DoNotStrip
    private static native byte nativeReadByte(long j);

    static {
        a.a();
    }

    public NativeMemoryChunk(int size) {
        boolean z;
        if (size > 0) {
            z = true;
        } else {
            z = false;
        }
        h.a(z);
        this.mSize = size;
        this.mNativePtr = nativeAllocate(this.mSize);
        this.mIsClosed = false;
    }

    @VisibleForTesting
    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0;
        this.mIsClosed = true;
    }

    public synchronized void close() {
        if (!this.mIsClosed) {
            this.mIsClosed = true;
            nativeFree(this.mNativePtr);
        }
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    public int getSize() {
        return this.mSize;
    }

    public synchronized int write(int memoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        h.a((Object) byteArray);
        h.b(!isClosed());
        actualCount = t.a(memoryOffset, count, this.mSize);
        t.a(memoryOffset, byteArray.length, byteArrayOffset, actualCount, this.mSize);
        nativeCopyFromByteArray(this.mNativePtr + ((long) memoryOffset), byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    public synchronized int read(int memoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        h.a((Object) byteArray);
        h.b(!isClosed());
        actualCount = t.a(memoryOffset, count, this.mSize);
        t.a(memoryOffset, byteArray.length, byteArrayOffset, actualCount, this.mSize);
        nativeCopyToByteArray(this.mNativePtr + ((long) memoryOffset), byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    public synchronized byte read(int offset) {
        byte nativeReadByte;
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
            if (offset >= this.mSize) {
                z = false;
            }
            h.a(z);
            nativeReadByte = nativeReadByte(this.mNativePtr + ((long) offset));
        }
        return nativeReadByte;
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }

    @Nullable
    public ByteBuffer getByteBuffer() {
        return null;
    }

    public long getUniqueId() {
        return this.mNativePtr;
    }

    public void copy(int offset, r other, int otherOffset, int count) {
        h.a((Object) other);
        if (other.getUniqueId() == getUniqueId()) {
            new StringBuilder("Copying from NativeMemoryChunk ").append(Integer.toHexString(System.identityHashCode(this))).append(" to NativeMemoryChunk ").append(Integer.toHexString(System.identityHashCode(other))).append(" which share the same address ").append(Long.toHexString(this.mNativePtr));
            h.a(false);
        }
        if (other.getUniqueId() < getUniqueId()) {
            synchronized (other) {
                synchronized (this) {
                    doCopy(offset, other, otherOffset, count);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (other) {
                doCopy(offset, other, otherOffset, count);
            }
        }
    }

    private void doCopy(int offset, r other, int otherOffset, int count) {
        boolean z = true;
        if (other instanceof NativeMemoryChunk) {
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
            t.a(offset, other.getSize(), otherOffset, count, this.mSize);
            nativeMemcpy(other.getNativePtr() + ((long) otherOffset), this.mNativePtr + ((long) offset), count);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }

    protected void finalize() throws Throwable {
        if (!isClosed()) {
            new StringBuilder("finalize: Chunk ").append(Integer.toHexString(System.identityHashCode(this))).append(" still active. ");
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
