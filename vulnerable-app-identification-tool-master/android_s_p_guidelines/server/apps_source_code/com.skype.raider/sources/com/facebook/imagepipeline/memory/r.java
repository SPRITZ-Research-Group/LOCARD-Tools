package com.facebook.imagepipeline.memory;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public interface r {
    void close();

    void copy(int i, r rVar, int i2, int i3);

    @Nullable
    ByteBuffer getByteBuffer();

    long getNativePtr() throws UnsupportedOperationException;

    int getSize();

    long getUniqueId();

    boolean isClosed();

    byte read(int i);

    int read(int i, byte[] bArr, int i2, int i3);

    int write(int i, byte[] bArr, int i2, int i3);
}
