package com.facebook.common.e;

import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public interface h extends Closeable {

    public static class a extends RuntimeException {
        public a() {
            super("Invalid bytebuf. Already closed");
        }
    }

    byte a(int i);

    int a();

    int a(int i, byte[] bArr, int i2, int i3);

    long b();

    @Nullable
    ByteBuffer c();

    boolean d();
}
