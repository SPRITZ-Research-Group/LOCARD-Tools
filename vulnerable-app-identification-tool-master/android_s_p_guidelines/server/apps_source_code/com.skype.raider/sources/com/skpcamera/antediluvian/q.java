package com.skpcamera.antediluvian;

import android.annotation.TargetApi;
import android.media.MediaCodec.BufferInfo;
import java.nio.ByteBuffer;

@TargetApi(16)
final class q implements r {
    private String a;
    private ByteBuffer b;
    private BufferInfo c;

    q(String type, ByteBuffer buffer, BufferInfo bufferInfo) {
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

    public final String c() {
        return this.a;
    }

    public final long d() {
        return this.c.presentationTimeUs;
    }
}
