package com.skypecam.obscura.c;

import android.media.MediaCodec.BufferInfo;
import java.nio.ByteBuffer;

public final class g {
    public String a;
    private ByteBuffer b;
    private BufferInfo c;

    public g(String type, ByteBuffer buffer, BufferInfo bufferInfo) {
        this.a = type;
        this.b = buffer;
        this.c = bufferInfo;
    }

    public final BufferInfo a() {
        return this.c;
    }

    public final ByteBuffer b() {
        return this.b;
    }

    public final long c() {
        return this.c.presentationTimeUs;
    }
}
