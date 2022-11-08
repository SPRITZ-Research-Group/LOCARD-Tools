package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class dl {
    private final ByteBuffer a;

    private dl(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
        this.a.order(ByteOrder.LITTLE_ENDIAN);
    }

    private dl(byte[] bArr) {
        this(ByteBuffer.wrap(bArr, 0, 0));
    }

    public static dl a(byte[] bArr) {
        return new dl(bArr);
    }
}
