package com.google.android.exoplayer2.a;

import com.google.android.exoplayer2.a.c.a;
import com.google.android.exoplayer2.d.s;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class k implements c {
    private int b = -1;
    private int c = -1;
    private j d;
    private float e = 1.0f;
    private float f = 1.0f;
    private ByteBuffer g = a;
    private ShortBuffer h = this.g.asShortBuffer();
    private ByteBuffer i = a;
    private long j;
    private long k;
    private boolean l;

    public final float a(float speed) {
        this.e = s.a(speed, 0.1f, 8.0f);
        return this.e;
    }

    public final float b(float pitch) {
        this.f = s.a(pitch, 0.1f, 8.0f);
        return pitch;
    }

    public final long i() {
        return this.j;
    }

    public final long j() {
        return this.k;
    }

    public final boolean a(int sampleRateHz, int channelCount, int encoding) throws a {
        if (encoding != 2) {
            throw new a(sampleRateHz, channelCount, encoding);
        } else if (this.c == sampleRateHz && this.b == channelCount) {
            return false;
        } else {
            this.c = sampleRateHz;
            this.b = channelCount;
            return true;
        }
    }

    public final boolean a() {
        return Math.abs(this.e - 1.0f) >= 0.01f || Math.abs(this.f - 1.0f) >= 0.01f;
    }

    public final int b() {
        return this.b;
    }

    public final int c() {
        return 2;
    }

    public final void a(ByteBuffer inputBuffer) {
        if (inputBuffer.hasRemaining()) {
            ShortBuffer shortBuffer = inputBuffer.asShortBuffer();
            int inputSize = inputBuffer.remaining();
            this.j += (long) inputSize;
            this.d.a(shortBuffer);
            inputBuffer.position(inputBuffer.position() + inputSize);
        }
        int outputSize = (this.d.b() * this.b) * 2;
        if (outputSize > 0) {
            if (this.g.capacity() < outputSize) {
                this.g = ByteBuffer.allocateDirect(outputSize).order(ByteOrder.nativeOrder());
                this.h = this.g.asShortBuffer();
            } else {
                this.g.clear();
                this.h.clear();
            }
            this.d.b(this.h);
            this.k += (long) outputSize;
            this.g.limit(outputSize);
            this.i = this.g;
        }
    }

    public final void d() {
        this.d.a();
        this.l = true;
    }

    public final ByteBuffer e() {
        ByteBuffer outputBuffer = this.i;
        this.i = a;
        return outputBuffer;
    }

    public final boolean f() {
        return this.l && (this.d == null || this.d.b() == 0);
    }

    public final void g() {
        this.d = new j(this.c, this.b);
        this.d.a(this.e);
        this.d.b(this.f);
        this.i = a;
        this.j = 0;
        this.k = 0;
        this.l = false;
    }

    public final void h() {
        this.d = null;
        this.g = a;
        this.h = this.g.asShortBuffer();
        this.i = a;
        this.b = -1;
        this.c = -1;
        this.j = 0;
        this.k = 0;
        this.l = false;
    }
}
